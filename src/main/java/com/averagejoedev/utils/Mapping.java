package com.averagejoedev.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by voncount on 9/1/16.
 */
public class Mapping {

    public static <T, F> T map(F source, Class<T> destination) throws InstantiationException, IllegalAccessException {
        // instantiate new destination
        T destInstance = destination.newInstance();

        // access all source fields
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Arrays.stream(sourceFields).forEach(sf -> {
            sf.setAccessible(true);
            // dest's field's name == source's field's name
            // set dest's field
            try {
                Field destField = source.getClass().getDeclaredField(sf.getName());
                destField.setAccessible(true);
                destField.set(destField, sf.get(source));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return destInstance;
    }

}
