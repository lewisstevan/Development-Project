package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import model.Conference;

public class AreYouSureGUI extends JFrame
{
	JFrame window;
	String conferenceName;
	String username;
	JPanel contentPane1;
	JPanel contentPane2;
	JPanel contentPane3;
	JPanel contentPane4;
	JButton Continue;
	JButton Cancel;
	JTextField deadlineField, nameNewConferenceField;
	JLabel deadlineLabel;
	JLabel Message;
	JLabel nameNewConference;
	public AreYouSureGUI(String username, String conferenceName)
	{
		window = this;
		this.conferenceName = conferenceName;
		this.username = username;
		contentPane1 = new JPanel();
		contentPane2 = new JPanel();
		contentPane3 = new JPanel();
		contentPane4 = new JPanel();

				
		deadlineField = new JTextField();
		nameNewConferenceField = new JTextField();
		Continue = new JButton();
		Cancel = new JButton();
		Message = new JLabel();
		deadlineLabel = new JLabel();
		nameNewConference = new JLabel();
		
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
		setPreferredSize(new Dimension(700, 250));
		
		
		
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
		contentPane3.setPreferredSize(new Dimension(700, 80));
		nameNewConference.setPreferredSize(new Dimension(350, 30));
		nameNewConference.setText("Please type your conference's name:");
		nameNewConferenceField.setPreferredSize(new Dimension(200, 30));
		deadlineLabel.setText("Please choose your conference's deadline (dd/mm/yyyy):");
		deadlineLabel.setPreferredSize(new Dimension(350, 30));
		deadlineField.setPreferredSize(new Dimension(200, 30));
		deadlineField.setText("//");
		contentPane3.add(nameNewConference);
		contentPane3.add(nameNewConferenceField);
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
		GregorianCalendar deadline = new GregorianCalendar();
		String conferencefilename = conferenceName + ".ser";
		FileOutputStream fos = null;
    	ObjectOutputStream out = null;

    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		int year = 0;
    		int month = 0;
    		int day = 0;
    		try
    		{
    			char[] Input = deadlineField.getText().toCharArray();
    			try
    			{
    			if (Input.length != 10 || ("" + Input[2]).compareTo("/") != 0 || ("" + Input[5]).compareTo("/") != 0 )
    			{
    				JOptionPane.showMessageDialog(window, "Please type the date in the specified format(mm/dd/yyyy)");
    				new AreYouSureGUI(username, conferenceName);
    				window.dispose();
    			}
    			else
    			{
    				try 
    				{
    					year = Integer.parseInt("" + Input[6] + Input[7] + Input[8] + Input[9]);
    					month = Integer.parseInt("" + Input[3] + Input[4]);
    					day = Integer.parseInt("" + Input[0] + Input[1]);
    					fos = new FileOutputStream(conferencefilename);
    	    			out = new ObjectOutputStream(fos);
    	    			deadline.set(year, month, day);
    	    			out.writeObject(new Conference(conferenceName.toLowerCase(), username, deadline));
    	    			out.close();
    	    			new MainMenuPCGUI(conferenceName, username);
    	        		window.dispose();
    				}
    				catch(NumberFormatException e)
    				{
    					JOptionPane.showMessageDialog(window, "Please type numbers into the field");
    					new AreYouSureGUI(username, conferenceName);
    					window.dispose();
    				}
    			}
    			}
    			catch (NullPointerException e)
    			{
    				new AreYouSureGUI(username, conferenceName);
    				window.dispose();
    			}
    			
    		} 
    		catch (Exception ex)
    		{
    			ex.printStackTrace();
    		}
    		
    	}

    }
}
