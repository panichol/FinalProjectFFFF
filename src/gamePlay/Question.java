package gamePlay;

public class Question {
	Fraction correctAnswer;
	Fraction falseAnswer1;
	Fraction falseAnswer2;
	Fraction falseAnswer3;
	
	public Question(){
		correctAnswer = new Fraction();
		falseAnswer1 = new Fraction();
		falseAnswer2 = new Fraction();
		falseAnswer3  = new Fraction();
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
	
}
