package gameTests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import gamePlay.*;

//This test is seperate because it requires waiting. And no one wants to wait when running all their tests. 
public class GameTimerTests {
	public static boolean listenerRan = false; //For testing the listener.
	
	@Test				//Needs the throw for the TimeUnit parts. 
	public void Timer() throws InterruptedException {
		
		//Test listener that for testing. 
		class testListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				listenerRan = true;
			}
		}
		
		GameTimer timer = new GameTimer(new testListener());
		//Starting time
		long time = timer.getTime();
		//Lets timer run for 2 seconds. 
		timer.start();
		TimeUnit.SECONDS.sleep(2);
		timer.pause();
		//Tests that time is now smaller. 
		assertTrue(time > timer.getTime());
		
		//Reset the timer and wait. Then make sure that the time left is still equal to the initial time. 
		timer.reset();
		TimeUnit.SECONDS.sleep(2);
		assertTrue(timer.getTime() == time);
		
		//Start the timer. Let it run to completion plus extra. Ensure it is now zero
		//Then make sure it activated the listener.
		timer.start();
		TimeUnit.SECONDS.sleep(timer.timeStart + 2);
		assertEquals(timer.getTime(),0);
		assertTrue(listenerRan);
	}
}