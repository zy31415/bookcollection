package bookcollection.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesInDirectory {
	protected File directory = null;
	protected List<File> files = null;
	
	public FilesInDirectory(File directory){
		this.directory = directory;
		files = new ArrayList<File>();
	}
	
	public void iterateAllFiles(){
		File [] tp = {directory};
		iterateAllFiles(tp);
	}
	
	public void iterateAllFiles(File[] directory){
		for (File file : directory) {
	        if (file.isDirectory()) {
	            System.out.println("Directory: " + file.getName());
	            iterateAllFiles(file.listFiles()); // Calls same method again.
	        } else { 
	        	files.add(file);
	        }
	    }
		
	}
	
	public File [] getFiles(){
		Object [] arr = files.toArray();
		return Arrays.copyOf(arr, arr.length, File[].class);
	}

}
