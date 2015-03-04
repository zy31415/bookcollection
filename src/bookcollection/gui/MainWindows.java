package bookcollection.gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import bookcollection.gui.docspanel.DocsPanel;
import bookcollection.gui.tagspanel.AddATagMenuItem;
import bookcollection.gui.tagspanel.TagsPanel;
import bookcollection.sqlite.SQLite;
import bookcollection.test.TestsDatabaseInitializer;

public class MainWindows {

	private JFrame frame;
	private TagsPanel tagsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindows window = new MainWindows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindows() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tag a directory");
		
		SQLite db = new SQLite();
		db.createTables();
		
		TestsDatabaseInitializer initDB = new TestsDatabaseInitializer();
		// initDB.insertIntoTagsTable();
		// initDB.insertIntoBooksTable();		
		
		//db.insertTestData();
		
		tagsPanel = new TagsPanel();
		
		//Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				tagsPanel, new DocsPanel());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);
		
		frame.getContentPane().add(splitPane);
		
		frame.setJMenuBar(genJMenuBar());
		
		
	}
	
	private JMenuBar genJMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		ChooseDirectoryMenuItem mntmChooseDirectory = new ChooseDirectoryMenuItem();
		mnFile.add(mntmChooseDirectory);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitActionListener());
		mnFile.add(mntmExit);
		
		JMenu mnTags = new JMenu("Tags");
		menuBar.add(mnTags);
		
		JMenuItem mntmAddATag = new AddATagMenuItem(tagsPanel);
		mnTags.add(mntmAddATag);
				
		return menuBar;		
	}
	
	private class ExitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);			
		}
	}
}



