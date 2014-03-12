package kz.trei.office.attendance;

import java.util.Date;

import kz.trei.office.rfid.RfidUID;
import kz.trei.office.util.CalendarDate;
import kz.trei.office.util.TimeStamp;

public class Attendance {
	private RfidUID uid;
	private DirectionType direction;
	private CalendarDate date;  
	private TimeStamp time; 

	public Attendance() {
	}

	public Attendance(RfidUID uid, DirectionType direction, Date date) {
		super();
		this.uid = uid;
		this.direction = direction;
		this.date = CalendarDate.createDate(date);
		this.time = TimeStamp.create(date);
	}

	public RfidUID getUid() {
		return uid;
	}

	public void setUid(RfidUID uid) {
		this.uid = uid;
	}

	public DirectionType getDirection() {
		return direction;
	}

	public void setDirection(DirectionType direction) {
		this.direction = direction;
	}

	public CalendarDate getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = CalendarDate.createDate(date);
		this.time = TimeStamp.create(date);
	}
	
	public void setDate(CalendarDate date) {
		this.date = date;
		this.time = TimeStamp.create(date.getDate());
	}

	public TimeStamp getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		Attendance other = (Attendance) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (direction != other.direction)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
}
