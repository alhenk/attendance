package kz.trei.office.rfid;

public class RfidTag {
	private RfidUID uid;
	private RfidType type;
	private ProtocolType protocol;
	private Issue issue;
	
	public RfidTag(){
	}

	private RfidTag(RfidUID uid, RfidType type, ProtocolType protocol, Issue issue) {
		super();
		this.uid = uid;
		this.type = type;
		this.protocol = protocol;
		this.issue = issue;
	}
	
	public static  class Builder{
		private RfidUID uid;
		private RfidType type;
		private ProtocolType protocol;
		private Issue issue;
		
		public Builder setRfidUID(RfidUID uid){
			this.uid = uid;
			return this;
		}
		public Builder setRfid(RfidType type){
			this.type = type;
			return this;
		}
		public Builder setProtocol(ProtocolType protocol){
			this.protocol = protocol;
			return this;
		}
		public Builder setIssue(Issue issue){
			this.issue = issue;
			return this;
		}
		public RfidTag build(){
			return new RfidTag(uid , type , protocol, issue);
		}
	}

	public RfidUID getUid() {
		return uid;
	}

	public void setUid(RfidUID uid) {
		this.uid = uid;
	}

	public RfidType getType() {
		return type;
	}

	public void setType(RfidType type) {
		this.type = type;
	}

	public ProtocolType getProtocol() {
		return protocol;
	}

	public void setProtocol(ProtocolType protocol) {
		this.protocol = protocol;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((issue == null) ? 0 : issue.hashCode());
		result = prime * result
				+ ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		RfidTag other = (RfidTag) obj;
		if (issue == null) {
			if (other.issue != null)
				return false;
		} else if (!issue.equals(other.issue))
			return false;
		if (protocol != other.protocol)
			return false;
		if (type != other.type)
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
}
