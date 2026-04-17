package tests;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import junit.framework.TestCase;
import utilities.Time;

public class TimeTest extends TestCase {

	// Test if output format is correct
	public void testElapsedTimeFormat() {
		Time timer = new Time();
		Instant startTime = timer.getStartTime();
		String result = timer.display(startTime);
		assertTrue(result.matches("Running for: \\d{2}"));

		Instant timeEarlier = Instant.now().minus(5, ChronoUnit.MINUTES);
		String result2 = timer.display(timeEarlier);
		assertTrue(result2.matches("Running for: \\d{2}:\\d{2}"));

		Instant timeEarliest = Instant.now().minus(5, ChronoUnit.HOURS);
		String result3 = timer.display(timeEarliest);
		assertTrue(result3.matches("Running for: \\d{2}:\\d{2}:\\d{2}"));
	}

	// Test if output changes over time
	public void testElapsedTimeIncreases() throws InterruptedException {
		Time timer = new Time();
		Instant startTime = timer.getStartTime();

		String first = timer.display(startTime);
		Thread.sleep(2000);
		String second = timer.display(startTime);
		assertFalse(first.equals(second));
	}
}
