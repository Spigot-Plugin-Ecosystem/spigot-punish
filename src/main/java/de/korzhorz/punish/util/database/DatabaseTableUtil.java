package de.korzhorz.punish.util.database;

public class DatabaseTableUtil {
    public void createTable() {

    }

    protected boolean requireDatabaseConnection() {
        if(MySQLUtil.isConnected()) {
            return true;
        }

        System.err.println("Database connection is not established");
        return false;
    }
}
