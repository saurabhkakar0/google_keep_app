package com.intuit.app.models;

import org.joda.time.DateTime;

/**
 * the {@code Label} class represents the label if assigned to any NoteNode or ListNode.
 * @see NoteNode
 * @see ListNode
 */
public class Label {

    /**
     * Name of the Label
     */
    private Long labelId;
    private String labelName;
    private DateTime ceratedDate;
    private DateTime lastModifiedDate;
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

    public DateTime getCeratedDate() {
        return ceratedDate;
    }

    public void setCeratedDate(DateTime ceratedDate) {
        this.ceratedDate = ceratedDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     *
     * @return All NoteNode labelled with this label name;
     * @see NoteNode
     */
    public java.util.List getAttachedNotes() {
        return attachedNotes;
    }

    public void setAttachedNotes(java.util.List attachedNotes) {
        this.attachedNotes = attachedNotes;
    }
}
