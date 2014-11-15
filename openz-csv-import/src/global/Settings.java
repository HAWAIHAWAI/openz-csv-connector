package global;

import java.io.File;
import java.net.URL;

/**
 * Globale Einstellungen für die Programinitalisierung
 * @author cglaeser
 */
public class Settings {
	
	/**
	 * The url where the openz-export web site is located
	 */
	private URL url;
	private File folderLocation;
	private File xmlBillListName;
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
	
	
	/**
	 * @return The name of the Bill list in the bill folder, e.g. bills.xml
	 */
	public File getXmlBillListName() {
		return xmlBillListName;
	}
	
	/**
	 * @return The full path to the bill list e.g. C:\....\bills\bills.xml
	 */
	public File getFullBillListName(){
		return new File(this.folderLocation + File.separator + this.xmlBillListName);
	}
	
	/**
	 * @param xmlBillListName The name of the bill list in the bill folder, e.g. bills.xml
	 */
	public void setXmlBillListName(File xmlBillListName) {
		this.xmlBillListName = xmlBillListName;
	}
	
	

}
