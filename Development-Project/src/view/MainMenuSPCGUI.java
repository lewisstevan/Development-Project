
package view;

import java.awt.Color;
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
public class MainMenuSPCGUI extends MainMenuGUI {
	
	public MainMenuSPCGUI(String currentConference, String username) {
		super(currentConference, username, "SubProgram Chair");
	}
	
	/**
     * adds the Unique buttons to perform the unique functions.
     */
    //Use the STANDARD_BUTTON_SIZE constant to set the size of your button to the GUI
    //standard
	 @Override
	    public void addUniqueButtons()
	    {
	    	
	    }
}