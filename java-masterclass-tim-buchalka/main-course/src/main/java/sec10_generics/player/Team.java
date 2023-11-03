package sec10_generics.player;

import java.util.ArrayList;

//T extends any class that extends from player or subclass of player. Player will be upper bound of T
//we can use multiple bounds using a class and multiple interfaces or just interfaces
//syntax will be            public class Team<T extends Player & Coach & Manager>

//using comparable: Comparable<Team<T>> means that not only we will compare teams, but also teams of the same sport
//if we wrote Comparable<Team> we could only compare Player type objects, so no special comparison
//between teams of the same sport

public class Team<T extends Player> implements Comparable<Team<T>> {

    private String name;
    private ArrayList<T> members = new ArrayList<>();
    int played = 0;
    int won = 0;
    int lost = 0;
    int tied = 0;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    //in this case addPlayer will accept only classes extended from player
    public boolean addPlayer(T player) {
        if(members.contains(player)) {
            System.out.println( player.getName() + " is on this team");
            return false;
        }
        else {
            members.add(player);
            System.out.println( player.getName() + " picked for team " + this.name);
            return true;
        }
    }

    public int numPlayers() {
        return this.members.size();
    }

    //will accept only teams from the same class (FootballPlayer) Team<T>
    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        if(ourScore > theirScore) {
            won++;
        }
        else if(ourScore == theirScore) {
            tied++;
        }
        else {
            lost++;
        }

        played++;
        //saving results in opponent's team as well
        if(opponent != null) {
            opponent.matchResult(null, theirScore, ourScore);
        }
    }

    public int ranking() {
        return (won * 2) + tied;
        //one point for o tie and 2 for a win
    }

    public void printResults() {
        System.out.println("Team " + this.name + " results after " + this.played + " games:");
        System.out.println("\tWON\t\tLOST\tTIE");
        System.out.println("\t" + this.won + "\t\t" + this.lost + "\t\t" + this.tied);
        System.out.println("Team has " + ranking() + " points");
    }

    @Override
    public int compareTo(Team<T> team) {
        if(this.ranking() > team.ranking()) {
            return -1;
        }
        else if(this.ranking() < team.ranking()) {
            return 1;
        }
        else {
            return 0;
        }
    }


}
