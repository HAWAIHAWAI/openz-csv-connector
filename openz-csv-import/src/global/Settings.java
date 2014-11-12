package global;

import java.net.URL;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author HAWAI
 */
public class Settings {
	
	private URL url;
	private String folderLocation;
	private Integer updateInterval;
	
	/**
	 * @return the location of the folder where files should be unpacked
	 */
	public String getFolderLocation() {
		return folderLocation;
	}
	public void setFolderLocation(String folderLocation) {
		this.folderLocation = folderLocation;
	}
	public URL getURL() {
		return url;
	}
	public void setURL(URL url) {
		this.url = url;
	}
	public Integer getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(Integer updateInterval) {
		this.updateInterval = updateInterval;
	}

}
