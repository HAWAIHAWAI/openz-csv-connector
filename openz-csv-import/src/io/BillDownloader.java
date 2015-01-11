package io;

import global.Settings;
import io.xml.BillXML;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

/**
 * Class for "downloading" Bills from OpenZ and putting
 * them as files in a folder.
 * 
 * @author cglaeser.
 */
public class BillDownloader extends TimerTask{
	
	/**
	 * Bills as a list of Strings.
	 */
	private List<String> listOfBills;
	
	/**
	 * Settings of the BillDownloader.
	 */
	private Settings settings;
	
	/**
	 * File object for the zip.
	 */
	private File localFile;
	
	/**
	 * Constructor for BillDownloader.
	 * @param settings Settings for the BillDownloader.
	 */
	public BillDownloader(Settings settings){
		this.settings = settings;		
		localFile = new File("bills.zip");
		listOfBills = new ArrayList<String>();
	}
	
	/**
	 * Refreshes the files in the bill folder.
	 */
	@Override
	public void run() {
		try {
			refreshBills();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Gets Bills and unpacks them to folder specified by settings.
	 * @throws IOException
	 */
	public void refreshBills() throws IOException{
		System.out.println("Bills refresh started");	
		getBills();
			unpack(settings.getFolderLocation());
			listOfBills = BillXML.xmlToStringList(settings.getFullBillListName());
			System.out.println("listOfBills: "+ listOfBills);
			localFile.delete();
	}
	
	/**
	 * Retrieves the zipped bills as file object.
	 * @return File object with zipped biles.
	 * @throws IOException
	 */
	public File getBills() throws IOException{
		URL url = new URL(settings.getURL() + "/" + settings.getZipBillsName());
		//FileUtils.copyURLToFile(url, filename);
		//usually only FileUtils.copyURLtoFile, but due to certificate problems the certificate used cannot be verified.
		//Lengthy method start here
		/*HttpsURLConnection httpsCon = (HttpsURLConnection) url.openConnection();  
        httpsCon.setHostnameVerifier(new HostnameVerifier()  
        {        
        	@Override
        	public boolean verify(String hostname, SSLSession session)  
            {  
                return true;  
            }
        });  
        httpsCon.connect();  
        InputStream is = httpsCon.getInputStream();  
		FileUtils.copyInputStreamToFile(is, filename);*/
		FileUtils.copyURLToFile(url, localFile);
		System.out.println(localFile.getAbsolutePath());
		return localFile;
	}
	
	/**
	 * Unpacks the file object (a zip file) in the specified folder.
	 * @param folderLocation Folder, where the files shall be stored.
	 */
	private void unpack(File folderLocation) {
		ZipUtil.unpack(localFile, folderLocation);
	}
	
}
