package kz.trei.office;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;

import kz.trei.office.hr.Employee;
import kz.trei.office.hr.Staff;
import kz.trei.office.rfid.RfidTag;
import kz.trei.office.rfid.RfidUID;
import kz.trei.office.structure.DepartmentType;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.structure.Table1C;
import kz.trei.office.util.DateStamp;
import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}

	public static void main(String[] args) {

		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");

//		LOGGER.info("RUN SAX PARSER");
//		TaskLogic.runSaxParser(xmlfile, xsdfile);
//		LOGGER.info("RUN StAX PARSER");
//		TaskLogic.runStaxParser(xmlfile, null);
//		String jaxbXmlFile = PropertyManager
//				.getValue("parser.staff.jaxb.xmlfile");
//		LOGGER.info("CREATE JAXB XMLFILE");
//		TaskLogic.createJaxbXml(jaxbXmlFile);
//		LOGGER.info("RUN JAXB PARSER");
//		TaskLogic.runJaxbParser(jaxbXmlFile, null);
////		TaskLogic.runJaxbParser(xmlfile, null);
//		LOGGER.info("RUN DOM PARSER");
//		TaskLogic.runDomParser(xmlfile, xsdfile);
		
		JAXBContext jaxbContext;
		try {
			SchemaOutputResolver sor = new MySchemaOutputResolver();
			jaxbContext = JAXBContext.newInstance(Staff.class, Employee.class,
					RfidTag.class, DepartmentType.class, PositionType.class, RoomType.class
					, RfidUID.class, Table1C.class, DateStamp.class);
			jaxbContext.generateSchema(sor);
		} catch (JAXBException e) {
			LOGGER.info(e);
		}catch (IOException e) {
			LOGGER.info(e);
		}
		
		 
	}
	
	public static class MySchemaOutputResolver extends SchemaOutputResolver {

	    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
	        File file = new File(suggestedFileName);
	        StreamResult result = new StreamResult(file);
	        result.setSystemId(file.toURI().toURL().toString());
	        return result;
	    }

	}
}
