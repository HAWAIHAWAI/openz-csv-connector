package io;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import global.Settings;

public class BillDownloader extends TimerTask{
	
	Settings settings;
	File filename;
	Integer numberOfExecutions;
	
	public BillDownloader(Settings settings){
		this.settings = settings;		
		filename = new File("bills.zip");
		numberOfExecutions = 0;
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
			unpack();
			filename.delete();
			++numberOfExecutions;
	}
	
	public File getBills() throws IOException{
		URL url = settings.getURL();
		FileUtils.copyURLToFile(url, filename);
		System.out.println(filename.getAbsolutePath());
		return filename;
	}
	
	private void unpack() {
		ZipUtil.unpack(filename, settings.getFolderLocation());
	}
}
