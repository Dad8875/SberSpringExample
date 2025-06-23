package sber.dad.dbexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static sber.dad.dbexample.consts.DBConst.*;


@Configuration
@ComponentScan("sber.dad.dbexample.dao")
public class MyDBConfigContext {

    @Bean
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME,
                DB_USER, DB_PASSWORD
        );
    }
}
