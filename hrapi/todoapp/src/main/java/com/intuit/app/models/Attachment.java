package com.intuit.app.models;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attachment extends BaseNode{

    public Attachment() {
        this.setNodeType(NodeType.BLOB);
    }

    private String attachmentId;
    private URI uri;
}
