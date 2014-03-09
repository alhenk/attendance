package kz.trei.office.rfid;

import kz.trei.office.util.CalendarDate;

public class Issue {
	private CalendarDate issueDate; // Time 
	private CalendarDate expirationDate;
	
	public Issue(){
	}

	public Issue(CalendarDate issueDate, CalendarDate expirationDate) {
		super();
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}

	public CalendarDate getIssueDate() {
		return issueDate;
	}
	// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		// Date date = format.parse("20120405");
	public void setIssueDate(CalendarDate issueDate) {
		this.issueDate = issueDate;
	}

	public CalendarDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(CalendarDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	
}
