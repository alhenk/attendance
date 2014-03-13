package kz.trei.office;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.rfid.RfidTag;
import kz.trei.office.structure.DepartmentType;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.structure.Table1C;
import kz.trei.office.util.CalendarDate;
import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}

	public static void main(String[] args) {

		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");
//		LOGGER.info("RUN SAXPARSER");
//		TaskLogic.runSaxParser(xmlfile, xsdfile);
//		LOGGER.info("RUN StAXPARSER");
//		TaskLogic.runStaxParser(xmlfile, xsdfile);

		Person person = new Employee(Table1C.createID("КК00000007"));
		person.setBirthday(CalendarDate.createDate("1967-06-10"));
		person.setFirstName("Alexandr");
		person.setLastName("Koryagin");
		((Employee) person)
				.setDepartment(DepartmentType.RESEARCH_AND_DEVELOPMENT);
		((Employee) person).setPosition(PositionType.DEPARTMENT_HEAD);
		((Employee) person).setRoom(RoomType.ROOM107);
		((Employee) person).setTag(new RfidTag());

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
				JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Person worker = (Person) jaxbUnmarshaller.unmarshal(_file);
				System.out.println(worker);
		 
			  } catch (JAXBException e) {
				e.printStackTrace();
			  }
	}

}
