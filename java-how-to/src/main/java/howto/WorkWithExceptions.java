package howto;

import java.sql.SQLException;

public class WorkWithExceptions {

    public static void main(String[] args) throws NoSuchMethodException, SQLException {

        MainClass m = new MainClass();
        m.foo2();
    }
}

class MainClass {

    public void foo() throws NoSuchMethodException, SQLException {
        //ANSWER:
        throw new SQLException("SQL TYPE");
    }

    public void foo2() {
        try {
            foo();
        } catch (NoSuchMethodException | SQLException e) {
            System.out.println("Exception was thrown: " + e.getMessage());
        }
    }
}
