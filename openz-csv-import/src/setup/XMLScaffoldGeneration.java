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
public class XMLScaffoldGeneration {
	
	URL url;
	Integer updateInterval;
	File folderLocation;
	
	/**
	 * Default values
	 */
	public XMLScaffoldGeneration(){
		try {
			url = new URL("http://localhost:8080/openz-csv-export/Bills.zip");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateInterval = 15;
		folderLocation = new File(FileOperations.getProgramDirectory() + File.separator + "bills");
	}
	
	/**
	 * Custom values
	 * @param url The url for creasting the XML scaffold
	 * @param updateInterval The update interval in seconds
	 * @param folderLocation The folder location 
	 */
	public XMLScaffoldGeneration(URL url, Integer updateInterval, File folderLocation){
		this.url = url;
		this.updateInterval = updateInterval;
		this.folderLocation = folderLocation;
	}
	
	/**
	 * Generates the XML scaffold
	 * @return A string with the generated XML file as content
	 */
	public String generateXMLScaffold(){
		Settings settings = new Settings();
		settings.setFolderLocation(folderLocation);
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
        XMLScaffoldGeneration xmlScaffold = new XMLScaffoldGeneration();
        PrintWriter writer = new PrintWriter("settings.xml", "UTF-8");
        writer.println(xmlScaffold.generateXMLScaffold());
        writer.close();
    }
}
