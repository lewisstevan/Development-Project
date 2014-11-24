package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoleChooserGUI extends JFrame
{
	JFrame currentWindow;
	JFrame oldWindow;
	String conferenceName;
	String userName;
	private JButton continueButton;
	private JButton cancelButton;
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JLabel chooseRoleLabel;
	private JComboBox<String> chooseRole;
	
	public RoleChooserGUI(String conferenceName, String username, JFrame oldWindow)
	{
		this.oldWindow = oldWindow;
		this.conferenceName = conferenceName;
		this.userName = username;
		continueButton = new JButton();
		cancelButton = new JButton();
		contentPane1 = new JPanel();
		contentPane2 = new JPanel();
		chooseRoleLabel = new JLabel();
		chooseRole = new JComboBox();
		currentWindow = this;
		loadElements();
	}
	
	private void loadElements()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Conference Organizer");
        setResizable(false);
		setVisible(true);
		FlowLayout layout = new FlowLayout();
        setLayout(layout);
		setPreferredSize(new Dimension(300, 160));
		
		
		
		//setup the chooseRoleLabel
		chooseRoleLabel.setPreferredSize(new Dimension(30,30));
		chooseRoleLabel.setText("Role:");
		
		//setup the chooseRole stuff
		chooseRole.addItem("           Author");
		chooseRole.addItem("           SubProgram Chair");
		chooseRole.addItem("           Program Chair");
		chooseRole.addItem("           Reviewer");
		chooseRole.setPreferredSize(new Dimension(200,30));
		
		//setup the buttons
		continueButton.setText("Continue");
		continueButton.setPreferredSize(new Dimension(100,50));
		continueButton.addActionListener(new continueButtonListener());
		
		cancelButton.setText("Cancel");
		cancelButton.setPreferredSize(new Dimension(100,50));
		cancelButton.addActionListener(new cancelButtonListener());
		
		
		contentPane1.setPreferredSize(new Dimension(300,40));
		contentPane1.add(chooseRoleLabel);
		contentPane1.add(chooseRole);
		
		contentPane2.setPreferredSize(new Dimension(300, 70));
		contentPane2.add(continueButton);
		contentPane2.add(cancelButton);
		
		add(contentPane1);
		add(contentPane2);
		
		pack();
        setLocationRelativeTo(null);
	}
	
	private class continueButtonListener implements ActionListener {

    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		oldWindow.dispose();
    		if (chooseRole.getSelectedItem() == "           Author")
    		{
    			new MainMenuGUI(conferenceName, userName);	
    		}
    		
    		else if (chooseRole.getSelectedItem() == "           Reviewer")
    		{
    			new MainMenuReviewerGUI(conferenceName, userName);
    		}
    		
    		else if (chooseRole.getSelectedItem() == "           SubProgram Chair")
    		{
    			new MainMenuSPCGUI(conferenceName, userName);
    		}
    		
    		else if (chooseRole.getSelectedItem() == "           Program Chair")
    		{
    			new MainMenuPCGUI(conferenceName, userName);
    		}
    		currentWindow.dispose();
    	}

    }
	
	private class cancelButtonListener implements ActionListener {
		
    	public void actionPerformed(ActionEvent buttonClick) 
    	{
    		currentWindow.dispose();
    	}

    }
}
