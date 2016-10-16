package com.averagejoedev.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by voncount on 7/15/16.
 */
public class MessageUtils {

    private static final String NOTFOUND = "not found";
    private static Properties props;

    public static String getConfig(String key) {
        try {
            if (props == null) {
                props = new Properties();
                props.load(MessageUtils.class.getClassLoader().getResourceAsStream("config.properties"));
            }
            return props.getProperty(key, NOTFOUND);
        } catch (IOException e) {
            return NOTFOUND;
        }
    }
}
