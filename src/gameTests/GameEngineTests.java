package gameTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import gamePlay.BadFormatException;
import gamePlay.Fraction;
import gamePlay.GameEngine;
import gamePlay.Question;

public class GameEngineTests {

	@Test
	public void randomAnswerSelectionTest() throws BadFormatException{
		ArrayList<Question> questions = new ArrayList<Question>();
		Fraction answer = new Fraction(1, 2);
		Fraction false1 = new Fraction(3, 4);
		Fraction false2 = new Fraction(3, 4);
		Fraction false3 = new Fraction(3, 4);
		Question q1 = new Question("Is this question 1?",answer, false1, false2, false3);
		questions.add(q1);
		answer = new Fraction(1, 3);
		false1 = new Fraction(3, 4);
		false2 = new Fraction(3, 4);
		false3 = new Fraction(3, 4);
		Question q2 = new Question("Is this question 2?",answer, false1, false2, false3);

		questions.add(q2);
		answer = new Fraction(1, 4);
		false1 = new Fraction(3, 4);
		false2 = new Fraction(3, 4);
		false3 = new Fraction(3, 4);


		Question q3 = new Question("Is this question 3?",answer, false1, false2, false3);

		questions.add(q3);
		GameEngine control = new GameEngine();
		control.setQuestionsArray(questions);
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (int i = 0; i<1000;i++){
			Question temp = control.getQuestion();
			if (temp.equals(q1)){
				count1++;
			}
			if (temp.equals(q2)){
				count2++;
			}
			if (temp.equals(q3)){
				count3++;
			}
		}
		assertEquals(1000,count1+count2+count3);
		assertTrue(count1>200);
		assertTrue(count2>200);
		assertTrue(count3>200);
	}

}
