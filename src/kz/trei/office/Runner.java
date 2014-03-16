package kz.trei.office;

import org.apache.log4j.Logger;
import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}

	public static void main(String[] args) {

		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");

		LOGGER.info("RUN SAX PARSER");
		TaskLogic.runSaxParser(xmlfile, xsdfile);
		LOGGER.info("RUN StAX PARSER");
		TaskLogic.runStaxParser(xmlfile, xsdfile);

		String jaxbXmlFile = PropertyManager
				.getValue("parser.staff.jaxb.xmlfile");
		LOGGER.info("CREATE JAXB XMLFILE");
		TaskLogic.createJaxbXml(jaxbXmlFile);
		LOGGER.info("RUN JAXB PARSER");
		TaskLogic.runJaxbParser(jaxbXmlFile);
		LOGGER.info("RUN DOM PARSER");
		TaskLogic.runDomParser(xmlfile, xsdfile);
	}
}
