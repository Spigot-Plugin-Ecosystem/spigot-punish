package de.korzhorz.punish.data;

import java.util.Date;

public class PunishPunishment {
    private final String uuid;
    private final int reasonId;
    private final Date punishStart;
    private final Date punishEnd;

    public PunishPunishment(String uuid, int reasonId, Date punishStart, Date punishEnd) {
        this.uuid = uuid;
        this.reasonId = reasonId;
        this.punishStart = punishStart;
        this.punishEnd = punishEnd;
    }

    public String getUuid() {
        return uuid;
    }

    public int getReasonId() {
        return reasonId;
    }

    public Date getPunishStart() {
        return punishStart;
    }

    public Date getPunishEnd() {
        return punishEnd;
    }
}
