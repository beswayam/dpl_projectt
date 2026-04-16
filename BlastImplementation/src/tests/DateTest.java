package tests;

import java.time.LocalDate;

import junit.framework.TestCase;
import utilities.Date;

public class DateTest extends TestCase {

	public void testDateFormat() {
		Date date = new Date();
		String today = date.display();
		
		assertTrue(today.matches("\\d{2}/\\d{2}/\\d{4}"));
	}
	
	public void testSetCorrectDate() {
		Date date = new Date();
		LocalDate today = LocalDate.now();
		
		assertEquals(date.getDay(),today.getDayOfMonth());
		assertEquals(date.getMonth(),today.getMonthValue());
		assertEquals(date.getYear(),today.getYear());
	}
}
