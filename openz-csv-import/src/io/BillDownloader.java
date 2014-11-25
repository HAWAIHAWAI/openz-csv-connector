package io;

import io.xml.BillXML;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import global.Settings;

public class BillDownloader extends TimerTask{
	
	List<String> listOfBills;
	Settings settings;
	File localFile;
	
	public BillDownloader(Settings settings){
		this.settings = settings;		
		localFile = new File("bills.zip");
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
		System.out.println("Bills refresh started");	
		getBills();
			unpack(settings.getFolderLocation());
			listOfBills = BillXML.xmlToStringList(settings.getFullBillListName());
			System.out.println("listOfBills: "+ listOfBills);
			localFile.delete();
	}
	
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
	
	public File getBill(){
		return null;
	}
	
	private void unpack(File folderLocation) {
		ZipUtil.unpack(localFile, folderLocation);
	}
	
}
