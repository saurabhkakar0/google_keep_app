package com.intuit.app.models;

/**
 * NodeType can be of following types:
 *
 *
 *    1. Basic Note Node. This will not have any checked or line items
 *    2. ListNote Node is like Basic Note but may have Line Items. Line Items are associated with the completed or inCompleted status.
 *    3. ListItem Node. This node is associated with ListNode or Basic Note as its child.
 *    4. Attachment Node This can be image of format jpeg and png.
 */
public enum NodeType {

    NOTE("NOTE"),
    LIST("LIST"),
    LIST_ITEM("LIST_ITEM"),
    BLOB("BLOB");
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    private String value;
    NodeType(String value){
        this.value = value;
    }

}
