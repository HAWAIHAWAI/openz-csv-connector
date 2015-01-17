package io.tests;

import static org.junit.Assert.assertTrue;
import io.BillDownloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;

import global.Settings;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zeroturnaround.zip.ZipUtil;

import static common.FileOperations.*;

/**
 * Tests for Bill downloads.
 * 
 * @author cglaeser
 *
 */
public class BillDownloaderTests {

	private Settings settings;

	/**
	 * Setup up for tests.
	 * @throws MalformedURLException
	 *             URL is not in an accepted format. Use
	 *             http://www.example.com/ressource as format
	 */
	@Before
	public void setUp() throws MalformedURLException {
		settings = new Settings();
		settings.setURL(new URL(
				"http://localhost:8080/openz-csv-export/Bills.zip"));
		settings.setFolderLocation(new File(getProgramDirectory()
				+ File.separator + "bills"));
		settings.setUpdateInterval(1);
		settings.setXmlBillListName(new File("bills.xml"));
	}

	/**
	 * Checks if the downloaded file contains the specified .csv
	 * @throws IOException
	 */
	@Test
	public void downloadFile() throws IOException {
		BillDownloader bd = new BillDownloader(settings);
		File file = bd.getBills();
		assertTrue(ZipUtil.containsEntry(file,
				"88E0E5CB1A294C6AA174708A587486EC.csv"));
	}

	/**
	 * Checks if the specified folder (configured in settings )
	 * contains the csv-file after a refresh.
	 * @throws IOException
	 */
	@Test
	public void unzipFile() throws IOException {
		BillDownloader bd = new BillDownloader(settings);
		bd.refreshBills();
		assertTrue(fileIsInDirectory(new File(
				"88E0E5CB1A294C6AA174708A587486EC.csv"),
				settings.getFolderLocation()));
	}

	/**
	 * Checks if a bill (mirrored as csv) is still in the
	 * folder after a specified amount of refreshing calls.
	 * @throws IOException
	 */
	@Test
	public void unzipMoreThanOnce() throws IOException {
		BillDownloader bd = new BillDownloader(settings);
		for (int i = 0; i < 10; i++) {
			bd.refreshBills();
		}
		assertTrue(fileIsInDirectory(new File(
				"88E0E5CB1A294C6AA174708A587486EC.csv"),
				settings.getFolderLocation()));
	}

	/**
	 * Test if the running programm can be scheduled and cancelled.
	 * @throws InterruptedException
	 *             Timed execution example
	 */
	@Test
	public void runBillDownloadAsTask() throws InterruptedException {
		Timer time = new Timer();
		BillDownloader bd = new BillDownloader(settings);
		time.schedule(bd, settings.getUpdateInterval() * 1000);
		// Simulates running program
		Thread.sleep(10000);
		time.cancel();
	}

	/**
	 * Clean up after tests.
	 */
	@After
	public void cleanUp() {
		deleteDirectory(settings.getFolderLocation());
	}
}
