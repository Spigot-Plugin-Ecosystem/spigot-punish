package de.korzhorz.punish.database;

import de.korzhorz.punish.data.PunishPunishment;
import de.korzhorz.punish.util.database.DatabaseTableUtil;
import de.korzhorz.punish.util.database.MySQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class DB_Punishments extends DatabaseTableUtil {
    private static DB_Punishments instance;

    public static DB_Punishments getInstance() {
        if (instance == null) {
            instance = new DB_Punishments();
        }
        return instance;
    }

    private DB_Punishments() {

    }

    @Override
    public void createTable() {
        if(!this.requireDatabaseConnection()) {
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS `Punish_Punishments` (";
        sql += "`uuid` VARCHAR(36) NOT NULL UNIQUE,";
        sql += "`reasonId` INT(11) NOT NULL,";
        sql += "`punishStart` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,";
        sql += "`punishEnd` TIMESTAMP NULL DEFAULT NULL";
        sql += ");";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public PunishPunishment getPunishment(String uuid) {
        if(!this.requireDatabaseConnection()) {
            return null;
        }

        String sql = "SELECT * FROM `Punish_Punishments` WHERE `uuid` = ? AND `punishEnd` < CURRENT_TIMESTAMP();";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()) {
                return new PunishPunishment(
                    result.getString("uuid"),
                    result.getInt("reasonId"),
                    result.getDate("punishStart"),
                    result.getDate("punishEnd")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public PunishPunishment getPunishment(Player player) {
        return this.getPunishment(player.getUniqueId().toString());
    }

    public void savePunishment(PunishPunishment punishment) {
        if(!this.requireDatabaseConnection()) {
            return;
        }

        if(this.getPunishment(punishment.getUuid()) != null) {
            this.removePunishment(punishment.getUuid());
        }

        String sql = "INSERT INTO `Punish_Punishments` (`uuid`, `reasonId`, `punishStart`, `punishEnd`) VALUES (?, ?, ?, ?);";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            preparedStatement.setString(1, punishment.getUuid());
            preparedStatement.setInt(2, punishment.getReasonId());
            preparedStatement.setString(3, timestampFormat.format(punishment.getPunishStart()));
            preparedStatement.setString(4, timestampFormat.format(punishment.getPunishEnd()));

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePunishment(String uuid) {
        if(!this.requireDatabaseConnection()) {
            return;
        }

        String sql = "DELETE FROM `Punish_Punishments` WHERE `uuid` = ?;";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
