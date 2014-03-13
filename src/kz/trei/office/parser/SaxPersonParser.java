package kz.trei.office.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

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
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxPersonParser implements PersonParser {
	
	private static final Logger LOGGER = Logger
			.getLogger(SaxPersonParser.class);
	private List<Person> staff;

	@Override
	public List<Person> parse(String xmlfile, String xsdfile)
			throws SaxParserException {
		staff = new ArrayList<Person>();
		try {
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(W3C_XML_SCHEMA);
			Schema schema = schemaFactory.newSchema(new File(xsdfile));
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(false);// "true" for using DTD!
			factory.setSchema(schema);
			factory.setNamespaceAware(true);
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new PersonHandler();
			saxParser.parse(xmlfile, handler);
		} catch (ParserConfigurationException e) {
			LOGGER.error(e);
			throw new SaxParserException(e);
		} catch (SAXException e) {
			LOGGER.error(e);
			throw new SaxParserException(e);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new SaxParserException(e);
		}
		return staff;
	}

	private class PersonHandler extends DefaultHandler {
		Employee.Builder employee;
		RfidTag.Builder tag;
		Issue.Builder issue;
		StringBuilder elementValue;

		public PersonHandler() {
		}

		public void startDocument() {
			employee = new Employee.Builder();
			tag = new RfidTag.Builder();
			elementValue = new StringBuilder();
			issue = new Issue.Builder();
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			elementValue.setLength(0);
			if (qName.equalsIgnoreCase("tns:EMPLOYEE")) {
				String value = attributes.getValue("tableId");
				if (value == null) {
					employee.setTableId(Table1C.createRandomID());
				} else {
					try {
						employee.setTableId(Table1C.createID(value));
					} catch (IllegalArgumentException e) {
						LOGGER.error(e);
					}
				}
			} else if (qName.equalsIgnoreCase("RFIDTAG")) {
				String value = attributes.getValue("uid");
				if (value != null) {
					tag.setRfidUID(RfidUID.createUID(value));
				}
			}
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if (qName.equalsIgnoreCase("tns:EMPLOYEE")) {
				staff.add(employee.build());
			} else if (qName.equalsIgnoreCase("FIRSTNAME")) {
				employee.setFirstName(elementValue.toString().trim());
			} else if (qName.equalsIgnoreCase("PATRONYM")) {
				employee.setPatronym(elementValue.toString().trim());
			} else if (qName.equalsIgnoreCase("LASTNAME")) {
				employee.setLastName(elementValue.toString().trim());
			} else if (qName.equalsIgnoreCase("BIRTHDAY")) {
				employee.setBirthday(DateStamp.createDate(elementValue
						.toString().trim()));
			} else if (qName.equalsIgnoreCase("POSITION")) {
				employee.setPosition(PositionType.valueOf(elementValue
						.toString().trim()));
			} else if (qName.equalsIgnoreCase("DEPARTMENT")) {
				employee.setDepartment(DepartmentType.valueOf(elementValue
						.toString().trim()));
			} else if (qName.equalsIgnoreCase("ROOM")) {
				RoomType room = RoomType.DEFAULT;
				employee.setRoom(room.select(Integer.valueOf(elementValue
						.toString().trim())));
			} else if (qName.equalsIgnoreCase("TABLEID")) {
				employee.setTableId(Table1C.createID(elementValue.toString()
						.trim()));
			} else if (qName.equalsIgnoreCase("RFIDTAG")) {
				employee.setTag(tag.build());
			} else if (qName.equalsIgnoreCase("TAGTYPE")) {
				tag.setRfid(RfidType.valueOf(elementValue.toString().trim()));
			} else if (qName.equalsIgnoreCase("PROTOCOL")) {
				tag.setProtocol(ProtocolType.valueOf(elementValue.toString()
						.trim()));
			} else if (qName.equalsIgnoreCase("ISSUE")) {
				tag.setIssue(issue.build());
			} else if (qName.equalsIgnoreCase("ISSUEDATE")) {
				issue.setIssueDate(DateStamp.createDate(elementValue
						.toString().trim()));
			} else if (qName.equalsIgnoreCase("EXPIRATIONDATE")) {
				issue.setExpirationDate(DateStamp.createDate(elementValue
						.toString().trim()));
			}
		}

		public void characters(char ch[], int start, int length) {
			elementValue.append(new String(ch, start, length));
		}

		public void endDocument() {
		}

		public void ignorableWhitespace(char[] cbuf, int start, int len) {
		}

		public void processingInstruction(String target, String data) {
		}

		public void warning(SAXParseException exception) {
			LOGGER.error("WARNING: line " + exception.getLineNumber() + ": "
					+ exception.getMessage());
		}

		public void error(SAXParseException exception) {
			LOGGER.error("ERROR: line " + exception.getLineNumber() + ": "
					+ exception.getMessage());
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			LOGGER.error("FATAL: line " + exception.getLineNumber() + ": "
					+ exception.getMessage());
			throw (exception);
		}
	}
}