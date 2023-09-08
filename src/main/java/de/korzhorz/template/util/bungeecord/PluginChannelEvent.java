package de.korzhorz.template.util.bungeecord;

import com.google.common.io.ByteArrayDataInput;

public interface PluginChannelEvent {
    String getHandledSubChannel();

    void handle(ByteArrayDataInput byteArrayDataInput);
}
