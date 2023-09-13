package de.korzhorz.punish.database;

import de.korzhorz.punish.data.PunishReason;
import de.korzhorz.punish.enums.PunishType;
import de.korzhorz.punish.util.database.DatabaseTableUtil;
import de.korzhorz.punish.util.database.MySQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Reasons extends DatabaseTableUtil {
    private static DB_Reasons instance;

    public static DB_Reasons getInstance() {
        if(instance == null) {
            instance = new DB_Reasons();
        }
        return instance;
    }

    private DB_Reasons() {

    }

    @Override
    public void createTable() {
        if(!this.requireDatabaseConnection()) {
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS `Punish_Reasons` (";
        sql += "`reasonId` INT(11) NOT NULL PRIMARY KEY,";
        sql += "`reasonName` VARCHAR(63) NOT NULL DEFAULT \"\",";
        sql += "`punishType` INT(11) NOT NULL DEFAULT 0,";
        sql += "`punishDuration` INT(11) NULL DEFAULT 604800";
        sql += ");";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PunishReason> getReasons() {
        if(!this.requireDatabaseConnection()) {
            return new ArrayList<>();
        }

        String sql = "SELECT * FROM `Punish_Reasons` ORDER BY reasonId ASC;";

        ArrayList<PunishReason> punishReasons = new ArrayList<>();

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                int reasonId = result.getInt("reasonId");
                String reasonName = result.getString("reasonName");
                PunishType punishType = PunishType.fromType(result.getInt("punishType"));
                int punishDuration = result.getInt("punishDuration");

                PunishReason punishReason = new PunishReason(reasonId, reasonName, punishType, punishDuration);
                punishReasons.add(punishReason);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return punishReasons;
    }

    public PunishReason getReason(int punishId) {
        if(!this.requireDatabaseConnection()) {
            return null;
        }

        String sql = "SELECT * FROM `Punish_Reasons` WHERE `reasonId` = ?;";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, punishId);

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()) {
                int reasonId = result.getInt("reasonId");
                String reasonName = result.getString("reasonName");
                PunishType punishType = PunishType.fromType(result.getInt("punishType"));
                int punishDuration = result.getInt("punishDuration");

                return new PunishReason(reasonId, reasonName, punishType, punishDuration);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addReason(PunishReason punishReason) {
        if(!this.requireDatabaseConnection()) {
            return;
        }

        String sql = "INSERT INTO `Punish_Reasons` (`reasonId`, `reasonName`, `punishType`, `punishDuration`) VALUES (?, ?, ?, ?);";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, punishReason.getReasonId());
            preparedStatement.setString(2, punishReason.getReasonName());
            preparedStatement.setInt(3, punishReason.getPunishType().getType());
            preparedStatement.setInt(4, punishReason.getPunishDuration());

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeReason(PunishReason punishReason) {
        if(!this.requireDatabaseConnection()) {
            return;
        }

        String sql = "DELETE FROM `Punish_Reasons` WHERE `reasonId` = ?;";

        try(PreparedStatement preparedStatement = MySQLUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, punishReason.getReasonId());

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
