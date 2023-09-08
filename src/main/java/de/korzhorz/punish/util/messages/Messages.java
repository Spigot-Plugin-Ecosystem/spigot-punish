package de.korzhorz.punish.util.messages;

import de.korzhorz.punish.configs.ConfigFiles;

public class Messages {
    private Messages() {

    }

    public static String get(String path) {
        return ConfigFiles.messages.getString(path);
    }
}
