package global;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author HAWAI
 */
public class Settings {
	
	private String url;
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
	public String getURL() {
		return url;
	}
	public void setURL(String url) {
		this.url = url;
	}
	public Integer getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(Integer updateInterval) {
		this.updateInterval = updateInterval;
	}

}
