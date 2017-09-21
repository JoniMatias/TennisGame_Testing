import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Ignore
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EachPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	
	@Test
	public void testTennisGame_Player1Wins4Points() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Win score incorrect", "player1 wins", score);
		
	}
	
	@Test
	public void testTennisGame_Player2Wins4Points() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Win score incorrect", "player2 wins", score);
		
	}
	
	@Test
	public void testTennisGame_ThreePointDeuce() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Three point deuce is not correct", "deuce", score);
		
	}
	
	@Test
	public void testTennisGame_StartingScores() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		String score = game.getScore();
		
		assertEquals("Starting scores are not correct", "love - love", score);
		
	}
	
	@Test
	public void testTennisGame_Player1Advantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Player1 advantage problem", "player1 has advantage", score);
	}
	
	@Test
	public void testTennisGame_Player2Advantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Player2 advantage problem", "player2 has advantage", score);
	}
	
	@Test
	public void testTennisGame_Player1WinsAfterAdvantage() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Player1 win problem", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2WinsAfterAdvantage() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Player2 win problem", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_alternativeScoringPlayer1Win() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Player1 alternating win problem", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_1pointScores() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Scores wrong for 1 point each", "15 - 15", score);
	}
	
	@Test
	public void testTennisGame_2pointScores() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Scores wrong for 2 point each", "30 - 30", score);
	}
	
	@Test
	public void testTennisGame_3_2Scores() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Scores wrong for 40 - 30", "40 - 30", score);
	}
	
	@Test
	public void testTennisGame_2_3Scores() throws TennisGameException {
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Scores wrong for 30 - 40", "30 - 40", score);
	}
}
