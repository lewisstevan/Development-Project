/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import Controller.ExitButtonListener;
import model.Conference;
import model.Paper;
import tools.Serializer;

/**
 *
 * @author lewis_000
 */
public class MainMenuGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Dimension scroll_size;
	private Dimension default_size;
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JPanel contentPane3;
	private JPanel contentPane4;
	private JPanel contentPane5;
	private JPanel contentPane6;
	private JPanel contentPane7;
	private JPanel contentPane8;
	private JPanel contentPane9;
    private JLabel conferenceLabel;
    private JLabel titleLabel;
    private JButton assignRoleBtn;
    private JButton changeRoleBtn;
    private JButton exitBtn;
    private JScrollPane scrollPanel;
    private JLabel nameLabel;
    private JButton uploadReviewBtn;
    private Conference currentConference;
    private String username;
    private JLabel Paper;
    private JLabel SpcRating;
    private JLabel ReviewerRating;
    private JLabel status;
    private String role;
    
    /**
     * Creates new form MainMenuGUI
     */
    public MainMenuGUI(Conference currentConference, String username, String role) {
    	status = new JLabel();
    	titleLabel = new JLabel();
        conferenceLabel = new JLabel();
        nameLabel = new JLabel();
        Paper = new JLabel();
        SpcRating = new JLabel();
        ReviewerRating = new JLabel();
        assignRoleBtn = new JButton();
        uploadReviewBtn = new JButton();
        changeRoleBtn = new JButton();
        exitBtn = new JButton();
        
        contentPane1 = new JPanel();
        contentPane2 = new JPanel();
        contentPane3 = new JPanel();
        contentPane4 = new JPanel();
        contentPane5 = new JPanel();
        contentPane6 = new JPanel();
        contentPane7 = new JPanel();
        contentPane8 = new JPanel();
        contentPane9 = new JPanel();
        
        default_size = new Dimension(800,800);
        scroll_size = new Dimension(default_size.width-50, currentConference.getPapers(username, role).size() * (default_size.height/4-25));
        this.currentConference = currentConference;
        this.username = username;
        this.role = role;
        createComponents();
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
		setPreferredSize(default_size);
		setVisible(true);
		FlowLayout layout = new FlowLayout(10,10,10);
        setLayout(layout);
        
        status.setText("Status");
        SpcRating.setText("Spc Rating");
        ReviewerRating.setText("Reviewer Rating");    
        Paper.setText("Paper Title");
        titleLabel.setText(role);
        conferenceLabel.setText(currentConference.getConferenceTitle());
        nameLabel.setText(username);
        assignRoleBtn.setText("Assign Role"); 
        uploadReviewBtn.setText("Upload Review");
        changeRoleBtn.setText("Change Role");
        exitBtn.setText("Exit");
        
        //attach listeners
        exitBtn.addActionListener(new ExitButtonListener());
        
        //Content pane 1 setup
        contentPane1.setPreferredSize(new Dimension(default_size.width-25, default_size.height/4-25));
        contentPane1.setLayout(new GridLayout(3,2,0,1));
        
        //Content pane 2 setup
        contentPane2.setPreferredSize(new Dimension(default_size.width-25, (default_size.height/2-25+(default_size.height/4-25)/2)));
        
        //Content pane 3 setup
        contentPane3.setPreferredSize(new Dimension(default_size.width-25, (default_size.height/4-25)/2));
        contentPane3.setLayout(new GridLayout(1,8,5,0));
        
        //content Pane 4 setup
        contentPane4.setPreferredSize(new Dimension(default_size.width-30, (default_size.height/4-30)/2));      
        contentPane4.setLayout(new GridLayout(1,2,5,0));
        
        //content pane 5 setup
        contentPane5.setLayout(new GridLayout(1,1,5,0));
        
        //content pane 6 setup
        contentPane6.setLayout(new GridLayout(1,3,5,0));
        
        //content pane 7 setup
        //change dimension to scroll_size once working data is available.
        contentPane7.setPreferredSize(scroll_size);
        contentPane7.setLayout(new GridLayout(1,2,5,0));
        
        //content pane 8 setup
        contentPane8.setLayout(new GridLayout(currentConference.getPapers(username, role).size(),3,5,0));     
        
        //content pane 9 setup
        contentPane9.setLayout(new GridLayout(currentConference.getPapers(username, role).size(),2,5,0));
       
        //Scroll Panel setup
        scrollPanel = new JScrollPane(contentPane7);
        scrollPanel.setPreferredSize(new Dimension(default_size.width-30, (default_size.height/2-30+(default_size.height/4-30)/2) - ((default_size.height/4-30)/2 + 5)));
 
        //set background colors for testing
        contentPane1.setBackground(Color.red);
        conferenceLabel.setBackground(Color.BLACK); 
        nameLabel.setBackground(Color.BLACK);
        titleLabel.setBackground(Color.BLACK);
        contentPane2.setBackground(Color.GREEN);
        contentPane4.setBackground(Color.RED);
        contentPane8.setBackground(Color.CYAN);
        contentPane9.setBackground(Color.YELLOW);
        contentPane3.setBackground(Color.RED);
        
        //adding components
        contentPane1.add(conferenceLabel);
        contentPane1.add(nameLabel);
        contentPane1.add(titleLabel);
        add(contentPane1);
        contentPane5.add(Paper);
        contentPane4.add(contentPane5);
        contentPane6.add(ReviewerRating);
        contentPane6.add(SpcRating);
        contentPane6.add(status);
        contentPane4.add(contentPane6);
        contentPane2.add(contentPane4);
        for (int x = 0; x  < currentConference.getPapers(username, role).size(); x++)
        {
        	JLabel status = new JLabel();
        	JLabel paperTitles = new JLabel();
        	JLabel paperSPCReviews = new JLabel();
        	JLabel paperReviews = new JLabel();
        	status.setText("Accepted");
        	paperTitles.setText("Title example");
        	paperSPCReviews.setText("SPCReview example");
        	paperReviews.setText("Reviews example");
        	contentPane9.add(paperTitles);
        	contentPane8.add(paperReviews);
        	contentPane8.add(paperSPCReviews);
        	contentPane8.add(status);
        }
        
        contentPane7.add(contentPane9);
        contentPane7.add(contentPane8);
        contentPane2.add(scrollPanel);
        add(contentPane2);
        contentPane3.add(changeRoleBtn);
        contentPane3.add(assignRoleBtn);
        contentPane3.add(uploadReviewBtn);
        contentPane3.add(exitBtn);
        add(contentPane3);
        
        pack();
        setLocationRelativeTo(null);
    }

   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	model.Paper currentPaper = new model.Paper("10/10", "9/10");
    	Conference currentConference = new Conference("ConferenceA","Stevan",new GregorianCalendar());
    	currentConference.assignPaper("Stevan", currentPaper, "Author");
    	Serializer<Conference> conferenceSerializer = new Serializer<Conference>();
    	conferenceSerializer.serialize(currentConference, "src/files/Conference.ser");
    	Serializer<Paper> paperSerializer = new Serializer<Paper>();
    	paperSerializer.serialize(currentPaper, "src/files/Papers.ser");
    	new MainMenuGUI(conferenceSerializer.deserialize("src/files/Conference.ser"), "Stevan", "Author"); 
    	
    	model.Paper testPaper = paperSerializer.deserialize("src/files/Papers.ser");
    	Conference testConference = conferenceSerializer.deserialize("src/files/Conference.ser");
    	
    	System.out.println("Conference : " + testConference.toString());
    	System.out.println("Papers : " + testPaper.toString());
    }


    
}
