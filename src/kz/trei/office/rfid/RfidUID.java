package kz.trei.office.rfid;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import kz.trei.office.util.PropertyManager;

@XmlRootElement(name = "rfidUid")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public final class RfidUID implements Serializable, Comparable<RfidUID> {
	private static final long serialVersionUID = 2727289459571896461L;
	static {
		PropertyManager.load("configure.properties");
	}
	@XmlValue
	private String uid;

	public RfidUID() {
	}

	private RfidUID(String value) {
		this.uid = value;
	}

	public static boolean isValid(String uid) {
		String uidRegex = PropertyManager.getValue("rfid.uidRegex");
		Pattern uidPattern = Pattern.compile(uidRegex,
				Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
		Matcher tableIDMatcher = uidPattern.matcher(uid);
		return tableIDMatcher.matches();
	}

	public static RfidUID createUID(String uid) {
		if (isValid(uid)) {
			return new RfidUID(uid);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getValue() {
		return uid;
	}

	
	public void setValue(String value) {
		if (isValid(value)) {
			this.uid = value;
		} else{
			throw new IllegalArgumentException(value +" is not valid UID");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		RfidUID other = (RfidUID) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RfidUID [UID = " + uid + "]";
	}

	@Override
	public int compareTo(RfidUID anotherUID) {
		return uid.compareTo(anotherUID.getValue());
	}

}
