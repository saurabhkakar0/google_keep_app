package com.intuit.app.web.change;

import java.util.List;

import com.intuit.app.dto.APIRequestInfo;
import com.intuit.app.models.BaseNode;

public class NodesChangeRequest {

    private String requestId;
    private List<BaseNode> nodeList;
    private APIRequestInfo apiRequestInfo;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<BaseNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<BaseNode> nodeList) {
        this.nodeList = nodeList;
    }

    public APIRequestInfo getApiRequestInfo() {
        return apiRequestInfo;
    }

    public void setApiRequestInfo(APIRequestInfo apiRequestInfo) {
        this.apiRequestInfo = apiRequestInfo;
    }
}
