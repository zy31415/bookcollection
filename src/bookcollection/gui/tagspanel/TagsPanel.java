package bookcollection.gui.tagspanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.Box;

import bookcollection.gui.DeleteATagDialog;
import bookcollection.sqlite.SQLite;

public class TagsPanel extends JPanel{
	private JList<String> list = null;
	
	public TagsPanel(){
		
		super();
		
		this.setLayout(new BorderLayout());		
		
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		updateList();
		
		JTabbedPane tagsTabbedPane = new JTabbedPane();
		tagsTabbedPane.addTab("Tags", list);
		this.add(tagsTabbedPane, BorderLayout.CENTER);
		
		Box baseBox = Box.createHorizontalBox();
		this.add(baseBox, BorderLayout.SOUTH);		
		
		baseBox.add(Box.createHorizontalGlue());
		
		JButton addItemButton = new JButton("+");
		addItemButton.addActionListener(new AddATagDialog(this));
		baseBox.add(addItemButton);
		
		JButton deleteItemButton = new JButton("-");
		deleteItemButton.addActionListener(new DeleteATagDialog(this));
		baseBox.add(deleteItemButton);			
	}
	
	private void updateList(){
		DefaultListModel<String> model = new DefaultListModel<String>();
				
		SQLite db = new SQLite();
		db.createTables();			
		String listData[] = db.getAllTags();
		
		for (String item : listData){		
			model.addElement(item);
		}
		
		list.setModel(model);
	}
	
	public	void addATag(String tag){
		SQLite db = new SQLite();
		if (db.hasTag(tag)){
			JOptionPane.showMessageDialog(null, 
					String.format("Duplicate tag: \n  %s", tag));				
		} else {
			db.addATag(tag);
			updateList();		
		}
	}
	
	public	void deleteATag(String tag){
		SQLite db = new SQLite();
		if (db.hasTag(tag)){
			db.deleteATag(tag);
			updateList();
		} else {
			JOptionPane.showMessageDialog(null, 
					String.format("Doesn't find tag: \n  %s", tag));
		}
	}
	
	public String getSelectedTag(){
		return list.getSelectedValue().toString();		
	}
	
	
	
}
