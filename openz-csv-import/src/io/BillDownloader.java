package io;

import io.xml.BillXML;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import global.Settings;

public class BillDownloader extends TimerTask{
	
	List<String> listOfBills;
	Settings settings;
	File filename;
	
	public BillDownloader(Settings settings){
		this.settings = settings;		
		filename = new File("bills.zip");
		listOfBills = new ArrayList<String>();
	}
	
	@Override
	public void run() {
		try {
			refreshBills();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * @throws IOException
	 * Gets Bills and unpacks them to folder specified by settings 
	 */
	public void refreshBills() throws IOException{
			getBills();
			unpack(settings.getFolderLocation());
			listOfBills = BillXML.xmlToStringList(settings.getFullBillListName());
			System.out.println("listOfBills: "+ listOfBills);
			filename.delete();
	}
	
	public File getBills() throws IOException{
		URL url = settings.getURL();
		FileUtils.copyURLToFile(url, filename);
		System.out.println(filename.getAbsolutePath());
		return filename;
	}
	
	public File getBill(){
		return null;
	}
	
	private void unpack(File folderLocation) {
		ZipUtil.unpack(filename, folderLocation);
	}
	
}
