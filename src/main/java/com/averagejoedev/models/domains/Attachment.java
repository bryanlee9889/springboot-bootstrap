package com.averagejoedev.models.domains;

/**
 * Created by voncount on 8/2/16.
 */
public class Attachment extends Timestamp {

    private String name;
    private String type;
    private String size;
    private String uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        if (size == null || size.isEmpty()) {
            this.size = "0.0 MB";
        } else {
            long _size = Long.valueOf(size);
            // KB
            if (_size < 1000000L) {
                System.out.println( _size / 1000d);
                this.size = String.format("%.2f KB", _size / 1000d);
            }
            // MB
            else {
                this.size = String.format("%.2f MB", _size / 1000000d);
            }
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}