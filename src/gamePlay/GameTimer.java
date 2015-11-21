package gamePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;			//Not that this is not the java.util.Timer! Very different beast! 

public class GameTimer {
	public long timeStart;			//The time that the timer will start at when reset.
	private long timeLeft;			//The time remaining in the timer. 
	public boolean timeRemaining;	//True for has time, false if out of time.
	private Timer timer;			//The timer
	
	//Initialize the timer
	public GameTimer(ActionListener timerListener) {
		
	}
	
	//Reset timer to timeStart value
	public void reset() {
		
	}
	
	//Start the timer from current time left 
	public void start() {
		
	}
	
	//Pauses the timer. Does not reset time left
	public void pause() {
		
	}
	
	//Stops the timer, resets timer, but does not start timer again
	public void stop() {
		
	}
	
	//Returns the time left
	public long getTime() {
		return timeLeft;
	}
}
