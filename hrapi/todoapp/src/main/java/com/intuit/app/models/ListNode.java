package com.intuit.app.models;

public class ListNode extends NoteNode {

    public ListNode() {
        this.setNodeType(NodeType.LIST);
    }

    public java.util.List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(java.util.List<ListItem> listItems) {
        this.listItems = listItems;
    }

    private java.util.List<ListItem> listItems;
}
