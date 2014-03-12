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

public final class TaskLogic {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final Logger LOGGER = Logger.getLogger(TaskLogic.class);
	
	private TaskLogic(){
	}

	public static void runSaxParser(String xmlfile,String xsdfile){
		List<Person> staff = new ArrayList<Person>();
		PersonParser parser = new SaxPersonParser();
		try {
			staff = parser.parse(xmlfile, xsdfile);
			LOGGER.info(staff);
		} catch (SaxParserException e) {
			LOGGER.error(e);
		}
		for (Person employee: staff){
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
