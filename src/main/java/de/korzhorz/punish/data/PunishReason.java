package de.korzhorz.punish.data;

import de.korzhorz.punish.enums.PunishType;

public class PunishReason {
    private int reasonId;
    private String reasonName;
    private PunishType punishType;
    private long punishDuration;

    public PunishReason(int reasonId, String reasonName, PunishType punishType, long punishDuration) {
        this.reasonId = reasonId;
        this.reasonName = reasonName;
        this.punishType = punishType;
        this.punishDuration = punishDuration;
    }

    public int getReasonId() {
        return reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public PunishType getPunishType() {
        return punishType;
    }

    public long getPunishDuration() {
        return punishDuration;
    }
}
