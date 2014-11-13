package io.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import io.BillDownloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import global.Settings;

import org.junit.Test;
import org.zeroturnaround.zip.ZipUtil;

public class BillDownloaderTests {
	
	@Test
	public void DownloadFile() throws IOException{
		Settings.setURL(new URL("http://localhost:8080/openz-csv-export/Bills.zip"));
		BillDownloader bd = new BillDownloader(new File("bills.zip"));
		File file = bd.getBills();
		assertTrue(ZipUtil.containsEntry(file, "88E0E5CB1A294C6AA174708A587486EC.csv"));}
}
