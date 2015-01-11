package global;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class SettingsValidator {
	
	/**
	 * Private - no instantiation
	 */
	private SettingsValidator(){
		
	}
	
	/**
	 * Checks if all settings are validate.
	 * @param settings The settings which shall be checked.
	 * @return true, if all settings are correct. Else false.
	 */
	public static boolean validateSettings(Settings settings){
		return validateURLLocation(settings);
	}
	
	/**
	 * Checks if the openz-export-location is correctly set.
	 * @param settings Settings with the openz-export-location.
	 * @return True, if correctly set. Else false.
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
