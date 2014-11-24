package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartingGUI extends JFrame
{
	private Dimension DEFAULT_SIZE;
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JLabel usernameLabel;
	private JLabel conferenceLabel;
	private J
	public StartingGUI()
	{
		DEFAULT_SIZE = new Dimension(600,200);
		contentPane1 = new JPanel();
		contentPane2 = new JPanel();
		createElements();
	}
	
	private void createElements()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Conference Organizer");
        setResizable(false);
		setPreferredSize(DEFAULT_SIZE);
		setVisible(true);
		FlowLayout layout = new FlowLayout(10,10,10);
        setLayout(layout);
		
		pack();
        setLocationRelativeTo(null);
	}
	
	public static void main(String args[])
	{
		new StartingGUI();
	}
}
