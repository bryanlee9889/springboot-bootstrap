package com.averagejoedev.models.constant;

import com.averagejoedev.utils.MessageUtils;

/**
 * Created by voncount on 7/20/16.
 */
public class ConstGlobal {

    /******************************************************************************************************************
     * FILTER DEFAULT CONFIG
     ******************************************************************************************************************/
    public static final int PAGE_INDEX                                  = 0;
    public static final int PAGE_SIZE                                   = 10;

    public static final String FILTER_ORDER                             = "id";
    public static final String FILTER_DIRECTION_ASC                     = "ASC";
    public static final String FILTER_DIRECTION_DESC                    = "DESC";

    /******************************************************************************************************************
     * ENCRYPTION CONFIG
     ******************************************************************************************************************/
    public static final String ENCRYPTION_ISSUER                        = MessageUtils.getConfig("encryption.issuer");
    public static final String ENCRYPTION_KEY                           = MessageUtils.getConfig("encryption.key");

    /******************************************************************************************************************
     * WEB CONFIG
     ******************************************************************************************************************/
    public static final String BASE_URL                                 = MessageUtils.getConfig("base.url");
    public static final String UPLOAD_BASE_DIRECTORY                    = MessageUtils.getConfig("upload.base.directory");
    public static final String LOCALE_DEFAULT                           = MessageUtils.getConfig("locale.default");

}
