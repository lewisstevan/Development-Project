package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Conference;

public class AreYouSureGUI extends JFrame
{
	JFrame window;
	String conferenceName;
	String username;
	JPanel contentPane1;
	JPanel contentPane2;
	JPanel contentPane3;
	JButton Continue;
	JButton Cancel;
	JTextField deadlineField;
	JLabel deadlineLabel;
	JLabel Message;
	public AreYouSureGUI(String username, String conferenceName)
	{
		window = this;
		this.conferenceName = conferenceName;
		this.username = username;
		contentPane1 = new JPanel();
		contentPane2 = new JPanel();
		contentPane3 = new JPanel();
		deadlineField = new JTextField();
		Continue = new JButton();
		Cancel = new JButton();
		Message = new JLabel();
		deadlineLabel = new JLabel();
		createElements();
	}
	
	private void createElements()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Conference Organizer");
        setResizable(false);
		setVisible(true);
		FlowLayout layout = new FlowLayout();
        setLayout(layout);
		setPreferredSize(new Dimension(600, 200));
		
		//setup message
		Message.setText("We could not find the Conference you Specified. Would you like to create a new Conference?");
		Message.setPreferredSize(new Dimension(550,50));
		
		//setup contentPane1
		contentPane1.setPreferredSize(new Dimension(550, 50));
		contentPane1.add(Message);
		
		//setup Buttons
		Continue.setText("Continue");
		Continue.setPreferredSize(new Dimension(100, 40));
		Continue.addActionListener(new continueButtonListener());
		
		Cancel.setText("Cancel");
		Cancel.setPreferredSize(new Dimension(100, 40));
		Cancel.addActionListener(new cancelButtonListener());
		
		//contentPane2
		contentPane2.setPreferredSize(new Dimension(600,50));
		contentPane2.add(Cancel);
		contentPane2.add(Continue);
		
		//contentPane3
		contentPane3.setPreferredSize(new Dimension(600, 40));
		deadlineLabel.setText("Please choose your conference's deadline:");
		deadlineLabel.setPreferredSize(new Dimension(250, 30));
		deadlineField.setPreferredSize(new Dimension(200, 30));
		contentPane3.add(deadlineLabel);
		contentPane3.add(deadlineField);
		
		add(contentPane1);
		add(contentPane3);
		add(contentPane2);
		pack();
        setLocationRelativeTo(null);
	}
	
	private class cancelButtonListener implements ActionListener {
		
    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		new StartingGUI();
    		window.dispose();
    	}

    }
	
	private class continueButtonListener implements ActionListener {
		GregorianCalendar deadline;
		String conferencefilename = conferenceName + ".ser";
		FileOutputStream fos = null;
    	ObjectOutputStream out = null;

    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		try
    		{
    			char[] Input = deadlineField.getText().toCharArray();
    			
    			fos = new FileOutputStream(conferencefilename);
    			out = new ObjectOutputStream(fos);
    			deadline.set(((int)c * 1000), arg1, arg2);
    			out.writeObject(new Conference(conferenceName.toLowerCase(), username, new GregorianCalendar()));
    			out.close();
    		} 
    		catch (Exception ex)
    		{
    			ex.printStackTrace();
    		}
    		new MainMenuPCGUI(conferenceName, username);
    		window.dispose();
    	}

    }
}
