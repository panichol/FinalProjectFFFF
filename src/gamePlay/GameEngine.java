package gamePlay;

import java.util.ArrayList;

public class GameEngine {
	private int questionsLeft;
	private Player player;
	private Fraction playerFraction;
	private ArrayList<Question> question;
	
	public GameEngine() {
		player = new Player();
		questionsLeft = 10;
	}
	
	public boolean askQuestion(){//asks the player a question , and returns whether or not they got it right
		
		return false;//return whether or not the player got the question right
	}

	public int getQuestionsLeft() {
		return questionsLeft;
	}

	public Player getPlayer() {
		return player;
	}
}
