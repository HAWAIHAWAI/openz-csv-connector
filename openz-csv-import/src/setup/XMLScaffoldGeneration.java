package setup;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import global.Settings;

public class XMLScaffoldGeneration {
	
	/**
	 * Generates the XML scaffold 
	 */
	public String generateXMLScaffold(){
		Settings settings = new Settings();
		settings.setFolderLocation("folderLocation");
		settings.setStorageLocation("storageLocation");
		settings.setUpdateInterval(15);
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("settings", Settings.class);
        return xStream.toXML(settings);
	}
	
    /**
     * Creates a XML scaffold
     * @param args 
     * @throws UnsupportedEncodingException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        XMLScaffoldGeneration xmlScaffold = new XMLScaffoldGeneration();
        PrintWriter writer = new PrintWriter("settings.xml", "UTF-8");
        writer.println(xmlScaffold.generateXMLScaffold());
        writer.close();

    }


}
