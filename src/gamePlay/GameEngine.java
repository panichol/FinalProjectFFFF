package gamePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {
	private int questionsLeft;
	private Player player;
	private Fraction playerFraction;
	private ArrayList<Question> questions;
	private Fraction playerAnswer;
	private GameTimer timer;
	
	public GameEngine() throws BadFormatException {
		player = new Player();
		questionsLeft = 10;
		playerAnswer = new Fraction();
		timer = new GameTimer(new TimerListener());
		loadQuestionFile("input.txt");
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
}
