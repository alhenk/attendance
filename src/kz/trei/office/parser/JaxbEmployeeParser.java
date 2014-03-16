package kz.trei.office.parser;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.hr.Staff;
import kz.trei.office.rfid.Issue;
import kz.trei.office.rfid.ProtocolType;
import kz.trei.office.rfid.RfidTag;
import kz.trei.office.rfid.RfidType;
import kz.trei.office.rfid.RfidUID;
import kz.trei.office.structure.DepartmentType;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.structure.Table1C;
import kz.trei.office.util.DateStamp;

public class JaxbEmployeeParser implements EmployeeParser {
	private static final Logger LOGGER = Logger
			.getLogger(JaxbEmployeeParser.class);
	private List<Person> staff;

	@Override
	public List<Person> parse(String xmlfile, String xsdfile)
			throws XmlParserException {
		try {
			File file = new File("./resources/staff_jaxb.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(new Class[] { Staff.class, Employee.class,
							RfidTag.class, Table1C.class });

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Staff staff = (Staff) jaxbUnmarshaller.unmarshal(file);
			System.out.println(staff);

		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		return staff;
	}

	public void createEmployeeXml(String fileName) {
		Person person = new Employee(Table1C.createID("КК00000007"));
		Staff staff = new Staff();
		person.setBirthday(DateStamp.create("1967-06-10"));
		person.setFirstName("Alexandr");
		person.setPatronym("G.");
		person.setLastName("Koryagin");
		((Employee) person)
				.setDepartment(DepartmentType.RESEARCH_AND_DEVELOPMENT);
		((Employee) person).addPosition(PositionType.DEPARTMENT_HEAD);
		((Employee) person).addPosition(PositionType.CHIEF_METROLOGIST);
		((Employee) person).addRoom(RoomType.ROOM107);
		((Employee) person).addRoom(RoomType.ROOM204);
		Issue issue = new Issue();
		System.out.println(issue);
		RfidTag rfidTag = new RfidTag.Builder()
				.setProtocol(ProtocolType.ISO14443A)
				.setRfidUID(RfidUID.createUID("E0040100594737350000"))
				.setIssue(issue).setRfidType(RfidType.CARD).build();

		((Employee) person).setTag(rfidTag);
		staff.addEmployee(person);
		person = new Employee(Table1C.createID("КК00000012"));
		person.setBirthday(DateStamp.create("1991-05-21"));
		person.setFirstName("Oleg");
		person.setPatronym("I.");
		person.setLastName("Krugov");
		((Employee) person)
				.setDepartment(DepartmentType.ADMINISTRATIVE_SERVICE_UTILITY);
		((Employee) person).addPosition(PositionType.SECURITY_GUARD);
		((Employee) person).addRoom(RoomType.ROOM101);
		issue = new Issue();
		System.out.println(issue);
		rfidTag = new RfidTag.Builder().setProtocol(ProtocolType.ISO15693)
				.setRfidUID(RfidUID.createUID("3A08265B")).setIssue(issue)
				.setRfidType(RfidType.KEYFOB).build();

		((Employee) person).setTag(rfidTag);
		staff.addEmployee(person);

		try {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {
					Staff.class, Employee.class });
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(staff, file);
			jaxbMarshaller.marshal(staff, System.out);

		} catch (JAXBException e) {
			LOGGER.error(e);
		}
	}
}
