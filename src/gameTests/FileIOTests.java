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
	
	@Before
	public void startup(){
		
	}

	@Test
	public void loadBitMapTest() {		//TODO make a function to test the reading in of the background 
		fail("Not yet implemented");
	}
	
	@Test
	public void loadQuestionsTest(){		//This function tests that the questions were read in from the file correctly
		try{ 
			GameEngine control = new GameEngine();
			control.loadQuestionFile("input.txt");
			ArrayList<Question> questions = control.getQuestionsArray();
			
			Fraction f1 = new Fraction(5,4);
			Fraction f2 = new Fraction(3,2);
			Fraction f3 = new Fraction(4,4);
			Fraction f4 = new Fraction(10,8);
			Question q1 = new Question("What is 3/4 + 1/2 equal to?",f1,f2,f3,f4);
			assertTrue(q1.equals(questions.get(0)));
			
			f1 = new Fraction(5,12);
			f2 = new Fraction(8,12);
			f3 = new Fraction(1,3);
			f4 = new Fraction(7,12);
			Question q5 = new Question("What is 3/4 - 1/3 equal to?",f1,f2,f3,f4);
			assertTrue(q5.equals(questions.get(4)));
			
			f1 = new Fraction(15,8);
			f2 = new Fraction(12,10);
			f3 = new Fraction(6,5);
			f4 = new Fraction(8,15);
			Question q10 = new Question("What is 3/2 ÷ 4/5 equal to?",f1,f2,f3,f4);
			assertTrue(q10.equals(questions.get(9)));
		}
		catch (BadFormatException bfe) {
			fail(bfe.getMessage());
		}
	}
}
