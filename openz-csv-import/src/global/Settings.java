package global;

import java.io.File;
import java.net.URL;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author cglaeser
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
	/**
	 * @param newFileLocation The new Folder Location
	 */
	public void setFolderLocation(File newFileLocation) {
	    folderLocation = newFileLocation;
	}
	/**
	 * @return A url in the form "http://www.example.com/resourceIdentifier"
	 */
	public URL getURL() {
		return url;
	}
	/**
	 * @param newUrl A url in the form "http://www.example.com/resourceIdentifier"
	 */
	public void setURL(URL newUrl) {
		url = newUrl;
	}
	/**
	 * @return The update Interval in seconds
	 */
	public Integer getUpdateInterval() {
		return updateInterval;
	}
	/**
	 * @param newUpdateInterval The update Interval in seconds
	 */
	public void setUpdateInterval(Integer newUpdateInterval) {
		updateInterval = newUpdateInterval;
	}

}
