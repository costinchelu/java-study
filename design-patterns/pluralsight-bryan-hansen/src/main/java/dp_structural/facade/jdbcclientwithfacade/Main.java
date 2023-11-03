package dp_structural.facade.jdbcclientwithfacade;

import java.sql.SQLException;
import java.util.List;

public class Main {

    private static final String CREATE_TABLE_ADDRESS =
            "CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(20), City VARCHAR(20))";

    private static final String INSERT_INTO_ADDRESS =
            "INSERT INTO Address (ID, StreetName, City) VALUES (1, '1234 Some Street', 'Seattle')";

    private static final String SELECT_ALL = "SELECT * FROM Address";

    public static void main(String[] args) {

        JdbcFacade jdbcFacade = new JdbcFacade();

        jdbcFacade.createInsert(CREATE_TABLE_ADDRESS);
        jdbcFacade.createInsert(INSERT_INTO_ADDRESS);

        List<Address> addresses = jdbcFacade.getAddresses(SELECT_ALL);
        for(Address address : addresses) {
            System.out.println("Entry " + address.getId() + "\n" + "Street: " + address.getStreetName() + "\n" +
                    address.getCity() + "\n");
        }

        try {
            jdbcFacade.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
simplifies client interface
easy
refactoring pattern
 */