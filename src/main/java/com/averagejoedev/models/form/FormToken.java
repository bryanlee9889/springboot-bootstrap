package com.averagejoedev.models.form;

/**
 * Created by voncount on 8/31/16.
 */
public class FormToken extends Form {

    private Integer type;
    private String token;
    private String tableRef;
    private Integer rowRef;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public Integer getRowRef() {
        return rowRef;
    }

    public void setRowRef(Integer rowRef) {
        this.rowRef = rowRef;
    }

}
