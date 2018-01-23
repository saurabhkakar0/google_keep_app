package com.intuit.app.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intuit.app.service.notes.INotesService;
import com.intuit.app.web.BaseAPIResponse;
import com.intuit.app.web.NodeResponse;
import com.intuit.app.web.change.NodesChangeRequest;
import com.intuit.app.web.change.NodesChangeResponse;

@Controller
@RequestMapping(value = "/v1/notes")
public class NodeController {

    private static final Logger logger = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    INotesService notesService;


    /**
     *
     * @param nodesChangeRequest
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "change/",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public ResponseEntity<BaseAPIResponse> updateOrInserNote(@RequestBody NodesChangeRequest nodesChangeRequest, HttpServletRequest request) throws Exception{
        logger.debug("NodeController::updateOrInserNote : requestId is {}",nodesChangeRequest.getRequestId());
        notesService.updateNodes(nodesChangeRequest);
        NodesChangeResponse response = new NodesChangeResponse();
        response.setStatusCode(HttpStatus.OK.value()); //Adding Status in the Response Body too.
        response.setStatusMessage("SUCCESS");
        response.setNodes(nodesChangeRequest.getNodeList());
        response.setRequestId(nodesChangeRequest.getRequestId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BaseAPIResponse> getNotes(){

        logger.debug("NodeController::getNotes : requestId is {}\",nodesChangeRequest.getRequestId()");
        NodeResponse response = new NodeResponse();
        response.setStatusCode(HttpStatus.OK.value()); //Adding Status in the Response Body too.
        response.setStatusMessage("SUCCESS");
        response.setNodes(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
