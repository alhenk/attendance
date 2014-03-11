package kz.trei.office.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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
import kz.trei.office.util.CalendarDate;
import kz.trei.office.util.CalendarDateException;
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

		UUID uuid;
		String firstName;
		String patronym;
		String lastName;
		CalendarDate birthday;
		PositionType position;
		DepartmentType department;
		RoomType room;
		Table1C tableId;
		RfidUID uid;
		RfidTag tag;
		RfidType tagType;
		ProtocolType protocol;
		Issue issue;
		CalendarDate issueDate;
		CalendarDate expirationDate;

		StringBuilder elementValue = new StringBuilder();

		public PersonHandler() {
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			//LOGGER.debug("Start Element :" + qName);
			if (qName.equalsIgnoreCase("tns:EMPLOYEE")) {
				String value = attributes.getValue("uuid");
				if (value == null) {
					uuid = UUID.randomUUID();
				} else {
					uuid = UUID.fromString(value);
				}
				boolEmployee = true;
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
			} else if (qName.equalsIgnoreCase("PROTOCOL")) {
				boolProtocol = true;
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
			//LOGGER.debug("End Element :" + qName);
			if (qName.equalsIgnoreCase("tns:EMPLOYEE")) {
				Person employee = new Employee.Builder().setUUID(uuid)
						.setFirstName(firstName).setPatronym(patronym)
						.setLastName(lastName).setPosition(position)
						.setRoom(room).setTableId(tableId).setTag(tag)
						.build();
				personnel.add(employee);
				LOGGER.debug(employee);
			} else if (qName.equalsIgnoreCase("FIRSTNAME")) {
				firstName = elementValue.toString().trim();
				LOGGER.debug(firstName);
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("PATRONYM")) {
				patronym = elementValue.toString().trim();
				LOGGER.debug(patronym);
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("LASTNAME")) {
				lastName = elementValue.toString().trim();
				LOGGER.debug(lastName);
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("BIRTHDAY")) {
				try {
					birthday = CalendarDate.createDate(elementValue.toString()
							.trim());
				} catch (CalendarDateException e) {
					e.printStackTrace();
				}
				LOGGER.debug(birthday.getYear() + "-" + birthday.getMonth()
						+ "-" + birthday.getDay());
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("POSITION")) {
				position = PositionType.valueOf(elementValue.toString().trim());
				LOGGER.debug(position);
				elementValue = new StringBuilder();
			}
			if (qName.equalsIgnoreCase("DEPARTMENT")) {
				department = DepartmentType.valueOf(elementValue.toString()
						.trim());
				LOGGER.debug(department);
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("ROOM")) {
				RoomType _room = RoomType.DEFAULT;
				room = _room.select(Integer.valueOf(elementValue.toString()
						.trim()));
				LOGGER.debug(room.getRoomName());
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("TABLEID")) {
				tableId = Table1C.createID(elementValue.toString().trim());
				LOGGER.debug(tableId.getId());
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("RFIDTAG")) {
				tag = new RfidTag(uid, tagType, protocol, issue);
				LOGGER.debug(tag);
			} else if (qName.equalsIgnoreCase("TAGTYPE")) {
				tagType = RfidType.valueOf(elementValue.toString().trim());
				LOGGER.debug(tagType);
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("PROTOCOL")) {
				protocol = ProtocolType.valueOf(elementValue.toString().trim());
				LOGGER.debug(protocol);
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("ISSUE")) {
				issue = new Issue(issueDate, expirationDate);
				LOGGER.debug(issue);
			} else if (qName.equalsIgnoreCase("ISSUEDATE")) {
				try {
					issueDate = CalendarDate.createDate(elementValue.toString()
							.trim());
				} catch (CalendarDateException e) {
					e.printStackTrace();
					LOGGER.debug(e);
				}
				LOGGER.debug(issueDate.getYear() + "-" + issueDate.getMonth()
						+ "-" + issueDate.getDay());
				elementValue = new StringBuilder();
			} else if (qName.equalsIgnoreCase("EXPIRATIONDATE")) {
				try {
					expirationDate = CalendarDate.createDate(elementValue
							.toString().trim());
				} catch (CalendarDateException e) {
					e.printStackTrace();
					LOGGER.debug(e);
				}
				LOGGER.debug(expirationDate.getYear() + "-" + expirationDate.getMonth()
						+ "-" + expirationDate.getDay());
				elementValue = new StringBuilder();

			}
		}

		public void characters(char ch[], int start, int length) {
			if (boolEmployee) {
				LOGGER.debug("Employee UUID = " + uuid);
				boolEmployee = false;
			} else if (boolFirstName) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("First Name = " + new String(ch, start, length));
				boolFirstName = false;
			} else if (boolPatronym) {
				elementValue.append(new String(ch, start, length));
				LOGGER.debug("Patronym = " + new String(ch, start, length));
				boolPatronym = false;
			} else if (boolLastName) {
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
				LOGGER.debug("UID = " + uid.getValue());
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
				LOGGER.debug("ISSUE");
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