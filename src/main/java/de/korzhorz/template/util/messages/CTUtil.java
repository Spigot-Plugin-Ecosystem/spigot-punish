package de.korzhorz.template.util.messages;

import org.bukkit.ChatColor;

public class CTUtil {
    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
