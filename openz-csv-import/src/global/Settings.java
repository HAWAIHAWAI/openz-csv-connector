package global;

import java.net.URL;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author HAWAI
 */
public class Settings {
	
	private static URL url;
	private static String folderLocation;
	private static Integer updateInterval;
	
	/**
	 * @return the location of the folder where files should be unpacked
	 */
	public static String getFolderLocation() {
		return folderLocation;
	}
	public static void setFolderLocation(String newFileLocation) {
	    folderLocation = newFileLocation;
	}
	public static URL getURL() {
		return url;
	}
	public static void setURL(URL newUrl) {
		url = newUrl;
	}
	public static Integer getUpdateInterval() {
		return updateInterval;
	}
	public static void setUpdateInterval(Integer newUpdateInterval) {
		updateInterval = newUpdateInterval;
	}

}
