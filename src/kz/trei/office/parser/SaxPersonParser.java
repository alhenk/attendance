package kz.trei.office.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import kz.trei.office.hr.Person;
import kz.trei.office.util.FileManager;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxPersonParser implements PersonParser{
	private static final Logger LOGGER = Logger.getLogger(SaxPersonParser.class);
	private List<Person> personnel;

	@Override
	public List<Person> parse(InputStream input) {
		personnel = new ArrayList<Person>();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				boolean bRfidTag  = false;
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
		
		
		return personnel;
	}
	
}
