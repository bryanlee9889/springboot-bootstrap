package com.averagejoedev.models.domains;

import java.util.Date;

/**
 * Created by voncount on 8/1/16.
 */
public class Timestamp extends Domain {

    protected Date createdDate;
    protected Date modifiedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                "} " + super.toString();
    }
}
