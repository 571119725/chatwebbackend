package com.indi.utils;

import java.lang.reflect.Field;

public class CheckObjectIsNullUtils {
    public static boolean checkObjectIsNull(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
//            String fieldName = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fieldTypeName = field.getType().getTypeName();
            if (fieldTypeName.equals("java.lang.String")) {
                if (fieldValue == null || fieldValue.equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}
