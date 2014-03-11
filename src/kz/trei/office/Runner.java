package kz.trei.office;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.parser.SaxPersonParser;
import kz.trei.office.structure.Table1C;
import kz.trei.office.util.FileManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		
		
		// LOGGER.info(FileManager.getResourceAsStream("staff.xml"));
		LOGGER.info("UUID = " + UUID.randomUUID());
		Person person = new Employee.Builder()
				.setUUID("d0f3de3a-260a-480f-af56-4d9086de61aa")
				.setLastName("Koryagin").build();
		LOGGER.info(person);
		
		Table1C table = Table1C.createID("tt12345678");
		LOGGER.info(table.getId());
		table = Table1C.createRandomID();
		LOGGER.info(table.getId());
		SaxPersonParser parser = new SaxPersonParser();
		List<Person> personnel = new ArrayList<Person>();
		personnel = parser.parse(FileManager.getResourceAsStream("staff.xml"));
		LOGGER.info(personnel);
	}
}
