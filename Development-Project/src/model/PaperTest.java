package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {

	Conference test;
	
	@Before
	public void setup()
	{
		test = new Conference("conference1");
		test.assignRole("Stevan", "Spc");
	}
	
	
	@Test
	public void testgetPapers() {
		assertEquals(null, test.getPapers("Stevan", "Spc"));
	}
	


}
