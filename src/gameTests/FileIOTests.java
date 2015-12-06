package gameTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import gamePlay.BadFormatException;
import gamePlay.Fraction;
import gamePlay.GameEngine;
import gamePlay.Question;

public class FileIOTests {
	GameEngine control;

	@Before
	public void startup(){
		control  = new GameEngine();
	}
	
	@Test
	public void loadQuestionsTest(){		//This function tests that the questions were read in from the file correctly
		try{ 
			control.loadQuestionFile("/data/input.txt");
			ArrayList<Question> questions = control.getQuestionsArray();
			
			Fraction correctAnswer = questions.get(0).getCorrectAnswer();
			String question = questions.get(0).getQuestion();
			assertTrue(question.equals("What is 3/4 plus 1/2 equal to?"));
			assertEquals(correctAnswer.getNumerator(), 5);
			assertEquals(correctAnswer.getDenominator(), 4);
			
			Fraction correctAnswerMiddle = questions.get(9).getCorrectAnswer();
			String questionMiddle = questions.get(9).getQuestion();
			System.out.println(questionMiddle);
			assertTrue(questionMiddle.equals("What is 3/2 divided by 4/5 equal to?"));
			assertEquals(correctAnswerMiddle.getNumerator(), 15);
			assertEquals(correctAnswerMiddle.getDenominator(), 8);
			
			Fraction correctAnswerFinal = questions.get(questions.size() - 1).getCorrectAnswer();
			String questionFinal = questions.get(questions.size() - 1).getQuestion();
			System.out.println(questionFinal);
			assertTrue(questionFinal.equals("What is 1/4 divided by 1/5 equal to?"));
			assertEquals(correctAnswerFinal.getNumerator(), 5);
			assertEquals(correctAnswerFinal.getDenominator(), 4);
			
		}
		catch (BadFormatException bfe) {
			fail(bfe.getMessage());
		}
	}
}
