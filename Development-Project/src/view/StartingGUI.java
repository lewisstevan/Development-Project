package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartingGUI extends JFrame
{
	private Dimension DEFAULT_SIZE;
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JPanel spaceFiller;
	private JLabel usernameLabel;
	private JLabel conferenceLabel;
	private JTextField usernameField;
	private JTextField conferenceField;
	private JButton okButton;
	private JButton exitButton;
	
	public StartingGUI()
	{
		DEFAULT_SIZE = new Dimension(600,200);
		contentPane1 = new JPanel();
		contentPane2 = new JPanel();
		usernameLabel = new JLabel();
		conferenceLabel = new JLabel();
		usernameField = new JTextField();
		conferenceField = new JTextField();
		spaceFiller = new JPanel();
		okButton = new JButton();
		exitButton = new JButton();
		createElements();
	}
	
	private void createElements()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Conference Organizer");
        setResizable(false);
		setPreferredSize(DEFAULT_SIZE);
		setVisible(true);
		FlowLayout layout = new FlowLayout();
        setLayout(layout);
        
        usernameLabel.setPreferredSize(new Dimension(DEFAULT_SIZE.width/4, DEFAULT_SIZE.height/4));
        usernameLabel.setText("Username:");
        
        conferenceLabel.setText("Conference:");
        conferenceLabel.setPreferredSize(new Dimension(DEFAULT_SIZE.width/4, DEFAULT_SIZE.height/4));
        
        usernameField.setPreferredSize(new Dimension(DEFAULT_SIZE.width/2, 30));
        
        conferenceField.setPreferredSize(new Dimension(DEFAULT_SIZE.width/2, 30));
        
        //setup exitButton
        exitButton.setText("Exit");
        exitButton.setPreferredSize(new Dimension(DEFAULT_SIZE.width/8, DEFAULT_SIZE.height/4));
        exitButton.addActionListener(new ExitButtonListener());
        
        //setup okButton
        okButton.setText("Continue");
        okButton.setPreferredSize(new Dimension(DEFAULT_SIZE.width/8 + 30, DEFAULT_SIZE.height/4));
        okButton.addActionListener(new okButtonListener(this));
        
        //setup contentPane1
        contentPane1.setPreferredSize(new Dimension(DEFAULT_SIZE.width, DEFAULT_SIZE.height/4));
        contentPane1.setBackground(Color.RED);
        
        //setup space filler
        spaceFiller.setPreferredSize(new Dimension(DEFAULT_SIZE.width-DEFAULT_SIZE.width/2, DEFAULT_SIZE.height/4));
        spaceFiller.setBackground(Color.black);
        
        //setup contentPane2
        contentPane2.setPreferredSize(new Dimension(DEFAULT_SIZE.width, DEFAULT_SIZE.height/4));
		contentPane2.setBackground(Color.yellow);
		
		//add elements
		contentPane1.add(usernameLabel);
		contentPane1.add(usernameField);
		contentPane2.add(conferenceLabel);
		contentPane2.add(conferenceField);
		add(contentPane1);
		add(contentPane2);
		add(spaceFiller);
		add(okButton);
		add(exitButton);
        
        pack();
        setLocationRelativeTo(null);
	}
	
	private class ExitButtonListener implements ActionListener {

    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		System.exit(0);	
    	}

    }
	
	private class okButtonListener implements ActionListener {
		
		JFrame window;
		public okButtonListener(JFrame window)
		{
			this.window = window;
		}
    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		new MainMenuGUI(conferenceField.getText(), usernameField.getText());	
    		window.dispose();
    	}

    }
	
	public static void main(String args[])
	{
		new StartingGUI();
	}
}
