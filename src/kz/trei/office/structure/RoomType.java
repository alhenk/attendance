package kz.trei.office.structure;

import java.util.HashMap;
import java.util.Map;

public enum RoomType {
	DEFAULT(1,"DEFAULT"),
	ROOM101(101, "SECURITY"),
	ROOM102(102, "ASSEMBLY"),
	ROOM103(103, "WAREHOUSE"),
	ROOM104(104, "SWITCHBOARD_PREMISES"),
	ROOM105(105, "METROLOGICAL_SERVICE"),
	ROOM106(106, "OFFICE_CANTEEN"),
	ROOM107(107, "RESEARCH_AND_DEVELOPMENT"),
	ROOM108(108, "REST_ROOM"),
	ROOM109(109, "BOILER"),
	ROOM201(201, "RECEPTION"),
	ROOM202(202, "DIRECTOR"),
	ROOM203(203, "DEPUTIES"),
	ROOM204(204, "ENGINEERING"),
	ROOM205(205, "ACCOUNTANCY"),
	ROOM206(206, "SERVER"),
	ROOM207(207, "PRODUCTION"),
	;

	private int roomNumber;
	private String roomName;

	private RoomType(int roomNumber, String roomName) {
		this.setRoomName(roomName);
		this.roomNumber = roomNumber;
	}

	private static Map<Integer, RoomType> codeToRoomTypeMapping;

	public RoomType select(int roomNumber) {
		if (codeToRoomTypeMapping == null) {
			initMapping();
		}
		return codeToRoomTypeMapping.get(roomNumber);
	}

	private void initMapping() {
		codeToRoomTypeMapping = new HashMap<Integer, RoomType>();
		for (RoomType e : values()) {
			codeToRoomTypeMapping.put(e.roomNumber, e);
		}
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
}
