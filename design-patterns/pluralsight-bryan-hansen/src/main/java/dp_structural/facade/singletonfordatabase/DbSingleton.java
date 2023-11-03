package dp_structural.facade.singletonfordatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {

    private Connection connection = null;
    private static final String JDBC_DERBY_URL =
            "jdbc:derby:memory;databaseName=jdbc_no_facade;create=true";    // creates memory folder in the project


    private static DbSingleton instance = null;

    private DbSingleton() {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static DbSingleton getInstance() {
        if (instance == null) {
            // Ensure thread safety on initial load
            synchronized (DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }
        }
        return instance;
    }


    public Connection getConnection() {
        if (connection == null) {
            synchronized (DbSingleton.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(JDBC_DERBY_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return connection;
    }
}
