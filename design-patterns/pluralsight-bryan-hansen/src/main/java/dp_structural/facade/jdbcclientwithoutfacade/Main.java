package dp_structural.facade.jdbcclientwithoutfacade;


import dp_structural.facade.singletonfordatabase.DbSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        DbSingleton instance = DbSingleton.getInstance();

        String createTableAddress =
                "CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(20), City VARCHAR(20))";

        String insertIntoAddress =
                "INSERT INTO Address (ID, StreetName, City) VALUES (1, '1234 Some Street', 'Seattle')";

        String selectAll = "SELECT * FROM Address";


        try {
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();

            try {
                sta.executeUpdate(createTableAddress);
                System.out.println("Table created.");
            } catch (SQLException e) {
                if(e.getSQLState().equals("X0Y32")) {
                    System.out.println("Address table was already created!");
                }
            } finally {
                sta.close();
            }


            sta = conn.createStatement();
            int count = sta.executeUpdate(insertIntoAddress);
            System.out.println(count + " record(s) created");
            sta.close();


            sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectAll);
            while (rs.next()) {
                System.out.println("Entry " + rs.getString(1) + "\n" +
                        rs.getString(2) + "\n" + rs.getString("City"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


