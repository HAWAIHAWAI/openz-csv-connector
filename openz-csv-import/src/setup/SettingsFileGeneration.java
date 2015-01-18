package setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import common.FileOperations;

import global.Settings;

/**
 * Intended to generate the XML settings files
 * 
 * @author Christian Gläser
 */
public class SettingsFileGeneration {

	/**
	 * Url for the web interface.
	 */
	private URL url;

	/**
	 * Update interval in seconds.
	 */
	private Integer updateInterval;

	/**
	 * Location of the folder.
	 */
	private File folderLocation;

	/**
	 * File object for the bills.xml
	 */
	private File xmlBillListName;

	/**
	 * File object for the zipped.bills.
	 */
	private File zipBillsName;

	/**
	 * Constructor with default values.
	 */
	public SettingsFileGeneration() {
		try {
			url = new URL("http://141.22.32.186/openz-csv-export");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateInterval = 15;
		folderLocation = new File(FileOperations.getProgramDirectory()
				+ File.separator + "bills");
		xmlBillListName = new File("Bills.xml");
		zipBillsName = new File("Bills.zip");
	}

	/**
	 * Constructor for custom values.
	 * 
	 * @param url
	 *            The url for creasting the XML scaffold
	 * @param updateInterval
	 *            The update interval in seconds
	 * @param folderLocation
	 *            The folder location
	 * @param xmlBillListName Name of the xml bill list.
	 * @param zipBillsName Name of zip-file containing the bills.
	 */
	public SettingsFileGeneration(URL url, Integer updateInterval,
			File folderLocation, File xmlBillListName, File zipBillsName) {
		this.url = url;
		this.updateInterval = updateInterval;
		this.folderLocation = folderLocation;
		this.xmlBillListName = xmlBillListName;
		this.zipBillsName = zipBillsName;
	}

	/**
	 * Generates the XML scaffold
	 * 
	 * @return A string with the generated XML file as content
	 */
	public String generateXMLScaffold() {
		Settings settings = new Settings();
		settings.setFolderLocation(folderLocation);
		settings.setXmlBillListName(xmlBillListName);
		settings.setURL(url);
		settings.setUpdateInterval(15);
		settings.setZipBillsName("Bills.zip");
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("settings", Settings.class);
		return xStream.toXML(settings);
	}

	/**
	 * Creates a XML scaffold with default values
	 * 
	 * @param args
	 *            arguments for XMLScaffoldGeneration - currently none
	 * @throws UnsupportedEncodingException
	 *             The character encoding is not supported
	 * @throws FileNotFoundException
	 *             File path could not be opened
	 */
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		SettingsFileGeneration xmlScaffold = new SettingsFileGeneration();
		PrintWriter writer = new PrintWriter("settings.xml", "UTF-8");
		writer.println(xmlScaffold.generateXMLScaffold());
		writer.close();
	}
}
