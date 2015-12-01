package gamePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

import javax.swing.Timer;

public class GameEngine {
	private int questionsLeft;
	private Player player;
	private Fraction playerFraction;
	private ArrayList<Question> questions;
	private Fraction playerAnswer;
	private GameGUI gui;
	
	//Timer variables
	private Timer timer;		//The timer object. Can pause, start, and stop. Stops when out of time. 
	private int timeLeft;			//The time left for the question.
	private int startTime;			//The time that the timer will start at when reset.
	public boolean timeRemaining;	//True for has time, false if out of time.

	public GameEngine() {
		player = new Player();
		questionsLeft = 10;
		playerAnswer = new Fraction();
		startTime = 10;
		timeLeft = startTime;
		timeRemaining = true;
		timer = new Timer(1000, new TimerListener());
		gui = new GameGUI();
		questions = new ArrayList<Question>();
	}
	
	public void loadQuestionFile(String fileName) throws BadFormatException{
		try{
			FileReader reader = new FileReader(fileName);
			Scanner in = new Scanner(reader);
			int numQuestions = Integer.parseInt(in.nextLine());			//TODO MAKE WORK
			
			//int numQuestions = Integer.parseInt("10");
			in.nextLine();
			Question q = new Question();
			Fraction f = new Fraction();
			int a1;
			int a2;
			int countLines = 0;
			for (int i = 0; i < numQuestions; i++){
				if (in.hasNextLine()){				//TODO Refactor
					countLines = 0;
					String a = in.nextLine();
					countLines++;
					q.setQuestion(a);
					
					a = in.nextLine();
					String[] input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setCorrectAnswer(f);

					a = in.nextLine();
					input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setFalseAnswer1(f);

					a = in.nextLine();
					input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setFalseAnswer2(f);

					a = in.nextLine();
					input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setFalseAnswer3(f);
					
					if (countLines != 5){
						throw new BadFormatException("Incorrect number of lines in question. was " +countLines + " should be 5");
					}
					else{
						questions.add(new Question(q));
					}
				}
			}
		}
		catch (FileNotFoundException fnfe) {
				System.out.println("File not found.");
		}
		catch (BadFormatException bfe) {
			BadFormatException ex = new BadFormatException(bfe.getMessage());
			throw ex;
		}
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
	
	public ArrayList<Question> getQuestionsArray(){
		return questions;
	}

	public void setQuestionsArray(ArrayList<Question> questions) {
		this.questions = questions;

	}

	public Question getQuestion() {//TODO he questions list should be shuffled when it is loaded in
		Collections.rotate(questions, 1);
		return questions.get(1);
	}

	//------------TIMER FUNCTIONS----------------//
	/**
	 * This is the listener that will activate upon timer completion
	 * Not that the actual javax.swing.Timer is not a timer in the general sense. 
	 * What it does is it calls this action listener ever second. So it updates timeLeft which is the "timer" in the general sense. 
	 */
	public class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//http://stackoverflow.com/questions/9721066/how-to-display-java-timer-on-a-separate-j-frame-form-label
			if(timeLeft > 0)
			{
				timeLeft--;
				//TODO add the update GUI timer here.
			}
			else {
				//TODO what happens when the time is up here.
				resetTimer();
			}
		}
	}

	/**
	 * Start the timer from current time left 
	 */
	public void startTimer() {
		timer.start();
	}
	/**
	 * Pauses the timer. Does not reset time left
	 */
	public void pauseTimer() {
		timer.stop();
	}
	/**
	 * Reset timer to timeStart value
	 */
	public void resetTimer() {
		timeLeft = startTime;
	}
	/**
	 * Returns the time that you have left for the problem
	 * 
	 * @return time left to finish question.
	 */
	public int getTime() {
		return timeLeft;
	}
	/**
	 * Stops the timer, resets timer, but does not start timer again
	 */
	public void stop() {
		timer.stop();
		timer.restart();
		timer.stop();
	}
	/**
	 * Change the amount of time the player has
	 * 
	 * @param time Change the starting time to time.
	 */
	public void changeStartTime(int time)
	{
		startTime = time;
	}
	/**
	 * Get the amount of time the player has
	 * 
	 * @return timeStart
	 */
	public int getStartTime() {
		return startTime;
	}
	
	public static void main(String args[]) {
		GameEngine game = new GameEngine();
		game.gui.setVisible(true);
	}
}
