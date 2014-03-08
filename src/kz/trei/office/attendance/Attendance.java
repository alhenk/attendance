package kz.trei.office.attendance;

import java.util.Date;

import kz.trei.office.rfid.RfidUID;

public class Attendance {
	private RfidUID uid;
	private DirectionType direction;
	private Date date;   //TODO Date
	private String time; //TODO Time 

	public Attendance() {
	}

	public Attendance(RfidUID uid, DirectionType direction, Date date, String time) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
