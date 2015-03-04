package bookcollection.gui.tagspanel;

import javax.swing.JMenuItem;

public class AddATagMenuItem extends JMenuItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddATagMenuItem(TagsPanel tagsPanel){
		super("Add a tag...");	
		this.addActionListener(new AddATagDialog(tagsPanel));
	}
				
}
