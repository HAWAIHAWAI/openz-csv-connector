package io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import global.Settings;

public class BillDownloader extends Thread{
	
	Settings settings;
	File filename;
	
	public BillDownloader(Settings settings){
		this.settings = settings;		
		filename = new File("bills.zip");
	}
	
	public void repeat() {
	  while(!this.isInterrupted()){
		try {
			this.sleep(settings.getUpdateInterval());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			this.interrupt();
		}
		//TODO We need some good logic here to do the following
		/*
		 * 1. Create folder to store files in if it doesn't exist
		 * 2. Every x seconds, where x is determined by an interval set by settings, wake up and refresh folder
		 * 2a Refresh folder only with those files that are new!
		 * */
	}
	}
	
	/**
	 * @throws IOException
	 * Gets Bills and unpacks them to folder specified by settings 
	 */
	public void refreshBills() throws IOException{
			getBills();
			unpack();
			filename.delete();
	}
	
	public File getBills() throws IOException{
		URL url = settings.getURL();
		FileUtils.copyURLToFile(url, filename);
		System.out.println(filename.getAbsolutePath());
		return filename;
	}
	
	/**
	 * @return The absolute location of the Bill
	 * @throws IOException  If an I/O error occurs, which is possible because the construction of the canonical pathname may require filesystem queries
	 */
	private String getBillLocation() throws IOException{
		return filename.getCanonicalPath();
	}
	
	private void unpack() {
		ZipUtil.unpack(filename, settings.getFolderLocation());
	}
}
