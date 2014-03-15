package kz.trei.office.rfid;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "rfidType")
@XmlEnum
public enum RfidType {
	CARD, KEYFOB, STICKER;
}
