package output.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import output.BillHandler;
import pojo.Bill;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BillList {
	
	private BillList(){
		//No instantiation
	}
	
	/**
	 * @return A list of Bills as XML
	 */
	public static String convertBillsToXML(){
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("billNumber", String.class);
		List<String> listOfBillNumbers = new ArrayList();
		for(Bill b:new BillHandler().getBills()){
			listOfBillNumbers.add(b.getInvoiceNumber());
		}
		return xstream.toXML(listOfBillNumbers);
	}
	
	public static File convertBillsToXMLFile(File storageDirectory){
		File xml = new File(storageDirectory + File.separator + "bills.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xml),"UTF-8"));
	 	    bw.write(convertBillsToXML());
	 	    bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 	    return xml;
	}
}
