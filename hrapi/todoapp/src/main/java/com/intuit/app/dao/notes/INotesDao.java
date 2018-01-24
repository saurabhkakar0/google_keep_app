package com.intuit.app.dao.notes;

import java.util.List;

import com.intuit.app.models.BaseNode;
import com.intuit.app.web.change.NodesChangeRequest;

public interface INotesDao {

    List<List<BaseNode>> getNotes();

    List<BaseNode> insertOrUpdate(NodesChangeRequest nodesChangeRequest);
}
