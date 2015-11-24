package gamePlay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
	String question;
	Fraction correctAnswer;
	Fraction falseAnswer1;
	Fraction falseAnswer2;
	Fraction falseAnswer3;
	ArrayList<Fraction> sequencedAnswers;
	public Question(){
		correctAnswer = new Fraction();
		falseAnswer1 = new Fraction();
		falseAnswer2 = new Fraction();
		falseAnswer3  = new Fraction();
		sequencedAnswers = new ArrayList<Fraction>();
		sequencedAnswers.add(correctAnswer);
		sequencedAnswers.add(falseAnswer1);
		sequencedAnswers.add(falseAnswer2);
		sequencedAnswers.add(falseAnswer3);
		
	}
	public Question(String question, Fraction correctAnswer,Fraction falseAnswer1,Fraction falseAnswer2,Fraction falseAnswer3){
		this.question = question;
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
	public ArrayList<Fraction> orderAnswers(){
		Collections.shuffle(sequencedAnswers);
		return sequencedAnswers;
	}
	public boolean equals(Question other){//returns true if the question and its answer are the same
		return question.equals(other.question)&&correctAnswer.equals(other.correctAnswer);
		
	}
}
