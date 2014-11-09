package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will hold all relevant information about the conference class
 * and provide some operations on that information.
 * @author lewiss6
 * @version 1.01
 */
public class Conference 
{
	/**
	 * Initializes the four maps involved in the Spc, reviewer, and author data.
	 * Maps hold a key value (the users username) and the papers associated with that user.
	 */
	String title;
	Map<String, Collection<Paper>> Spcs, Reviewers, Authors 
	= new HashMap<String, Collection<Paper>>();
	
	/**
	 * The collection of papers the PC has access too (all papers in the conference).
	 */
	Collection<Paper> PC = new ArrayList<Paper>();
	
	/**
	 * Constructs a new conference object
	 * @param title the title of this conference.
	 */
	public Conference(String title)
	{
		this.title = title;
	}
	
	/**
	 * Returns the papers of a selected user.
	 * @param username The user to return the papers of.
	 * @param role The role of the user to get the papers for (0 - 3).
	 * @return [return name] the array of papers associated with this user (null if no papers are found).
	 */
	public Collection<Paper> getPapers(final String username, final int role)
	{
		Collection<Paper> temp = null;
		
		switch(role) {
		case 0:
			temp = Authors.get(username);
			break;
			
		case 1:
			temp = Reviewers.get(username);
			break;
			
		case 2:
			temp = Spcs.get(username);
			break;
			
		case 3:
			temp = PC;
			break;
			
		default:
			break;
			
		}
		return temp;
	}

	/**
	 * Returns the users associated with a certain roll.
	 * @param jobTitle the title by which to search under.
	 * @return [return name] the users associated with the roll.
	 */
	public String[] getUsers(String jobTitle)
	{
		return null;
		
	}
	
	/**
	 * Assigns a paper to a user.
	 * @param username the user to assign the paper to.
	 * @param paper the paper to assign to the user.
	 */
	public void assignPaper(String username, Paper paper)
	{
		
	}
	
	/**
	 * Assigns a role to a user.
	 * @param username the user to assign a role to.
	 * @param role the array to place the user into.
	 */
	public void assignRole(String username, String role)
	{
		
	}
	
	/**
	 * Adds a Paper to a users array of papers.
	 * @param paper the paper to add to the users list.
	 */
	public void addPaper(Paper paper)
	{
		
	}


}
