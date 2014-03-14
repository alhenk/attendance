package kz.trei.office.rfid;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import kz.trei.office.util.PropertyManager;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rfidUid")
public final class RfidUID implements Serializable, Comparable<RfidUID> {
	private static final long serialVersionUID = 2727289459571896461L;
	static {
		PropertyManager.load("configure.properties");
	}
	
	private String value;

	public RfidUID() {
	}

	private RfidUID(String value) {
		this.value = value;
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
		return value;
	}

	
	public void setValue(String value) {
		if (isValid(value)) {
			this.value = value;
		} else{
			throw new IllegalArgumentException(value +" is not valid UID");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RfidUID [UID = " + value + "]";
	}

	@Override
	public int compareTo(RfidUID anotherUID) {
		return value.compareTo(anotherUID.getValue());
	}

}
