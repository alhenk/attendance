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
}
