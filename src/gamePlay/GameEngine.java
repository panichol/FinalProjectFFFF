package gamePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameEngine {
	private int questionsLeft;
	private Player player;
	private Fraction playerFraction;
	private ArrayList<Question> questions;
	private Fraction playerAnswer;
	private GameTimer timer;
	private GameGUI gui;
	
	public GameEngine() {
		player = new Player();
		questionsLeft = 10;
		playerAnswer = new Fraction();
		timer = new GameTimer(new TimerListener());
	}
	
	public Fraction getPlayerAnswer() {
		return playerAnswer;
	}

	public boolean askQuestion(){//asks the player a question , and returns whether or not they got it right

		return false;//return whether or not the player got the question right
	}
	
	//This is the listener that will activate upon timer completion
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//http://stackoverflow.com/questions/9721066/how-to-display-java-timer-on-a-separate-j-frame-form-label
		}
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
	
	public void main(String args[]) {
		
	}
}
