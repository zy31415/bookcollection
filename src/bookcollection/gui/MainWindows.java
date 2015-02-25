package bookcollection.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import bookcollection.config.DirectoryInitializer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindows {

	private JFrame frame;

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
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		ChooseDirectoryMenuItem mntmChooseDirectory = new ChooseDirectoryMenuItem();
		mnFile.add(mntmChooseDirectory);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitActionListener());
		mnFile.add(mntmExit);
		
		// Create a panel to hold all other components
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		
		
		String	listData[] =
			{
				"Item 1",
				"Item 2",
				"Item 3",
				"Item 4"
			};
		
		JList list = new JList(listData);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		topPanel.add(list);
		
		//Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		                           topPanel, new JPanel());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		topPanel.setMinimumSize(minimumSize);
		
		frame.getContentPane().add(splitPane);
		
		
	}
	
	private class ExitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);			
		}
	}
	
	private class ChooseDirectoryMenuItem extends JMenuItem implements ActionListener{
		private File directory;
		private JFileChooser chooser;
		
		public ChooseDirectoryMenuItem(){
			super("Choose directory");
			initChooser();
			this.addActionListener(this);
		}
		
		private void initChooser(){
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Choose a directory");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				directory = chooser.getSelectedFile();
				System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				} else {
			      System.out.println("No Selection ");
			    }
		}
	}

}



