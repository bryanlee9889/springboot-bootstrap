package com.averagejoedev.models.domains;

import java.util.Date;

/**
 * Created by voncount on 8/31/16.
 */
public class Token extends Timestamp {

    private Date expiredDate;
    private int type;
    private String token;
    private String tableRef;
    private int rowRef;

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTableRef() {
        return tableRef;
    }

    public void setTableRef(String tableRef) {
        this.tableRef = tableRef;
    }

    public int getRowRef() {
        return rowRef;
    }

    public void setRowRef(int rowRef) {
        this.rowRef = rowRef;
    }

}
