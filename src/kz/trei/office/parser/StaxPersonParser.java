package kz.trei.office.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Person;

public class StaxPersonParser implements PersonParser {
	private static final Logger LOGGER = Logger
			.getLogger(StaxPersonParser.class);

	@Override
	public List<Person> parse(String xmlfile, String xsdfile)
			throws SaxParserException {
		InputStream inputStream=null;
		try {
			inputStream = new FileInputStream(xmlfile);
		} catch (FileNotFoundException e) {
			LOGGER.info(e);
		}
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader;
		String element;
		try {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					element = reader.getLocalName();
					if (element.equalsIgnoreCase("Staff")) {
						LOGGER.info("staff");
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

		return null;
	}

}
