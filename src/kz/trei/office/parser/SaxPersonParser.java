package kz.trei.office.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import kz.trei.office.hr.Person;
import kz.trei.office.rfid.RfidUID;
import kz.trei.office.util.FileManager;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxPersonParser implements PersonParser {
	private static final Logger LOGGER = Logger
			.getLogger(SaxPersonParser.class);
	private List<Person> personnel;

	@Override
	public List<Person> parse(InputStream input) {
		personnel = new ArrayList<Person>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new PersonHandler();
			InputStream inputStream = FileManager
					.getResourceAsStream("staff.xml");
			saxParser.parse(inputStream, handler);
		} catch (Exception e) {
		}
		return personnel;
	}

	private class PersonHandler extends DefaultHandler {

		boolean boolEmployee = false;
		boolean boolLastName = false;
		boolean boolFirstName = false;
		boolean boolPatronym = false;
		boolean boolBirthday = false;
		boolean boolPosition = false;
		boolean boolDepartment = false;
		boolean boolRoom = false;
		boolean boolTableId = false;
		boolean boolRfidTag = false;
		boolean boolTagType = false;
		boolean boolProtocol = false;
		boolean boolIssue = false;
		boolean boolIssueDate = false;
		boolean boolExpirationDate = false;

		RfidUID uid;
		UUID uuid;

		public PersonHandler() {
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// LOGGER.info("Start Element :" + qName);
			if (qName.equalsIgnoreCase("Employee")) {
				String value = attributes.getValue("uuid");
				if (value == null) {
					uuid = UUID.randomUUID();
				} else {
					uuid = UUID.fromString(value);
				}
				boolRfidTag = true;
			} else if (qName.equalsIgnoreCase("firstName")) {
				boolFirstName = true;
			} else if (qName.equalsIgnoreCase("patronym")) {
				boolPatronym = true;
			} else if (qName.equalsIgnoreCase("LastName")) {
				boolLastName = true;
			} else if (qName.equalsIgnoreCase("birthday")) {
				boolBirthday = true;
			} else if (qName.equalsIgnoreCase("position")) {
				boolPosition = true;
			} else if (qName.equalsIgnoreCase("department")) {
				boolDepartment = true;
			} else if (qName.equalsIgnoreCase("room")) {
				boolRoom = true;
			} else if (qName.equalsIgnoreCase("tableId")) {
				boolTableId = true;
			} else if (qName.equalsIgnoreCase("RfidTag")) {
				String value = attributes.getValue("uid");
				if (value == null) {
					uid = null;
				} else {
					uid = RfidUID.createUID(value);
				}
				boolRfidTag = true;
			} else if (qName.equalsIgnoreCase("tagType")) {
				boolTagType = true;
			}else if (qName.equalsIgnoreCase("Issue")) {
				boolIssue = true;
			}else if (qName.equalsIgnoreCase("issueDate")) {
				boolIssueDate = true;
			}

		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// LOGGER.info("End Element :" + qName);
		}

		public void characters(char ch[], int start, int length) {
			if (boolLastName) {
				LOGGER.info("Last Name = " + new String(ch, start, length));
				boolLastName = false;
			}
			if (boolRfidTag) {
				LOGGER.info("UID = " + uid);
				boolRfidTag = false;
			}
		}
	}

}