package de.korzhorz.punish.enums;

public enum PunishType {
    BAN,
    KICK,
    MUTE;

    public int getType() {
        switch(this) {
            case BAN -> {
                return 0;
            }
            case KICK -> {
                return 1;
            }
            case MUTE -> {
                return 2;
            }
            default -> {
                return -1;
            }
        }
    }

    public static PunishType fromType(int type) {
        switch(type) {
            case 0 -> {
                return BAN;
            }
            case 1 -> {
                return KICK;
            }
            case 2 -> {
                return MUTE;
            }
            default -> {
                return null;
            }
        }
    }
}
