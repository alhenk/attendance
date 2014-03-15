package kz.trei.office;

import java.io.File;

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
import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}

	public static void main(String[] args) {

		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");
		LOGGER.info("RUN SAXPARSER");
		TaskLogic.runSaxParser(xmlfile, xsdfile);
		LOGGER.info("RUN StAXPARSER");
		TaskLogic.runStaxParser(xmlfile, xsdfile);

		Person person = new Employee(Table1C.createID("КК00000007"));
		person.setBirthday(DateStamp.create("1967-06-10"));
		person.setFirstName("Alexandr");
		person.setPatronym("G.");
		person.setLastName("Koryagin");
		((Employee) person)
				.setDepartment(DepartmentType.RESEARCH_AND_DEVELOPMENT);
		((Employee) person).setPosition(PositionType.DEPARTMENT_HEAD);
		((Employee) person).setRoom(RoomType.ROOM107);
		Issue issue = new Issue();
		System.out.println(issue);
		RfidTag rfidTag = new RfidTag.Builder().setProtocol(ProtocolType.ISO14443A)
				.setRfidUID(RfidUID.createUID("E0040100594737350000"))
				.setIssue(issue).setRfidType(RfidType.CARD).build() ;
		
		((Employee) person).setTag(rfidTag);

		try {

			File file = new File("./resources/staff_.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(person, file);
			jaxbMarshaller.marshal(person, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		
		 try {
				File _file = new File("./resources/staff_.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{Employee.class, RfidTag.class});
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Person worker = (Person) jaxbUnmarshaller.unmarshal(_file);
				System.out.println(worker);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
	}

}
