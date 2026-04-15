package tests;

import junit.framework.TestCase;
import utilities.Time;

public class TimeTest extends TestCase {
	
	
	//Test if output format is correct
	public void testElapsedTimeFormat() {
		Time timer = new Time();
		String result = timer.getElapsedTime();
			
			
		assertTrue(result.matches("\\d{2}:\\d{2}:\\d{2}"));	
	}
	
	//Test if output changes over time
	public void testElapsedTimeIncreases() throws InterruptedException {
		Time timer = new Time();
		
		String first = timer.getElapsedTime();
		Thread.sleep(2000);
		String second = timer.getElapsedTime();
		assertFalse(first.equals(second));
	}
}
