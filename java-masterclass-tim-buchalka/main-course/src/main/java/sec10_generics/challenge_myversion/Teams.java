package sec10_generics.challenge_myversion;

public class Teams<T extends Sport> implements Comparable<Teams<T>> {

    private String teamName;
    int matchesPlayed = 0;
    int matchesWon = 0;
    int matchesLost = 0;
    int matchesTie = 0;

    public Teams(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void match(Teams<T> opponentTeam, int hostScore, int visitorScore) {
        matchesPlayed++;
        if(hostScore > visitorScore) {
            matchesWon++;
        }
        else if(hostScore < visitorScore) {
            matchesLost++;
        }
        else {
            matchesTie++;
        }
        if(opponentTeam != null) {
            opponentTeam.match(null, visitorScore, hostScore);
        }
    }

    public int rankingCalculation() {
        return (this.matchesWon * 2) + matchesTie;
    }

    public void printResults() {
        System.out.println("Team " + this.teamName + " results after " + this.matchesPlayed + " games:");
        System.out.println("\tWON\t\tLOST\tTIE");
        System.out.println("\t" + this.matchesWon + "\t\t" + this.matchesLost + "\t\t" + this.matchesTie);
        System.out.println("Team has " + rankingCalculation() + " points");
    }

    @Override
    public int compareTo(Teams<T> otherTeam) {
        if(this.rankingCalculation() > otherTeam.rankingCalculation()) {
            return -1;
        }
        else if(this.rankingCalculation() < otherTeam.rankingCalculation()) {
            return 1;
        }
        else return 0;
    }
}
