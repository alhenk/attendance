package kz.trei.office.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class DateStampTest {
	private static final Logger LOGGER = Logger.getLogger(TimeStampTest.class);
	private String dateStamp = "2014-03-14";

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	private Date testDate = null;
	
	@Before
	public void setUp(){
		try {
			Date d = dateformat.parse(dateStamp);
			testDate = new Date(d.getTime() );
		} catch (ParseException e) {
			LOGGER.error(e);
		}
	}
	
	@Test
	public void test() {
		DateStamp ds = DateStamp.create(dateStamp);
		assertTrue(ds.getDate().equals(dateStamp));
		ds = DateStamp.create(testDate);
		assertTrue(ds.getDate().equals(dateStamp));
		assertTrue(DateStamp.isValid(dateStamp));
		assertFalse(DateStamp.isValid("2014-13-03"));
	}

}
