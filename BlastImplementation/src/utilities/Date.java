package utilities;

import java.time.LocalDate;

import interfaces.Displayable;

/**
 * The Date class represent a simple calendar date (day, month, year)
 * based on the current system date at the time of object creation.
 * 
 * It provides a formatted display of the date and access to individual date 
 * components.
 */
public class Date implements Displayable {
	
	/** The day of the month (1-31). */
	private int day;
	
	/** The month of the year (1-12). */
	private int month;
	
	/** The year (e.g. 2026). */
	private int year;
	
	/**
	 * Constructs a Date object initialized to the current system date.
	 */
	public Date() {
		LocalDate today = LocalDate.now();
		this.day = today.getDayOfMonth();
		this.month = today.getMonthValue();
		this.year = today.getYear();
	}

	/**
	 * Returns the date formatted as {@code dd/mm/yyyy}
	 * 
	 * @return a formatted string representation of the date
	 */
	public String display() {
		String output = String.format("%02d/%02d/%04d", day, month, year);
		return output;
	}

	/**
	 * Returns the day of the month.
	 * 
	 * @return the day (1-31)
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Returns the month of the year.
	 * 
	 * @return the month (1-12)
	 */
	public int getMonth() {
		return this.month;
	}

	/** Returns the year.
	 * 
	 * @return the year (e.g. 2026)
	 */
	public int getYear() {
		return this.year;
	}
}
