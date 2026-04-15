package tests;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import junit.framework.TestCase;
import utilities.Time;

public class TimeTest extends TestCase {
	
	
	//Test if output format is correct
	public void testElapsedTimeFormat() {
		Time timer = new Time();
		Instant startTime = timer.getStartTime();
		String result = timer.getElapsedTime(startTime);	
		assertTrue(result.matches("Running for: \\d{2}"));
		
		Instant timeEarlier = Instant.now().minus(5, ChronoUnit.MINUTES);
		String result2 = timer.getElapsedTime(timeEarlier);
		assertTrue(result2.matches("Running for: \\d{2}:\\d{2}"));
		
		Instant timeEarliest = Instant.now().minus(5, ChronoUnit.HOURS);
		String result3 = timer.getElapsedTime(timeEarliest); 
		assertTrue(result3.matches("Running for: \\d{2}:\\d{2}:\\d{2}"));
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
