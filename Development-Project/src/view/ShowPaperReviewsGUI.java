package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Conference;
import model.Paper;

/**
 * 
 * @author Kyle Martens
 * @version Autumn 2014
 */
public class ShowPaperReviewsGUI extends JFrame{
	
	private static Dimension DEFAULT_SIZE = new Dimension(600,500);
	
	/**
	 * This variable determines how large the panel will be which houses all submitted papers.
	 * The more papers that have been submitted the larger this multiplier will be and
	 * the larger the panel will be to house them.
	 */
	private int scrollSizeMultiplier;
	
	private JPanel contentPane1, contentPane2, contentPane3, contentPane4, contentPane5, contentPane6,
	contentPane7, contentPane8, contentPane9;
    private JLabel reviewNamelbl, scoreLbl, paperNamelbl, paperTitle;
    private JScrollPane scrollPanel;
	
	private Paper myPaper;
	private Conference myConference;
	private String myUsername;
	private String myRole;
	
	public ShowPaperReviewsGUI(Conference theConference, String username,
								String role, Paper thePaper) {
		myPaper = thePaper;
		myConference = theConference;
		myUsername = username.toLowerCase();
		myRole = role;
		
        scoreLbl = new JLabel();
        reviewNamelbl = new JLabel();
        paperNamelbl = new JLabel();
        paperTitle = new JLabel();
        scrollSizeMultiplier = myPaper.getReviews().size();
        if(myPaper.isRecommended()) {
        	scrollSizeMultiplier++;
        }
        contentPane1 = new JPanel();
        contentPane2 = new JPanel();
        contentPane3 = new JPanel();
        contentPane4 = new JPanel();
        contentPane5 = new JPanel();
        contentPane6 = new JPanel();
        contentPane7 = new JPanel();
        contentPane8 = new JPanel();
        contentPane9 = new JPanel();
        
        createComponents();
	}
	
    /**
     * Creates the GUI components
     */
    private void createComponents() {
    	//Master Pane setup
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Conference Organizer: " + myConference);
        setResizable(false);
		setPreferredSize(DEFAULT_SIZE);
		setVisible(true);
		FlowLayout layout = new FlowLayout(10,10,10);
        setLayout(layout);
        
        //setup components
        paperNamelbl.setText("Title: ");
        paperTitle.setText(myPaper.getTitle());
    	paperTitle.addMouseListener(new openLabelListener(paperTitle));
        reviewNamelbl.setText("Reviews/Recommendation");
        scoreLbl.setText("Score");
                
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
//        contentPane2.setBackground(Color.GREEN);
//        contentPane4.setBackground(Color.RED);
//        contentPane8.setBackground(Color.CYAN);
//        contentPane9.setBackground(Color.YELLOW);
//        contentPane3.setBackground(Color.RED);
        
        //adding components
        contentPane1.add(paperNamelbl);
        contentPane1.add(paperTitle);
        add(contentPane1);
        contentPane5.add(reviewNamelbl);
        contentPane6.add(scoreLbl);
        contentPane4.add(contentPane5);
        contentPane4.add(contentPane6);
        contentPane2.add(contentPane4);
        Object[] reviews = myPaper.getReviews().toArray();
        Object[] ratings = myPaper.getReviewRating().toArray();
        for (int x = 0; x  < scrollSizeMultiplier; x++)
        {
        	JLabel review = new JLabel();
        	JLabel reviewScore = new JLabel();
        	review.setText((String) reviews[x]);
        	reviewScore.setText(String.valueOf(ratings[x]));
        	review.addMouseListener(new openLabelListener(review));
        	contentPane9.add(review);
        	contentPane8.add(reviewScore);
        }
        
        contentPane7.add(contentPane9);
        contentPane7.add(contentPane8);
        contentPane2.add(scrollPanel);
        add(contentPane2);
        
        pack();
        setLocationRelativeTo(null);
    }
	
	/**
	 * Opens the selected paper from its JLabel representation in the GUI.
	 * @author lewis_000
	 * @author Kyle
	 */
	private class openLabelListener implements MouseListener {
		
		private JLabel myLabel;
		
		public openLabelListener(JLabel theLabel) {
			this.myLabel = theLabel;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Paper currentPaper = myConference.getPaper(myUsername, myRole, myLabel.getText());
			
			File f = new File(currentPaper.getFilePath());
			try {
				Desktop.getDesktop().open(f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	}
}