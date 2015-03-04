package bookcollection.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import bookcollection.gui.tagspanel.TagsPanel;

public class DeleteATagDialog implements ActionListener{
	TagsPanel tagsPanel = null;
	public DeleteATagDialog(TagsPanel tagsPanel){
		this.tagsPanel = tagsPanel;		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String tag = tagsPanel.getSelectedTag();
		int reply = JOptionPane.showConfirmDialog(null,
				String.format("Are you sure you want to delete tag:\n  %s", tag),
				"Confirm deletion", JOptionPane.OK_CANCEL_OPTION);
		if (reply ==JOptionPane.YES_OPTION){
			tagsPanel.deleteATag(tag);			
		}
	}
}