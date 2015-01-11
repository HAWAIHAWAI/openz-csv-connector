package output.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import output.BillHandler;
import pojo.Bill;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Utility-class for converting bills to XML.
 * 
 * @author Christian Gläser
 *
 */
public class BillList {

	private BillList() {
		// No instantiation
	}

	/**
	 * Method for converting a list of bills to a XML string.
	 * 
	 * @return A list of Bills as XML string.
	 */
	public static String convertBillsToXML() {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("billNumber", String.class);
		List<String> listOfBillNumbers = new ArrayList<>();
		for (Bill b : new BillHandler().getBills()) {
			listOfBillNumbers.add(b.getInvoiceNumber());
		}
		return xstream.toXML(listOfBillNumbers);
	}

	/**
	 * Method to convert bills to a XML file.
	 * 
	 * @param storageDirectory
	 *            Directory for the XML file.
	 * @return XML-file object.
	 */
	public static File convertBillsToXMLFile(File storageDirectory) {
		File xml = new File(storageDirectory + File.separator + "bills.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(xml), "UTF-8"));
			bw.write(convertBillsToXML());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xml;
	}
}
