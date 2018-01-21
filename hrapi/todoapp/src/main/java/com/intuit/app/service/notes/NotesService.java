package com.intuit.app.service.notes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.app.dao.notes.INotesDao;
import com.intuit.app.models.BaseNode;
import com.intuit.app.web.change.NodesChangeRequest;
import com.intuit.app.web.change.NodesChangeResponse;

@Component
public class NotesService implements INotesService {

    private static final Logger logger = LoggerFactory.getLogger(NotesService.class);

    @Autowired
    INotesDao notesDao;

    @Override
    public List<List<BaseNode>> getNotes() {
        return null;
    }

    @Override
    public NodesChangeResponse updateNodes(NodesChangeRequest nodesChangeRequest) {
        logger.debug("NotesService::updateNodes : requestId is {}, nodes in this request are {}",nodesChangeRequest.getRequestId(),nodesChangeRequest.getNodeList().size());
        notesDao.insertOrUpdate(nodesChangeRequest);
        return null;
    }
}
