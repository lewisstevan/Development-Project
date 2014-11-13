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
	 * A field representing the title of this conference.
	 */
	String title;
	
	/**
	 * Initializes the four maps involved in the Spc, reviewer, and author data.
	 * Maps hold a key value (the users username) and the papers associated with that user.
	 */
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
	public Collection<Paper> getPapers(final String username, final String role)
	{
		Collection<Paper> temp = null;
		
		if (role == "Author")
		{
			temp = Authors.get(username);
		}
		else if (role == "Reviewer")
		{
			temp = Reviewers.get(username);
		}
		else if (role == "SPC")
		{
			temp = Spcs.get(username);
		}
		else if (role == "PC")
		{
			temp = PC;
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
	public void assignRole(final String username, final String role)
	{
		if (role == "Spc")
		{
			Spcs.put(username, null);
		}
		else if (role == "Reviewer")
		{
			Reviewers.put(username, null);
		}
		else if (role == "Author")
		{
			Authors.put(username, null);
		}
	}
	
	/**
	 * Adds a Paper to a users array of papers.
	 * @param paper the paper to add to the users list.
	 */
	public void addPaper(Paper paper)
	{
		
	}


}
