package io.tests;

import static org.junit.Assert.fail;
import io.BillDownloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import global.Settings;

import org.junit.Test;

public class BillDownloaderTests {
	
	@Test
	public void DownloadFile() throws IOException{
		Settings settings = new Settings();
		settings.setURL(new URL("http://www.haw-hamburg.de/impressum/print.html"));
		BillDownloader bd = new BillDownloader(settings, new File("bills.zip"));
		File file = bd.getBills();
	}

}
