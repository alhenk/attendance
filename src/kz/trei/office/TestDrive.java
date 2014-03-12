package kz.trei.office;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import kz.trei.office.rfid.RfidUID;
import kz.trei.office.structure.Table1C;



public class TestDrive {
	private static final Logger LOGGER = Logger.getLogger(TestDrive.class);
	public static void main(String[] args) {
		//FileManager.readFile("office.properties");
		//LOGGER.info(FileManager.readFile("office.properties"));
		RfidUID uid = RfidUID.createUID("E0040100594737350000");
		LOGGER.info("UID = " + uid.getValue());
		
		Table1C table = Table1C.createID("tt12345678");
		LOGGER.info(table.getId());
		table = Table1C.createRandomID();
		LOGGER.info(table.getId());
		
		long timeInMillis = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTimeInMillis(timeInMillis);
		SimpleDateFormat _format = new SimpleDateFormat("hh:mm");
		System.out.println(_format.format(calendar.getTime())+"!!!!!!!!!!!!!!!!");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = format.parse("20120405");
			LOGGER.info(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
