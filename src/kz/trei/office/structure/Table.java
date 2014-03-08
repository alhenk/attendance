package kz.trei.office.structure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Table ID assigned to employee accordingly to
 * 1C account data base
 * @throws  IllegalArgumentException
 */
public class Table {
	/**
	 * regex id verification
	 */
	public static boolean checkID(String id){
		Pattern tableIDPattern = Pattern.compile("\\w{2}\\d{8}",
				Pattern.UNICODE_CHARACTER_CLASS|Pattern.CASE_INSENSITIVE);
		Matcher tableIDMatcher = tableIDPattern.matcher(id);
		return tableIDMatcher.matches();
	}
	/**
	 * Static fabric method with random ID
	 */
	public static Table createRandomID(){
		StringBuilder id = new StringBuilder();
		long number = 1L+ (long)(Math.random()*99999999L);
		id.append("\u041A\u041A");
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
