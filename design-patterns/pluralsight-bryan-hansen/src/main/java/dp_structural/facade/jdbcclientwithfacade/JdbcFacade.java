package dp_structural.facade.jdbcclientwithfacade;


import dp_structural.facade.singletonfordatabase.DbSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcFacade {

    DbSingleton instance;

    public JdbcFacade() {
        instance = DbSingleton.getInstance();
    }

    public void closeConnection() throws SQLException {
        Connection conn = instance.getConnection();
        conn.close();
    }

    public int createInsert (String sqlExpression) {
        int count = 0;
        try {
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();
            count = sta.executeUpdate(sqlExpression);
            System.out.println("Table updated\n");
            sta.close();
            //conn.close();

        } catch (SQLException e) {
            if(e.getSQLState().equals("X0Y32")) {
                System.out.println("Address table was already created!\n");
            }
        }
        return count;
    }

    public List<Address> getAddresses(String sqlQuery) {
        List<Address> addressList = new ArrayList<>();

        try {
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(sqlQuery);

            while (rs.next()) {
                Address address = new Address(rs.getString(1),
                        rs.getString(2), rs.getString("city"));
                addressList.add(address);
                System.out.println("Read:\n" + address.getId() + " " + address.getStreetName()
                        + " " + address.getCity() + "\n");
            }

            rs.close();
            sta.close();
            //conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return addressList;
    }
}
