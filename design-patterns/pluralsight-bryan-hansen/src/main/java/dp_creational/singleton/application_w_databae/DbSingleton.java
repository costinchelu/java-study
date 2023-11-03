package dp_creational.singleton.application_w_databae;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSingleton {

    private static DbSingleton instance = null;		// Lazy load singleton
    private Connection connection = null;
    private static final String JDBC_DERBY_URL = "jdbc:derby:memory;databaseName=singleton_test;create=true";    
	// JDBC_DERBY_URL creates new database in memory folder inside the project  


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


    public static void main(String[] args) {
		
        String createTableAddress = "CREATE TABLE Address (ID INT, ADDRESS1 VARCHAR(20), CITY VARCHAR(20))";

        DbSingleton dbSingleton = DbSingleton.getInstance();
        Connection connection = dbSingleton.getConnection();

        Statement statement;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(createTableAddress);
            System.out.println("Address table created!");
            statement.close();
			
        } catch (SQLException e) {
            if(!e.getSQLState().equals("XoY32")) {
                //e.printStackTrace();
                System.out.println("Address table was already created!");
                // this is necessary in case we want to run the program more than once
                // or else we would get an error of "table already created"
            }
        }
    }
}

