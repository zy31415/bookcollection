package bookcollection.gui.docspanel;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;


public class DocsPanel extends JPanel {
	public DocsPanel(){
		super();
		this.setLayout(new BorderLayout());
		
		String[] columnNames = {"Authors",
				"Title",
                "Year",
                };
		Object[][] data = {
			    {"Booth", "The craft of research", new Integer(2008)}
			};
		
		JTable table = new JTable(data, columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		JTabbedPane tagsTabbedPane = new JTabbedPane();
		tagsTabbedPane.addTab("Docs", scrollPane);
		this.add(tagsTabbedPane, BorderLayout.CENTER);
		
		Box baseBox = Box.createHorizontalBox();
		this.add(baseBox, BorderLayout.SOUTH);		
		
		baseBox.add(Box.createHorizontalGlue());
		
		JButton addItemButton = new JButton("+");
		addItemButton.addActionListener(new NewDocDialog());
		baseBox.add(addItemButton);
		
		JButton deleteItemButton = new JButton("-");
		baseBox.add(deleteItemButton);			
	}
}
