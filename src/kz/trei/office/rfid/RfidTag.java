package kz.trei.office.rfid;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rfidTag", namespace ="http://www.trei.kz/attendance/tns")
// @XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "type", "protocol", "issue", "rfidUid" })
public class RfidTag implements Serializable, Comparable<RfidTag> {
	private static final long serialVersionUID = -1380395087317256237L;
	@XmlElement(name = "rfidUid")
	private RfidUID rfidUid;
	private RfidType type;
	private ProtocolType protocol;
	private Issue issue;

	public RfidTag() {
	}

	private RfidTag(RfidUID uid, RfidType type, ProtocolType protocol,
			Issue issue) {
		super();
		this.rfidUid = uid;
		this.type = type;
		this.protocol = protocol;
		this.issue = issue;
	}

	public static class Builder {
		private RfidUID uid;
		private RfidType type;
		private ProtocolType protocol;
		private Issue issue;

		public Builder setRfidUID(RfidUID uid) {
			this.uid = uid;
			return this;
		}

		public Builder setRfidType(RfidType type) {
			this.type = type;
			return this;
		}

		public Builder setProtocol(ProtocolType protocol) {
			this.protocol = protocol;
			return this;
		}

		public Builder setIssue(Issue issue) {
			this.issue = issue;
			return this;
		}

		public RfidTag build() {
			return new RfidTag(uid, type, protocol, issue);
		}
	}

	public RfidUID getRfidUid() {
		return rfidUid;
	}

	@XmlAttribute(name = "uid")
	public String getUidValue() {
		return rfidUid.getValue();
	}

	public void setUid(RfidUID uid) {
		this.rfidUid = uid;
	}

	@XmlElement(name = "tagtype", required = true)
	public RfidType getType() {
		return type;
	}

	public void setType(RfidType type) {
		this.type = type;
	}

	@XmlElement(name = "protocol", required = true)
	public ProtocolType getProtocol() {
		return protocol;
	}

	public void setProtocol(ProtocolType protocol) {
		this.protocol = protocol;
	}

	@XmlElement(name = "issue", required = true)
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
		result = prime * result + ((rfidUid == null) ? 0 : rfidUid.hashCode());
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
		if (rfidUid == null) {
			if (other.rfidUid != null)
				return false;
		} else if (!rfidUid.equals(other.rfidUid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RfidTag [uid=" + rfidUid.getValue() + ", type=" + type + "]";
	}

	@Override
	public int compareTo(RfidTag anotherTag) {
		String anotherUid = anotherTag.getRfidUid().getValue();
		String thisUid = this.rfidUid.getValue();
		return thisUid.compareTo(anotherUid);
	}
}
