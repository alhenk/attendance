package kz.trei.office.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class CalendarDate implements Serializable {
	private static final long serialVersionUID = -8961625269572879384L;
	private static final Logger LOGGER = Logger.getLogger(CalendarDate.class);
	private static SimpleDateFormat format;
	public static boolean isValid(String date) {
		format = new SimpleDateFormat("yyyyMMdd");
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

	public static CalendarDate createDate(String date) throws CalendarDateException {
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
		
		} catch (ParseException e) {
			LOGGER.error("" + e);
			throw new CalendarDateException(e) ;
		} catch (IllegalArgumentException e) {
			LOGGER.error("" + e);
			throw new CalendarDateException(e) ;
		}
		return new CalendarDate(year,month,day,unixDate);
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
		return year + "-" + month + "-" + day;
	}
	
}
