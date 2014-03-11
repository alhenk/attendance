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
			elementValue.setLength(0);
			if (qName.equalsIgnoreCase("tns:EMPLOYEE")) {
				String value = attributes.getValue("uuid");
				if (value == null) {
					uuid = UUID.randomUUID();
				} else {
					uuid = UUID.fromString(value);
				}
			}
			 else if (qName.equalsIgnoreCase("RFIDTAG")) {
				String value = attributes.getValue("uid");
				if (value == null) {
					uid = null;
				} else {
					uid = RfidUID.createUID(value);
				}
			 }
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
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
			} else if (qName.equalsIgnoreCase("PATRONYM")) {
				patronym = elementValue.toString().trim();
				LOGGER.debug(patronym);
			} else if (qName.equalsIgnoreCase("LASTNAME")) {
				lastName = elementValue.toString().trim();
				LOGGER.debug(lastName);
			} else if (qName.equalsIgnoreCase("BIRTHDAY")) {
				try {
					birthday = CalendarDate.createDate(elementValue.toString()
							.trim());
				} catch (CalendarDateException e) {
					e.printStackTrace();
				}
				LOGGER.debug(birthday.getYear() + "-" + birthday.getMonth()
						+ "-" + birthday.getDay());
			} else if (qName.equalsIgnoreCase("POSITION")) {
				position = PositionType.valueOf(elementValue.toString().trim());
				LOGGER.debug(position);
			}
			if (qName.equalsIgnoreCase("DEPARTMENT")) {
				department = DepartmentType.valueOf(elementValue.toString()
						.trim());
				LOGGER.debug(department);
			} else if (qName.equalsIgnoreCase("ROOM")) {
				RoomType _room = RoomType.DEFAULT;
				room = _room.select(Integer.valueOf(elementValue.toString()
						.trim()));
				LOGGER.debug(room.getRoomName());
			} else if (qName.equalsIgnoreCase("TABLEID")) {
				tableId = Table1C.createID(elementValue.toString().trim());
				LOGGER.debug(tableId.getId());
			} else if (qName.equalsIgnoreCase("RFIDTAG")) {
				tag = new RfidTag(uid, tagType, protocol, issue);
				LOGGER.debug(tag);
			} else if (qName.equalsIgnoreCase("TAGTYPE")) {
				tagType = RfidType.valueOf(elementValue.toString().trim());
				LOGGER.debug(tagType);
			} else if (qName.equalsIgnoreCase("PROTOCOL")) {
				protocol = ProtocolType.valueOf(elementValue.toString().trim());
				LOGGER.debug(protocol);
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
				LOGGER.debug(issueDate);
			} else if (qName.equalsIgnoreCase("EXPIRATIONDATE")) {
				try {
					expirationDate = CalendarDate.createDate(elementValue
							.toString().trim());
				} catch (CalendarDateException e) {
					e.printStackTrace();
					LOGGER.debug(e);
				}
				LOGGER.debug(expirationDate);
			}
		}

		public void characters(char ch[], int start, int length) {
			elementValue.append(new String(ch, start, length));
		}
	}
}