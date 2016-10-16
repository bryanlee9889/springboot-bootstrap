package com.averagejoedev.utils;

import org.slf4j.Logger;

/**
 * Created by voncount on 8/31/16.
 */
public class LoggingUtils {

    public static void loggingDebug(Logger logger, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("===============================================================================");
            logger.debug(message);
            logger.debug("===============================================================================");
        }
    }

}
