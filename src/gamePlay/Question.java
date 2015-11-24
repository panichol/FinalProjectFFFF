package gamePlay;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
	Fraction correctAnswer;
	Fraction falseAnswer1;
	Fraction falseAnswer2;
	Fraction falseAnswer3;
	Fraction[] sequencedAnswers;
	public Question(){
		correctAnswer = new Fraction();
		falseAnswer1 = new Fraction();
		falseAnswer2 = new Fraction();
		falseAnswer3  = new Fraction();
		sequencedAnswers = new Fraction[]{correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3};
	}
	public Question(Fraction correctAnswer,	Fraction falseAnswer1,	Fraction falseAnswer2,Fraction falseAnswer3){
		this.correctAnswer = correctAnswer;
		this.falseAnswer1 = falseAnswer1;
		this.falseAnswer2 = falseAnswer2;
		this.falseAnswer3  = falseAnswer3;
	}
	
	public void loadQuestionFile(String fileName){
		FileReader reader;
		try {
			reader = new FileReader(fileName);
			Scanner in = new Scanner(reader);
			int numQuestions = Integer.parseInt(in.nextLine());
			for (int i = 0; i < numQuestions; i++){
				if (in.hasNextLine()){
					String a = in.nextLine();
					
					a = in.nextLine();
					a = in.nextLine();
					a = in.nextLine();
					a = in.nextLine();

				}
				else{
					//throw exception
					System.out.println("error reading input file");
				}
			}
				//first line is string question
				
				if(test.length != 3)
				{
					throw new BadConfigFormatException("Improper format for key, Incorrect number of elements found on a line");
				}
				rooms.put(test[0].charAt(0), test[1]);
				if(!test[2].equals("Card") && !test[2].equals("Other"))
				{
					throw new BadConfigFormatException("Improper type for a room");
				}
			}
		} catch (FileNotFoundException e) {
			throw new BadConfigFormatException(e.getMessage());
		}
	}
	public Fraction getCorrectAnswer() {
		return correctAnswer;
	}
	public boolean isCorrect(Fraction input){
		return input == correctAnswer;
	}
	public Fraction[] orderAnswers(){
//		Boolean[] otherList = new Boolean[] {false, false, false, false};
		for (int i = 0; i < 4; i++){
			int rand = (int) (Math.random() % 4);

		}
		
		return sequencedAnswers;
	}
}
