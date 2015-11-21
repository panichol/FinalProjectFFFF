package gamePlay;

import java.util.ArrayList;

public class GameEngine {
	private int questionsLeft;
	private Player player;
	private Fraction playerFraction;
	private ArrayList<Question> questions;
	private Fraction playerAnswer;
	
	
	public GameEngine() {
		player = new Player();
		questionsLeft = 10;
		playerAnswer = new Fraction();
	}
	
	public Fraction getPlayerAnswer() {
		return playerAnswer;
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

	public void setQuestionsArray(ArrayList<Question> questions) {
		this.questions = questions;
		
	}

	public Question getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}
}
