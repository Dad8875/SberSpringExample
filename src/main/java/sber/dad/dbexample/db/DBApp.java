package sber.dad.dbexample.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static sber.dad.dbexample.consts.DBConst.*;

public enum DBApp {
    INSTANCE;

    private Connection connection;

    public Connection  newConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME,
                DB_USER, DB_PASSWORD
        );
    }
}
