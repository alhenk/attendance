package kz.trei.office;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.hr.Staff;
import kz.trei.office.parser.DomEmployeeParser;
import kz.trei.office.parser.EmployeeParser;
import kz.trei.office.parser.JaxbEmployeeParser;
import kz.trei.office.parser.XmlParserException;
import kz.trei.office.parser.SaxEmployeeParser;
import kz.trei.office.parser.StaxEmployeeParser;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.util.PropertyManager;

public final class TaskLogic {
	private static final Logger LOGGER = Logger.getLogger(TaskLogic.class);
	static {
		PropertyManager.load("configure.properties");
	}

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
		Staff staff = JaxbEmployeeParser.createTestStaff();
		LOGGER.info("See " + xmlfile);
		new JaxbEmployeeParser().createStaffXml(staff, xmlfile);
	}

	public static void runJaxbParser(String xmlfile, String xsdfile) {
		try {
			List<Person> staff = (List<Person>) new JaxbEmployeeParser().parse(
					xmlfile, xsdfile);
			LOGGER.info(staff);
		} catch (XmlParserException e) {
			LOGGER.error(e);
		}
	}

	public static void runDomParser(String xmlfile, String xsdfile) {
		try {
			List<Person> staff = new DomEmployeeParser()
					.parse(xmlfile, xsdfile);
			LOGGER.info(staff);
		} catch (XmlParserException e) {
			LOGGER.info(e);
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
			LOGGER.info(((Employee) employee).getTag().getRfidUid().getRfidUID());
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
