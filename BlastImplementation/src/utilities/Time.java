package utilities;
import java.time.Duration;
import java.time.Instant;

public class Time {
	
	private final Instant startTime;
	
	//Initiate with startTime
	public Time() {
		this.startTime = Instant.now();
	}
	
	//Returns the time elapsed since instantiation of the object
	public String getElapsedTime() {
		Duration elapsed = Duration.between(this.startTime, Instant.now());
		
		long hours = elapsed.toHours();
		long minutes = elapsed.toMinutesPart();
		long seconds = elapsed.toSecondsPart();
		
		String output = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
		return output;
	}
}
