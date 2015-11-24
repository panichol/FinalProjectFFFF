package gameTests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import gamePlay.*;

//This test is separate because it requires waiting. And no one wants to wait when running all their tests. 
public class GameTimerTests {
	@Test
	public void TimerTest() throws InterruptedException { //Needs the throw for the TimeUnit parts. 
		GameEngine game = new GameEngine();
		//Starting time
		int time = game.getTime();
		//Lets timer run for 2 seconds. 
		game.startTimer();
		TimeUnit.SECONDS.sleep(2);
		game.pauseTimer();
		//Tests that time is now smaller. 
		assertTrue(time > game.getTime());
		
		//Reset the timer and wait. Then make sure that the time left is still equal to the initial time. 
		game.resetTimer();
		TimeUnit.SECONDS.sleep(2);
		assertTrue(game.getTime() == time);
		
		//Start the timer. Let it run to completion plus extra. Ensure it is now zero
		game.startTimer();
		TimeUnit.SECONDS.sleep(game.getStartTime() + 2);
		assertEquals(game.getTime(),0);
	}
}