package kz.trei.office;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.parser.PersonParser;
import kz.trei.office.parser.SaxParserException;
import kz.trei.office.parser.SaxPersonParser;
import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}
	public static void main(String[] args) {
		List<Person> personnel = new ArrayList<Person>();
		PersonParser parser = new SaxPersonParser();
		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");
		try {
			personnel = parser.parse(xmlfile, xsdfile);
			LOGGER.info(personnel);
		} catch (SaxParserException e) {
			LOGGER.error(e);
		}
		for (Person employee: personnel){
			LOGGER.info(((Employee)employee).getFirstName());
			LOGGER.info(((Employee)employee).getPatronym());
			LOGGER.info(((Employee)employee).getLastName());
			LOGGER.info(((Employee)employee).getBirthday());
			LOGGER.info(((Employee)employee).getPosition());
			LOGGER.info(((Employee)employee).getRoom().getRoomName());
			LOGGER.info(((Employee)employee).getTableId().getId());
			LOGGER.info(((Employee)employee).getTag().getUid().getValue());
			LOGGER.info(((Employee)employee).getTag().getProtocol());
			LOGGER.info(((Employee)employee).getTag().getType());
			LOGGER.info(((Employee)employee).getTag().getIssue().getIssueDate());
			LOGGER.info(((Employee)employee).getTag().getIssue().getExpirationDate());

			LOGGER.info("**************************************");
		}
	}
}
