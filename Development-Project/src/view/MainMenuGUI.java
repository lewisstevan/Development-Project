
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Conference;
import model.Paper;

/**
 *
 * @author lewis_000
 */
public class MainMenuGUI extends JFrame {
	
	private Dimension STANDARD_BUTTON_SIZE = new Dimension(150,50);;
	Object[] papersList;
	int scrollSizeMultiplier;
	SimpleDateFormat df;
	public static int SECONDS_IN_A_DAY = 24 * 60 * 60;
	private static final long serialVersionUID = 1L;
	private Dimension DEFAULT_SIZE = new Dimension(600,600);
	private JPanel contentPane1, contentPane2, contentPane3, contentPane4, contentPane5, contentPane6,
	contentPane7, contentPane8, contentPane9;
    private JLabel conferenceLabel, titleLabel, deadlineLabel, deadlinelbl, conferencelbl, namelbl,
    nameLabel, paperNamelbl, paperStatuslbl, reviewLabel, spcScoreLabel;
    private JButton changeRoleBtn, uploadPaperBtn;
    private JScrollPane scrollPanel;
    private Conference currentConference;
    private String username, conferenceFilename, role, conferenceName;
    
    /**
     * Creates new form MainMenuGUI
     */
    public MainMenuGUI(String currentConference, String username, String role) {
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
        changeRoleBtn = new JButton();
        this.role = role;
        conferenceFilename = currentConference.toLowerCase() + ".ser";
        scrollSizeMultiplier = 0;       
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        
        //set the text of various labels
        conferencelbl.setText(this.currentConference.getConferenceTitle());
        deadlineLabel.setText("      Deadline:");
        deadlinelbl.setText(df.format(currentConference.getDeadline().getTime()));
        namelbl.setText(username);
        changeRoleBtn.setText(role);
        paperStatuslbl.setText("Status");
        paperNamelbl.setText("Paper Title");
        titleLabel.setText("              Role:");
        reviewLabel.setText("Reviews");
        conferenceLabel.setText("Conference:");
        nameLabel.setText("           Name:");
        uploadPaperBtn.setText("Upload Paper");
        spcScoreLabel.setText("S.P.C. Score");
        
        //attach listeners
        changeRoleBtn.addActionListener(new changeRoleButtonListener(this));
        uploadPaperBtn.addActionListener(new submitPaperButtonListener());
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
        
        //Content pane 1 setup
        contentPane1.setPreferredSize(new Dimension(DEFAULT_SIZE.width/2-50, DEFAULT_SIZE.height/4-25));
        contentPane1.setLayout(new GridLayout(4,2,-100,1));
        
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
//        contentPane1.setBackground(Color.red);
//        conferenceLabel.setBackground(Color.BLACK); 
//        nameLabel.setBackground(Color.BLACK);
//        titleLabel.setBackground(Color.BLACK);
//        contentPane2.setBackground(Color.GREEN);
//        contentPane4.setBackground(Color.RED);
//        contentPane8.setBackground(Color.CYAN);
//        contentPane9.setBackground(Color.YELLOW);
//        contentPane3.setBackground(Color.RED);
        
        //adding components

        contentPane1.add(conferenceLabel);
        contentPane1.add(conferencelbl);

        contentPane1.add(nameLabel);
        contentPane1.add(namelbl);

        contentPane1.add(titleLabel);
        contentPane1.add(changeRoleBtn);
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
        	paperStatus.setText(((Paper)papersList[x]).getStatus());   	
        	paperTitles.setText(((Paper)papersList[x]).getTitle()); 	
        	paperReviews.setText(((Paper)papersList[x]).isReviewed());
        	contentPane9.add(paperTitles);
        	contentPane8.add(paperScore);
        	contentPane8.add(paperReviews);
        	contentPane8.add(paperStatus);
        }
        
        contentPane7.add(contentPane9);
        contentPane7.add(contentPane8);
        contentPane2.add(scrollPanel);
        add(contentPane2);
        add(contentPane3);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void addUniqueButtons()
    {
    	uploadPaperBtn.setPreferredSize(STANDARD_BUTTON_SIZE);
        contentPane3.add(uploadPaperBtn);
        contentPane3.repaint();
    }
    

  private class changeRoleButtonListener implements ActionListener {
  
	  JFrame window;
	  public changeRoleButtonListener(JFrame window)
	  {
		  this.window = window;
	  }
	  
  	public void actionPerformed(ActionEvent buttonClick) 
  	{
  		new RoleChooserGUI(conferenceName,username, window);
  	}

  }
  
  private class submitPaperButtonListener implements ActionListener {
  	FileOutputStream fos = null;
  	ObjectOutputStream out = null;
  	public void actionPerformed(ActionEvent buttonClick) 
  	{
  		JFileChooser choosePaper = new JFileChooser();
  		int status = choosePaper.showOpenDialog(MainMenuGUI.this);
  		if (status != JFileChooser.CANCEL_OPTION)
  		{

  	  		File Paper = new File(choosePaper.getSelectedFile().getPath());
	  		currentConference.assignPaper(username, new Paper(Paper.getName(), Paper.getPath()), role);
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
}
