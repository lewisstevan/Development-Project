package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Conference;
import model.Paper;
import tools.Serializer;

public class ExitButtonListener implements ActionListener {
	
	public Conference myConference;
	public Paper myPaper;

	public void actionPerformed(ActionEvent buttonClick) {
		Serializer<Conference> conferenceSerializer = new Serializer<Conference>();
		Serializer<Paper> paperSerializer = new Serializer<Paper>();
		conferenceSerializer.serialize(myConference, "src/files/Conference2.ser");
		paperSerializer.serialize(myPaper, "src/files/Paper2.ser");
		System.exit(0);	
	}

}
