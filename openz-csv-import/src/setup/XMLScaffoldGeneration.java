package setup;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

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
		try {
			settings.setURL(new URL("http://localhost:8080/openz-csv-export/Bills.zip"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
