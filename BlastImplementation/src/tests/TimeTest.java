package tests;

import java.time.Instant;

import junit.framework.TestCase;
import utilities.Time;

public class TimeTest extends TestCase {
	
	
	//Test if output format is correct
	public void testElapsedTimeFormat() {
		Time timer = new Time();
		Instant startTime = timer.getStartTime();
		String result = timer.getElapsedTime(startTime);
			
			
		assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}"));	
	}
	
	//Test if output changes over time
	public void testElapsedTimeIncreases() throws InterruptedException {
		Time timer = new Time();
		Instant startTime = timer.getStartTime();
		
		String first = timer.getElapsedTime(startTime);
		Thread.sleep(2000);
		String second = timer.getElapsedTime(startTime);
		assertFalse(first.equals(second));
	}
}
