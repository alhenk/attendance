package kz.trei.office;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import kz.trei.office.rfid.RfidUID;



public class TestDrive {
	private static final Logger LOGGER = Logger.getLogger(TestDrive.class);
	public static void main(String[] args) {
		//FileManager.readFile("office.properties");
		//LOGGER.info(FileManager.readFile("office.properties"));
		RfidUID uid = RfidUID.createUID("E0040100594737350000");
		LOGGER.info("UID = " + uid.getUid());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = format.parse("20120405");
			LOGGER.info(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
