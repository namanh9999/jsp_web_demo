package util;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateProcess {
	public static DateProcess getInstance() {
		return new DateProcess();
	}

	public Date getCurrentDay() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();
		// Define the desired date format
		Date dateFormatter = Date.valueOf(currentDate);
		return dateFormatter;

	}

	public String takeDayOfWeek() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();
		// Define the desired date format
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		String result = String.valueOf(dayOfWeek);
		return result;

	}

	public int takeWeekOfMonth() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();
		// get the week fields for the default locale
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int weekOfMonth = currentDate.get(weekFields.weekOfMonth());
		return weekOfMonth;

	}

	public String takeMonthOfYear() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// get month of the year
		Month month = currentDate.getMonth();
		String result = String.valueOf(month);
		return result;
	}

	public int takeCurrentYear() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();
		// get current year
		int year = currentDate.getYear();
		return year;
	}

	public int takeDayOfMonth() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Get the day of the month
		int dayOfMonth = currentDate.getDayOfMonth();
		return dayOfMonth;
	}
}
