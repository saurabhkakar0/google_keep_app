package com.intuit.app.dao.notes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.app.dao.mappers.NoteMapper;
import com.intuit.app.models.BaseNode;
import com.intuit.app.models.NodeType;
import com.intuit.app.service.notes.NotesService;
import com.intuit.app.utils.DBUtill;
import com.intuit.app.web.change.NodesChangeRequest;

@Transactional
@Repository
public class NotesDao implements INotesDao {

    private static final String EXISTS_SQL = "SELECT count(*) FROM NODE WHERE node_id = ?";
    private static final String INSERT_NODE_SQL = "INSERT INTO NODE (" +
            "node_id," +
            "parent_node_id," +
            "node_type," +
            "title," +
            "text," +
            "isChecked," +
            "isPinned," +
            "isArchived," +
            "baseVersion," +
            "created_date," +
            "updated_date) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    String sql = "UPDATE articles SET title=?, category=? WHERE articleId=?";

    private static final String UPDATE_NODE_SQL = "UPDATE NODE SET" +

            "node_type = ?," +
            "title = ?," +
            "text = ?," +
            "isChecked = ?," +
            "isPinned = ?," +
            "isArchived = ?," +
            "baseVersion = ?," +
            "created_date = ?," +
            "updated_date = ?) " +
            "WHERE node_id = ?";

    private static final Logger logger = LoggerFactory.getLogger(NotesService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<List<BaseNode>> getNotes(){
        List<List<BaseNode>> result = new ArrayList<>();
        String sql = "SELECT node_id, title, isPinned, isArchived FROM NODE";
        RowMapper<BaseNode> rowMapper = new NoteMapper();
        final List<BaseNode> baseNodes = this.jdbcTemplate.query(sql, rowMapper);

        return result;
    }


    @Override
    @Transactional
    public void insertOrUpdate(NodesChangeRequest nodesChangeRequest) {
        logger.debug("NotesDao::insertOrUpdate : requestId is {}, nodes in this request are {}",nodesChangeRequest.getRequestId(),nodesChangeRequest.getNodeList().size());
        for(BaseNode node:nodesChangeRequest.getNodeList()){
            if(nodeExists(node.getNodeId())){
                updateNode(node);
            }else{
                insertNode(node);
            }
        }
    }

    private void insertNode(BaseNode node) {
        if(node.getNodeType().equals(NodeType.BLOB)){
            insertAttachment();
        }else{
            final int update = jdbcTemplate.update(INSERT_NODE_SQL,
                    node.getNodeId(),
                    node.getParentId(),
                    node.getNodeType().getValue(),
                    node.getTitle(),
                    node.getText(),
                    node.isChecked(),
                    node.isPinned(),
                    node.isArchived(),
                    node.getBaseVersion(),
                    DBUtill.convertToJavaSqlTimeStamp(node.getTimestamps().getCreated()),
                    DBUtill.convertToJavaSqlTimeStamp(node.getTimestamps().getUpdated()));
            logger.debug("NotesDao::insertNode : update is {}",update);

        }
    }

    private void insertAttachment() {
    }

    private void updateNode(BaseNode node) {

        final int update = jdbcTemplate.update(INSERT_NODE_SQL,
                node.getNodeId(),
                node.getParentId(),
                node.getNodeType().getValue(),
                node.getTitle(),
                node.getText(),
                node.isChecked(),
                node.isPinned(),
                node.isArchived(),
                node.getBaseVersion(),
                DBUtill.convertToJavaSqlTimeStamp(node.getTimestamps().getCreated()),
                DBUtill.convertToJavaSqlTimeStamp(node.getTimestamps().getUpdated()));
        logger.debug("NotesDao::insertNode : update is {}",update);

    }

    private boolean nodeExists(String id) {
        String nodeId = jdbcTemplate.queryForObject(EXISTS_SQL,String.class,id);
        return !nodeId.equals("0");
    }

}
