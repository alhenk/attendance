package kz.trei.office.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import kz.trei.office.hr.Employee;
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

		StringBuilder elementValue = new StringBuilder();

		public PersonHandler() {
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// LOGGER.debug("Start Element :" + qName);
			if (qName.equalsIgnoreCase("EMPLOYEE")) {
				String value = attributes.getValue("uuid");
				if (value == null) {
					uuid = UUID.randomUUID();
				} else {
					uuid = UUID.fromString(value);
				}
				boolRfidTag = true;
			} else if (qName.equalsIgnoreCase("FIRSTNAME")) {
				boolFirstName = true;
			} else if (qName.equalsIgnoreCase("PATRONYM")) {
				boolPatronym = true;
			} else if (qName.equalsIgnoreCase("LASTNAME")) {
				boolLastName = true;
			} else if (qName.equalsIgnoreCase("BIRTHDAY")) {
				boolBirthday = true;
			} else if (qName.equalsIgnoreCase("POSITION")) {
				boolPosition = true;
			} else if (qName.equalsIgnoreCase("DEPARTMENT")) {
				boolDepartment = true;
			} else if (qName.equalsIgnoreCase("ROOM")) {
				boolRoom = true;
			} else if (qName.equalsIgnoreCase("TABLEID")) {
				boolTableId = true;
			} else if (qName.equalsIgnoreCase("RFIDTAG")) {
				String value = attributes.getValue("uid");
				if (value == null) {
					uid = null;
				} else {
					uid = RfidUID.createUID(value);
				}
				boolRfidTag = true;
			} else if (qName.equalsIgnoreCase("TAGTYPE")) {
				boolTagType = true;
			} else if (qName.equalsIgnoreCase("ISSUE")) {
				boolIssue = true;
			} else if (qName.equalsIgnoreCase("ISSUEDATE")) {
				boolIssueDate = true;
			} else if (qName.equalsIgnoreCase("EXPIRATIONDATE")) {
				boolExpirationDate = true;
			}

		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// LOGGER.debug("End Element :" + qName);
			if (qName.equalsIgnoreCase("EMPLOYEE")) {
				LOGGER.debug(new Employee(uuid));
			}
		}

		public void characters(char ch[], int start, int length) {
			if (boolEmployee) {
				LOGGER.debug("UUID = " + uuid);
				boolEmployee = false;
			} else if (boolFirstName) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("First Name = " + new String(ch, start, length));
				boolFirstName = false;
			} else

			if (boolPatronym) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Patronym = " + new String(ch, start, length));
				boolPatronym = false;
			} else

			if (boolLastName) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Last Name = " + new String(ch, start, length));
				boolLastName = false;
			} else if (boolBirthday) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Birthday = " + new String(ch, start, length));
				boolBirthday = false;
			} else if (boolPosition) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Position = " + new String(ch, start, length));
				boolPosition = false;
			} else if (boolDepartment) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Department = " + new String(ch, start, length));
				boolDepartment = false;
			} else if (boolRoom) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Room = " + new String(ch, start, length));
				boolRoom = false;
			} else if (boolTableId) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Table ID = " + new String(ch, start, length));
				boolTableId = false;
			} else if (boolRfidTag) {
				LOGGER.debug("UID = " + uid);
				boolRfidTag = false;
			} else if (boolTagType) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Tag type = " + new String(ch, start, length));
				boolTagType = false;
			} else if (boolProtocol) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Protocol = " + new String(ch, start, length));
				boolProtocol = false;
			} else if (boolIssue) {
				LOGGER.debug("Issue ");
				boolIssue = false;
			} else if (boolIssueDate) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Issue date = " + new String(ch, start, length));
				boolIssueDate = false;
			} else if (boolExpirationDate) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Expiration date = "
						+ new String(ch, start, length));
				boolExpirationDate = false;
			}
		}
	}

}