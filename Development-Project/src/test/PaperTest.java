package test;

import static org.junit.Assert.*;
import model.Conference;
import model.Paper;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {

	Conference test;
	Paper paper;
	@Before
	public void setup()
	{
//		test = new Conference("conference1");
//		test.assignRole("Stevan", "Spc");
//		test = new Conference("conference1", "ACFowler");
//		test.assignRole("Stevan", "Spc");
	}

	@Test
	public void testAssignReview() {
		Paper paper1 = new Paper("The Adventures of Alan Fowler", "c:/idk");
		Paper paper2 = new Paper(null, null);
		
		paper1.assignRecommendation("johnsmith1", "c:/**");
		
//		assertEquals(paper1.myReview, "c:/**");
//		assertEquals(paper2.myReview, null);
//		assertEquals(paper1.myIsReviewed, true);
	}

}
