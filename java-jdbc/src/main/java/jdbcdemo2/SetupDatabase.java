package jdbcdemo2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;

public class SetupDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zoo";
        try (Connection conn = DriverManager.getConnection(url,"root", "mysql123");
             Statement stmt = conn.createStatement()) {

            dropExisting(conn);
            createTables(conn);
            createStoredProcedures(conn);

            printCount(conn, "SELECT count(*) FROM names");

            callReadENames(conn);
            callReadNamesByLetter(conn, "Z");
            callMagicNumber(conn);
            callDoubleNumber(conn, 5);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void run(Connection conn, String sql) {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            int updatedRowsCount = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void printCount(Connection conn, String sql) {
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void callDoubleNumber(Connection conn, int input) {
        var sql = "{call double_number(?)}";
        try (var cs = conn.prepareCall(sql)) {
            cs.setInt(1, input);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt("num"));
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void callMagicNumber(Connection conn) {
        var sql = "{?= call magic_number(?) }";
        try (var cs = conn.prepareCall(sql)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt("num"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void callReadNamesByLetter(Connection conn, String input) {
        var sql = "{call read_names_by_letter(?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString("prefix", input);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void callReadENames(Connection conn) {
        String sql = "{call read_e_names()}";
        try (CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void dropExisting(Connection conn) throws SQLException {
        run(conn, "DROP PROCEDURE IF EXISTS read_e_names ");
        run(conn, "DROP PROCEDURE IF EXISTS read_names_by_letter ");
        run(conn, "DROP PROCEDURE IF EXISTS magic_number ");
        run(conn, "DROP PROCEDURE IF EXISTS double_number ");
        run(conn, "DROP TABLE IF EXISTS names ");
        run(conn, "DROP TABLE IF EXISTS exhibits ");
    }

    private static void createTables(Connection conn) throws SQLException {
        run(conn, """
                CREATE TABLE exhibits (
                  id INTEGER PRIMARY KEY,
                  name VARCHAR(255),
                  num_acres DECIMAL(4,1))""");

        run(conn, """
                CREATE TABLE names (
                   id INTEGER PRIMARY KEY,
                   species_id integer REFERENCES exhibits (id),
                   name VARCHAR(255))""");

        run(conn, "INSERT INTO exhibits VALUES (1, 'African Elephant', 7.5)");
        run(conn, "INSERT INTO exhibits VALUES (2, 'Zebra', 1.2)");

        run(conn, "INSERT INTO names VALUES (1, 1, 'Elsa')");
        run(conn, "INSERT INTO names VALUES (2, 2, 'Zelda')");
        run(conn, "INSERT INTO names VALUES (3, 1, 'Ester')");
        run(conn, "INSERT INTO names VALUES (4, 1, 'Eddie')");
        run(conn, "INSERT INTO names VALUES (5, 2, 'Zoe')");

        insertBatch(conn, 6, 1, "Elias", "Amy", "John", "Dana");
    }

    public static void insertBatch(Connection conn, int firstKey, int type, String... names) {
        var sql = "INSERT INTO names VALUES(?, ?, ?)";
        var nextIndex = firstKey;
        try (var ps = conn.prepareStatement(sql)) {
            ps.setInt(2, type);
            for(var name: names) {
                ps.setInt(1, nextIndex);
                ps.setString(3, name);
                ps.addBatch();
                nextIndex++;
            }
            int[] result = ps.executeBatch();
            System.out.println(Arrays.toString(result));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }

    private static void createStoredProcedures(Connection conn) {
        // Returns all rows in names table that have name beginning with e or E
        String noParams = """
                CREATE PROCEDURE read_e_names()
                  BEGIN
                  DECLARE result CURSOR FOR
                  SELECT * FROM names WHERE LOWER(name) LIKE 'e%';
                  OPEN result;
                  END""";

        // Returns all rows in names table that have name beginning with specified parameter (case insensitive)
        String inParam = """
                CREATE PROCEDURE read_names_by_letter(IN prefix VARCHAR(10))
                  BEGIN
                  DECLARE result CURSOR FOR
                  SELECT * FROM names WHERE LOWER(name) LIKE CONCAT(LOWER(prefix), '%');
                  OPEN result;
                  END""";

        // Multiplies parameter by two and returns that number
        String inOutParam = """
                CREATE PROCEDURE double_number(INOUT num INT) READS SQL DATA
                  BEGIN
                  SET num = num * 2;
                  END""";

        // returns number 42
        String outParam = """
                CREATE PROCEDURE magic_number(OUT num INT) READS SQL DATA
                  BEGIN
                  SET num = 42;
                  END""";

        run(conn, noParams);
        run(conn, inParam);
        run(conn, outParam);
        run(conn, inOutParam);
    }


}
