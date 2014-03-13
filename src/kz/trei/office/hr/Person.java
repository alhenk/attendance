package kz.trei.office.hr;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import kz.trei.office.util.CalendarDate;
@XmlRootElement
public abstract class Person implements Serializable, Comparable<Person> {
	private static final long serialVersionUID = -756620067328644499L;
	private String firstName;
	private String patronym;
	private String lastName;
	private CalendarDate birthday;

	public Person() {
	}

	public Person(String firstName, String patronym, String lastName,
			CalendarDate birthday) {
		this.firstName = firstName;
		this.patronym = patronym;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}
	@XmlElement
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronym() {
		return patronym;
	}
	@XmlElement
	public void setPatronym(String patronym) {
		this.patronym = patronym;
	}

	public String getLastName() {
		return lastName;
	}
	@XmlElement
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CalendarDate getBirthday() {
		return birthday;
	}
	@XmlElement
	public void setBirthday(CalendarDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((patronym == null) ? 0 : patronym.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (patronym == null) {
			if (other.patronym != null)
				return false;
		} else if (!patronym.equals(other.patronym))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	
}
