package kz.trei.office.structure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kz.trei.office.util.PropertyManager;
/**
 * Table ID assigned to employee accordingly to
 * 1C account data base
 * @throws  IllegalArgumentException
 */
public final class Table1C {
	static{
		PropertyManager.load("office.properties");
	}
	/**
	 * regex id verification
	 */
	private static boolean checkID(String id){
		String tableIDregex = PropertyManager.getValue("structure.tableIDRegex");
		Pattern tableIDPattern = Pattern.compile(tableIDregex,
				Pattern.UNICODE_CHARACTER_CLASS|Pattern.CASE_INSENSITIVE);
		Matcher tableIDMatcher = tableIDPattern.matcher(id);
		return tableIDMatcher.matches();
	}
	/**
	 * Static fabric method with random ID
	 */
	public static Table1C createRandomID(){
		StringBuilder id = new StringBuilder();
		long number = 1L+ (long)(Math.random()*9999L);
		id.append(PropertyManager.getValue("structure.tablePrefix"));
		return new Table1C(id.append(String.format("%08d",number)).toString());
	}
	/**
	 * Static fabric method with regex verification
	 */
	public static Table1C createID(String id){
		if(checkID(id)){
			return new Table1C(id);
		} else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Table ID number (KK00000001)
	 */
	private String id;
	/**
	 * Default constructor
	 */
	public Table1C(){
	}
	/**
	 * Constructor with direct assignment. 
	 */
	private Table1C(String id){
		this.id = id;
	}

	/**
	 * Getter
	 */
	public String getId() {
		return id;
	}
	

}
