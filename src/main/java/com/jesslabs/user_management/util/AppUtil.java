package com.jesslabs.user_management.util;

/**
 * @author barbo on 22-03-2023
 */
public class AppUtil {

    /**
     * checks whether string is null
     *
     * @return true or false
     */
    public static boolean isNullString(String value) {
        boolean isNull = false;
        if (value.isEmpty()) {
            isNull = true;
        } else if (value.isBlank()) {
            isNull = true;
        } else if (value.equals("null")) {
            isNull = true;
        } else if (value.equals("NULL")) {
            isNull = true;
        } else if (value.equals("Null")) {
            isNull = true;
        }
        return isNull;
    }

}
