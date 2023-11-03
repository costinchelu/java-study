package sec19b_jdbcsqlite;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        try {
            Connection conn =
                    DriverManager.getConnection("jdbc:sqlite:E:\\GITHUB\\JAVA\\Udemy (Tim Buchalka)\\test1.db");

            // optional line to set autocommit to false. In this case we have to commit ourselves the
            // changes to database.
            // conn.setAutoCommit(false);

            // next we need to create a statement to execute some sql
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
//            statement.execute(
//                    "INSERT INTO contacts (name, phone, email) "
//                            + "VALUES('Joe', 456588, 'joe@email.com')");
//            statement.execute(
//                    "INSERT INTO contacts (name, phone, email) "
//                            + "VALUES('Jane', 562558, 'jane@email.com')");
//            statement.execute(
//                    "INSERT INTO contacts (name, phone, email) "
//                            + "VALUES('Fred', 522523, 'fred@email.com')");
//
//            statement.execute("UPDATE contacts SET phone = 558541 WHERE name = 'Jane'");
//
//            statement.execute("DELETE FROM contacts WHERE name = 'Fred'");

            statement.execute("SELECT * FROM contacts");

            ResultSet results = statement.getResultSet();
            while(results.next()) {
                System.out.println(
                        results.getString("name") + " " +
                                results.getInt("phone") + " " +
                                results.getString("email"));
            }
            results.close();
            /*
that's worked ok now every results set has a cursor now this cursor isn't the same as a database cursor we could
have had several results set objects and each one will have a cursor however
if you reuse a statement object to do a query than any results set associated with that statement object is closed

and a new one is created for the new query so if we want to work with several query results at the same time and it's
imperative to use a different statement instance for each query
reusing the statement instance was ok when we're just doing insertions updates and deletes because we weren't
using or checking the result.
We can reuse a statement object to query but only if we finished processing the results of one query before
we execute the next query.
A statement object ultimately can only ever have one active result set associated with it now when
the results set is created is cursor is positioned before the first record and so the first time we call results .

so the first time we call results.next, the cursor will be moved to the first record when we call it again the
cursor will be moved to the second record in the result set when there are no more records the next method will return
false and when we call one of the ResultSet get methods the values returned are those from the record at the ResultSet
cursors current position.
Note that ResultSet is a resource so we have to close it.
we have to close it before we close the statement
we're closing all our resources at the end of the try block
             */

            // a statement is associated with a connection. The correct closing order is:
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Database error! " + e.getMessage());
        }
    }
}

/*
It'a also working as try with resources. (In this case we have the advantage that the connection will close itself
whenever it's not used anymore)

try(Connection conn =
                    DriverManager.getConnection("jdbc:sqlite:E:\\GITHUB\\JAVA\\Udemy (Tim Buchalka)\\test1.db");
     Statement statement = conn.createStatement()) {
          statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
      } catch ...
 */