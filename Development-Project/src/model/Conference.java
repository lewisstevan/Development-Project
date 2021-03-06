package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import java.util.Set;
import java.util.TreeSet;

/**
 * This class will hold all relevant information about the conference class
 * and provide some operations on that information.
 * @author lewiss6
 * @author Mickey Johnson
 * @version 1.01
 */
public class Conference implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A String representing the title of this conference.
	 */
	private String title;
	
	/**
	 * Initializes the three maps involved in the Spc, reviewer, and author data.
	 * Maps hold a key value (the users username) and the papers associated with that user for that role.
	 */
	private Map<String, Collection<Paper>> Spcs;
	private Map<String, Collection<Paper>> Reviewers;
	private Map<String, Collection<Paper>> Authors;
	
	/**
	 * The user that is assigned as PC for this conference.
	 */
	private String PCusername;
	
	/**
	 * Deadline date for this conference.
	 */
	private GregorianCalendar deadline;
	
	/**
	 * @param theTitle the title of this conference.
	 * @param thePCUsername The username of the user to assign as PC for this conference.
	 * @param theDeadline The submission deadline for papers for this conference.
	 */
	public Conference(final String theTitle, final String thePCUsername, final GregorianCalendar theDeadline) {
		title = theTitle.substring(0, 1).toUpperCase() + theTitle.substring(1);
		PCusername = thePCUsername;
		Spcs = new HashMap<String, Collection<Paper>>();
		Reviewers = new HashMap<String, Collection<Paper>>();
		Authors = new HashMap<String, Collection<Paper>>();
		deadline = theDeadline;
	}
	
	/**
	 * Returns the papers of a selected user.
	 * @param theUsername The user to return the papers of.
	 * @param theRole The role of the user to get the papers for (0 - 3).
	 * @return The array of papers associated with this user (null if no papers are found).
	 */
	public Collection<Paper> getPapers(final String theUsername, final String theRole) {
		Collection<Paper> temp = null;
		
		if (theRole == "Author") {
			temp = Authors.get(theUsername);
		} else if (theRole == "Reviewer") {
			temp = Reviewers.get(theUsername);
		} else if (theRole == "SubProgram Chair") {
			temp = Spcs.get(theUsername);
		} else if (theRole == "Program Chair") {
			temp = new ArrayList<Paper>();
			for (String s : Authors.keySet()) {
				Collection<Paper> authorTemp = Authors.get(s);
				if (authorTemp != null) {
					for (Paper p : authorTemp) {
						temp.add(p);
					}
				}
			}
		}

		return temp;
	}
	
	/**
	 * Retrieves one specific paper from a user
	 * @param theUsername the name of the person who is assigned this paper.
	 * @param theRole the role of the person who is assigned this paper.
	 * @param thePaperName the name of the paper.
	 * @return currentPaper the paper that is being searched for. 
	 */
	public Paper getPaper(final String theUsername, final String theRole, final String thePaperName)
	{
		Collection<Paper> temp = getPapers(theUsername, theRole);
		Paper currentPaper = null;
		Iterator<Paper> itr = temp.iterator();
		while (itr.hasNext())
		{
			Paper thisPaper = itr.next();
			if (thisPaper.getTitle() == thePaperName)
			{
				 currentPaper = thisPaper;
			}
		}
		return currentPaper;
	}
	
	public boolean authenticate(final String theUsername, final String theRole)
	{
		boolean result = false;
		if (theRole == "           Author")
		{
			result = true;
		}
		else if (theRole == "           Reviewer")
		{
			result = Reviewers.keySet().contains(theUsername.toLowerCase());
		}
		else if (theRole == "           SubProgram Chair")
		{
			result = Spcs.keySet().contains(theUsername.toLowerCase());
		}
		if (theUsername.equalsIgnoreCase(PCusername))
		{
			result = true;
		}
		return result;
	}

	/**
	 * Gets the number of papers assigned to the specified user for the specified role.
	 * @param theUsername The username of the user to be checked.
	 * @param theRole The role of the user to be checked.
	 * @return The number of papers assigned to the specified user for the specified role.
	 */
	public int getNumberofPapers(final String theUsername, final String theRole) {
		int num = 0;
		if (theRole == "Author") {
			num = Authors.get(theUsername).size();
		} else if (theRole == "Reviewer") {
			num = Authors.get(theUsername).size();;
		} else if (theRole == "SubProgram Chair") {
			num = Authors.get(theUsername).size();;
		}
		return num;
	}
	
	
	/**
	 * Returns the users associated with a certain role.
	 * @param role the title by which to search under.
	 * @return A set containing the users associated with the role.
	 */
	public Collection<String> getUsers(final String role) {
		Collection<String> temp = null;
		
		if (role == "Author") {
			temp = Authors.keySet();
		} else if (role == "Reviewer") {
			temp = Reviewers.keySet();
		} else if (role == "SubProgram Chair") {
			temp = Spcs.keySet();
		} else if (role == "Program Chair") {
			temp = new TreeSet<String>();
			temp.add(PCusername);
		}
		
		return temp;
	}
	
	/**
	 * Assigns a paper to a user for a given role.
	 * @param username The user to assign the paper to.
	 * @param Paper the paper to assign to the user.
	 * @param role The role of the user that the paper is being assigned to.
	 */
	public void assignPaper(String username, Paper paper, final String role) {
		if (role == "SubProgram Chair") {
			
			if(!Spcs.keySet().contains(username)) {
				assignRole(username, role);
			}
			Spcs.get(username).add(paper);
			paper.assignToSPC(true);
			
		} else if (role == "Reviewer") {
			
			if(!Reviewers.keySet().contains(username)) {
				assignRole(username, role);
			}
			Reviewers.get(username).add(paper);
			
		} else if (role == "Author") {
			
			if(!Authors.keySet().contains(username)) {
				assignRole(username, role);
			}
			Authors.get(username).add(paper);
		}
	}
	
	/**
	 * Allows an author to unsubmit their paper.
	 * @param username The authors username.
	 * @param paper the paper the author wants to remove.
	 */
	public void removePaper(String username, String paperName, String role) {
		Collection<Paper> temp = getPapers(username, role);
		if (temp != null) {
			Iterator<Paper> itr = temp.iterator();
			boolean found = false;
			while (itr.hasNext() && found == false) {
				Paper currentPaper = itr.next();
				if (currentPaper.getTitle().equalsIgnoreCase(paperName)) {
					temp.remove(currentPaper);
					found = true;
				}
			}
		}
	}
	
	/**
	 * Assigns a role to a user.
	 * @param username the user to assign a role to.
	 * @param role the array to place the user into.
	 */
	public void assignRole(final String username, final String role) {
		if (role == "SubProgram Chair") {
			Spcs.put(username, new ArrayList<Paper>());
			
		} else if (role == "Reviewer") {
			Reviewers.put(username, new ArrayList<Paper>());
			
		} else if (role == "Author") {
			Authors.put(username, new ArrayList<Paper>());
		}
	}
	
	public GregorianCalendar getDeadline() {
		return deadline;
	}

	public String getConferenceTitle() {
		return title;
	}
	
	public boolean beforeDue() {
		return !(new GregorianCalendar().after(this.deadline));
	}
	
	public String toString() {
		String str = "";
		str = str + title;
		
		return str;
	}

}
