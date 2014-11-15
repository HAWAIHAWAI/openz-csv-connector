package global;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class SettingsValidator {
	
	private SettingsValidator(){
		
	}
	
	/**
	 * @param settings
	 * @return true if all settings are correct
	 */
	public static boolean validateSettings(Settings settings){
		return validateURLLocation(settings);
	}
	
	/**
	 * If the openz-export-location is correctly set, this method returns true
	 * @param settings
	 * @return 
	 */
	public static boolean validateURLLocation(Settings settings){
		try {
			URL validationURL = new URL(settings.getURL().toString()  + "/ProgramIdentifier");
			String out = IOUtils.toString(validationURL);
			System.out.println("URL returns: " + out);
			if(out.equals("openz-csv-export")){
				return true;
			}
		} catch (IOException e) {
			
		}
		return false;
	}

}
