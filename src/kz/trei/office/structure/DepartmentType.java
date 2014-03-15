package kz.trei.office.structure;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "department")
@XmlEnum
public enum DepartmentType {
	ACCOUNTANCY,
	RESEARCH_AND_DEVELOPMENT,
	COMMERCIAL,
	WAREHOUSE,
	ASSEMBLY,
	PRODUCTION,
	ENGINEERING,
	METROLOGICAL_SERVICE,
	VERIFICATION_LABORATORY,
	ADMINISTRATIVE_SERVICE_UTILITY,
	NONE;
}
