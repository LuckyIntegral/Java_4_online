package ua.com.alevel.persistence.config.impl;

import ua.com.alevel.Main;
import ua.com.alevel.persistence.config.JdbcConfig;
import ua.com.alevel.persistence.util.ResourceUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

public class JdbcConfigImpl implements JdbcConfig {
    private Connection connection;
    private Statement statement;

    private void init() {
        Map<String, String> properties = ResourceUtil.getResources(Main.class.getClassLoader());
        try {
            Class.forName(properties.get("jdbc.driver"));
            connection = DriverManager.getConnection(
                    properties.get("jdbc.url"), properties.get("jdbc.username"), properties.get("jdbc.password"));
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        if (connection == null) {
            init();
        }
        return this.connection;
    }

    @Override
    public Statement getStatement() {
        if (statement == null) {
            init();
        }
        return this.statement;
    }
}
