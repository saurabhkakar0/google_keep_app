package com.intuit.app.models;

/**
 * NodeType can be of following types:
 *
 *
 *    @see NoteNode Basic Note with no checked or line items
 *    @see ListNode ListNode is like Note but may have Line Items. Line Items are associated with the Status.
 *    @see ListItem Item
 *    @see Attachment This can be image of format jpeg and png.
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
