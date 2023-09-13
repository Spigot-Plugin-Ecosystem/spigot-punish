package de.korzhorz.punish;

import de.korzhorz.punish.database.DB_Reasons;
import de.korzhorz.punish.util.database.DatabaseTableUtil;
import de.korzhorz.punish.util.bungeecord.PluginChannelEvent;
import de.korzhorz.punish.util.data.Command;
import org.bukkit.event.Listener;

public class PluginConfig {
    public static String pluginName = "Punish";

    public static boolean requireBungeeCord = false;
    public static boolean pluginChannels = true;
    public static boolean mySql = true;
    public static boolean requireMySql = true;

    public static PluginChannelEvent[] pluginChannelEvents = new PluginChannelEvent[]{

    };
    public static DatabaseTableUtil[] databaseTableUtils = new DatabaseTableUtil[]{
        DB_Reasons.getInstance()
    };
    public static Command[] commands = new Command[]{

    };
    public static Listener[] listeners = new Listener[]{

    };

    public static String gitHubUser = "Spigot-Plugin-Ecosystem";
    public static String gitHubRepo = "spigot-punish";

    private PluginConfig() {

    }
}
