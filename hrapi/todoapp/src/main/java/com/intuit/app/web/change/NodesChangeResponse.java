package com.intuit.app.web.change;

import java.util.List;

import com.intuit.app.models.BaseNode;
import com.intuit.app.web.BaseAPIResponse;

public class NodesChangeResponse extends BaseAPIResponse{

    List<BaseNode> nodes;

    public List<BaseNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<BaseNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
