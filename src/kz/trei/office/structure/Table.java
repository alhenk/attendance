package kz.trei.office.structure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kz.trei.office.util.PropertyManager;
/**
 * Table ID assigned to employee accordingly to
 * 1C account data base
 * @throws  IllegalArgumentException
 */
public class Table {
	static{
		PropertyManager.load("office.properties");
	}
	/**
	 * regex id verification
	 */
	public static boolean checkID(String id){
		String tableIDregex = PropertyManager.getValue("structure.tableIDRegex");
		Pattern tableIDPattern = Pattern.compile(tableIDregex,
				Pattern.UNICODE_CHARACTER_CLASS|Pattern.CASE_INSENSITIVE);
		Matcher tableIDMatcher = tableIDPattern.matcher(id);
		return tableIDMatcher.matches();
	}
	/**
	 * Static fabric method with random ID
	 */
	public static Table createRandomID(){
		StringBuilder id = new StringBuilder();
		long number = 1L+ (long)(Math.random()*9999L);
		id.append(PropertyManager.getValue("structure.tablePrefix"));
		return new Table(id.append(String.format("%08d",number)).toString());
	}
	/**
	 * Static fabric method with regex verification
	 */
	public static Table createID(String id){
		if(checkID(id)){
			return new Table(id);
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
	public Table(){
	}
	/**
	 * Constructor with direct assignment. 
	 */
	private Table(String id){
		this.id = id;
	}

	/**
	 * Getter
	 */
	public String getId() {
		return id;
	}
	

}
