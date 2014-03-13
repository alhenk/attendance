package kz.trei.office.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateStampTest {
	@Before
	public void setUp(){
		
		
	}
	
	@After
	public void tearDown(){
		
	}

	@Test
	public void test() {
		assertTrue(DateStamp.isValid("2014-03-13"));
		assertFalse(DateStamp.isValid("2014-13-03"));
		//assertEquals("Test getLine()", "First line.", doc.getLine(0));
	}

}
