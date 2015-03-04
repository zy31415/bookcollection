package bookcollection.gui.docspanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bookcollection.config.FilesInDirectory;


public class NewDocDialog extends JDialog implements ActionListener{
	public NewDocDialog(){
		super();
		this.setBounds(100, 100, 350, 350);
		this.setTitle("New document");
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4,2));		
		
		
		inputPanel.add(createAuthorsPanel());
		inputPanel.add(createYearPanel());
		inputPanel.add(createPublisherPanel());
		inputPanel.add(createPathPanel());
		
		contentPane.add(inputPanel, BorderLayout.CENTER);
		
		
		Box baseBox = Box.createHorizontalBox();
		contentPane.add(baseBox, BorderLayout.SOUTH);		
		
		baseBox.add(Box.createHorizontalGlue());
		
		JButton confirmButton = new JButton("ADD");
		baseBox.add(confirmButton);
		
		baseBox.add(Box.createHorizontalGlue());
		
		JButton cancelItemButton = new JButton("CANCEL");
		cancelItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();               
            }}
		);
		baseBox.add(cancelItemButton);	
		
		baseBox.add(Box.createHorizontalGlue());
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(true);			
	}
	
	private JPanel createAuthorsPanel(){
		JPanel authorsPanel = new JPanel();
		authorsPanel.setLayout(new FlowLayout());
		JLabel authorsLabel = new JLabel("authors");
		authorsPanel.add(authorsLabel);
		
		JTextArea authorsTextArea = new JTextArea("", 4, 20);
		authorsPanel.add(authorsTextArea);
		return authorsPanel;
	}
	
	private JPanel createYearPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel yearLabel = new JLabel("year");
		panel.add(yearLabel);
		
		JTextField yearField = new JTextField(5);
		panel.add(yearField);
		
		return panel;
	}
	
	private JPanel createPublisherPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel yearLabel = new JLabel("publisher");
		panel.add(yearLabel);
		
		JTextField yearField = new JTextField(30);
		panel.add(yearField);
		
		return panel;
	}
	
	private JPanel createPathPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel yearLabel = new JLabel("file path");
		panel.add(yearLabel);
		
		JTextArea textArea = new JTextArea("", 4, 30);
		JButton button = new ChooseAFileButton(textArea);
		panel.add(button);
		panel.add(textArea);
		
		
		return panel;
	}
	
	private class ChooseAFileButton extends JButton implements ActionListener{
		JTextArea textArea;
		public ChooseAFileButton(JTextArea textArea){
			super("Locate a file...");
			this.textArea = textArea;
			this.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e){
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("/home/zy/3Archive/collections/"));
			chooser.setDialogTitle("Choose files");
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setMultiSelectionEnabled(true);
			chooser.setVisible(true);
			
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File files [] = chooser.getSelectedFiles();
				
				for (File f : files){
					System.out.println(f);
					textArea.append(f.toString()+"\n");
				}
				
				} else {
			      System.out.println("No Selection ");
			    }
		}
		}
}
