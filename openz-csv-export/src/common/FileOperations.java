package common;

import java.io.File;
import java.io.IOException;

/**
 * @author Christian Gläser
 *
 */
public class FileOperations {
	
	public static File getProgramDirectory(){
		String currentDir = "";
		try {
			currentDir = new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new File(currentDir);
	}
	
	/**
	 * Deletes directory and all files included
	 * @param directory The directory to delete
	 * @return The result of the deletion attempt, true if successful, else false
	 */
	public static boolean deleteDirectory(File directory){
	    if( directory.exists() ) {
	        File[] files = directory.listFiles();
	        for(int i=0; i<files.length; i++) {
	           if(files[i].isDirectory()) {
	             deleteDirectory(files[i]);
	           }
	           else {
	             files[i].delete();
	           }
	        }
	      }
	      return(directory.delete());
	}

}
