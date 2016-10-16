package com.averagejoedev.models.domains;

/**
 * Created by voncount on 8/1/16.
 */
public class Domain {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "id=" + id +
                '}';
    }
}
