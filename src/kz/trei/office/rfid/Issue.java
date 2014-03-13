package kz.trei.office.rfid;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kz.trei.office.util.DateStamp;
@XmlRootElement
public class Issue {
	private DateStamp issueDate;
	private DateStamp expirationDate;

	public Issue() {
	}

	public Issue(DateStamp issueDate, DateStamp expirationDate) {
		super();
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
	}

	public static class Builder {
		private DateStamp issueDate;
		private DateStamp expirationDate;

		public Builder setIssueDate(DateStamp issueDate) {
			this.issueDate = issueDate;
			return this;
		}

		public Builder setExpirationDate(DateStamp experationDate) {
			this.expirationDate = experationDate;
			return this;
		}

		public Issue build() {
			return new Issue(issueDate, expirationDate);
		}

	}

	public DateStamp getIssueDate() {
		return issueDate;
	}
	@XmlElement
	public void setIssueDate(DateStamp issueDate) {
		this.issueDate = issueDate;
	}

	public DateStamp getExpirationDate() {
		return expirationDate;
	}
	@XmlElement
	public void setExpirationDate(DateStamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
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
		Issue other = (Issue) obj;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		return true;
	}

}
