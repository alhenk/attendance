package kz.trei.office.rfid;

import kz.trei.office.util.CalendarDate;

public class Issue {
	private CalendarDate issueDate; 
	private CalendarDate expirationDate;
	
	public Issue(){
	}

	public Issue(CalendarDate issueDate, CalendarDate expirationDate) {
		super();
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}
	
	public static class Builder{
		private CalendarDate issueDate; 
		private CalendarDate expirationDate;
		
		public Builder setIssueDate(CalendarDate issueDate){
			this.issueDate = issueDate;
			return this;
		}
		public Builder setExpirationDate(CalendarDate experationDate){
			this.expirationDate = experationDate;
			return this;
		}
		public Issue build(){
			return new Issue(issueDate, expirationDate);
		}
		
	}

	public CalendarDate getIssueDate() {
		return issueDate;
	}
	
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
