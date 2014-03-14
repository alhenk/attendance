package kz.trei.office.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@XmlRootElement
public class DateStamp implements Serializable, Comparable<DateStamp> {
	private static final long serialVersionUID = -8961625269572879384L;
	private static final Logger LOGGER = Logger.getLogger(DateStamp.class);
	private static SimpleDateFormat format;

	public static boolean isValid(String date) {
		try {
			format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			format.parse(date);
		} catch (ParseException e) {
			LOGGER.error(e);
			return false;
		} catch (IllegalArgumentException e) {
			LOGGER.error(e);
			return false;
		} catch (NullPointerException e) {
			LOGGER.error(e);
			return false;
		}
		return true;
	}

	public static DateStamp createDate(String date) {
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
			return new DateStamp(year, month, day, unixDate);
		} catch (ParseException e) {
			LOGGER.error("String yyyy-MM-dd is invalid" + e);
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error(e);
			return null;
		}
	}

	public static DateStamp createDate(Date date) {
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
			return new DateStamp(year, month, day, date);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("" + e);
			return null;
		}
	}

	private int year;
	private int month;
	private int day;
	private Date date;

	public DateStamp() {
	}

	private DateStamp(int year, int month, int day, Date date) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	@XmlElement
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
		DateStamp other = (DateStamp) obj;
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
	public int compareTo(DateStamp anotherDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long thisTime = calendar.getTimeInMillis();
		calendar.setTime(anotherDate.getDate());
		long anotherTime = calendar.getTimeInMillis();
		return (thisTime < anotherTime ? -1 : (thisTime == anotherTime ? 0 : 1));
	}
}
