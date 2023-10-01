package de.korzhorz.punish.util;

import java.util.UUID;

public class UUIDFormatter {
    public static String formatUuid(String uuidString) {
        if(isValidUUID(uuidString)) {
            return uuidString;
        }

        uuidString = uuidString.replaceAll("-", "");

        StringBuilder formattedUUID = new StringBuilder(uuidString);
        formattedUUID.insert(8, "-");
        formattedUUID.insert(13, "-");
        formattedUUID.insert(18, "-");
        formattedUUID.insert(23, "-");

        return formattedUUID.toString();
    }

    public static boolean isValidUUID(String uuidString) {
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
