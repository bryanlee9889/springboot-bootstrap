package com.averagejoedev.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by voncount on 7/20/16.
 */
public class ValidationUtils {

    public static class Strings {

        private static final String PATTERN_EMAIL = ".+@.+\\..+";

        public static final boolean isNullOrEmpty(String input) {
            return input == null || input.trim().isEmpty();
        }

        public static final boolean isValidEmail(String email) {
            return email != null && email.matches(PATTERN_EMAIL);
        }

        public static final boolean isValidPhoneNumber(String phoneNumber) {

            if (phoneNumber == null) {
                return false;
            }

            List<String> PHONE_PATTERNS = new ArrayList<>();
            PHONE_PATTERNS.add("^\\(\\+82\\)\\d{11}$");
            PHONE_PATTERNS.add("^\\(\\+992\\)\\d{11}$");
            PHONE_PATTERNS.add("^\\(\\+1\\)\\d{11}$");

            for (String pattern : PHONE_PATTERNS) {
                if (phoneNumber.matches(pattern)) {
                    return true;
                }
            }

            return false;
        }

    }

    public static class File {

        public static final List<String> MIME_IMAGE;

        static {
            MIME_IMAGE = new LinkedList<>();
            MIME_IMAGE.add("image/jpeg");
            MIME_IMAGE.add("image/png");
            MIME_IMAGE.add("image/bmp");
            MIME_IMAGE.add("image/png/jpg");
        }

        public static final boolean isValidImage(String contentType) {
            return MIME_IMAGE.contains(contentType);
        }

    }

}
