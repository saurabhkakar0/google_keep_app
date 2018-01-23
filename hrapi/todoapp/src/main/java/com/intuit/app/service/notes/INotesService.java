package com.intuit.app.service.notes;

import java.util.List;

import com.intuit.app.models.BaseNode;
import com.intuit.app.web.change.NodesChangeRequest;

public interface INotesService {

    List<List<BaseNode>> getNotes();

    void updateNodes(NodesChangeRequest nodesChangeRequest);
}
