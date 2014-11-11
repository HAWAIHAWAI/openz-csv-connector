package global;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author HAWAI
 */
public class Settings {
	
	private String storageLocation;
	private String folderLocation;
	private Integer updateInterval;
	
	public String getFolderLocation() {
		return folderLocation;
	}
	public void setFolderLocation(String folderLocation) {
		this.folderLocation = folderLocation;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public Integer getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(Integer updateInterval) {
		this.updateInterval = updateInterval;
	}

}
