package bookcollection.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AddATagDialog implements ActionListener{
	TagsPanel tagsPanel = null;
	public AddATagDialog(TagsPanel tagsPanel){
		this.tagsPanel = tagsPanel;		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String tagname = JOptionPane.showInputDialog("Please input a value");
		tagsPanel.addATag(tagname);			
	}
}