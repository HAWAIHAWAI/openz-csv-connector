package global;

import java.io.File;
import java.net.URL;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author HAWAI
 */
public class Settings {
	
	private URL url;
	private File folderLocation;
	private Integer updateInterval;
	
	/**
	 * @return the location of the folder where files should be unpacked
	 */
	public File getFolderLocation() {
		return folderLocation;
	}
	public void setFolderLocation(File newFileLocation) {
	    folderLocation = newFileLocation;
	}
	public URL getURL() {
		return url;
	}
	public void setURL(URL newUrl) {
		url = newUrl;
	}
	public Integer getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(Integer newUpdateInterval) {
		updateInterval = newUpdateInterval;
	}

}
