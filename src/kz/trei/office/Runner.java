package kz.trei.office;

import java.io.InputStream;
import java.util.UUID;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Person;
import kz.trei.office.structure.Table;
import kz.trei.office.util.FileManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {
				boolean bRfidTag = false;
				boolean bLastName = false;
				String uid = "";

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					// LOGGER.info("Start Element :" + qName);
					if (qName.equalsIgnoreCase("RfidTag")) {
						uid = attributes.getValue("uid");
						if (uid == null)
							uid = "NULL";
						bRfidTag = true;
					}
					if (qName.equalsIgnoreCase("LastName")) {
						bLastName = true;
					}
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					// LOGGER.info("End Element :" + qName);
				}

				public void characters(char ch[], int start, int length) {
					if (bLastName) {
						LOGGER.info("Last Name = "
								+ new String(ch, start, length));
						bLastName = false;
					}
					if (bRfidTag) {
						LOGGER.info("UID = " + uid);
						bRfidTag = false;
					}
				}
			};
			InputStream inputStream = FileManager
					.getResourceAsStream("staff.xml");
			saxParser.parse(inputStream, handler);
		} catch (Exception e) {
		}
		// LOGGER.info(FileManager.getResourceAsStream("staff.xml"));
		LOGGER.info("UUID = " + UUID.randomUUID());
		Person person = new Employee.Builder()
				.setUUID("d0f3de3a-260a-480f-af56-4d9086de61aa")
				.setLastName("Koryagin").build();
		LOGGER.info(person);
		
		Table table = Table.createID("tt12345678");
		LOGGER.info(table.getId());
		table = Table.createRandomID();
		LOGGER.info(table.getId());
	}
}
