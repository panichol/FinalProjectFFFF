package gameTests;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

import gamePlay.Fraction;
import gamePlay.GameEngine;
import gamePlay.Question;

import org.junit.BeforeClass;

public class QuestionAnswerTests extends TestCase {
	GameEngine newGame;
	
	@BeforeClass
	public void setUp() {
		newGame = new GameEngine();
	}
	
	@Test
	public void answerHandling(){
		
		
		Fraction answer = new Fraction(1, 2);
		Fraction false1 = new Fraction(3, 4);
		Fraction false2 = new Fraction(3, 4);
		Fraction false3 = new Fraction(3, 4);
		Question q1 = new Question(answer, false1, false2, false3);
				
		assertTrue (q1.isCorrect(newGame.getPlayerAnswer()));		//checks that if user selects answer 
		assertFalse(q1.isCorrect(newGame.getPlayerAnswer()));
		
		
	}
	
}
