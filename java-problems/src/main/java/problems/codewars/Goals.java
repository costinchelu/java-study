package problems.codewars;

/*
JAVA
Grasshopper Messi goals function
8 kyu

Messi is a soccer player with goals in three leagues:
LaLiga
Copa del Rey
Champions.

Complete the goals function to return his total goals (the sum) for all three leagues.
Note: the parameter for this function will always be a valid number.

Ex :
goals(5,10,2) == 5+10+2 = 17

*/


public class Goals {

    public static int goals(int laLigaGoals, int copaDelReyGoals, int championsLeagueGoals) {
        if(laLigaGoals >= 0 && copaDelReyGoals >= 0 && championsLeagueGoals >= 0) {
            return laLigaGoals + copaDelReyGoals + championsLeagueGoals;
        }
        else return 0;
    }
}