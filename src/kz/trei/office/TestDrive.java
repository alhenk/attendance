package kz.trei.office;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.rfid.RfidUID;
import kz.trei.office.structure.Table1C;



public class TestDrive {
	private static final Logger LOGGER = Logger.getLogger(TestDrive.class);
	public static void main(String[] args) {
		//FileManager.readFile("office.properties");
		//LOGGER.info(FileManager.readFile("office.properties"));
		RfidUID uid = RfidUID.createUID("E0040100594737350000");
		LOGGER.info("UID = " + uid.getValue());
		
		
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
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = format.parse("20120405");
			LOGGER.info(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
