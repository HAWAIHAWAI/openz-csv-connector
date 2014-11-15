package io.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author cglaeser
 *
 */
public class BillXML {
	
	public static List<String> xmlToStringList(File xmlFile) throws FileNotFoundException{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("billNumber", String.class);
		String xmlString = "";
		try {
			xmlString = FileUtils.readFileToString(xmlFile, "UTF-8");
			System.out.println("BillXML-xmlString: " + xmlString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
		return (List<String>) xstream.fromXML(xmlString);
	}
	
	
}
