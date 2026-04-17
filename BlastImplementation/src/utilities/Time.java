package utilities;

import java.time.Duration;
import java.time.Instant;


/**
 * The Time class represent a simple timer that tracks the elapsed time 
 * since its instantiation.
 * 
 * It provides formatted output of the elapsed time in hours, minutes
 * and seconds.
 */
public class Time implements Displayable {
	
	/** The moment this Time object was created. */
	private final Instant startTime;

	/** 
	 * Constructs a Time object initialized to the current time. 
	 */
	public Time() {
		this.startTime = Instant.now();
	}
	
	/** 
	 * Returns the start time of this timer.
	 * 
	 * @return the {@link Instant} when this object was created
	 */
	public Instant getStartTime() {
		return this.startTime;
	}

	/**
	 * Returns a formatted string representing the time elapsed since
	 * this object was created.
	 * 
	 * <p>
	 * Format:
	 * <ul>
	 * 	<li>hh:mm:ss if hours are present</li>
	 * <li>mm:ss if minutes are present</li>
	 * <li>ss if seconds are present</li>
	 * </ul>
	 * 
	 * @return a formatted string of elapsed time
	 */
	@Override
	public String display() {
		Duration elapsed = Duration.between(startTime, Instant.now());

		long hours = elapsed.toHours();
		long minutes = elapsed.toMinutesPart();
		long seconds = elapsed.toSecondsPart();

		String output;

		if (hours > 0) {
			output = String.format("Running for: %02d:%02d:%02d", hours, minutes, seconds);
		} else if (minutes > 0) {
			output = String.format("Running for: %02d:%02d", minutes, seconds);
		} else {
			output = String.format("Running for: %02d", seconds);
		}
		return output;
	}

	/** Returns a formatted string representing the time elapsed between 
	 * a given {@link Instant} and the current time.
	 * 
	 * <p><b>Note:</b> This method is intended for testing purposes.
	 * @param previousTime
	 * @return a formatted string of elapsed time
	 */
	public String display(Instant previousTime) {
		Duration elapsed = Duration.between(previousTime, Instant.now());

		long hours = elapsed.toHours();
		long minutes = elapsed.toMinutesPart();
		long seconds = elapsed.toSecondsPart();

		String output;

		if (hours > 0) {
			output = String.format("Running for: %02d:%02d:%02d", hours, minutes, seconds);
		} else if (minutes > 0) {
			output = String.format("Running for: %02d:%02d", minutes, seconds);
		} else {
			output = String.format("Running for: %02d", seconds);
		}
		return output;
	}
	
	/**
	 * Indicates whether the displayed time needs to be updated.
	 * 
	 * @return always {@code true}
	 */
	public boolean needsUpdate() {
		return true;
	}
}
