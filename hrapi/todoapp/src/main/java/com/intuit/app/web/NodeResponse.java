package com.intuit.app.web;

import java.util.List;

import com.intuit.app.models.BaseNode;

public class NodeResponse extends BaseAPIResponse {


    List<List<BaseNode>> nodes;

    public List<List<BaseNode>> getNodes() {
        return nodes;
    }

    public void setNodes(List<List<BaseNode>> nodes) {
        this.nodes = nodes;
    }


}
