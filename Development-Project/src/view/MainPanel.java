package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainPanel {
	private static final Dimension DEFAULT_APPLICATION_SIZE = new Dimension(1000, 1000);
	
	private JFrame guiFrame;
	
	private JLabel nameLabel;
	
	private JLabel conferenceLabel;
	
	private JLabel titleLabel;
	
	private JButton getPapers;
	
	private JButton viewPaper;
	
	private JButton assignPaper;
	
	private JButton assignRole;
	
	private JButton assignReview;
	
	private JButton assignRecommendation;
	
	public MainPanel()
	{
		guiFrame = new JFrame();
		nameLabel = new JLabel();
		conferenceLabel = new JLabel();
		titleLabel = new JLabel();
		setUp();
		
	}
	
	private void setUp()
	{
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Start Menu");
		guiFrame.setSize(DEFAULT_APPLICATION_SIZE);
		guiFrame.setResizable(false);
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MainPanel();
	}
}
