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
import kz.trei.office.util.DateStamp;

public class StaxEmployeeParser implements EmployeeParser {
	private static final Logger LOGGER = Logger
			.getLogger(StaxEmployeeParser.class);

	@Override
	public List<Person> parse(String xmlfile, String xsdfile)
			throws XmlParserException {
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

		Employee.Builder employee = new Employee.Builder();
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
						employee = new Employee.Builder();
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
						tag = new RfidTag.Builder();
						String value = reader.getAttributeValue(null, "uid");
						if (value != null) {
							tag.setRfidUID(RfidUID.createUID(value));
						}
					} else if (elementName.equalsIgnoreCase("ISSUE")){
						issue = new Issue.Builder();
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
						employee.setBirthday(DateStamp.create(elementText));
					} else if (elementName.equalsIgnoreCase("POSITION")) {
						employee.setPosition(PositionType.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("DEPARTMENT")) {
						employee.setDepartment(DepartmentType
								.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("ROOM")) {
						RoomType room = RoomType.DEFAULT;
						employee.addRoom(room.select(Integer
								.valueOf(elementText)));
					} else if (elementName.equalsIgnoreCase("TABLEID")) {
						employee.setTableId(Table1C.createID(elementText));
					} else if (elementName.equalsIgnoreCase("RFIDTAG")) {
						employee.setTag(tag.build());
					} else if (elementName.equalsIgnoreCase("TAGTYPE")) {
						tag.setRfidType(RfidType.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("PROTOCOL")) {
						tag.setProtocol(ProtocolType.valueOf(elementText));
					} else if (elementName.equalsIgnoreCase("ISSUE")) {
						tag.setIssue(issue.build());
					} else if (elementName.equalsIgnoreCase("ISSUEDATE")) {
						issue.setIssueDate(DateStamp.create(elementText));
					} else if (elementName.equalsIgnoreCase("EXPIRATIONDATE")) {
						issue.setExpirationDate(DateStamp.create(elementText));
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
