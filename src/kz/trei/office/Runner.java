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
		LOGGER.info("RUN SAXPARSER");
		TaskLogic.runSaxParser(xmlfile, xsdfile);
		LOGGER.info("RUN StAXPARSER");
		TaskLogic.runStaxParser(xmlfile, xsdfile);
	}
}
