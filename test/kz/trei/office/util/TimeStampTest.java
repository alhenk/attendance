package kz.trei.office.util;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TimeStampTest {
	private static final Logger LOGGER = Logger.getLogger(TimeStampTest.class);
	private String timeStamp = "12:37:21";
	private SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");
	private Date testDate = null;

	@Before
	public void setUp() {
		try {
			Date date = timeformat.parse(timeStamp);
			testDate = new Date(date.getTime());
		} catch (ParseException e) {
			LOGGER.error(e);
		}
	}

	@Test
	public void test() {
		TimeStamp ts = TimeStamp.create(timeStamp);
		assertTrue(ts.getTime().equals(timeStamp));
		ts = TimeStamp.create(testDate);
		assertTrue(ts.getTime().equals(timeStamp));
		assertTrue(TimeStamp.isValid("12:37:14"));
		assertFalse(TimeStamp.isValid("37:12:14"));
	}
}
