package tdd;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class FootballTeamTest {

   private final static int THREE_GAMES_WON = 3;

   private static final int ANY_NUMBER = 123;

   @DataProvider
   public Object[][] nbOfGamesWon() {
      return new Object[][] { { 0 }, { 1 }, { 2 } };
   }

   @DataProvider
   public Object[][] illegalNbOfGamesWon() {
      return new Object[][] { { -10 }, { -1 } };
   }

   @Test(dataProvider = "nbOfGamesWon")
   public void constructorShouldSetGamesWon(int nbOfGamesWon) {
      FootballTeam team = new FootballTeam(nbOfGamesWon);

      assertEquals(team.getGamesWon(), nbOfGamesWon,
            nbOfGamesWon + " games were passed to constructor, but " + team.getGamesWon() + " were returned");
   }

   @Test(dataProvider = "illegalNbOfGamesWon", expectedExceptions = IllegalArgumentException.class)
   public void shouldThrowExceptionForIllegalGamesNb(int illegalNbOfGames) {
      new FootballTeam(illegalNbOfGames);
   }

   public void shouldBePossibleToCompareTeams() {
      FootballTeam team = new FootballTeam(ANY_NUMBER);

      assertTrue(team instanceof Comparable, "FootballTeam should implement Comparable");
   }

   public void teamsWithMoreMatchesWonShouldBeGreater() {
      FootballTeam team_2 = new FootballTeam(2);
      FootballTeam team_3 = new FootballTeam(3);

      assertTrue(team_3.compareTo(team_2) > 0);
   }

   public void teamsWithLessMatchesWonShouldBeLesser() {
      FootballTeam team_2 = new FootballTeam(2);
      FootballTeam team_3 = new FootballTeam(3);

      assertTrue(team_2.compareTo(team_3) < 0, "team with " + team_2.getGamesWon()
            + " games won should be ranked after the team with " + team_3.getGamesWon() + " games won");
   }

   public void teamsWithSameNumberOfMatchesWonShouldBeEqual() {
      FootballTeam teamA = new FootballTeam(2);
      FootballTeam teamB = new FootballTeam(2);

      assertEquals(teamA.compareTo(teamB), 0, "both teams have won the same number of games: " + teamA.getGamesWon()
              + " vs. " + teamB.getGamesWon() + " and should be ranked equal");
   }
}
