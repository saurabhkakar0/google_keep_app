package com.intuit.app.models;

import java.net.URI;

public class Attachment extends BaseNode{

    public Attachment() {
        this.setNodeType(NodeType.BLOB);
    }

    private String attachmentId;
    private URI uri;
}
