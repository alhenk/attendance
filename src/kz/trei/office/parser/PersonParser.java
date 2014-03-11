package kz.trei.office.parser;

import java.io.InputStream;
import java.util.List;

import kz.trei.office.hr.Person;


public interface PersonParser {
	List<Person> parse(InputStream input);
}
