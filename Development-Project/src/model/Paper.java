package model;
/**
 * This method establishes the characteristics of a paper.
 * 
 * @author Kim Stuart
 * @author Kyle Martens
 * @author Stevan Lewis
 * @author Mickey Johnson
 * @version autumn 2014
 *
 */

public class Paper {


	public String myReview;
	
	public String myRecommendation;
	
	public boolean myAssignedToReviewer;
	
	public boolean myAssignedToSPC;
	
	public boolean myIsReviewed;
	
	public int myAccepted;
	
	/**
	 * Constructor for a paper.
	 * @param theReview initially the paper has not yet been reviewed so it is null.
	 * @param theRecommendation initially the paper has not been given a recommendation so it is null.
	 * @param theAssignedToReviewer  initially the paper has not been assigned to a reviewer to review so its status is false.
	 * @param theAssignedToSPC initially the PC has not assigned this paper to a SPC so its status is false.
	 * @param theIsReviewed initially the paper has not been reviewed so it is false.
	 * @param theAccepted  initially the PC has not made a decision until is goes through the review and scoring process, so its int is 0 to represent undecided.
	 */
	public Paper(String theReview, String theRecommendation,
			boolean theAssignedToReviewer, boolean theAssignedToSPC,
			boolean theIsReviewed, int theAccepted) {
		
		theReview = null;
		theRecommendation = null;
		theAssignedToReviewer = false;
		theAssignedToSPC = false;
		theIsReviewed = false;
		theAccepted = 0;
	}
	
	/**
	 * this method will determine if the paper has been assigned to a reviewer.
	 * @return false if the paper has not been assigned to a reviewer or true if it has.
	 */
	public boolean setAssignedToReviewer() {
		
		return false;
	}
	
	/**
	 * this method will determine if the paper has been assigned to a SPC.
	 * @return false if the paper has not been assigned to a SPC or true if it has.
	 */
	public boolean setAssignedToSPC() {
		
		return false;
	}
	
	/**
	 * this method will determine if the paper has been reviewed by the reviewer.
	 * @return false if the paper has been reviewed or true if it has.
	 */
	public boolean setIsReviewed() {
		
		return false;
	}
	
	/**
	 * this method will allow the PC to set the status of a paper based on his or her decision.
	 * @param theDecision -1 if rejected, 0 if undecided or 1 if accepted.
	 */
	public void setStatus(final int theDecision) {
		
	}
	
	/**
	 * this method will allow the SPC to claim a paper to be reviewed
	 */
	public void setReview() {
		
	}
	
	/**
	 * this method will allow the Reviewer to make a recommendation to the SPC or the SPC to make a recommendation to the PC.
	 */
	public void setRecommendation() {
		
	}
	
}
