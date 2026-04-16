package utilities;

import java.time.LocalDate;

public class Date {
	
	private int day;
	private int month;
	private int year;
		
		
	public Date() {
		LocalDate today = LocalDate.now();
		this.day = today.getDayOfMonth();
		this.month = today.getMonthValue();
		this.year = today.getYear();
	}
			
	public String display() {
		String output = String.format("%02d/%02d/%04d", day, month, year);
		return output;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
}
