package cx.mscott.breakout;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest {

	@Test
	public void testGameState() {
		GameState gs = new GameState();
		
		assertEquals(3, gs.getLives());
		assertEquals(0, gs.getScore());
	}

	@Test
	public void testReset() {
		GameState gs = new GameState();

		gs.reset();
		assertEquals(3, gs.getLives());
		assertEquals(0, gs.getScore());
		
		gs.adjustLives(1);
		gs.incrementScore(100);
		gs.reset();
		assertEquals(3, gs.getLives());
		assertEquals(0, gs.getScore());
	}

	@Test
	public void testGetScore() {
		GameState gs = new GameState();
		gs.incrementScore(100);
		assertEquals(100, gs.getScore());
	}

	@Test
	public void testGetLives() {
		GameState gs = new GameState();
		gs.adjustLives(1);
		assertEquals(4, gs.getLives());
	}

	@Test
	public void testIncrementScore() {
		GameState gs = new GameState();
		gs.incrementScore(100);
		assertEquals(100, gs.getScore());
		gs.incrementScore(150);
		assertEquals(250, gs.getScore());
		gs.incrementScore(-50);
		assertEquals(200, gs.getScore());
	}

	@Test
	public void testAdjustLives() {
		GameState gs = new GameState();
		gs.adjustLives(1);
		assertEquals(4, gs.getLives());

		gs.adjustLives(2);
		assertEquals(6, gs.getLives());

		gs.adjustLives(3);
		assertEquals(9, gs.getLives());

		gs.adjustLives(1);
		assertEquals(9, gs.getLives());

		gs.adjustLives(-6);
		assertEquals(3, gs.getLives());

		gs.adjustLives(-4);
		assertEquals(0, gs.getLives());
	}

	@Test
	public void testIsDead() {
		GameState gs = new GameState();
		assertEquals(false, gs.isDead());

		gs.adjustLives(2);
		assertEquals(false, gs.isDead());

		gs.adjustLives(-4);
		assertEquals(1, gs.getLives());
		assertEquals(false, gs.isDead());

		gs.adjustLives(-1);
		assertEquals(0, gs.getLives());
		assertEquals(true, gs.isDead());
	}

}
