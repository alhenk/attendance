package kz.trei.office;

public class RfidTag {
	private String uid;
	private String type;		//enum RfidTagType
	private String protocol;	//enum ProtocolType
	
	public RfidTag(){
	}

	public RfidTag(String uid, String type, String protocol) {
		super();
		this.uid = uid;
		this.type = type;
		this.protocol = protocol;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	
}
