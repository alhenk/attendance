package kz.trei.office.hr;

import kz.trei.office.rfid.RfidTag;
import kz.trei.office.structure.DepartmentType;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.structure.Table1C;
import kz.trei.office.util.CalendarDate;

public class Employee extends Person {
	private static final long serialVersionUID = -8363247132437924285L;
	private PositionType position;
	private DepartmentType department;
	private RoomType room;
	private Table1C tableId;
	private RfidTag tag;

	public Employee() {
	}

	public Employee(Table1C tableId) {
		this.tableId = tableId;
	}

	public Employee(PositionType position, DepartmentType department,
			RoomType room, Table1C tableId, RfidTag tag) {
		super();
		this.position = position;
		this.department = department;
		this.room = room;
		this.tableId = tableId;
		this.tag = tag;
	}

	public PositionType getPosition() {
		return position;
	}

	public void setPosition(PositionType position) {
		this.position = position;
	}

	public DepartmentType getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentType department) {
		this.department = department;
	}

	public RoomType getRoom() {
		return room;
	}

	public void setRoom(RoomType room) {
		this.room = room;
	}

	public Table1C getTableId() {
		return tableId;
	}

	public void setTableId(Table1C tableId) {
		this.tableId = tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = Table1C.createID(tableId);
	}

	public RfidTag getTag() {
		return tag;
	}

	public void setUid(RfidTag uid) {
		this.tag = uid;
	}

	public static class Builder {
		private Table1C tableId;
		private String firstName;
		private String patronym;
		private String lastName;
		private CalendarDate birthday;
		private PositionType position;
		private DepartmentType department;
		private RoomType room;
		private RfidTag rfidTag;
	
		public Builder setTableId(Table1C tableId) {
			this.tableId = tableId;
			return this;
		}
		
		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setPatronym(String patronym) {
			this.patronym = patronym;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setBirthday(CalendarDate birthday) {
			this.birthday = birthday;
			return this;
		}

		public Builder setPosition(PositionType position) {
			this.position = position;
			return this;
		}

		public Builder setDepartment(DepartmentType department) {
			this.department = department;
			return this;
		}

		public Builder setRoom(RoomType room) {
			this.room = room;
			return this;
		}

		public Builder setTag(RfidTag tag) {
			this.rfidTag = tag;
			return this;
		}

		public Person build() {
			Person person;
			person = new Employee();
			((Employee) person).setTableId(tableId);
			((Employee) person).setBirthday(birthday);
			((Employee) person).setDepartment(department);
			((Employee) person).setFirstName(firstName);
			((Employee) person).setLastName(lastName);
			((Employee) person).setPatronym(patronym);
			((Employee) person).setPosition(position);
			((Employee) person).setRoom(room);
			((Employee) person).setUid(rfidTag);
			return person;
		}
	}

	@Override
	public String toString() {
		return "Employee [" + this.getLastName() + ", uid = "
				+ tag.getUid().getValue() + "tableID = "+this.tableId.getId()+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department != other.department)
			return false;
		if (position != other.position)
			return false;
		if (room != other.room)
			return false;
		if (tableId == null) {
			if (other.tableId != null)
				return false;
		} else if (!tableId.equals(other.tableId))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}

	@Override
	public int compareTo(Person person) {
		return super.getLastName().toLowerCase()
				.compareTo(person.getLastName().toLowerCase());
	}
}
