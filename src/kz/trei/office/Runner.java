package kz.trei.office;

import org.apache.log4j.Logger;

import kz.trei.office.parser.JaxbEmployeeSchema;
import kz.trei.office.parser.StaxEmployeeParser;
import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}

	public static void main(String[] args) {

		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");

		String jaxbXmlFile = PropertyManager
				.getValue("parser.staff.jaxb.xmlfile");
		String jaxbXsdFile = PropertyManager
				.getValue("parser.staff.jaxb.auto.xsdfile");

		LOGGER.info("RUN SAX PARSER");
		TaskLogic.runSaxParser(xmlfile, xsdfile);
		
		LOGGER.info("RUN StAX VALIDATOR");
		StaxEmployeeParser.isValid(xmlfile,xsdfile);
		LOGGER.info("RUN StAX PARSER");
		TaskLogic.runStaxParser(xmlfile, "");

		LOGGER.info("CREATE JAXB XMLFILE");
		TaskLogic.createJaxbXml(jaxbXmlFile);

		LOGGER.info("CREATE JAXB SCHEMA XSDFILE");
		JaxbEmployeeSchema.generateXsd("./resources/jaxb_schema.xsd");

		LOGGER.info("RUN JAXB PARSER");
		TaskLogic.runJaxbParser(jaxbXmlFile, jaxbXsdFile);
		// TaskLogic.runJaxbParser(xmlfile, null);
		LOGGER.info("RUN DOM PARSER");
		TaskLogic.runDomParser(xmlfile, xsdfile);
	}
}
