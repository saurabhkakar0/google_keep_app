package com.intuit.app.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.intuit.app.models.BaseNode;

public class NoteMapper implements RowMapper<BaseNode>{


        @Override
        public BaseNode mapRow(ResultSet row, int rowNum) throws SQLException {
            BaseNode node = new BaseNode();
            node.setNodeId(row.getString("node_id"));
            node.setPinned(row.getString("isPinned")=="Y"?true:false);
            node.setArchived(row.getString("isArchived")=="Y"?true:false);
            return node;
        }


}
