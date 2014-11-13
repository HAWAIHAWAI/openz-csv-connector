package io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import global.Settings;

public class BillDownloader extends Thread{
	
	Settings settings;
	File file;
	
	public BillDownloader(Settings settings, File file){
		this.settings = settings;		
		this.file = file;
	}
	
	public void refreshBills() throws IOException{
		
		while(!this.isInterrupted()){
			try {
				this.sleep(settings.getUpdateInterval());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				this.interrupt();
			}
			getBills();
			
			
			//TODO We need some good logic here to do the following
			/*
			 * 1. Create folder to store files in if it doesn't exist
			 * 2. Every x seconds, where x is determined by an interval set by settings, wake up and refresh folder
			 * 2a Refresh folder only with those files that are new!
			 * */
		}
	}
	
	public File getBills() throws IOException{
		URL url = settings.getURL();
		FileUtils.copyURLToFile(url, file);
		System.out.println(file.getAbsolutePath());
		return file;
	}
	
	private void refreshBillFolder() {
		ZipUtil.unpack(file, settings.getFolderLocation());
	}
}
