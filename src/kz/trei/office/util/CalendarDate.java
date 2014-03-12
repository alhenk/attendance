package kz.trei.office.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class CalendarDate implements Serializable, Comparable<Date> {
	private static final long serialVersionUID = -8961625269572879384L;
	private static final Logger LOGGER = Logger.getLogger(CalendarDate.class);
	private static SimpleDateFormat format;

	public static boolean isValid(String date) {
		format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.setLenient(false);
			format.parse(date);
		} catch (ParseException e) {
			LOGGER.error("" + e);
			return false;
		} catch (IllegalArgumentException e) {
			LOGGER.error("" + e);
			return false;
		}
		return true;
	}

	public static CalendarDate createDate(String date){
		int year;
		int month;
		int day;
		Date unixDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			format.setLenient(false);
			unixDate = format.parse(date);
			calendar.setTime(unixDate);
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			return new CalendarDate(year, month, day, unixDate);
		} catch (ParseException e) {
			LOGGER.error("String yyyy-MM-dd is invalid" + e);
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error(e);
			return null;
		}
	}

	public static CalendarDate createDate(Date date) {
		int year;
		int month;
		int day;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			format.setLenient(false);
			calendar.setTime(date);
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			return new CalendarDate(year, month, day, date);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("" + e);
			return null;
		}
	}

	private int year;
	private int month;
	private int day;
	private Date date;

	public CalendarDate() {
	}

	private CalendarDate(int year, int month, int day, Date date) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarDate other = (CalendarDate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public int compareTo(Date anotherDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long thisTime = calendar.getTimeInMillis();
		calendar.setTime(anotherDate);
		long anotherTime = calendar.getTimeInMillis();
		return (thisTime < anotherTime ? -1 : (thisTime == anotherTime ? 0 : 1));
	}
}
