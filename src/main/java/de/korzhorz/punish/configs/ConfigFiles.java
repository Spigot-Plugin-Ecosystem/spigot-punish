package de.korzhorz.punish.configs;

import de.korzhorz.punish.Main;
import de.korzhorz.punish.PluginConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigFiles {
    public static ConfigFile config = new ConfigFile("config.yml");
    public static ConfigFile messages = new ConfigFile("messages.yml");
    public static ConfigFile updater = new ConfigFile("updater.yml");

    public static void initFileContents() {
        // Config
        if(PluginConfig.mySql) {
            config.setDefault("mysql.host", "localhost");
            config.setDefault("mysql.port", 3306);
            config.setDefault("mysql.database", "database");
            config.setDefault("mysql.username", "username");
            config.setDefault("mysql.password", "password");
        }
        if(PluginConfig.requireBungeeCord) {
            config.setDefault("bungeecord.enforce", false);
        }

        config.save();

        // Messages
        messages.setDefault("prefix", "&6&l" + PluginConfig.pluginName + " &8»");

        messages.setDefault("commands.errors.no-player", "&cDu musst ein Spieler sein um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.no-permission", "&cDu hast keine Rechte um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.bad-usage", "&cBenutze: &7%usage%");
        messages.setDefault("commands.errors.save-failed", "&cDie Änderungen konnten nicht gespeichert werden.");
        messages.setDefault("commands.errors.reason-not-found", "&cDer Punish-Grund konnte nicht gefunden werden.");
        messages.setDefault("commands.errors.player-not-found", "&cDer Spieler konnte nicht gefunden werden.");

        messages.setDefault("commands.punish.initial", "&cDer Spieler &e%player% &cwurde bestraft.");
        messages.setDefault("commands.punish.override", "&cDer Spieler &e%player% &cwurde bestraft. &7(Überschrieben)");

        messages.save();

        // Updater
        updater.setDefault("latest", JavaPlugin.getPlugin(Main.class).getDescription().getVersion());
        updater.setDefault("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        updater.save();
    }
}
