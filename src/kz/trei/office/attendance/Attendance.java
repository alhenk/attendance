package kz.trei.office.attendance;

import java.util.Date;

public class Attendance {
	private String uid;
	private String direction;// enum IN OUT
	private Date date;
	private String time;

	public Attendance() {
	}

	public Attendance(String uid, String direction, Date date, String time) {
		super();
		this.uid = uid;
		this.direction = direction;
		this.date = date;
		this.time = time;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
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
