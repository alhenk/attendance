package kz.trei.office.attendance;

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

	public Attendance(RfidUID uid, DirectionType direction, CalendarDate date, TimeStamp time) {
		super();
		this.uid = uid;
		this.direction = direction;
		this.date = date;
		this.time = time;
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

	public void setDate(CalendarDate date) {
		this.date = date;
	}

	public TimeStamp getTime() {
		return time;
	}

	public void setTime(TimeStamp time) {
		this.time = time;
	}

}
