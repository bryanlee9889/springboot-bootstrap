package com.averagejoedev.models.form;


import com.averagejoedev.models.constant.ConstGlobal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by voncount on 7/20/16.
 */
public abstract class  Form implements Serializable {

    protected Integer id;
    protected List<Integer> ids;
    protected Integer authId;
    protected String authUsername;
    protected Integer callerId;
    protected String query;
    protected String orders     = ConstGlobal.FILTER_ORDER;
    protected String direction  = ConstGlobal.FILTER_DIRECTION_DESC;
    protected int pageIndex     = ConstGlobal.PAGE_INDEX;
    protected int pageSize      = ConstGlobal.PAGE_SIZE;
    protected int[] statuses;
    protected Integer status;
    protected String uuid;
    protected String os;
    protected String version;
    protected String longitude;
    protected String latitude;
    protected String deviceToken;
    protected String locale     = ConstGlobal.LOCALE_DEFAULT;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public void setAuthUsername(String authUsername) {
        this.authUsername = authUsername;
    }

    public Integer getCallerId() {
        return callerId;
    }

    public void setCallerId(Integer callerId) {
        this.callerId = callerId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int[] getStatuses() {
        return statuses;
    }

    public void setStatuses(int[] statuses) {
        this.statuses = statuses;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
