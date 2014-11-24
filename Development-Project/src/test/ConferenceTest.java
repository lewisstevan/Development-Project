/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import model.Conference;
import model.Paper;

import org.junit.Before;
import org.junit.Test;

/**
 * @author daymo
 *
 */
public class ConferenceTest {

	Conference test;
	Paper dummyPaper = new Paper(null, null);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		test = new Conference("Test", "Steven Speilberg", new GregorianCalendar(2015, 10, 31));
	}

	/**
	 * Test method for {@link model.Conference#getPapers(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testPaperMethods() {
		
		//Tests assignPaper and getPaper when the user is already assigned to the specified role.
		test.assignRole("Bob", "Spc");
		test.assignPaper("Bob", dummyPaper, "Spc");
		assertTrue(test.getPapers("Bob", "Spc").contains(dummyPaper));
		
		//Tests that a user will not get access to a paper for a different role.
		assertEquals(test.getPapers("Bob", "Author"), null);
		
		//Tests assignPaper and getPaper when the user is not assigned the specified role. (i.e. new author)
		test.assignPaper("Jim", dummyPaper, "Author");
		assertTrue(test.getPapers("Jim", "Author").contains(dummyPaper));
		
		//Tests that a user only has access to papers for their specified role when other users with that role are assigned a paper.
		test.assignPaper("Jon", dummyPaper, "Spc");
		assertEquals(test.getPapers("Jim", "Spc"), null);
		
		//Tests the functionality of getPapers for the PC (should return all papers in the conference.)
		Paper dummy2 = new Paper(null, null);
		test.assignPaper("George", dummy2, "Author");
		Paper dummy3 = new Paper(null, null);
		test.assignPaper("Steve", dummy3, "Author");
		assertTrue(test.getPapers("Steven Speilberg", "PC").contains(dummyPaper));
		assertTrue(test.getPapers("Steven Speilberg", "PC").contains(dummy2));
		assertTrue(test.getPapers("Steven Speilberg", "PC").contains(dummy3));
		
		//Tests the remove paper method.
		test.assignPaper("Steve", dummy2, "Author");
		test.removePaper("Steve", dummy2);
		assertFalse(test.getPapers("Steve", "Author").contains(dummy2));
	}

	/**
	 * Test method for {@link model.Conference#getUsers(java.lang.String)}.
	 */
	@Test
	public void testUserMethods() {
		// Checks the initialization of a conference sets the correct PC value.
		assertTrue(test.getUsers("PC").contains("Steven Speilberg"));
		
		// Tests that assignRole and getUsers function correctly for specified inputs.
		test.assignRole("Bob", "Author");
		assertTrue(test.getUsers("Author").contains("Bob"));
		test.assignRole("Jim", "Reviewer");
		assertTrue(test.getUsers("Reviewer").contains("Jim"));
		
		// Tests that assignrole only assigns a user to the role specified.
		test.assignRole("Frank", "Spc");
		assertTrue(test.getUsers("Spc").contains("Frank"));
		assertFalse(test.getUsers("Author").contains("Frank"));
	}

}
