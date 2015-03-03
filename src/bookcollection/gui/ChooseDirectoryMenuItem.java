package bookcollection.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import bookcollection.config.FilesInDirectory;

public class ChooseDirectoryMenuItem extends JMenuItem implements ActionListener{
	private static final long serialVersionUID = 1L;
	private File directory;
	private JFileChooser chooser;
	
	public ChooseDirectoryMenuItem(){
		super("Choose directory...");
		initChooser();
		this.addActionListener(this);
	}
	
	private void initChooser(){
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("/home/zy/3Archive/collections/"));
		chooser.setDialogTitle("Choose a directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			directory = chooser.getSelectedFile();
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
			
			FilesInDirectory files = new FilesInDirectory(directory);
			files.iterateAllFiles();
			
			for (File f : files.getFiles()){
				System.out.println(f);
			}
			
			} else {
		      System.out.println("No Selection ");
		    }
	}
}
