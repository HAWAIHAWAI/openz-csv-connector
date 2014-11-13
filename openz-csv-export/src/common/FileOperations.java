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

}
