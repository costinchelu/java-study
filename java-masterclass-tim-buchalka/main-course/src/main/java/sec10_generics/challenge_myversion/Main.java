package sec10_generics.challenge_myversion;

// Create a generic class to implement a league table for a sport.
// The class should allow teams to be added to the list, and store
// a list of teams that belong to the league.
//
// Your class should have a method to print out the teams in order,
// with the team at the top of the league printed first.
//
// Only teams of the same type should be added to any particular
// instance of the league class - the program should fail to compile
// if an attempt is made to add an incompatible team.

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Teams<Football>> footballTop = new ArrayList<>();
        ArrayList<Teams<Basketball>> basketballTop = new ArrayList<>();

        Teams<Basketball> b1 = new Teams<>("Team basketball 1");
        Teams<Football> f1 = new Teams<>("Football team A");
        Teams<Football> f2 = new Teams<>("Football team B");
        Teams<Football> f3 = new Teams<>("Football team C");

        footballTop.add(f1);
        footballTop.add(f2);
        footballTop.add(f3);
        //footballTop.add(b1);

        f1.match(f2, 3, 2);
        f1.match(f3, 3, 3);
        f2.match(f1, 0, 2);
        f2.match(f3, 2, 2);
        f3.match(f1, 2, 1);
        f3.match(f2, 3, 2);

        Collections.sort(footballTop);
        for(Teams t : footballTop) {
            t.printResults();
        }
    }
}
