package com.intuit.app.models;


import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class contains all the timestamps related information.
 * All the timestamps are expected to be passed by front end.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Timestamps {

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private DateTime created;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private DateTime deleted;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private DateTime trashed;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")

    private DateTime updated;


    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(DateTime deleted) {
        this.deleted = deleted;
    }

    public DateTime getTrashed() {
        return trashed;
    }

    public void setTrashed(DateTime trashed) {
        this.trashed = trashed;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

}
