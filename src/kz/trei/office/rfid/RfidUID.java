package kz.trei.office.rfid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import kz.trei.office.util.PropertyManager;

@XmlRootElement
public final class RfidUID {
	static {
		PropertyManager.load("office.properties");
	}

	private String value;

	public RfidUID() {
	}

	private RfidUID(String value) {
		this.setValue(value); 
	}

	private static boolean checkUID(String uid) {
		String uidRegex = PropertyManager.getValue("rfid.uidRegex");
		Pattern uidPattern = Pattern.compile(uidRegex,
				Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
		Matcher tableIDMatcher = uidPattern.matcher(uid);
		return tableIDMatcher.matches();
	}

	public static RfidUID createUID(String uid) {
		if (checkUID(uid)) {
			return new RfidUID(uid);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getValue() {
		return value;
	}
	@XmlAttribute
	private void setValue(String value) {
		this.value = value;
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
}
