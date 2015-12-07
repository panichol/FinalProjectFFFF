package gamePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameEngine {
	private int questionsLeft;
	private Player player;
	private Fraction playerFraction;
	private ArrayList<Question> questions;
	private Fraction playerAnswer;
	private GameGUI gui;
	public static boolean firstLevel = true;

	//Timer variables
	private static Timer timer;		//The timer object. Can pause, start, and stop. Stops when out of time. 
	private int timeLeft;			//The time left for the question.
	private int startTime;			//The time that the timer will start at when reset.
	public boolean timeRemaining;	//True for has time, false if out of time.

	public GameEngine(String boardLoc1, String boardLoc2, boolean firstLevel) {
		player = new Player();
		questionsLeft = 10;
		playerAnswer = new Fraction();
		startTime = 180;//TODO set back to 180
		timeLeft = startTime;
		timeRemaining = true;
		timer = new Timer(1000, new TimerListener());
		gui = new GameGUI(player, boardLoc1, boardLoc2, firstLevel);
		questions = new ArrayList<Question>();

		//gui.updatePlayerRock(10);
	}

	public void loadQuestionFile(String fileName) throws BadFormatException{
		try{
			InputStream reader = getClass().getResourceAsStream(fileName);
			Scanner in = new Scanner(reader);

			int numQuestions = 20;
			in.nextLine();

			int a1;
			int a2;
			int countLines = 0;
			for (int i = 0; i < numQuestions; i++){
				Question q = new Question();
				Fraction f1 = new Fraction();
				Fraction f2 = new Fraction();
				Fraction f3 = new Fraction();
				Fraction f4 = new Fraction();
				if (in.hasNextLine()){				
					countLines = 0;
					String a = in.nextLine();
					String[] divisionTest = a.split("~");//no longer necessary
					//and fix it with this if statement
					if (divisionTest.length == 1)
						q.setQuestion(a);
					else if (divisionTest.length == 2){
						q.setQuestion(divisionTest[0] + "�" + divisionTest[1]);
					}
					countLines++;

					a = in.nextLine();
					String[] input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f1.setNumerator(a1);
					f1.setDenominator(a2);
					q.setCorrectAnswer(f1);

					a = in.nextLine();
					input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f2.setNumerator(a1);
					f2.setDenominator(a2);
					q.setFalseAnswer1(f2);

					a = in.nextLine();
					input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f3.setNumerator(a1);
					f3.setDenominator(a2);
					q.setFalseAnswer2(f3);

					a = in.nextLine();
					input = a.split("/");
					countLines++;
					a1 = Integer.parseInt(input[0]);
					a2 = Integer.parseInt(input[1]);
					f4.setNumerator(a1);
					f4.setDenominator(a2);
					q.setFalseAnswer3(f4);

					if (countLines != 5){
						throw new BadFormatException("Incorrect number of lines in question. was " +countLines + " should be 5");
					}
					else{
						q.sequenceAnswers();
						questions.add(q);
					}
				}
			}
		}
		catch (BadFormatException bfe) {
			BadFormatException ex = new BadFormatException(bfe.getMessage());
			throw ex;
		}
		catch (Exception fnfe) {
			System.out.println("File not found.");
		}
	}


	public Fraction getPlayerAnswerTrue() {
		playerAnswer = new Fraction(1,2);
		return playerAnswer;
	}
	public Fraction getPlayerAnswerFalse() {
		playerAnswer = new Fraction (3,2);
		return playerAnswer;
	}

	/*	public boolean askQuestion(){//asks the player a question , and returns whether or not they got it right
		return GameEngine.gui.updateQuestion(getQuestion());//return whether or not the player got the question right
	}*/

	public boolean askQuestion(){//asks the player a question , and returns whether or not they got it right
		gui.updateQuestion(getQuestionsArray());
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
	public void printQuestions(){
		for (Question q : questions){
			System.out.println(q.sequencedAnswers.get(0).getDenominator());
		}
	}
	public void setQuestionsArray(ArrayList<Question> questions) {
		this.questions = questions;

	}

	public Question getQuestion() {
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
			if(timeLeft > 0)
			{
				timeLeft--;
				gui.updateTime(timeLeft);
				gui.repaint();
			}
			else {

				player.loseAllLives();
				gui.updateStatus();
				//resetTimer();
			}
		}
	}

	/**
	 * Start the timer from current time left 
	 */
	public static void startTimer() {
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

	public static void main(String args[]) throws BadFormatException {
		GameEngine game = new GameEngine("/images/BoardWave1.png","/images/BoardWave2.png",true);
		game.loadQuestionFile("/data/input.txt");
		JOptionPane.showMessageDialog(game.gui, "Welcome to Fraction Flash Flood - please press OK to continue"
				, "Welcome", JOptionPane.INFORMATION_MESSAGE);
		game.gui.setVisible(true);
		game.gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gui.updateStatus();

		GameEngine.startTimer();
		if (game.player.getLivesRemaining() > 0){
			Question q = game.getQuestion();
			boolean correct = game.askQuestion();

			//GameEngine.gui.updateQuestionField(game.getQuestion());
			game.gui.setVisible(true);
		}
		//game = new GameEngine();

		while (firstLevel) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		game.gui.setVisible(false);
		game = null;
		System.gc();

		game = new GameEngine("/images/BoardLava1.png","/images/BoardLava2.png",false);
		game.loadQuestionFile("/data/input2.txt");
		game.gui.setVisible(true);
		//game.gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gui.updateStatus();

		GameEngine.startTimer();
		if (game.player.getLivesRemaining() > 0){
			Question q = game.getQuestion();
			boolean correct = game.askQuestion();

			//GameEngine.gui.updateQuestionField(game.getQuestion());
			game.gui.setVisible(true);
		}
		//game = new GameEngine();		
	}
}
