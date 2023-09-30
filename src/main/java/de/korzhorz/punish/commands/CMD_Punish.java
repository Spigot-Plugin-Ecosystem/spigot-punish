package de.korzhorz.punish.commands;

import de.korzhorz.punish.data.PunishPunishment;
import de.korzhorz.punish.data.PunishReason;
import de.korzhorz.punish.database.DB_Punishments;
import de.korzhorz.punish.database.DB_Reasons;
import de.korzhorz.punish.util.messages.CTUtil;
import de.korzhorz.punish.util.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class CMD_Punish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender.hasPermission("punish.punish"))) {
            sender.sendMessage(CTUtil.translate(Messages.get("prefix") + "&r " + Messages.get("commands.errors.no-permission")));
            return true;
        }

        if(args.length < 2) {
            String message = Messages.get("commands.errors.bad-usage");
            message = message.replaceAll("%usage%", command.getUsage());
            sender.sendMessage(CTUtil.translate(Messages.get("prefix") + "&r " + message));
            return true;
        }

        String playerName = args[0];
        String reasonIdString = args[1];

        try {
            int reasonIdInt = Integer.parseInt(reasonIdString);
            PunishReason punishReason = DB_Reasons.getInstance().getReason(reasonIdInt);

            if(punishReason == null) {
                sender.sendMessage(CTUtil.translate(Messages.get("prefix") + "&r " + Messages.get("commands.errors.reason-not-found")));
                return true;
            }

            Player punishedPlayer = Bukkit.getPlayer(playerName);

            if(punishedPlayer == null) {
                sender.sendMessage(CTUtil.translate(Messages.get("prefix") + "&r " + Messages.get("commands.errors.player-not-found")));
                return true;
            }

            UUID punishedPlayerUuid = punishedPlayer.getUniqueId();

            Date punishStart = new Date();
            Date punishEnd = new Date(punishStart.getTime() + (punishReason.getPunishDuration() * 1000));
            if(punishReason.getPunishDuration() == -1) {
                punishEnd = null;
            }

            PunishPunishment punishment = new PunishPunishment(punishedPlayerUuid.toString(), punishReason.getReasonId(), punishStart, punishEnd);

            boolean override = DB_Punishments.getInstance().getPunishment(punishedPlayer) != null;
            DB_Punishments.getInstance().savePunishment(punishment);

            String message = "";
            if(override) {
                message = Messages.get("commands.punish.override");
                message = message.replaceAll("%player%", punishedPlayer.getDisplayName());
            } else {
                message = Messages.get("commands.punish.initial");
                message = message.replaceAll("%player%", punishedPlayer.getDisplayName());
            }
            sender.sendMessage(CTUtil.translate(Messages.get("prefix") + "&r " + message));
        } catch(NumberFormatException e) {
            sender.sendMessage(CTUtil.translate(Messages.get("prefix") + "&r " + Messages.get("commands.errors.reason-not-found")));
            return true;
        }

        return true;
    }
}
