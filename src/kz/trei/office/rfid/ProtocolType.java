package kz.trei.office.rfid;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "protocol")
@XmlEnum
public enum ProtocolType {
	ISO14443A, ISO14443B, ISO15693; 
}
