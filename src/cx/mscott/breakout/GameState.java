package cx.mscott.breakout;

public class GameState {
	private int score;
	private int lives;
	
	/**
	 * Constructor
	 */
	public GameState() {
		reset();
	}
	
	/**
	 * Reset game to initial state.	
	 */
	public void reset() {
		score = 0;
		lives = 3;
	}
	
	/**
	 * Get current score.
	 * @return player's current score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Get number of lives left.
	 * @return lives left.
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Increase player's score
	 * @param delta Increment amount.
	 */
	public void incrementScore(int delta) {
		score += delta;
	}
	
	/**
	 * Adjust player's lives.
	 * 
	 * Note that lives are bounded to range 0 <= lives < 10.
	 * 
	 * @param delta Number of lives to add or lose.
	 */
	public void adjustLives(int delta) {
		lives += delta;
		if (lives < 0)
			lives = 0;
		else if (lives > 9)
			lives = 9;
	}

	/**
	 * Is player dead?
	 * @return player death status
	 */
	public boolean isDead() {
		return (lives == 0);
	}
}
