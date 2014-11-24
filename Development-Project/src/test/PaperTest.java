package test;

import java.util.HashMap;
import java.util.Map;

import model.Paper;

import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	
	/**
	 * a few papers used for testing
	 */
	private Paper paper, paper2, paper3, paper4;
	
	/**
	 * constructing the paper objects for use later on
	 */
	@Before
	public void setup()
	{
		paper = new Paper("The Adventures of Alan Fowler", "C:\\idk");
		paper2 = new Paper(null, null);
		paper3 = new Paper("Random Title","C:\\Users\\Kyle\\My Documents\\Random Title",
				new HashMap<String, String>(), "C:\\recommendation", true, true, 4, 1);
		
		Map<String, String> reviews = new HashMap<String, String>();
		reviews.put("Kyle Martens", "D:\\KyleReview");
		reviews.put("Kim Stuart", "D:\\KimReview");
		reviews.put("Mick Johnson", "D:\\MickReview");
		reviews.put("Stevan Lewis", "D:\\StevanReview");
		paper4 = new Paper("The Adventures of Alan Fowler pt 2", "D:\\TopSecret",
				reviews, "D:\\Recommended", true, true, 2, -1);
	}
	
	/**
	 * test code for the assignToReviewer method
	 */
	@Test
	public void testAssignToReviewer() {
		
	}

	/**
	 * test code for the assignToSPC method
	 */
	@Test
	public void testAssignToSPC() {
		
	}

	/**
	 * test code for the isReviewed method
	 */
	@Test
	public void testIsReviewed() {
		
	}

	/**
	 * test code for the isRecommended method
	 */
	@Test
	public void testIsRecommended() {
		
	}

	/**
	 * test code for the updateStatus method
	 */
	@Test
	public void testUpdateStatus() {
		
	}

	/**
	 * test code for the assignReview method
	 */
	@Test
	public void testAssignReview() {
		
	}

	/**
	 * test code for the assignToRecommendation method
	 */
	@Test
	public void testAssignRecommendation() {
		
	}
}