package de.korzhorz.punish;

import de.korzhorz.punish.util.database.DatabaseTableUtil;
import de.korzhorz.punish.util.bungeecord.PluginChannelEvent;
import de.korzhorz.punish.util.data.Command;
import org.bukkit.event.Listener;

public class PluginConfig {
    public static String pluginName = "Template";

    public static boolean requireBungeeCord = false;
    public static boolean pluginChannels = false;
    public static boolean mySql = false;
    public static boolean requireMySql = false;

    public static PluginChannelEvent[] pluginChannelEvents = new PluginChannelEvent[]{

    };
    public static DatabaseTableUtil[] databaseTableUtils = new DatabaseTableUtil[]{

    };
    public static Command[] commands = new Command[]{

    };
    public static Listener[] listeners = new Listener[]{

    };

    public static String gitHubUser = "Spigot-Plugin-Ecosystem";
    public static String gitHubRepo = "plugin-template-spigot";

    private PluginConfig() {

    }
}
