package kz.trei.office.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
import kz.trei.office.util.CalendarDate;

public class StaxPersonParser implements PersonParser {
	private static final Logger LOGGER = Logger
			.getLogger(StaxPersonParser.class);

	@Override
	public List<Person> parse(String xmlfile, String xsdfile)
			throws SaxParserException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(xmlfile);
		} catch (FileNotFoundException e) {
			LOGGER.info(e);
		}
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader;
		String elementName = "";
		String elementText = "";
		List<Person> staff = new ArrayList<>();

		Employee.Builder employee=new Employee.Builder();
		RfidTag.Builder tag = new RfidTag.Builder();
		Issue.Builder issue = new Issue.Builder();
		try {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					elementName = reader.getLocalName();
					if (elementName.equalsIgnoreCase("EMPLOYEE")) {
						String value = reader
								.getAttributeValue(null, "tableId");
						if (value == null) {
							employee.setTableId(Table1C.createRandomID());
						} else {
							try {
								employee.setTableId(Table1C.createID(value));
							} catch (IllegalArgumentException e) {
								LOGGER.error(e);
							}
						}
					} else if (elementName.equalsIgnoreCase("RFIDTAG")) {
						String value = reader.getAttributeValue(null, "uid");
						if (value != null) {
							tag.setRfidUID(RfidUID.createUID(value));
						}
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					elementText = reader.getText().trim();
					break;
				case XMLStreamConstants.END_ELEMENT:
					elementName = reader.getLocalName();
					if (elementName.equalsIgnoreCase("EMPLOYEE")) {
						staff.add(employee.build());
					} else if (elementName.equalsIgnoreCase("FIRSTNAME")) {
						employee.setFirstName(elementText);
					} else if (elementName.equalsIgnoreCase("PATRONYM")) {
						employee.setPatronym(elementText);
					} else if (elementName.equalsIgnoreCase("LASTNAME")) {
						employee.setLastName(elementText);
					} else if (elementName.equalsIgnoreCase("BIRTHDAY")) {
						employee.setBirthday(CalendarDate
								.createDate(elementText));
					} else if (elementName.equalsIgnoreCase("POSITION")) {
						employee.setPosition(PositionType.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("DEPARTMENT")) {
						employee.setDepartment(DepartmentType
								.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("ROOM")) {
						RoomType room = RoomType.DEFAULT;
						employee.setRoom(room.select(Integer
								.valueOf(elementText)));
					} else if (elementName.equalsIgnoreCase("TABLEID")) {
						employee.setTableId(Table1C.createID(elementText));
					} else if (elementName.equalsIgnoreCase("RFIDTAG")) {
						employee.setTag(tag.build());
					} else if (elementName.equalsIgnoreCase("TAGTYPE")) {
						tag.setRfid(RfidType.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("PROTOCOL")) {
						tag.setProtocol(ProtocolType.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("ISSUE")) {
						tag.setIssue(issue.build());
					} else if (elementName.equalsIgnoreCase("ISSUEDATE")) {
						issue.setIssueDate(CalendarDate.createDate(elementText));
					} else if (elementName.equalsIgnoreCase("EXPIRATIONDATE")) {
						issue.setExpirationDate(CalendarDate
								.createDate(elementText));
					}
					break;
				default:
					break;
				}
			}
		} catch (XMLStreamException e) {
			LOGGER.error(e);
			return null;
		}

		return staff;
	}

}