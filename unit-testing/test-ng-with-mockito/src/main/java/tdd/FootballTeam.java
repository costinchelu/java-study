package tdd;

public class FootballTeam implements Comparable<FootballTeam> {

   private int gamesWon;

   public FootballTeam(int gamesWon) {
      if (gamesWon < 0) {
         throw new IllegalArgumentException("Negative number of games won");
      }
      this.gamesWon = gamesWon;
   }

   public int getGamesWon() {
      if (gamesWon < 0) {
         throw new IllegalArgumentException("Negative number of games won");
      }
      return gamesWon;
   }

   @Override
   public int compareTo(FootballTeam otherTeam) {
      return gamesWon - otherTeam.getGamesWon();
   }
}
