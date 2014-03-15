package kz.trei.office.structure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import kz.trei.office.util.PropertyManager;
/**
 * Table ID assigned to employee accordingly to
 * 1C account data base
 * @throws  IllegalArgumentException
 */

@XmlType
public final class Table1C {
	static{
		PropertyManager.load("configure.properties");
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
	 * @throws IllegalArgumentException
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
	@XmlAttribute
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Table1C other = (Table1C) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
