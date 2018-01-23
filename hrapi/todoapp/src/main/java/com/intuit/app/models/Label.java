package com.intuit.app.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * the {@code Label} class represents the label if assigned to any Node.
 * Label can not be associated with any Node of type LIST_ITEM
 * @see BaseNode
 * @see NodeType
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Label {

    /**
     * Name of the Label
     */
    private Long labelId;
    private String labelName;
    private Timestamps timestamps;
    private boolean deleted;
    private boolean selected;
    private java.util.List attachedNotes;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Timestamps getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamps timestamps) {
        this.timestamps = timestamps;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     *
     * @return All NoteNode labelled with this label name;
     * @see BaseNode
     */
    public java.util.List getAttachedNotes() {
        return attachedNotes;
    }

    public void setAttachedNotes(java.util.List attachedNotes) {
        this.attachedNotes = attachedNotes;
    }
}
