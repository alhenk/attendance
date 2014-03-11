package kz.trei.office.rfid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kz.trei.office.util.PropertyManager;

public final class RfidUID {
	static{
		PropertyManager.load("office.properties");
	}
	
	private String value;
	
	public RfidUID(){
	}
	private RfidUID(String value){
		this.value = value;
	}
	
	private static boolean checkUID(String uid){
		String uidRegex = PropertyManager.getValue("rfid.uidRegex");
		Pattern uidPattern = Pattern.compile(uidRegex,
				Pattern.UNICODE_CHARACTER_CLASS|Pattern.CASE_INSENSITIVE);
		Matcher tableIDMatcher = uidPattern.matcher(uid);
		return tableIDMatcher.matches();
	}

	public static RfidUID createUID(String uid){
		if(checkUID(uid)){
			return new RfidUID(uid);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getValue() {
		return value;
	}
}
