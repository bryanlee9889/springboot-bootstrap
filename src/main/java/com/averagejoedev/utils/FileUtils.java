package com.averagejoedev.utils;

import com.averagejoedev.exceptions.ApplicationException;
import com.averagejoedev.models.enumeration.EnumMessage;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by voncount on 7/20/16.
 */
public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class);

    public static String upload(InputStream is, String path, String fileName) throws ApplicationException {
        try {
            String _fileName = UUID.randomUUID().toString() + "_" + fileName.replaceAll("\\s+", "");
            File file = new File(path, _fileName);
            if (!file.exists()) file.getParentFile().mkdirs();
            FileCopyUtils.copy(is, new FileOutputStream(file));
            return _fileName;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(EnumMessage.EXCEPTION_FILEUPLOAD_FAILED);
        }
    }

}
