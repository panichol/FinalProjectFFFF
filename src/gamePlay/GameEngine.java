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
	
	public GameEngine() {
		player = new Player();
		questionsLeft = 10;
		playerAnswer = new Fraction();
		timeStart = 10;
		timeLeft = timeStart;
		timeRemaining = true;
		timer = new Timer(1000, new TimerListener());
	}
	
	public void loadQuestionFile(String fileName) throws BadFormatException{
		try{
			FileReader reader = new FileReader(fileName);
			Scanner in = new Scanner(reader);
			int numQuestions = Integer.parseInt(in.nextLine());
			Question q = new Question();
			Fraction f = new Fraction();
			char a1, a2;
			int countLines = 0;
			for (int i = 0; i < numQuestions; i++){
				if (in.hasNextLine()){
					String a = in.nextLine();
					countLines++;
					q.setQuestion(a);
					
					a = in.nextLine();
					countLines++;
					a1 = a.charAt(0);
					a2 = a.charAt(2);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setCorrectAnswer(f);

					a = in.nextLine();
					countLines++;
					a1 = a.charAt(0);
					a2 = a.charAt(2);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setFalseAnswer1(f);

					a = in.nextLine();
					countLines++;
					a1 = a.charAt(0);
					a2 = a.charAt(2);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setFalseAnswer2(f);

					a = in.nextLine();
					countLines++;
					a1 = a.charAt(0);
					a2 = a.charAt(2);
					f.setNumerator(a1);
					f.setDenominator(a2);
					q.setFalseAnswer3(f);
					if (countLines != 5){
						throw new BadFormatException("Incorrect number of lines in question.");
					}
					else{
						questions.add(q);
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
	//Timer variables
	private Timer timer;		//The timer object. Can pause, start, and stop. Stops when out of time. 
	private int timeLeft;			//The time left for the question.
	private int timeStart;			//The time that the timer will start at when reset.
	public boolean timeRemaining;	//True for has time, false if out of time.

	
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

	public Question getQuestion() {//TODO he questions list should be shuffled when it is loaded in
		Collections.rotate(questions, 1);
		return questions.get(1);
	}

	//------------TIMER FUNCTIONS----------------//
	//This is the listener that will activate upon timer completion
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
				stop();
			}
		}
	}

	//Start the timer from current time left 
	public void startTimer() {
		timer.start();
	}
	//Pauses the timer. Does not reset time left
	public void pauseTimer() {
		timer.stop();
	}
	//Reset timer to timeStart value
	public void resetTimer() {
		timeLeft = timeStart;
	}
	//Returns the time that you have left for the problem
	public int getTime() {
		return timeLeft;
	}
	//Stops the timer, resets timer, but does not start timer again
	public void stop() {
		timer.stop();
		timer.restart();
		timer.stop();
	}
	//Change the amount of time the player has
	public void changeStartTime(int time)
	{
		timeStart = time;
	}
	//Get the amount of time the player has
	public int getStartTime() {
		return timeStart;
	}
}
