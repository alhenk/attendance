package kz.trei.office.structure;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "position")
@XmlEnum
public enum PositionType {
	@XmlEnumValue("GENERAL_DIRECTOR")
	GENERAL_DIRECTOR,
	@XmlEnumValue("COMMERCIAL_DIRECTOR")
	COMMERCIAL_DIRECTOR,
	@XmlEnumValue("DEPARTMENT_HEAD")
	DEPARTMENT_HEAD,
	@XmlEnumValue("SECRETARY_ADVISER")
	SECRETARY_ADVISER,
	@XmlEnumValue("TECHNICAL_DIRECTOR")
	TECHNICAL_DIRECTOR,
	@XmlEnumValue("SYSTEM_ADMINISTRATOR")
	SYSTEM_ADMINISTRATOR,
	@XmlEnumValue("CHIEF_ACCOUNTANT")
	CHIEF_ACCOUNTANT,
	@XmlEnumValue("DEPUTY_CHIEF_ACCOUNTANT")
	DEPUTY_CHIEF_ACCOUNTANT,
	@XmlEnumValue("ACCOUNTANT_MATERIALISTS")
	ACCOUNTANT_MATERIALISTS,
	@XmlEnumValue("HR_ADMINISTRATION")
	HR_ADMINISTRATION,
	@XmlEnumValue("WAREHOUSE_MANAGER")
	WAREHOUSE_MANAGER,
	@XmlEnumValue("EQUIPMENT_ENGINEER")
	EQUIPMENT_ENGINEER,
	@XmlEnumValue("ADMINISTRATIVE_SERVICE_UTILITY_MANAGER")
	ADMINISTRATIVE_SERVICE_UTILITY_MANAGER,
	@XmlEnumValue("DRIVER")
	DRIVER,
	@XmlEnumValue("SECURITY_GUARD")
	SECURITY_GUARD,
	@XmlEnumValue("JANITOR")
	JANITOR,
	@XmlEnumValue("ENGINEER")
	ENGINEER,
	@XmlEnumValue("SOFTWARE_ENGINEER")
	SOFTWARE_ENGINEER,
	@XmlEnumValue("SENIOR_SOFTWARE_ENGINEER")
	SENIOR_SOFTWARE_ENGINEER,
	@XmlEnumValue("LEAD_SOFTWARE_ENGINEER")
	LEAD_SOFTWARE_ENGINEER,
	@XmlEnumValue("COMMISSIONING_ENGINEER")
	COMMISSIONING_ENGINEER,
	@XmlEnumValue("APCS_ENGINEER")
	APCS_ENGINEER,
	@XmlEnumValue("LEAD_APCS_ENGINEER")
	LEAD_APCS_ENGINEER,
	@XmlEnumValue("LEAD_DESIGNER")
	LEAD_DESIGNER,
	@XmlEnumValue("DESIGN_ENGINEER")
	DESIGN_ENGINEER,
	@XmlEnumValue("ELECTRONICS_ENGINEER")
	ELECTRONICS_ENGINEER,
	@XmlEnumValue("SENIOR_ELECTRONICS_ENGINEER")
	SENIOR_ELECTRONICS_ENGINEER,
	@XmlEnumValue("CHIEF_METROLOGIST")
	CHIEF_METROLOGIST,
	@XmlEnumValue("ENGINEER_METROLOGIST")
	ENGINEER_METROLOGIST,
	@XmlEnumValue("CONSTRUCTION_ELECTRICIAN")
	CONSTRUCTION_ELECTRICIAN;
}
