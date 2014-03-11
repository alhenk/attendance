package kz.trei.office;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Person;
import kz.trei.office.parser.PersonParser;
import kz.trei.office.parser.SaxParserException;
import kz.trei.office.parser.SaxPersonParser;
import kz.trei.office.util.FileManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		List<Person> personnel = new ArrayList<Person>();

		PersonParser parser = new SaxPersonParser();
		try {
			personnel = parser.parse(FileManager.getResourceAsStream("staff.xml"));
			LOGGER.info(personnel);
		} catch (SaxParserException e) {
			LOGGER.error(e);
		}
	}
}
