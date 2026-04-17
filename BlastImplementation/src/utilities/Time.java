package utilities;

import java.time.Duration;
import java.time.Instant;

public class Time implements Displayable {

	private final Instant startTime;

	// Initiate with startTime
	public Time() {
		this.startTime = Instant.now();
	}

	public Instant getStartTime() {
		return this.startTime;
	}

	// Returns the time elapsed since instantiation of the object
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

	// Returns the time elapsed between a given Instant object and the current time
	// This method is only used to test the display() method
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

	public boolean needsUpdate() {
		return true;
	}
}
