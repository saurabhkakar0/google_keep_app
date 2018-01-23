package com.intuit.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This is the base class of all the Nodes.
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseNode {

    /**
     * This is the nodeId made from the unix timestamp coming from the front end.
     * This cannot be passed as null front frontend.
     */
    private String nodeId;
    /**
     * deleted will have value true if this node has been deleted on the front end.
     */
    private boolean deleted;

    /**
     * deleted will have value true if this node has been trashed on the front end.
     */
    private boolean trashed;

    /**
     * This will store the type of Node.
     * @see NodeType
     */
    private NodeType nodeType ;

    /**
     * This stores the User who created this node.
     */
    private User createdBy;

    /**
     * This stores the User who updated this node.
     */
    private User lastModifiedBy;

    /**
     * This stores all the timestamps related to this node. All the timestamps are coming from the front end.
     */
    private Timestamps timestamps;
    /**
     * This is the parent id of the NoteNode it is associated with.
     * It is default to "root" for NoteNode types NoteNode and ListNode.
     * NodeType ListItem and Attachment will have parent id of either NoteNode or ListNode.
     *
     */
    private String parentId = "root";

    private Integer baseVersion;

    /**
     * title is applicable for NodeTyope root only.
     * @see NodeType
     */
    private String title;

    private List<Long> attachmentList;

    /**
     * title is applicable for NodeType Note and List only.
     * @see NodeType
     */
    private boolean pinned;

    private boolean isArchived;

    private List<User> collaborators;

    /**
     * This stores the labels associated with the root node.
     * Labels can only be associated with NodeType Note and List which are actually root nodes.
     * @see Label
     */
    private List<Label> labels;

    private String text;
    private boolean isChecked;

    public BaseNode() {
    }

    public BaseNode(NodesBuilder nodesBuilder) {
        this.setArchived(nodesBuilder.isArchived);
        this.setNodeId(nodesBuilder.nodeId);
        this.setDeleted(nodesBuilder.deleted);
        this.setTrashed(nodesBuilder.trashed);
        this.setNodeType(nodesBuilder.nodeType);
        this.setTimestamps(nodesBuilder.timestamps);
        this.setCreatedBy(nodesBuilder.createdBy);
        this.setLastModifiedBy(nodesBuilder.lastModifiedBy);
        this.setParentId(nodesBuilder.parentId);
        this.setBaseVersion(nodesBuilder.baseVersion);
        this.setTitle(nodesBuilder.title);
        this.setAttachmentList(nodesBuilder.attachmentList);
        this.setPinned(nodesBuilder.pinned);
        this.setArchived(nodesBuilder.isArchived);
        this.setCollaborators(nodesBuilder.collaborators);
        this.setLabels(nodesBuilder.labels);
        this.setText(nodesBuilder.text);
        this.setChecked(nodesBuilder.isChecked);

    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Timestamps getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamps timestamps) {
        this.timestamps = timestamps;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(Integer baseVersion) {
        this.baseVersion = baseVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Long> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
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

    @Override
    public String toString() {
        return "BaseNode{" +
                "nodeId='" + nodeId + '\'' +
                ", deleted=" + deleted +
                ", trashed=" + trashed +
                ", nodeType=" + nodeType +
                ", createdBy=" + createdBy +
                ", lastModifiedBy=" + lastModifiedBy +
                ", timestamps=" + timestamps +
                ", parentId='" + parentId + '\'' +
                ", baseVersion=" + baseVersion +
                ", title='" + title + '\'' +
                ", attachmentList=" + attachmentList +
                ", pinned=" + pinned +
                ", isArchived=" + isArchived +
                ", collaborators=" + collaborators +
                ", labels=" + labels +
                ", text='" + text + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }

    public static class NodesBuilder {

        private String nodeId;
        private boolean deleted;
        private boolean trashed;
        private NodeType nodeType ;
        private User createdBy;
        private User lastModifiedBy;
        private Timestamps timestamps;
        private String parentId = "root";
        private Integer baseVersion;
        private String title;
        private List<Long> attachmentList;
        private boolean pinned;
        private boolean isArchived;
        private List<User> collaborators;
        private List<Label> labels;
        private String text;
        private boolean isChecked;

        public BaseNode build(){
            return new BaseNode(this);
        }

        public NodesBuilder setNodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public NodesBuilder setDeleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public NodesBuilder setTrashed(boolean trashed) {
            this.trashed = trashed;
            return this;
        }

        public NodesBuilder setNodeType(NodeType nodeType) {
            this.nodeType = nodeType;
            return this;
        }

        public NodesBuilder setCreatedBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public NodesBuilder setLastModifiedBy(User lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
            return this;
        }

        public NodesBuilder setTimestamps(Timestamps timestamps) {
            this.timestamps = timestamps;
            return this;
        }

        public NodesBuilder setParentId(String parentId) {
            this.parentId = parentId;
            return this;
        }

        public NodesBuilder setBaseVersion(Integer baseVersion) {
            this.baseVersion = baseVersion;
            return this;
        }

        public NodesBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public NodesBuilder setAttachmentList(List<Long> attachmentList) {
            this.attachmentList = attachmentList;
            return this;
        }

        public NodesBuilder setPinned(boolean pinned) {
            this.pinned = pinned;
            return this;
        }

        public NodesBuilder setArchived(boolean archived) {
            isArchived = archived;
            return this;
        }

        public NodesBuilder setCollaborators(List<User> collaborators) {
            this.collaborators = collaborators;
            return this;
        }

        public NodesBuilder setLabels(List<Label> labels) {
            this.labels = labels;
            return this;
        }

        public NodesBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public NodesBuilder setChecked(boolean checked) {
            isChecked = checked;
            return this;
        }
    }

}
