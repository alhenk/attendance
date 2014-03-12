package kz.trei.office.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStamp {
	private String time;
	
	public TimeStamp(){
	}
	private TimeStamp(String time){
		this.time = time;
	}
	
	public static TimeStamp create(Date date){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		SimpleDateFormat time = new SimpleDateFormat("hh:mm");
		return new TimeStamp(time.format(calendar.getTime()));
	}

	public String getTime() {
		return time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		TimeStamp other = (TimeStamp) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
