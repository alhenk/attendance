package kz.trei.office.rfid;

import java.util.Date;

public class Issue {
	private Date issueDate; // Time 
	private Date expirationDate;
	
	public Issue(){
	}

	public Issue(Date issueDate, Date expirationDate) {
		super();
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}
	// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		// Date date = format.parse("20120405");
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	
}
