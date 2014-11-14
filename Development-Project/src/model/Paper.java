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

    public String mySPC;
    
    public String[] myReviewers;
    
    public String myReview;
    
    public String[] myReviews;
	
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
	public Paper(String theReview, String theRecommendation) {
		
		theReview = null;
		theRecommendation = null;
		myAssignedToReviewer = false;
		myAssignedToSPC = false;
		myIsReviewed = false;
		myAccepted = 0;
	    mySPC = null;
		myRecommendation = null;
		
		myReviewers = new String[4];
		myReviews = new String[5];
	}
	
	/**
	 * this method will determine if the paper has been assigned to a reviewer.
	 * @return false if the paper has not been assigned to a reviewer or true if it has.
	 */
	public boolean setAssignedToReviewer(String theReviewer) {
		boolean result = false;
		if(myReviewers[0] == null) {
			myReviewers[0] = theReviewer;
			result = true;
		} else if(myReviewers[0] != null) {
			myReviewers[1] = theReviewer;
			result = true;
		}else if (myReviewers[1] != null) {
		    myReviewers[2] = theReviewer;
		    result = true;
		} else if (myReviewers[2]!= null) {
			myReviewers[3] = theReviewer;
			result = true;
		}else if(myReviewers[3] != null){
			System.out.println("This paper cannot accept any more reviewers");
			result = true;
		}
		return result;
	}
	
	/**
	 * this method will determine if the paper has been assigned to a SPC.
	 * @return false if the paper has not been assigned to a SPC or true if it has.
	 */
	public void setAssignedToSPC(String theSPC) {
		if(myAssignedToSPC == true) {
			System.out.println("A Subprogram Chair has already been assigned to this paper.");
		} else {
			mySPC = theSPC;
			myAssignedToSPC = true;
		}
	}
	
	/**
	 * Getter method to return is an SPC has been assigned to a paper.
	 * @return boolean true if a SPC has been assigned and false if a SPC has not been assigned to a paper.
	 */
	public boolean isAssignedToSPC() {
		return myAssignedToSPC;
	}
	
	/**
	 * this method will determine if the paper has been reviewed by the reviewer.
	 * @return false if the paper has been reviewed or true if it has.
	 */
	public boolean isReviewed() {
		return myIsReviewed;
	}
	
	/**
	 * this method will allow the PC to set the status of a paper based on his or her decision.
	 * @param theDecision -1 if rejected, 0 if undecided or 1 if accepted.
	 */
	public void setStatus(final String theDecision) {
		if (theDecision.equals("accepted".toLowerCase())) {
			myAccepted = 1;
		} else if (theDecision.equals("rejected".toLowerCase())) {
			myAccepted = -1;
		}else {
			myAccepted = 0;
		}
	}
	
	/**
	 * this method will allow the SPC to claim a paper to be reviewed
	 */
	public void assignReview(String theUserName, String theFilePath) {
		myReview = theFilePath;
		myIsReviewed = true;
	}
	
	/**
	 * this method will allow the Reviewer to make a recommendation to the SPC or the SPC to make a recommendation to the PC.
	 * @param theUsername ?
	 * @param theFilePath the path in the directory where the recommendation is stored.
	 */
	public void assignRecommendation(String theUsername, String theFilePath) {
		myRecommendation = theFilePath;
		myAssignedToSPC = true;
	}
}
