
package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Conference;
import model.Paper;

public class MainMenuGUI extends JFrame {
	/**
	 * Determines whether a GUI is already created or not,
	 * this field helps with proper GUI creation and disposals
	 */
	boolean guiCreated = false;
	private Dimension STANDARD_BUTTON_SIZE = new Dimension(125,50);
	
	/**
	 * A list of the current users papers so that we may properly add them to the GUI components
	 */
	Object[] papersList;
	
	/**
	 * This variable determines how large the panel will be which houses all submitted papers.
	 * The more papers that have been submitted the larger this multiplier will be and
	 * the larger the panel will be to house them.
	 */
	private int scrollSizeMultiplier;
	
	/**
	 * A date format to properly display the deadlines of each conference.
	 */
	SimpleDateFormat df;
	private static final long serialVersionUID = 1L;
	private Dimension DEFAULT_SIZE = new Dimension(600,600);
	private JPanel contentPane1, contentPane2, contentPane3, contentPane4, contentPane5, contentPane6,
	contentPane7, contentPane8, contentPane9, conferenceNamePane;
    private JLabel conferenceLabel, titleLabel, deadlineLabel, deadlinelbl, conferencelbl, namelbl,
    nameLabel, paperNamelbl, paperStatuslbl, reviewLabel, spcScoreLabel;
    private JButton uploadPaperBtn,unsubmitPaperBtn, backBtn, submitReviewBTN, decideStatusBtn, assignReviewerBtn, uploadRecommendationBtn, assignPaperBtn, 
    assignSPCBtn, assignSPCPaperBtn;
    private JComboBox<String> changeRoleField;
    private JScrollPane scrollPanel;
    protected Conference currentConference;
    private String username, conferenceFilename, role, conferenceName;
    
    
    public MainMenuGUI(String currentConference, String username, String role) {
    	assignSPCPaperBtn = new JButton();
    	assignSPCBtn = new JButton();
    	decideStatusBtn = new JButton();
    	submitReviewBTN = new JButton();
    	assignPaperBtn = new JButton();
    	this.conferenceName = currentConference;
    	df = new SimpleDateFormat();
    	df.applyPattern("dd/MM/yyyy");
    	deadlineLabel = new JLabel();
        deadlinelbl = new JLabel();
        spcScoreLabel = new JLabel();
        conferencelbl = new JLabel();
        namelbl = new JLabel();
        paperStatuslbl = new JLabel();
    	titleLabel = new JLabel();
        conferenceLabel = new JLabel();
        nameLabel = new JLabel();
        paperNamelbl = new JLabel();
        reviewLabel = new JLabel();     
        uploadPaperBtn = new JButton();
        assignReviewerBtn = new JButton();
        uploadRecommendationBtn = new JButton();
        backBtn = new JButton();
        changeRoleField = new JComboBox<String>();
        unsubmitPaperBtn = new JButton();
        this.role = role;
        conferenceFilename = currentConference.toLowerCase() + ".ser";
        scrollSizeMultiplier = 0;     
        conferenceNamePane = new JPanel();
        contentPane1 = new JPanel();
        contentPane2 = new JPanel();
        contentPane3 = new JPanel();
        contentPane4 = new JPanel();
        contentPane5 = new JPanel();
        contentPane6 = new JPanel();
        contentPane7 = new JPanel();
        contentPane8 = new JPanel();
        contentPane9 = new JPanel();
        this.username = username.toLowerCase();
        
        deserialize();  
    }
    
    /**
     * Deserializes the selected conference.
     */
    private void deserialize()
    {
    	//deserialize
        FileInputStream fis = null;
        ObjectInputStream in = null;
        if (new File(conferenceFilename).exists())
        {
	        try
	        {
	        	fis = new FileInputStream(conferenceFilename);
	        	in = new ObjectInputStream(fis);
	        	this.currentConference = (Conference) in.readObject();
	        	in.close();
	        	if (this.currentConference.getPapers(this.username, role) != null)
	        	{
	        		scrollSizeMultiplier = this.currentConference.getPapers(this.username, role).size();
	        		papersList = this.currentConference.getPapers(this.username, role).toArray();
	        	}     
	        	if (!(this.currentConference.getUsers("Author").contains(username)))
	        	{
	        		this.currentConference.assignRole(username.toLowerCase(), "Author");
	        	}
	            createComponents();
	            addUniqueButtons();
	        }
	        catch (Exception ex)
	        {
	        	ex.printStackTrace();
	        	JOptionPane.showMessageDialog(this, "Data corrupted, please select a different Conference");
	        	new StartingGUI();
	        	this.dispose();
	        }
        }
        
        else
        {
        	new AreYouSureGUI(this.username, conferenceName);
        	this.dispose();
        }
    }
    

    /**
     * Creates the GUI components
     */
    private void createComponents() {
    	

    	//Master Pane setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Conference Organizer");
        setResizable(false);
		setPreferredSize(DEFAULT_SIZE);
		setVisible(true);
		FlowLayout layout = new FlowLayout(10,10,10);
        setLayout(layout);
        
        //setup components
        conferencelbl.setFont(conferencelbl.getFont().deriveFont(20.0f));
        conferencelbl.setText(this.currentConference.getConferenceTitle());
        deadlineLabel.setText("      Deadline:");
        deadlinelbl.setText(df.format(currentConference.getDeadline().getTime()));
        backBtn.setText("Back");
        backBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
        namelbl.setText(username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase());
        changeRoleField.addItem("           Author");
        changeRoleField.addItem("           Reviewer");
        changeRoleField.addItem("           SubProgram Chair");
        changeRoleField.addItem("           Program Chair");
        changeRoleField.setSelectedItem("           " + role);
        changeRoleField.addItemListener(new changeRoleFieldListener());
        paperStatuslbl.setText("Status");
        paperNamelbl.setText("Paper Title");
        titleLabel.setText("              Role:");
        reviewLabel.setText("Review Score");
        conferenceLabel.setText("Conference:");
        nameLabel.setText("           Name:");
        uploadPaperBtn.setText("Upload Paper");
        unsubmitPaperBtn.setText("Unsubmit Paper");
        spcScoreLabel.setText("S.P.C. Score");
        
        //attach listeners
        backBtn.addActionListener(new backButtonListener());
        uploadPaperBtn.addActionListener(new submitPaperButtonListener());
        unsubmitPaperBtn.addActionListener(new unSubmitPaperButtonListener());
      	submitReviewBTN.addActionListener(new submitReviewListener());
      	decideStatusBtn.addActionListener(new changeStatusBtnListener());
      	assignReviewerBtn.addActionListener(new assignReviewerListener());
      	uploadRecommendationBtn.addActionListener(new uploadRecommendationListener());
      	assignPaperBtn.addActionListener(new assignPaperBtnListener());
      	assignSPCBtn.addActionListener(new assignSPCBtnListener());
      	assignSPCPaperBtn.addActionListener(new assignSPCPaperBtnListener());
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	FileOutputStream fos = null;
            	ObjectOutputStream out = null;
            	try
        		{
        			fos = new FileOutputStream(conferenceFilename);
        			out = new ObjectOutputStream(fos);
        			out.writeObject(MainMenuGUI.this.currentConference);
        			out.close();
        		} 
        		catch (Exception ex)
        		{
        			ex.printStackTrace();
        		}
        		System.exit(0);	
        	}
        });   
        
        //conference pane setup
        conferenceNamePane.setPreferredSize(new Dimension(DEFAULT_SIZE.width -25, DEFAULT_SIZE.height/23));
        conferenceNamePane.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        //Content pane 1 setup
        contentPane1.setPreferredSize(new Dimension(DEFAULT_SIZE.width/2-50, DEFAULT_SIZE.height/4-50));
        contentPane1.setLayout(new GridLayout(3,2,-100,1));
        
        //Content pane 2 setup
        contentPane2.setPreferredSize(new Dimension(DEFAULT_SIZE.width-25, (DEFAULT_SIZE.height/2-25+(DEFAULT_SIZE.height/4-25)/2)));
        
        //Content pane 3 setup
        contentPane3.setPreferredSize(new Dimension(DEFAULT_SIZE.width-25, (DEFAULT_SIZE.height/4-25)/2));
        contentPane3.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        //content Pane 4 setup
        contentPane4.setPreferredSize(new Dimension(DEFAULT_SIZE.width-30, (DEFAULT_SIZE.height/4-30)/2));      
        contentPane4.setLayout(new GridLayout(1,2,5,0));
        
        //content pane 5 setup
        contentPane5.setLayout(new GridLayout(1,1,5,0));
        
        //content pane 6 setup
        contentPane6.setLayout(new GridLayout(1,3,5,0));
        
        //content pane 7 setup
        //change dimension to scroll_size once working data is available.
        contentPane7.setPreferredSize(new Dimension(DEFAULT_SIZE.width-50, scrollSizeMultiplier * (DEFAULT_SIZE.height/8-25)));
        contentPane7.setLayout(new GridLayout(1,2,5,0));
        
        //content pane 8 setup
        contentPane8.setLayout(new GridLayout(scrollSizeMultiplier,3,5,0));     
        
        //content pane 9 setup
        contentPane9.setLayout(new GridLayout(scrollSizeMultiplier,2,5,0));
       
        //Scroll Panel setup
        scrollPanel = new JScrollPane(contentPane7);
        scrollPanel.setPreferredSize(new Dimension(DEFAULT_SIZE.width-30, (DEFAULT_SIZE.height/2-30+(DEFAULT_SIZE.height/4-30)/2) - ((DEFAULT_SIZE.height/4-30)/2 + 5)));
 
        //set background colors for testing
//        contentPane1.setBackground(Color.GRAY);
//        conferenceLabel.setBackground(Color.BLACK); 
//        nameLabel.setBackground(Color.BLACK);
//        titleLabel.setBackground(Color.BLACK);
//        contentPane2.setBackground(Color.GREEN);
//        contentPane4.setBackground(Color.RED);
//        contentPane8.setBackground(Color.CYAN);
//        contentPane9.setBackground(Color.YELLOW);
//        contentPane3.setBackground(Color.RED);
        
        //adding components

        conferenceNamePane.add(conferenceLabel);
        conferenceNamePane.add(conferencelbl);
        add(conferenceNamePane);
        contentPane1.add(nameLabel);
        contentPane1.add(namelbl);

        contentPane1.add(titleLabel);
        contentPane1.add(changeRoleField);
        contentPane1.add(deadlineLabel);
        contentPane1.add(deadlinelbl);
        add(contentPane1);
        contentPane5.add(paperNamelbl);
        contentPane4.add(contentPane5);
        contentPane6.add(spcScoreLabel);
        contentPane6.add(reviewLabel);
        contentPane6.add(paperStatuslbl);
        contentPane4.add(contentPane6);
        contentPane2.add(contentPane4);
        for (int x = 0; x  < scrollSizeMultiplier; x++)
        {
        	JLabel paperScore = new JLabel();
        	JLabel paperStatus = new JLabel();
        	JLabel paperTitles = new JLabel();
        	JLabel paperReviews = new JLabel();
        	paperScore.setText(String.valueOf(((Paper)papersList[x]).getRating()));
        	if (!((Paper)papersList[x]).isRecommended())
        	{
        		paperScore.setText("Undecided");
        	}
        	paperStatus.setText(((Paper)papersList[x]).getStatus());   	
        	paperTitles.setText(((Paper)papersList[x]).getTitle()); 	
        	if (((Paper)papersList[x]).isReviewed() == "Has Reviews")
        	{
        		paperReviews.setText(((Paper)papersList[x]).getReviewRatingAvg());
        	}
        	else
        	{
        		paperReviews.setText("Unreviewed");
        	}
        	paperTitles.addMouseListener(new openLabelListener(paperTitles));
        	contentPane9.add(paperTitles);
        	contentPane8.add(paperScore);
        	contentPane8.add(paperReviews);
        	contentPane8.add(paperStatus);
        }
        
        contentPane7.add(contentPane9);
        contentPane7.add(contentPane8);
        contentPane2.add(scrollPanel);
        contentPane3.add(backBtn);
        add(contentPane2);
        add(contentPane3);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Opens a new instance of the current conference with a different role selected.
     * @author lewis_000
     */
    private class changeRoleFieldListener implements ItemListener {

    	@Override
    	public void itemStateChanged(ItemEvent arg0) {

    		if (guiCreated == false)
    		{
    			
				if (MainMenuGUI.this.currentConference.authenticate(MainMenuGUI.this.username, (String)changeRoleField.getSelectedItem()))
				{
		    		if (changeRoleField.getSelectedItem() == "           Author" && guiCreated == false)
		      		{
		      			new MainMenuGUI(conferenceName, MainMenuGUI.this.username, "Author");
		      		}
		      		
		      		else if (changeRoleField.getSelectedItem() == "           Reviewer" && guiCreated == false)
		      		{
		      			new MainMenuGUI(conferenceName, MainMenuGUI.this.username, "Reviewer");
		      		}
		      		
		      		else if (changeRoleField.getSelectedItem() == "           SubProgram Chair" && guiCreated == false)
		      		{
		      			new MainMenuGUI(conferenceName, MainMenuGUI.this.username,"SubProgram Chair");
		      		}
		      		
		      		else if (changeRoleField.getSelectedItem() == "           Program Chair" && guiCreated == false)
		      		{
		      			new MainMenuGUI(conferenceName, MainMenuGUI.this.username, "Program Chair");
		      		}		
		
		    		MainMenuGUI.this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(MainMenuGUI.this, "You are not authorized to view this role");
					new MainMenuGUI(conferenceName, MainMenuGUI.this.username, role);
					MainMenuGUI.this.dispose();
				}
				guiCreated = true;
    		}
    	
    	}

      }
    
    /**
     * Adds any components that may not exist on all instances of this GUI.
     * Any role specific functions are added here.
     */
    public void addUniqueButtons()
    {
    	if (role == "Author")
    	{
	    	uploadPaperBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
	    	uploadPaperBtn.setEnabled(MainMenuGUI.this.currentConference.beforeDue());
	    	if (!uploadPaperBtn.isEnabled())
	    	{
	    		uploadPaperBtn.setToolTipText("It is past this conferences deadline");
	    	}
	    	unsubmitPaperBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
	        contentPane3.add(uploadPaperBtn);
	        contentPane3.add(unsubmitPaperBtn);
	        contentPane3.repaint();
    	}
        
        if (role == "Reviewer")
        {
	        submitReviewBTN.setPreferredSize(STANDARD_BUTTON_SIZE);
	      	submitReviewBTN.setText("Submit Review");
	      	
	      	contentPane3.add(submitReviewBTN);
	      	contentPane3.repaint();
        }
        
        if (role == "Program Chair")
        {
        	assignSPCPaperBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
        	assignSPCPaperBtn.setText("Assign Paper");
        	assignSPCBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
        	assignSPCBtn.setText("Assign a Spc");
        	decideStatusBtn.setPreferredSize(new Dimension(STANDARD_BUTTON_SIZE.width + 50, STANDARD_BUTTON_SIZE.height));
        	decideStatusBtn.setText("Accept or Reject a Paper");
        	contentPane3.add(assignSPCBtn);
        	contentPane3.add(assignSPCPaperBtn);
        	contentPane3.add(decideStatusBtn);
        	contentPane3.repaint();
        }
        
        if (role == "SubProgram Chair") {
        	assignPaperBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
        	assignPaperBtn.setText("Assign a Paper");
        	assignReviewerBtn.setPreferredSize(new Dimension(STANDARD_BUTTON_SIZE.width + 50, STANDARD_BUTTON_SIZE.height));
        	assignReviewerBtn.setText("Assign a Reviewer");
        	uploadRecommendationBtn.setPreferredSize(new Dimension(STANDARD_BUTTON_SIZE.width, STANDARD_BUTTON_SIZE.height));
        	uploadRecommendationBtn.setText("Recommend");
        	contentPane3.add(backBtn);
        	contentPane3.add(assignReviewerBtn);
        	contentPane3.add(assignPaperBtn);
        	contentPane3.add(uploadRecommendationBtn);
        	contentPane3.repaint();
        }
    }
   
  	private class backButtonListener implements ActionListener {
  		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;
  		
		@Override
		public void actionPerformed(ActionEvent e) {
			try
	  		{
	  			fos = new FileOutputStream(conferenceFilename);
	  			out = new ObjectOutputStream(fos);
	  			out.writeObject(currentConference);
	  			out.close();
	  		} 
	  		catch (Exception ex)
	  		{
	  			ex.printStackTrace();
	  		}			

	  		MainMenuGUI.this.dispose();
	  		new StartingGUI();
		}
  	}
  
  private class submitPaperButtonListener implements ActionListener {
	  boolean wasEmpty = false;
  	FileOutputStream fos = null;
  	ObjectOutputStream out = null;
  	public void actionPerformed(ActionEvent buttonClick) 
  	{
  		if (!MainMenuGUI.this.currentConference.beforeDue())
  		{
  			JOptionPane.showMessageDialog(MainMenuGUI.this, "The deadline has past.\nYou may no longer submit papers to this conference");
  			uploadPaperBtn.setEnabled(false);
    		uploadPaperBtn.setToolTipText("It is past this conferences deadline");
  			contentPane3.repaint();
  		}
  		else
  		{
	  		String paperName = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Please type in the title of your paper\n", 
	  				"Conference Organizer", JOptionPane.PLAIN_MESSAGE);
	  		try
	  		{
		  		if (paperName.isEmpty())
		  		{
		  			JOptionPane.showMessageDialog(MainMenuGUI.this, "Please enter a name for your paper");
		  			wasEmpty = true;
		  			uploadPaperBtn.doClick();
		  		}
		  		else
		  		{
			  		JFileChooser choosePaper = new JFileChooser();
			  		int status = choosePaper.showOpenDialog(MainMenuGUI.this);
			  		if (status != JFileChooser.CANCEL_OPTION || wasEmpty)
			  		{
			
			  	  		File Paper = new File(choosePaper.getSelectedFile().getPath());
				  		currentConference.assignPaper(username, new Paper(paperName, Paper.getPath()), role);
				  		currentConference.getPaper(username, role, paperName).setAuthor(username.toLowerCase());
				  		try
				  		{
				  			fos = new FileOutputStream(conferenceFilename);
				  			out = new ObjectOutputStream(fos);
				  			out.writeObject(currentConference);
				  			out.close();
				  		} 
				  		catch (Exception ex)
				  		{
				  			ex.printStackTrace();
				  		}
				  		MainMenuGUI.this.dispose();
				  		new MainMenuGUI(conferenceName, username, role);
			  		}
		  		}
	  		}
	  		catch (NullPointerException ex)
	  		{
	  			JOptionPane.showMessageDialog(MainMenuGUI.this, "SORRY! There appears to have been a problem uploading that paper");
	  		}  		
  		}
  	}
  }
  
  private class submitReviewListener implements ActionListener {
	  	Collection<Paper> papers;
	  	
	  	@Override
		  	public void actionPerformed(ActionEvent arg0) {
	  		papers = MainMenuGUI.this.currentConference.getPapers(username, "Reviewer");
	  		//user has papers to review
	  		try
	  		{
		  		if (papers != null) {
		  			Object[] possibilities = papers.toArray();
		  			Paper submitTo = null;
		      		submitTo = (Paper)JOptionPane.showInputDialog(MainMenuGUI.this, "Choose a paper to review",
		      				"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
		      		//user selected a paper (did not hit cancel)
		      		if (submitTo != null) {
		      			Integer summaryRating = 0;
		      			//requires a user to input their summary rating before they continue
	  					try {
	  						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	  								"Please input your summary rating\n", "Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	  					} catch (NumberFormatException nfe) {
	  						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	  							"Summary rating must be an integer between 1 and 5,\nPlease input your summary rating\n", 
	  									"Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	  					}
	      				while (summaryRating < 1 || summaryRating > 5) {
	      					try {
	      						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	      								"Summary rating must be an integer between 1 and 5,\nPlease input your summary rating\n", "Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	      					} catch (NumberFormatException nfe) {
	      						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	      							"Summary rating must be an integer between 1 and 5,\nPlease input your summary rating\n", 
	      									"Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	      					}
	      				}
		        		JFileChooser choosePaper = new JFileChooser();
		        		choosePaper.setDialogTitle("Select your review");
		        		int status = choosePaper.showOpenDialog(MainMenuGUI.this);
		        		//user selects a file (did not hit cancel)
		    	  		if (status != JFileChooser.CANCEL_OPTION) {
		    	  			File review = new File(choosePaper.getSelectedFile().getPath());
		            		submitTo.assignReview(username, review.getPath());
		            		submitTo.assignReviewRating(username, summaryRating);
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
				  		MainMenuGUI.this.dispose();
				  		new MainMenuGUI(conferenceName, username, "Reviewer");
		      		}
		      		
		
		  		}
		  		else
	      		{
	      			JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers submitted to review");
	      		}
	  		}
	  		catch (Exception e)
	  		{
	  			JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers submitted to review");
	  		}
	  		

	  	}
	  }	
  
	private class unSubmitPaperButtonListener implements ActionListener {
		  
  		Object[] possibilities;
  		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;
  		
	  @Override
	  public void actionPerformed(ActionEvent arg0) {
		  try
		  {
		possibilities = MainMenuGUI.this.currentConference.getPapers(username, role).toArray();
		for(int x = 0;x < scrollSizeMultiplier; x++)
		{
			possibilities[x] = ((Paper)possibilities[x]).getTitle();
		}
		String removeThisOne = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Choose a paper to remove",
				"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
		MainMenuGUI.this.currentConference.removePaper(username, removeThisOne, role);
		
		try
  		{
  			fos = new FileOutputStream(conferenceFilename);
  			out = new ObjectOutputStream(fos);
  			out.writeObject(currentConference);
  			out.close();
  		} 
  		catch (Exception ex)
  		{
  			ex.printStackTrace();
  		}
  		MainMenuGUI.this.dispose();
  		new MainMenuGUI(conferenceName, username, role);
		  }
		  catch (Exception e)
		  {
	  			JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers submitted to un-submit");
		  }
	  }  
	}
	
	/**
	 * Opens the selected paper from its JLabel representation in the GUI.
	 * @author lewis_000
	 *
	 */
	private class openLabelListener implements MouseListener {
		JLabel myLabel;
		public openLabelListener(JLabel theLabel)
		{
			this.myLabel = theLabel;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Paper curPaper = MainMenuGUI.this.currentConference.getPaper(username, role, myLabel.getText());
			new ShowPaperReviewsGUI(currentConference, username, role, curPaper);
//					File f = new File(currentPaper.getFilePath());
//					try {
//						Desktop.getDesktop().open(f);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			myLabel.setForeground(Color.BLUE);
			myLabel.repaint();			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			myLabel.setForeground(Color.BLACK);
			myLabel.repaint();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class changeStatusBtnListener implements ActionListener 
	{
		
		Object[] possibilities;
  		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try
			  {
			possibilities = MainMenuGUI.this.currentConference.getPapers(username, role).toArray();
			for(int x = 0;x < scrollSizeMultiplier; x++)
			{
				possibilities[x] = ((Paper)possibilities[x]).getTitle();
			}
			String acceptOrRejectThisOne = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Choose a paper to accept or reject",
					"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
			if (acceptOrRejectThisOne != null)
			{
				Paper acceptOrRejectThisOnePaper = MainMenuGUI.this.currentConference.getPaper(username, "Program Chair", acceptOrRejectThisOne);
				
				int reply = JOptionPane.showConfirmDialog(MainMenuGUI.this, "Would you like to accept " + acceptOrRejectThisOne + "?", "Accept or Reject", JOptionPane.YES_NO_CANCEL_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					acceptOrRejectThisOnePaper.updateStatus(true);
				}
				else if (reply == JOptionPane.NO_OPTION)
				{
					acceptOrRejectThisOnePaper.updateStatus(false);
				}
				else if (reply == JOptionPane.CANCEL_OPTION)
				{
					
				}
			}
			try
	  		{
	  			fos = new FileOutputStream(conferenceFilename);
	  			out = new ObjectOutputStream(fos);
	  			out.writeObject(currentConference);
	  			out.close();
	  		} 
	  		catch (Exception ex)
	  		{
	  			ex.printStackTrace();
	  		}
	  		MainMenuGUI.this.dispose();
	  		new MainMenuGUI(conferenceName, username, role);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers submitted to accept or reject");
			}			
		}	
	}
	private class assignReviewerListener implements ActionListener {

  		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;
		Object[] reviewerPossibilities;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			reviewerPossibilities = MainMenuGUI.this.currentConference.getUsers("Author").toArray();
			
			
			String result = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Choose an Author to promote to Reviewer",
					"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,reviewerPossibilities,reviewerPossibilities[0]);
			if (result == null)
			{
				JOptionPane.showMessageDialog(MainMenuGUI.this, "No Author was selected");
			}
			else
			{
				MainMenuGUI.this.currentConference.assignRole(result, "Reviewer");
				try
		  		{
		  			fos = new FileOutputStream(conferenceFilename);
		  			out = new ObjectOutputStream(fos);
		  			out.writeObject(currentConference);
		  			out.close();
		  		} 
		  		catch (Exception ex)
		  		{
		  			ex.printStackTrace();
		  		}
		  		MainMenuGUI.this.dispose();
		  		new MainMenuGUI(conferenceName, username, role);
			}
			
		}	
	}
	
	
	private class uploadRecommendationListener implements ActionListener {
	  	Collection<Paper> papers;
	  	
	  	@Override
		  	public void actionPerformed(ActionEvent arg0) {
	  		papers = MainMenuGUI.this.currentConference.getPapers(username, "SubProgram Chair");
	  		//user has papers to recommend
	  		try
	  		{
		  		if (papers != null) {
		  			Object[] possibilities = papers.toArray();
		  			Paper submitTo = null;
		      		submitTo = (Paper)JOptionPane.showInputDialog(MainMenuGUI.this, "Choose a paper to recommend",
		      				"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,possibilities,possibilities[0]);
		      		//user selected a paper (did not hit cancel)
		      		if (submitTo != null) {
		      			Integer summaryRating = 0;
		      			//requires a user to input their summary rating before they continue
	  					try {
	  						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	  								"Please input your summary rating\n", "Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	  					} catch (NumberFormatException nfe) {
	  						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	  							"Summary rating must be an integer between 1 and 5,\nPlease input your summary rating\n", 
	  									"Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	  					}
	      				while (summaryRating < 1 || summaryRating > 5) {
	      					try {
	      						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	      								"Summary rating must be an integer between 1 and 5,\nPlease input your summary rating\n", "Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	      					} catch (NumberFormatException nfe) {
	      						summaryRating = Integer.valueOf((String)JOptionPane.showInputDialog(MainMenuGUI.this, 
	      							"Summary rating must be an integer between 1 and 5,\nPlease input your summary rating\n", 
	      									"Conference Organizer", JOptionPane.PLAIN_MESSAGE));
	      					}
	      				}
		        		JFileChooser chooseRecommendation = new JFileChooser();
		        		chooseRecommendation.setDialogTitle("Select your recommendation");
		        		int status = chooseRecommendation.showOpenDialog(MainMenuGUI.this);
		        		//user selects a file (did not hit cancel)
		    	  		if (status != JFileChooser.CANCEL_OPTION) {
		    	  			File recommendation = new File(chooseRecommendation.getSelectedFile().getPath());
		            		submitTo.assignReview(username, recommendation.getPath());
		            		submitTo.assignRecommendation(username, summaryRating);
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
				  		MainMenuGUI.this.dispose();
				  		new MainMenuGUI(conferenceName, username, "SubProgram Chair");
		      		}		
		  		}
		  		else
	      		{
	      			JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers submitted to recommend");
	      		}
	  		}
	  		catch (Exception e)
	  		{
	  			JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers submitted to recommend");
	  		}
	  	}
	  }	
	
	private class assignPaperBtnListener implements ActionListener 
	{
		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;
		Object[] reviewerPossibilities;
		Object[] papersTitles;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			reviewerPossibilities = MainMenuGUI.this.currentConference.getUsers("Reviewer").toArray();
			try
			{
				papersTitles = papersList;

				for (int x = 0; x < papersList.length; x++)
				{
					papersTitles[x] = ((Paper)papersList[x]).getTitle();
				}
			String result = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Select a Reviewer", "Reviewer Select",JOptionPane.PLAIN_MESSAGE,null,
					reviewerPossibilities,reviewerPossibilities[0]);
			if (result == null)
			{
				JOptionPane.showMessageDialog(MainMenuGUI.this, "No reviewer was selected");
			}
			else
			{
				String paper = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Select a Paper", "Paper Select",JOptionPane.PLAIN_MESSAGE,null,
						papersTitles,papersTitles[0]);
				if (paper == null)
				{
					JOptionPane.showMessageDialog(MainMenuGUI.this, "No paper was selected");
				}
				else
				{
					MainMenuGUI.this.currentConference.assignPaper(result, MainMenuGUI.this.currentConference.getPaper(username, role, paper), "Reviewer");
					if (!MainMenuGUI.this.currentConference.getPaper(username, role, paper).getAuthor().equalsIgnoreCase(result))
					{
					try
			  		{
			  			fos = new FileOutputStream(conferenceFilename);
			  			out = new ObjectOutputStream(fos);
			  			out.writeObject(currentConference);
			  			out.close();
			  		} 
			  		catch (Exception ex)
			  		{
			  			ex.printStackTrace();
			  		}
			  		MainMenuGUI.this.dispose();
			  		new MainMenuGUI(conferenceName, username, role);
					}
			  		else
					{
						JOptionPane.showMessageDialog(MainMenuGUI.this, "Cannot assign a paper to its Author");
					}
				}
			}
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers to assign");
			}
		}	
	}
	
	private class assignSPCPaperBtnListener implements ActionListener
	{
		
		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;
		Object[] spcPossibilities;
		Object[] papersTitles;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			spcPossibilities = MainMenuGUI.this.currentConference.getUsers("SubProgram Chair").toArray();
			try
			{
				papersTitles = papersList;
				for (int x = 0; x < papersList.length; x++)
				{
					papersTitles[x] = ((Paper)papersList[x]).getTitle();
				}
				String result = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Select a SubProgram Chair", "SubProgram Chair Select",JOptionPane.PLAIN_MESSAGE,null,
						spcPossibilities,spcPossibilities[0]);
				
				if (result == null)
				{
					JOptionPane.showMessageDialog(MainMenuGUI.this, "No SubProgram Chair was selected");
				}
				else
				{
					String paper = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Select a Paper", "Paper Select",JOptionPane.PLAIN_MESSAGE,null,
							papersTitles,papersTitles[0]);
					if (paper == null)
					{
						JOptionPane.showMessageDialog(MainMenuGUI.this, "No paper was selected");
					}
					else
					{
						if (!MainMenuGUI.this.currentConference.getPaper(username, role, paper).getAuthor().equalsIgnoreCase(result))
						{
						MainMenuGUI.this.currentConference.assignPaper(result, MainMenuGUI.this.currentConference.getPaper(username, role, paper), "SubProgram Chair");
						try
				  		{
				  			fos = new FileOutputStream(conferenceFilename);
				  			out = new ObjectOutputStream(fos);
				  			out.writeObject(currentConference);
				  			out.close();
				  		} 
				  		catch (Exception ex)
				  		{
				  			ex.printStackTrace();
				  		}
				  		MainMenuGUI.this.dispose();
				  		new MainMenuGUI(conferenceName, username, role);
						}
						else
						{
							JOptionPane.showMessageDialog(MainMenuGUI.this, "Cannot assign a paper to its Author");
						}
					}
				}
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainMenuGUI.this, "There are no Papers to assign");
			}
		}		
	}
	
	private class assignSPCBtnListener implements ActionListener
	{
		FileOutputStream fos = null;
  	  	ObjectOutputStream out = null;
  	  	Object[] spcPossibilities;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try
			{
				spcPossibilities = MainMenuGUI.this.currentConference.getUsers("Reviewer").toArray();
				
				
				String result = (String)JOptionPane.showInputDialog(MainMenuGUI.this, "Choose a Reviewer to promote to SubProgram Chair",
						"Conference Organizer",JOptionPane.PLAIN_MESSAGE,null,spcPossibilities,spcPossibilities[0]);
				if (result == null)
				{
					JOptionPane.showMessageDialog(MainMenuGUI.this, "No Author was selected");
				}
				else
				{
					MainMenuGUI.this.currentConference.assignRole(result, "SubProgram Chair");
					try
			  		{
			  			fos = new FileOutputStream(conferenceFilename);
			  			out = new ObjectOutputStream(fos);
			  			out.writeObject(currentConference);
			  			out.close();
			  		} 
			  		catch (Exception ex)
			  		{
			  			ex.printStackTrace();
			  		}
			  		MainMenuGUI.this.dispose();
			  		new MainMenuGUI(conferenceName, username, role);
				}
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(MainMenuGUI.this, "There are currently no Reviewers to promote");
			}
		}
		
	}
	
	
}
