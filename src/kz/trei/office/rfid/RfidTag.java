package kz.trei.office.rfid;

public class RfidTag {
	private RfidUID uid;
	private RfidType type;
	private ProtocolType protocol;
	
	public RfidTag(){
	}

	public RfidTag(RfidUID uid, RfidType type, ProtocolType protocol) {
		super();
		this.uid = uid;
		this.type = type;
		this.protocol = protocol;
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
}
