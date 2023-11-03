package dp_creational.prototype.shallowcloneexample;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // v. 0.1 - using clone()
        String sql = "SELECT * FROM movies WHERE title = ?";
        List<String> parameters = new ArrayList<>();
        parameters.add("Star Wars");
        parameters.add("Top Gun");
        Record record = new Record();

        Statement statement1 = new Statement(sql, parameters, record);
        Statement statement2 = statement1.clone();

        System.out.println(statement1.getSql());
        System.out.println(statement1.getParameters());
        System.out.println(statement1.getRecord());
        System.out.println("-----------------------------");
        System.out.println(statement2.getSql());
        System.out.println(statement2.getParameters());
        System.out.println(statement2.getRecord());

        // in the case of statement2 we can see that we've only made a reference copy (shallow clone)
        // (we are just using the second statement as a reference for the first statement)
    }
}

/*
In this example we would actually want an object copy, but instead we get only a reference copy
 */