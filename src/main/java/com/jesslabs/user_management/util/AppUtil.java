package com.jesslabs.user_management.util;

/**
 * Utility class for common functions.
 */
public class AppUtil {

    private AppUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks whether the string is null, empty, blank, or any variant of "null".
     *
     * @param value the string to check
     * @return true if the string is null, empty, blank, or "null"; false otherwise
     */
    public static boolean isNullString(String value) {
        if (value == null) {
            return true;
        }
        String lowerCaseValue = value.toLowerCase();
        return value.isEmpty() || value.isBlank() || "null".equals(lowerCaseValue);
    }
}
