package com.intuit.app.models;

public class ListItem extends BaseNode{

    private String text;
    private boolean isChecked;

    public ListItem() {
        this.setNodeType(NodeType.LIST_ITEM);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
