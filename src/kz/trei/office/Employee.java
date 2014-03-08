package kz.trei.office;

import java.util.Date;
import java.util.UUID;

import kz.trei.office.structure.DepartmentType;
import kz.trei.office.structure.PositionType;
import kz.trei.office.structure.RoomType;
import kz.trei.office.structure.Table;

public class Employee extends Person {
	private static final long serialVersionUID = -8363247132437924285L;
	private final UUID uuid;
	private PositionType position; // enum PositionType
	private DepartmentType department; // enum DepartmentType
	private RoomType room; // enum RoomType
	private Table tableId;
	private RfidTag uid;

	public Employee() {
		uuid = UUID.randomUUID();
	}

	public Employee(UUID uuid) {
		this.uuid = uuid;
	}

	public Employee(PositionType position, DepartmentType department,
			RoomType room, Table tableId, RfidTag uid) {
		super();
		this.uuid = UUID.randomUUID();
		this.position = position;
		this.department = department;
		this.room = room;
		this.tableId = tableId;
		this.uid = uid;
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

	public Table getTableId() {
		return tableId;
	}

	public void setTableId(Table tableId) {
		this.tableId = tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = Table.createID(tableId);
	}

	public RfidTag getUid() {
		return uid;
	}

	public void setUid(RfidTag uid) {
		this.uid = uid;
	}

	public static class Builder {
		private UUID uuid;
		private String firstName;
		private String patronym;
		private String lastName;
		private Date birthday;
		private PositionType position;
		private DepartmentType department;
		private RoomType room;
		private Table tableId;
		private RfidTag uid;

		public Builder setUUID(String uuid) {
			this.uuid = UUID.fromString(uuid);
			return this;
		}

		public Builder setUUID(UUID uuid) {
			this.uuid = uuid;
			return this;
		}

		public Builder randomUUID() {
			this.uuid = UUID.randomUUID();
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

		public Builder setBirthday(Date birthday) {
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

		public Builder setTableId(Table tableId) {
			this.tableId = tableId;
			return this;
		}

		public Builder setUid(RfidTag uid) {
			this.uid = uid;
			return this;
		}

		public Person build() {
			Person person;
			if (this.uuid != null) {
				person = new Employee(this.uuid);
			} else {
				person = new Employee();
			}
			((Employee) person).setBirthday(birthday);
			((Employee) person).setDepartment(department);
			((Employee) person).setFirstName(firstName);
			((Employee) person).setLastName(lastName);
			((Employee) person).setPatronym(patronym);
			((Employee) person).setPosition(position);
			((Employee) person).setRoom(room);
			((Employee) person).setTableId(tableId);
			((Employee) person).setUid(uid);
			return person;
		}
	}

	@Override
	public String toString() {
		return "Employee [position=" + position + ", uid=" + uid + "]";
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
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (tableId == null) {
			if (other.tableId != null)
				return false;
		} else if (!tableId.equals(other.tableId))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public int compareTo(Person person) {
		return super.getLastName().toLowerCase()
				.compareTo(person.getLastName().toLowerCase());
	}

	public UUID getUuid() {
		return uuid;
	}
}
