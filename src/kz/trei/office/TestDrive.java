package kz.trei.office;

import org.apache.log4j.Logger;

import kz.trei.office.util.DateStamp;
import kz.trei.office.util.TimeStamp;



public class TestDrive {
	private static final Logger LOGGER = Logger.getLogger(TestDrive.class);
	public static void main(String[] args) {
	
		LOGGER.info(new DateStamp());
		LOGGER.info(new TimeStamp());
	
		
	}
}
