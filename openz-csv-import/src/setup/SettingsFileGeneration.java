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
 * @author Christian Gläser
 * Intended to generate the XML settings files
 */
public class SettingsFileGeneration {
	
	URL url;
	Integer updateInterval;
	File folderLocation;
	File xmlBillListName;
	


	/**
	 * Default values
	 */
	public SettingsFileGeneration(){
		try {
			url = new URL("https://141.22.32.186/openz-csv-export/openz-csv-export");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateInterval = 15;
		folderLocation = new File(FileOperations.getProgramDirectory() + File.separator + "bills");
		xmlBillListName = new File("Bills.xml");
	}
	
	/**
	 * Custom values
	 * @param url The url for creasting the XML scaffold
	 * @param updateInterval The update interval in seconds
	 * @param folderLocation The folder location 
	 */
	public SettingsFileGeneration(URL url, Integer updateInterval, File folderLocation, File xmlBillListName){
		this.url = url;
		this.updateInterval = updateInterval;
		this.folderLocation = folderLocation;
		this.xmlBillListName = xmlBillListName;
	}
	
	/**
	 * Generates the XML scaffold
	 * @return A string with the generated XML file as content
	 */
	public String generateXMLScaffold(){
		Settings settings = new Settings();
		settings.setFolderLocation(folderLocation);
		settings.setXmlBillListName(xmlBillListName);
		settings.setURL(url);
		settings.setUpdateInterval(15);
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("settings", Settings.class);
        return xStream.toXML(settings);
	}
	
    /**
     * Creates a XML scaffold with default values
     * @param args arguments for XMLScaffoldGeneration - currently none
     * @throws UnsupportedEncodingException The character encoding is not supported
     * @throws FileNotFoundException File path could not be opened
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        SettingsFileGeneration xmlScaffold = new SettingsFileGeneration();
        PrintWriter writer = new PrintWriter("settings.xml", "UTF-8");
        writer.println(xmlScaffold.generateXMLScaffold());
        writer.close();
    }
}
