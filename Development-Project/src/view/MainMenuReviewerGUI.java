package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Conference;
import model.Paper;

/**
 *
 * @author lewis_000
 * @author Mickey Johnson
 */
public class MainMenuReviewerGUI extends MainMenuGUI {
	private Dimension STANDARD_BUTTON_SIZE = new Dimension(150,50);
	
	private JButton submitReviewBTN;
	private static final long serialVersionUID = 1L;
	Conference reviewerConference;
	String username;
	String conferenceName;

	public MainMenuReviewerGUI(String currentConference, String theUsername) {
		
		super(currentConference, theUsername, "Reviewer");
		submitReviewBTN = new JButton();
		addReviewerButtons();
    	reviewerConference = super.currentConference;
    	username = theUsername;
    	conferenceName = currentConference;
    }
    
    /**
     * adds the Unique buttons to perform the unique functions.
     */
    //Use the STANDARD_BUTTON_SIZE constant to set the size of your button to the GUI
    //standard
    public void addReviewerButtons() {
    	submitReviewBTN.setPreferredSize(STANDARD_BUTTON_SIZE);
    	submitReviewBTN.setText("Submit Review");
    	submitReviewBTN.addActionListener(new submitReviewListener());
    	
    	contentPane3.removeAll();
    	contentPane3.add(submitReviewBTN);
    }
    
    private class submitReviewListener implements ActionListener {
    	Collection<Paper> papers;
    	
    	@Override
  	  	public void actionPerformed(ActionEvent arg0) {
    		papers = reviewerConference.getPapers(username, "Reviewer");
    		//user has papers to review
    		if (papers != null) {
    			Object[] possibilities = papers.toArray();
    			Paper submitTo = null;
        		submitTo = (Paper)JOptionPane.showInputDialog(MainMenuReviewerGUI.this, "Choose a paper to review",
        				"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
        		//user selected a paper (did not hit cancel)
        		if (submitTo != null) {
	        		JFileChooser choosePaper = new JFileChooser();
	        		choosePaper.setDialogTitle("Select your review");
	        		int status = choosePaper.showOpenDialog(MainMenuReviewerGUI.this);
	        		//user selects a file (did not hit cancel)
	    	  		if (status != JFileChooser.CANCEL_OPTION) {
	    	  			File review = new File(choosePaper.getSelectedFile().getPath());
	            		submitTo.assignReview(username, review.getPath());
	    	  		}
	    	  		
	    	  		//redraw GUI
	    	  		String conferenceFilename = conferenceName.toLowerCase() + ".ser";
			  		try
			  		{
			  			FileOutputStream fos = new FileOutputStream(conferenceFilename);
			  			ObjectOutputStream out = new ObjectOutputStream(fos);
			  			out.writeObject(currentConference);
			  			out.close();
			  		} 
			  		catch (Exception ex)
			  		{
			  			ex.printStackTrace();
			  		}
			  		MainMenuReviewerGUI.this.dispose();
			  		new MainMenuReviewerGUI(conferenceName, username);
        		}

    		}
    		

    	}
    }	
}