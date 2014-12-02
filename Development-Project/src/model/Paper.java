package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class establishes the characteristics of a paper.
 * 
 * @author Kim Stuart
 * @author Kyle Martens
 * @author Stevan Lewis
 * @author Mickey Johnson
 * @version autumn 2014
 *
 */

public class Paper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7721497510495346947L;

	/**
	 * the Title of the paper
	 */
    private String myTitle;
	
    /**
     * the file path of the paper on the local file system
     */
    private String myFilePath;
    
    /**
     * a map of reviewers to their respective review file paths
     */
    private Map<String, String> myReviews;
	
    /**
     * a map of reviewers to their respective summary review ratings.
     */
    private Map<String, Integer> myReviewRatings;
    /**
     * the file path for the paper's recommendation
     */
    private String myRecommendation;
	
    /**
     * whether the paper is assigned to a SPC
     */
    private boolean myAssignedToSPC;
	
    /**
     * whether the paper has a submitted recommendation
     */
    private boolean myIsRecommended;
	
    /**
     * the rating given to the paper by the SPC
     */
    private int myRating;
    
    /**
     * the acceptance status of the paper
     */
    private String myAccepted;
	
    /**
     * Constructor for a paper
     * @param theTitle the title of the paper
     * @param theFilePath the file path on the local system for the paper
     */
	public Paper(String theTitle, String theFilePath) {
		this(theTitle, theFilePath, new HashMap<String, String>(), null,
			false, false, -1, new HashMap<String, Integer>());
	}
	
	/**
	 * Constructor for a paper.
     * @param theTitle the title of the paper
     * @param theFilePath the file path on the local system for the paper
	 * @param theReviews the map of reviewers to their reviews
	 * @param theRecommendation the recommendation for the paper
	 * @param theAssignedtoSPC whether the paper is assigned to a SPC
	 * @param theIsRecommended whether the paper is recommended
	 * @param theRating the rating given to the paper by the SPC
	 * @param theIsAccepted whether the paper is accepted by the PC
	 */
	public Paper(String theTitle, String theFilePath, Map<String, String> theReviews, 
				String theRecommendation, boolean theAssignedtoSPC, boolean theIsRecommended,
				int theRating, Map<String, Integer> theReviewRatings) {
		myTitle = theTitle;
		myFilePath = theFilePath;
		myReviews = theReviews;
		myReviewRatings = theReviewRatings;
		myRecommendation = theRecommendation;
		myAssignedToSPC = theAssignedtoSPC;
		myIsRecommended = theIsRecommended;
		myRating = theRating;
		myAccepted = "Undecided";
	}
	
	/**
	 * this method will assign a reviewer to this paper.
	 * @param theReviewer
	 */
	public void assignToReviewer(String theReviewer) {
		if(theReviewer != null) {
			myReviews.put(theReviewer, null);
		}
	}
	
	/**
	 * this method will determine if the paper has been assigned to a SPC.
	 */
	public void assignToSPC(boolean isAssigned) {
		myAssignedToSPC = isAssigned;
	}
	
	/**
	 * @return whether this paper has been assigned to the given reviewer.
	 */
	public boolean isAssignedToReviewer(String theReviewer) {
		return myReviews.containsKey(theReviewer);
	}
	
	/**
	 * @return whether this paper has been assigned to a SPC
	 */
	public boolean isAssignedToSPC() {
		return myAssignedToSPC;
	}
	
	/**
	 * @return whether the paper has been reviewed by the given reviewer.
	 */
	public boolean isReviewed(String theReviewer) {
		return myReviews.get(theReviewer) != null;
	}
	
	/**
	 * this method will determine if the paper has been reviewed by the reviewer.
	 * @return false if the paper has been reviewed or true if it has.
	 */
	public boolean isRecommended() {
		return myIsRecommended;
	}
	
	public String isReviewed()
	{
		String result = "Unreviewed";
		if (!getReviews().isEmpty())
    	{
    		result = "Has Reviews";
    	}
		return result;
	}
	
	/**
	 * this method will allow the PC to set the status of a paper based on his or her decision.
	 * @param theDecision -1 if rejected, 0 if undecided or 1 if accepted.
	 */
	public void updateStatus(boolean accepted) {
		if (accepted == true) {
			myAccepted = "Accepted";
		}
		else if (accepted == false) {
			myAccepted = "Rejected";
		}
	}
	
	/**
	 * This method will submit the review of a reviewer.
	 * @param theUserName the user name of the reviewer
	 * @param theFilePath the file path for the review
	 */
	public void assignReview(String theReviewer, String theFilePath) {
		if(theReviewer != null) {
			myReviews.put(theReviewer, theFilePath);
		}
	}
	
	/**
	 * Method to store the summary rating of a reviewer.
	 * @param theReviewer The reviewer who is submitting the score.
	 * @param theRating The rating the reviewer gave this paper.
	 */
	public void assignReviewRating(String theReviewer, Integer theRating) {
		if(theReviewer != null) {
			myReviewRatings.put(theReviewer, theRating);
		}
	}

	/**
	 * this method will submit the recommendation of the SPC.
	 * @param theFilePath the path in the directory where the recommendation is stored.
	 * @param rating the rating given to this paper
	 */
	public void assignRecommendation(String theFilePath, int theRating) {
		if(theFilePath != null) {
			myRecommendation = theFilePath;
			myRating = theRating;
			myIsRecommended = true;
		}
	}

	/**
	 * @return the Title of the paper
	 */
	public String getTitle() {
		return myTitle;
	}

	/**
	 * @return the file path of the paper
	 */
	public String getFilePath() {
		return myFilePath;
	}
	
	/**
	 * @return the reviews for this paper
	 */
	public Collection<String> getReviews() {
		return myReviews.values();
	}
	
	public String getReviewForReviewer(String theReviewer) {
		return myReviews.get(theReviewer);
	}
	
	public Collection<Integer> getReviewRating() {
		return myReviewRatings.values();
	}
	
	/**
	 * @return the myRecommendation
	 */
	public String getRecommendation() {
		return myRecommendation;
	}
	
	/**
	 * @return the rating given to this paper
	 */
	public int getRating(){
		return myRating;
	}
	
	/**
	 * @return the acceptance status of the paper
	 */
	public String getStatus() {
		return myAccepted;
	}
	
	/**
	 * @return a string representation of the paper
	 */
	public String toString() {
		return myTitle;
	}
}