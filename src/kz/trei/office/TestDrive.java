package kz.trei.office;

import org.apache.log4j.Logger;

import kz.trei.office.util.FileManager;


public class TestDrive {
	private static final Logger LOGGER = Logger.getLogger(TestDrive.class);
	public static void main(String[] args) {
		//FileManager.readFile("office.properties");
		LOGGER.info(FileManager.readFile("office.properties"));
	}
}
