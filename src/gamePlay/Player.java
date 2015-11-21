package gamePlay;

public class Player {
private int location;
private int livesRemaining;

	public Player() {
		location = 0;
		livesRemaining = 5;
	}

	public int getLocation() {
		return location;
	}

	public int getLivesRemaining() {
		return livesRemaining;
	}

}
