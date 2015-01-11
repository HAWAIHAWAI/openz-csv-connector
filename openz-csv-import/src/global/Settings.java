package global;

import java.io.File;
import java.net.URL;

/**
 * Global settings for initialization.
 * 
 * @author cglaeser
 */
public class Settings {

	/**
	 * The url where the openz-export web site is located
	 */
	private URL url;
	/**
	 * File object with the folder where files should be unpacked.
	 */
	private File folderLocation;

	/**
	 * File object for the Bill list in the folder.
	 */
	private File xmlBillListName;

	/**
	 * Name of the zipped bills.
	 */
	private String zipBillsName;

	/**
	 * Update interval in seconds.
	 */
	private Integer updateInterval;

	/**
	 * Retrieves the file object where files should be unpacked.
	 * 
	 * @return the location of the folder where files should be unpacked
	 */
	public File getFolderLocation() {
		return folderLocation;
	}

	/**
	 * Sets the new file location for unpackaging the files.
	 * 
	 * @param newFileLocation
	 *            The new Folder Location
	 */
	public void setFolderLocation(File newFileLocation) {
		folderLocation = newFileLocation;
	}

	/**
	 * Retrieves the url where the openz-export web site is located.
	 * 
	 * @return A url in the form "http://www.example.com/resourceIdentifier"
	 */
	public URL getURL() {
		return url;
	}

	/**
	 * Sets the url were the openz-export web site is located.
	 * 
	 * @param newUrl
	 *            A url in the form "http://www.example.com/resourceIdentifier"
	 */
	public void setURL(URL newUrl) {
		url = newUrl;
	}

	/**
	 * Retrieves the update interval in seconds.
	 * 
	 * @return The update Interval in seconds
	 */
	public Integer getUpdateInterval() {
		return updateInterval;
	}

	/**
	 * Sets the update interval in seconds.
	 * 
	 * @param newUpdateInterval
	 *            The update interval in seconds
	 */
	public void setUpdateInterval(Integer newUpdateInterval) {
		updateInterval = newUpdateInterval;
	}

	/**
	 * Retrieves the file object for the Bill list.
	 * 
	 * @return The name of the Bill list in the bill folder, e.g. bills.xml
	 */
	public File getXmlBillListName() {
		return xmlBillListName;
	}

	/**
	 * Retrieves the full path to the bill list as a file object.
	 * 
	 * @return The full path to the bill list e.g. C:\....\bills\bills.xml
	 */
	public File getFullBillListName() {
		return new File(this.folderLocation + File.separator
				+ this.xmlBillListName);
	}

	/**
	 * Sets the name of the bill list in the bill folder.
	 * 
	 * @param xmlBillListName
	 *            The name of the bill list in the bill folder, e.g. bills.xml
	 */
	public void setXmlBillListName(File xmlBillListName) {
		this.xmlBillListName = xmlBillListName;
	}

	/**
	 * Retrieves the name of zipped bills.
	 * 
	 * @return Name of the zipped bills.
	 */
	public String getZipBillsName() {
		return zipBillsName;
	}

	/**
	 * Sets the name of the zipped bills.
	 * 
	 * @param billZipName
	 *            New name of the zipped bills.
	 */
	public void setZipBillsName(String billZipName) {
		this.zipBillsName = billZipName;
	}

}
