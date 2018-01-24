package com.intuit.app.dao.notes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.intuit.app.dao.mappers.NoteMapper;
import com.intuit.app.exception.ErrorCodes;
import com.intuit.app.exception.NodeIdNotPresentException;
import com.intuit.app.exception.NodeUpdateException;
import com.intuit.app.models.BaseNode;
import com.intuit.app.models.Label;
import com.intuit.app.service.notes.NotesService;
import com.intuit.app.utils.DBUtill;
import com.intuit.app.web.change.NodesChangeRequest;

@Repository
public class NotesDao implements INotesDao {

    private static final String ROOT = "root";

    private static final String INSERT_UPDATE_NODE_SQL = "INSERT INTO NODE (   " +
            "   node_id,  " +
            "   parent_node_id,  " +
            "   node_type,  " +
            "   title,  " +
            "   text,  " +
            "   isChecked,  " +
            "   isPinned,  " +
            "   isArchived,  " +
            "   baseVersion,  " +
            "   created_date,  " +
            "   updated_date)  " +
            "   VALUES (" +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?,  " +
            "   ?)  " +
            "   ON DUPLICATE KEY UPDATE   " +
            "   node_id = VALUES(node_id),  " +
            "   parent_node_id = VALUES(parent_node_id),  " +
            "   node_type = VALUES(node_type),  " +
            "   title = 'TEST UPDATED',  " +
            "   text = VALUES(text),  " +
            "   isChecked = VALUES(isChecked),  " +
            "   isPinned = VALUES(isPinned),  " +
            "   isArchived = VALUES(isArchived),  " +
            "   baseVersion = VALUES(baseVersion),  " +
            "   created_date = VALUES(created_date),  " +
            "   updated_date = VALUES(updated_date);  ";

    private static final String DELETE_NODE_SQL = "DELETE FROM NODE WHERE node_id = ?";

    private static final String DELETE_ROOT_NODE = "DELETE FROM NODE WHERE node_id IN ( " +
            "   SELECT * FROM (         " +
            "   SELECT n2.node_id FROM NODE n " +
            "   LEFT JOIN NODE n2 ON " +
            "   n2.parent_node_id = n.node_id             " +
            "   WHERE n.node_id = ? " +
            "   UNION " +
            "   SELECT n.node_id FROM NODE n " +
            "   WHERE n.node_id = ? " +
            "   ) AS p " +
            "   )";

    private static final String DELETE_LABEL_SQL = "DELETE FROM NODE_LABEL WHERE node_id = :node_id and label_id in (:deletedLabels)";

    private static final String INSERT_LABEL_SQL = "INSERT INTO NODE_LABEL (node_id, label_id) " +
            "   SELECT * FROM ( SELECT :node_id, :label_id ) As tmp " +
            "   WHERE NOT EXISTS ( " +
            "   SELECT node_id,label_id FROM NODE_LABEL WHERE node_id = :node_id and label_id = :label_id" +
            "   ) LIMIT 1";

    private static final Logger logger = LoggerFactory.getLogger(NotesService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<List<BaseNode>> getNotes(){
        List<List<BaseNode>> result = new ArrayList<>();
        String sql = "SELECT node_id, title, isPinned, isArchived FROM NODE";
        RowMapper<BaseNode> rowMapper = new NoteMapper();
        final List<BaseNode> baseNodes = this.jdbcTemplate.query(sql, rowMapper);

        return result;
    }


    /**
     * This method is annotated with <code>Transactional</code> which means either all the nodes in the NodesChangeRequest will be processed or none.
     * Nodes can
     * @param nodesChangeRequest
     * @see NodesChangeRequest
     *
     */
    @Override
    public List<BaseNode> insertOrUpdate(NodesChangeRequest nodesChangeRequest) {
        logger.debug("NotesDao::insertOrUpdate : requestId is {}, nodes in this request are {}",nodesChangeRequest.getRequestId(),nodesChangeRequest.getNodes().size());
        List<BaseNode> baseNodes = nodesChangeRequest.getNodes();
        for(BaseNode node:baseNodes){
            if(node.getNodeId()==null || node.getNodeId().trim().isEmpty())
                throw new NodeIdNotPresentException(ErrorCodes.NODE_ID_MISSING,"Node Id is missing");
            if(node.isDeleted()){
                deleteNode(node);
            }else
                updateNode(node);
        }
        return baseNodes;
    }

    /**
     * @apiNote This method expects only two types of the node i.e LIST_ITEM and ROOT. If its LIST_ITEM, it will be simply deleted
     *          from the database. If it's ROOT node, then all the LIST_ITEM nodes and BLOB nodes associated with it will be
     *          deleted with this ROOT node. In case of ROOT node, deleteNodeAndAllChildNodes method  will be called.
     * @param node
     *        This is either the LIST_ITEM node or the ROOT node. If this is ROOT node, then all its children will be deleted.
     */
    private void deleteNode(BaseNode node) {
        logger.debug("NotesDao::deleteNode Deleting Node with id {}",node.getNodeId());
        if(node.getParentId().equals(ROOT)){
            deleteNodeAndAllChildNodes(node);
        }else {
            final int delete = jdbcTemplate.update(DELETE_NODE_SQL, node.getNodeId());
            logger.debug("NotesDao::deleteNode : delete is {}",delete);
        }
    }

    /**
     *
     * @param node
     *        ROOT node to be deleted. It will also delete all the children nodes (LIST_ITEM and BLOB) too.
     */
    private void deleteNodeAndAllChildNodes(BaseNode node) {
        final int delete = jdbcTemplate.update(DELETE_ROOT_NODE, node.getNodeId(),node.getNodeId());
        logger.debug("NotesDao::deleteNodeAndAllChildNodes : delete is {}",delete);
    }

    /**
     *
     * @param node
     *        node to be updated or inserted
     *
     *        if node is ROOT node and it is marked as trashed, then all the associated child nodes (LIST_ITEM and BLOB) will
     *        be trashed.
     *        if node is ROOT node then all its label will either be inserted if this label is assigned very first time by the
     *        user or it will be deleted if user has removed the label from the front end.
     *        NOTE: Deletion of label just removed the mapping between the root node and this label.
     *
     */
    private void updateNode(BaseNode node) {
        if(node.getParentId().equals(ROOT)){
            if (node.isTrashed()) {
                markNodeAsTrashed(node);
                return;
            }
            updateLabels(node);
        }
        insertUpdateNodeDetails(node);
    }

    /**
     *
     * @param node
     *        This node will either get inserted or updated depending on the <code>node.getNodeId</code> presence
     *        in the database.
     */
    private void insertUpdateNodeDetails(BaseNode node) {
        int updatedVersion = node.getBaseVersion()+1;
        node.setBaseVersion(updatedVersion);
        try {
            final int update = jdbcTemplate.update(INSERT_UPDATE_NODE_SQL,
                    node.getNodeId(),
                    node.getParentId(),
                    node.getNodeType().getValue(),
                    node.getTitle(),
                    node.getText(),
                    DBUtill.getString(node.isChecked()),
                    DBUtill.getString(node.isPinned()),
                    DBUtill.getString(node.isArchived()),
                    node.getBaseVersion(),
                    DBUtill.convertToJavaSqlTimeStamp(node.getTimestamps().getCreated()),
                    DBUtill.convertToJavaSqlTimeStamp(node.getTimestamps().getUpdated()));
            logger.debug("NotesDao::updateNode : update is {}", update);
        }catch(Throwable t){
            logger.error("NotesDao::updateLabels : Error updating node with id {}. Error is {}", node.getNodeId(),t.getMessage());
            throw new NodeUpdateException(ErrorCodes.NODE_UPDATE_EXCEPTION,t.getMessage(),t);
        }
    }

    private void updateLabels(BaseNode node) {
        try {
            List<Long> selectedLabels = new ArrayList<>();
            List<Long> deletedLabels = new ArrayList<>();
            if (node.getLabels() != null && node.getLabels().size() > 0)
                for (Label label : node.getLabels()) {
                    if (label.isDeleted()) {
                        deletedLabels.add(label.getLabelId());
                    } else if (label.isSelected()) {
                        selectedLabels.add(label.getLabelId());
                    }
                }
            if (deletedLabels.size() > 0) {
                deleteLabelsForNode(node, deletedLabels);
            }
            if (selectedLabels.size() > 0) {
                insertLabelsForNode(node, selectedLabels);
            }
        }catch(Throwable t){
            logger.error("NotesDao::updateLabels : Error updating node with id {}. Error is {}", node.getNodeId(),t.getMessage());
            throw new NodeUpdateException(ErrorCodes.NODE_UPDATE_EXCEPTION,t.getMessage(),t);
        }
    }

    private void insertLabelsForNode(BaseNode node, List<Long> selectedLabels) {
        for(Long labelId:selectedLabels) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("label_id", labelId);
            namedParameters.addValue("node_id", node.getNodeId());
            int insert = namedParameterJdbcTemplate.update(INSERT_LABEL_SQL, namedParameters);
            logger.debug("NotesDao::updateLabels : insert is {}", insert);
        }
    }

    private void deleteLabelsForNode(BaseNode node, List<Long> deletedLabels) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("deletedLabels", deletedLabels);
        namedParameters.addValue("node_id",node.getNodeId());
        int delete = namedParameterJdbcTemplate.update(DELETE_LABEL_SQL, namedParameters);
        logger.debug("NotesDao::updateLabels : delete is {}", delete);
    }

    /**
     *
     * @param node
     *        This will update the trash flags for the ROOT node and all its child nodes (LIST_ITEM and BLOBs)
     */
    private void markNodeAsTrashed(BaseNode node) {
    }


}
