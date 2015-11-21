package gamePlay;

import java.util.ArrayList;

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
