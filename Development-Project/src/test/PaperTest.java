package test;

import static org.junit.Assert.*;

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
				new HashMap<String, String>(), "C:\\recommendation", true, true, 4,
				new HashMap<String, Integer>());
		
		Map<String, String> reviews = new HashMap<String, String>();
		reviews.put("Kyle Martens", "D:\\KyleReview");
		reviews.put("Kim Stuart", "D:\\KimReview");
		reviews.put("Mick Johnson", "D:\\MickReview");
		reviews.put("Stevan Lewis", "D:\\StevanReview");
		Map<String, Integer> scores = new HashMap<String, Integer>();
		scores.put("Kyle Martens", 9);
		scores.put("Kim Stuart", 5);
		scores.put("Mick Johnson", 7);
		scores.put("Stevan Lewis", 2);		
		paper4 = new Paper("The Adventures of Alan Fowler pt 2", "D:\\TopSecret",
				reviews, "D:\\Recommendinator", true, true, 2, scores);
	}
	
	/**
	 * test code for the assignToReviewer method
	 */
	@Test
	public void testAssignToReviewer() {
		paper.assignToReviewer("Kyle Martens");
		assertTrue(paper.isAssignedToReviewer("Kyle Martens"));
		assertFalse(paper3.isAssignedToReviewer("Stevan Lewis"));
		
		paper2.assignToReviewer(null);
		assertFalse(paper2.isAssignedToReviewer("Kim Stuart"));
	}

	/**
	 * test code for the assignToSPC method
	 */
	@Test
	public void testAssignToSPC() {
		paper.assignToSPC(true);
		assertTrue(paper.isAssignedToSPC());
		paper.assignToSPC(false);
		assertFalse(paper.isAssignedToSPC());		
		paper3.assignToSPC(false);
		assertFalse(paper3.isAssignedToSPC());
	}
	
	/**
	 * test code for the isAssignedToReviewer method
	 */
	@Test
	public void testIsAssignedToReviewer() {
		assertFalse(paper.isAssignedToReviewer("Kim Stuart"));
		assertFalse(paper3.isAssignedToReviewer("Kyle Martens"));
		assertTrue(paper4.isAssignedToReviewer("Mick Johnson"));
	}
	
	/**
	 * test code for the isAssignedToSPC method
	 */
	@Test
	public void testIsAssignedToSPC() {
		assertFalse(paper2.isAssignedToSPC());
		assertTrue(paper3.isAssignedToSPC());
	}
	
	/**
	 * test code for the isReviewed method
	 */
	@Test
	public void testIsReviewed() {
		assertFalse(paper.isReviewed("someone"));
		assertFalse(paper.isAssignedToReviewer(null));
		assertFalse(paper3.isReviewed("Kyle Martens"));
		assertTrue(paper4.isReviewed("Mick Johnson"));
		assertFalse(paper4.isReviewed("Alan Fowler"));
	}

	/**
	 * test code for the isRecommended method
	 */
	@Test
	public void testIsRecommended() {
		assertFalse(paper.isRecommended());
		assertFalse(paper2.isRecommended());
		assertTrue(paper3.isRecommended());
		assertTrue(paper4.isRecommended());
	}

	/**
	 * test code for the updateStatus method
	 */
	@Test
	public void testUpdateStatus() {
		paper.updateStatus(true);
		assertEquals(paper.getStatus(), "Accepted");
		paper.updateStatus(false);
		assertEquals(paper.getStatus(), "Rejected");
		assertNotEquals(paper.getStatus(), "Undecided");
	}

	/**
	 * test code for the assignReview method
	 */
	@Test
	public void testAssignReview() {
		paper.assignReview("Kim Stuart", "C:\\It's OK");
		assertTrue(paper.isReviewed("Kim Stuart"));
		assertFalse(paper.isReviewed("Stevan Lewis"));
		paper.assignReview("Mick Johnson", "C:\\WONDERFUL");
		paper.assignReview("Kim Stuart", "C:\\OK it's not bad");
		paper.assignReview("Stevan Lewis", "C:\\Nitpicky but a good read");
		assertTrue(paper.isReviewed("Kim Stuart"));
		assertTrue(paper.getReviews().contains("C:\\WONDERFUL"));
		assertFalse(paper.getReviews().contains("C:\\It's OK"));
		assertEquals(paper.getReviewForReviewer("Stevan Lewis"), "C:\\Nitpicky but a good read");
		assertNotEquals(paper.getReviewForReviewer("Mick Johnson"), "C:\\crap crap crap");
		
		paper2.assignReview(null, null);
		assertNotEquals(paper2.getReviewForReviewer("Kyle Martens"), "C:\\I hated this paper!");
	}

	/**
	 * test code for the assignToRecommendation method
	 */
	@Test
	public void testAssignRecommendation() {
		paper.assignRecommendation("C:\\Perfect", 10);
		assertEquals(paper.getRecommendation(), "C:\\Perfect");
		assertEquals(paper.getRating(), 10);
		paper.assignRecommendation("C:\\I changed my mind", 6);
		assertNotEquals(paper.getRecommendation(), "C:\\Perfect");
		assertNotEquals(paper.getRating(), 10);
		assertEquals(paper.getRecommendation(), "C:\\I changed my mind");
		assertEquals(paper.getRating(), 6);
		
		paper2.assignRecommendation(null, 0);
		assertNotEquals(paper.getRecommendation(), "C:\\Perfect");
	}
}