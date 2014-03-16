package kz.trei.office;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kz.trei.office.util.PropertyManager;

public class Runner {
	private static final Logger LOGGER = Logger.getLogger(Runner.class);
	static {
		PropertyManager.load("configure.properties");
	}

	public static void main(String[] args) {

		String xmlfile = PropertyManager.getValue("parser.staff.xmlfile");
		String xsdfile = PropertyManager.getValue("parser.staff.xsdfile");

		// LOGGER.info("RUN SAX PARSER");
		// TaskLogic.runSaxParser(xmlfile, xsdfile);
		// LOGGER.info("RUN StAX PARSER");
		// TaskLogic.runStaxParser(xmlfile, xsdfile);
		//
		// String jaxbXmlFile =
		// PropertyManager.getValue("parser.staff.jaxb.xmlfile");
		// LOGGER.info("CREATE JAXB XMLFILE");
		// TaskLogic.createJaxbXml(jaxbXmlFile);
		// LOGGER.info("RUN JAXB PARSER");
		// TaskLogic.runJaxbParser(jaxbXmlFile);

		try {
			File file = new File(xmlfile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			//doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("tns:employee");
			System.out.println("----------------------------");
			System.out.println(nList.getLength());
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Staff id : "
							+ eElement.getAttribute("tableId"));
					System.out.println("First Name : "
							+ eElement.getElementsByTagName("firstName")
									.item(0).getTextContent());
					System.out.println("Last Name : "
							+ eElement.getElementsByTagName("lastName").item(0)
									.getTextContent());
					System.out.println("Birthday : "
							+ eElement.getElementsByTagName("birthday").item(0)
									.getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
