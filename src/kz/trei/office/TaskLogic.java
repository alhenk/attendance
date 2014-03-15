package kz.trei.office;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.parser.EmployeeParser;
import kz.trei.office.parser.JaxbEmployeeParser;
import kz.trei.office.parser.XmlParserException;
import kz.trei.office.parser.SaxEmployeeParser;
import kz.trei.office.parser.StaxEmployeeParser;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.util.PropertyManager;

public final class TaskLogic {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final Logger LOGGER = Logger.getLogger(TaskLogic.class);

	private TaskLogic() {
	}

	public static void runSaxParser(String xmlfile, String xsdfile) {
		List<Person> staff = new ArrayList<Person>();
		EmployeeParser parser = new SaxEmployeeParser();
		try {
			staff = parser.parse(xmlfile, xsdfile);
			LOGGER.info(staff);
		} catch (XmlParserException e) {
			LOGGER.error(e);
		}
		printStaff(staff);
	}

	public static void runStaxParser(String xmlfile, String xsdfile) {
		List<Person> staff = new ArrayList<Person>();
		EmployeeParser parser = new StaxEmployeeParser();
		try {
			staff = parser.parse(xmlfile, xsdfile);
			LOGGER.info(staff);
		} catch (XmlParserException e) {
			LOGGER.error(e);
		}
		printStaff(staff);
	}

	public static void createJaxbXml(String xmlfile) {
		new JaxbEmployeeParser().createEmployeeXml(xmlfile);
	}

	public static void runJaxbParser(String xmlfile) {
		try {
			new JaxbEmployeeParser().parse(xmlfile, "");
		} catch (XmlParserException e) {
			LOGGER.error(e);
		}
	}

	private static void printStaff(List<Person> staff) {
		for (Person employee : staff) {
			LOGGER.info(((Employee) employee).getFirstName());
			LOGGER.info(((Employee) employee).getPatronym());
			LOGGER.info(((Employee) employee).getLastName());
			LOGGER.info("Birthday "
					+ ((Employee) employee).getBirthday().getDate());
			if (((Employee) employee).getPosition() != null) {
				for (PositionType position : ((Employee) employee)
						.getPosition()) {
					LOGGER.info(position);
				}
			}
			if (((Employee) employee).getRoom() != null) {
				for (RoomType room : ((Employee) employee).getRoom()) {
					LOGGER.info(room);
				}
			}
			LOGGER.info(((Employee) employee).getTableId().getId());
			LOGGER.info(((Employee) employee).getTag().getRfidUid().getValue());
			LOGGER.info(((Employee) employee).getTag().getProtocol());
			LOGGER.info(((Employee) employee).getTag().getType());
			LOGGER.info("Issue Date "
					+ ((Employee) employee).getTag().getIssue().getIssueDate()
							.getDate());
			LOGGER.info("Expiration Date "
					+ ((Employee) employee).getTag().getIssue()
							.getExpirationDate().getDate());
			LOGGER.info("**************************************");
		}
	}
}
