package gameTests;
import org.junit.BeforeClass;
import org.junit.Test;

import gamePlay.GameEngine;
import junit.framework.TestCase;

public class PlayerGameStateTests extends TestCase {
private static GameEngine engine;
private final int TEST_RUNS=200;
	
	@BeforeClass
	public void setUp() {
		engine = new GameEngine();
	}
	
	@Test
	public void testQuestionResult(){
		for (int i=0; i < TEST_RUNS; i++) {
			int preLives = engine.getPlayer().getLocation();
			int preQuestions = engine.getQuestionsLeft();
			if (engine.askQuestion()) {
				assertEquals(preLives, engine.getPlayer().getLivesRemaining());
				assertEquals(preQuestions - 1, engine.getQuestionsLeft());
			}
			else {
				assertEquals(preLives - 1, engine.getPlayer().getLivesRemaining());
				assertEquals(preQuestions - 1, engine.getQuestionsLeft());
			}
		
		}
	}
	
}
