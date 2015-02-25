package bookcollection.gui;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenDirectory = new JMenuItem("Open Directory");
		mntmOpenDirectory.addActionListener(new OpenDirectoryActionListener());
		mnFile.add(mntmOpenDirectory);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitActionListener());
		mnFile.add(mntmExit);
		
	}
	
	private class OpenDirectoryActionListener implements ActionListener {
		private JFileChooser chooser;
		private File directoryChoosen;
		
		public OpenDirectoryActionListener(){
			initChooser();
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				directoryChoosen = chooser.getSelectedFile();
				System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				} else {
			      System.out.println("No Selection ");
			    }
		}
		
		private void initChooser(){
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Choose a directory");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);	
		}
	}
	
	private class ExitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);			
		}
	}
	
	private class BookCollectionDirectoryChooser extends JFileChooser implements ActionListener{
		private File directory;
		
		public void actionPerformed(ActionEvent arg0) {
			if (this.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				directory = this.getSelectedFile();
				System.out.println("getSelectedFile() : " + this.getSelectedFile());
				} else {
			      System.out.println("No Selection ");
			    }
		}
		
		public getDirectory(){
			return directory;
		}
	}

}



