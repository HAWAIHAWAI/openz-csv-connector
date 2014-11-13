package io.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import io.BillDownloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import global.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zeroturnaround.zip.ZipUtil;

import static common.FileOperations.*;

/**
 * Tests for Bill downloads
 * @author cglaeser
 *
 */
public class BillDownloaderTests {
	
	Settings settings;
	
	/**
	 * @throws MalformedURLException
	 */
	@Before
	public void setUp() throws MalformedURLException{
		settings = new Settings();
		settings.setURL(new URL("http://localhost:8080/openz-csv-export/Bills.zip"));
		settings.setFolderLocation(new File(getProgramDirectory() + File.separator + "bills"));
		
	}
	
	
	@Test
	public void downloadFile() throws IOException{
		BillDownloader bd = new BillDownloader(settings);
		File file = bd.getBills();
		assertTrue(ZipUtil.containsEntry(file, "88E0E5CB1A294C6AA174708A587486EC.csv"));
		}
	
	@Test
	public void unzipFile() throws IOException{
		BillDownloader bd = new BillDownloader(settings);
		bd.refreshBills();
		assertTrue(fileIsInDirectory(new File("88E0E5CB1A294C6AA174708A587486EC.csv"), settings.getFolderLocation()));
	}
	
	@Test
	public void unzipMoreThanOnce() throws IOException{
		BillDownloader bd = new BillDownloader(settings);
		for(int i=0;i<10;i++){bd.refreshBills();}
		assertTrue(fileIsInDirectory(new File("88E0E5CB1A294C6AA174708A587486EC.csv"), settings.getFolderLocation()));
	}
	
	@After
	public void cleanUp(){
		deleteDirectory(settings.getFolderLocation());		
	}
	
}
