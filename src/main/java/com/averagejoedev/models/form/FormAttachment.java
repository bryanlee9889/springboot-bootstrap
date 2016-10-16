package com.averagejoedev.models.form;

import com.averagejoedev.utils.ValidationUtils;

import javax.servlet.http.Part;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by voncount on 8/1/16.
 */
public class FormAttachment extends Form {

    private String ChannelSid;
    private Collection<Part> parts;

    public String getChannelSid() {
        return ChannelSid;
    }

    public void setChannelSid(String channelSid) {
        ChannelSid = channelSid;
    }

    public Collection<Part> getParts() {
        return parts;
    }

    public void setParts(Collection<Part> parts) {
        this.parts = parts
                .stream()
                .filter(p -> p != null && !ValidationUtils.Strings.isNullOrEmpty(p.getSubmittedFileName()))
                .collect(Collectors.toList());
    }
}
