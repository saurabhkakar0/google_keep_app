package com.intuit.app.service.notes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.app.dao.notes.INotesDao;
import com.intuit.app.models.BaseNode;
import com.intuit.app.web.change.NodesChangeRequest;

@Component
@Transactional
public class NotesService implements INotesService {

    private static final Logger logger = LoggerFactory.getLogger(NotesService.class);

    @Autowired
    INotesDao notesDao;

    @Override
    public List<List<BaseNode>> getNotes() {
        return null;
    }

    @Override
    public void updateNodes(NodesChangeRequest nodesChangeRequest) {
        logger.debug("NotesService::updateNodes : requestId is {}, nodes in this request are {}",nodesChangeRequest.getRequestId(),nodesChangeRequest.getNodes().size());
        notesDao.insertOrUpdate(nodesChangeRequest);
    }
}
