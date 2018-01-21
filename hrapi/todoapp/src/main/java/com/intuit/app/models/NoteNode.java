package com.intuit.app.models;

import java.util.List;

public class NoteNode extends BaseNode{

    private String title;

    private List<Attachment> attachmentList;
    private boolean isPinned;
    private boolean isArchived;
    private List<User> collaborators;
    private List<Label> labelIds;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public List<Label> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<Label> labelIds) {
        this.labelIds = labelIds;
    }

}
