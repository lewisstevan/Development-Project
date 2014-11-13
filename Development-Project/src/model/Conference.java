package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//import java.util.Set;
import java.util.TreeSet;

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
	 * Initializes the three maps involved in the Spc, reviewer, and author data.
	 * Maps hold a key value (the users username) and the papers associated with that user.
	 */
	private Map<String, Collection<Paper>> Spcs;
	private Map<String, Collection<Paper>> Reviewers;
	private Map<String, Collection<Paper>> Authors;
	
	/**
	 * The collection of papers the PC has access too (all papers in the conference).
	 */
	Collection<Paper> PC = new ArrayList<Paper>();
	
	private String PCusername;
	
	/**
	 * Constructs a new conference object
	 * @param title the title of this conference.
	 */
	public Conference(String title, String username)
	{
		this.title = title;
		PCusername = username;
		Spcs = new HashMap<String, Collection<Paper>>();
		Reviewers = new HashMap<String, Collection<Paper>>();
		Authors = new HashMap<String, Collection<Paper>>();
	}
	
	/**
	 * Returns the papers of a selected user.
	 * @param username The user to return the papers of.
	 * @param role The role of the user to get the papers for (0 - 3).
	 * @return The array of papers associated with this user (null if no papers are found).
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
	 * Returns the users associated with a certain role.
	 * @param role the title by which to search under.
	 * @return A set containing the users associated with the role.
	 */
	public Collection<String> getUsers(final String role)
	{
		Collection<String> temp = null;
		
		if (role == "Author")
		{
			temp = Authors.keySet();
		}
		else if (role == "Reviewer")
		{
			temp = Reviewers.keySet();
		}
		else if (role == "SPC")
		{
			temp = Spcs.keySet();
		}
		else if (role == "PC")
		{
			temp = new TreeSet<String>();
			temp.add(PCusername);
		}
		
		return temp;
		
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
