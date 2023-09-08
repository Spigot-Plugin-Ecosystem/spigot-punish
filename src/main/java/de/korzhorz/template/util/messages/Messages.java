package de.korzhorz.template.util.messages;

import de.korzhorz.template.configs.ConfigFiles;

public class Messages {
    private Messages() {

    }

    public static String get(String path) {
        return ConfigFiles.messages.getString(path);
    }
}
