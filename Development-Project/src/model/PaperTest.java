package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {

	Conference test;
	Paper paper;
	@Before
	public void setup()
	{
		test = new Conference("conference1");
		test.assignRole("Stevan", "Spc");
		test = new Conference("conference1");
		test.assignRole("Stevan", "Spc");
		paper.setAssignedToReviewer("Stevan");
		paper.setAssignedToReviewer("Rick");
	}
	
	
	@Test
	public void testgetPapers() {
		assertEquals(null, test.getPapers("Stevan", "Spc"));
	}
	
	@Test
	public void testassignRole() {
		test.assignRole("George", "Author");
		test.Authors.keySet().contains("George");
		
		test.assignRole("", "");
	}
	
	@Test
	public void testAssignReview() {
		Paper paper1 = new Paper("The Adventures of Alan Fowler", "c:/idk");
		Paper paper2 = new Paper(null, null);
		
		paper1.assignReview("johnsmith1", "c:/**");
		
		assertEquals(paper1.myReview, "c:/**");
		assertEquals(paper2.myReview, null);
		assertEquals(paper1.myIsReviewed, true);
	}

}
