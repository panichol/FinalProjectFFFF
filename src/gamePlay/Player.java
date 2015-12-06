package gamePlay;

public class Player {
private int location;
private int livesRemaining;
	
	/**
	 * Default initializer. 
	 * Places location at 0 and lives to 5.
	 */
	public Player() {
		location = 0;
		livesRemaining = 5;
	}
	
	/**
	 * Get the location the player is at.
	 * 
	 * @return the integer location of the player.
	 */
	public int getLocation() {
		return location;
	}
	
	/**
	 * Get the lives the player has.
	 * 
	 * @return the lives remaining. 
	 */
	public int getLivesRemaining() {
		return livesRemaining;
	}
	
	/**
	 * Makes the player lose a life. 
	 * 
	 * @return true if still has lives remaining. False if lives == 0.
	 */
	//public boolean loseLife() {
	public void loseLife() {
		if(livesRemaining == 0) {
			System.out.println("No lives remain.");
			return;
			//return false;
		}
		livesRemaining--;
		//System.out.println("lives remaining " + livesRemaining);
		//return true;
	}

	public void loseAllLives() {
		livesRemaining = 0;
	}
	
}
