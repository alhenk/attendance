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
			File _file = new File("./resources/staff_jaxb.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {
					Employee.class, RfidTag.class, Table1C.class });

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Person worker = (Person) jaxbUnmarshaller.unmarshal(_file);
			System.out.println(worker);

		} catch (JAXBException e) {
			LOGGER.error(e);
		}
		return staff;
	}

	public void createEmployeeXml(String fileName) {
		Person person = new Employee(Table1C.createID("КК00000007"));
		person.setBirthday(DateStamp.create("1967-06-10"));
		person.setFirstName("Alexandr");
		person.setPatronym("G.");
		person.setLastName("Koryagin");
		((Employee) person)
				.setDepartment(DepartmentType.RESEARCH_AND_DEVELOPMENT);
		((Employee) person).setPosition(PositionType.DEPARTMENT_HEAD);
		((Employee) person).addRoom(RoomType.ROOM107);
		((Employee) person).addRoom(RoomType.ROOM204);
		Issue issue = new Issue();
		System.out.println(issue);
		RfidTag rfidTag = new RfidTag.Builder()
				.setProtocol(ProtocolType.ISO14443A)
				.setRfidUID(RfidUID.createUID("E0040100594737350000"))
				.setIssue(issue).setRfidType(RfidType.CARD).build();

		((Employee) person).setTag(rfidTag);

		try {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {
					Employee.class, RfidTag.class, Table1C.class });
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(person, file);
			jaxbMarshaller.marshal(person, System.out);

		} catch (JAXBException e) {
			LOGGER.error(e);
		}
	}
}
