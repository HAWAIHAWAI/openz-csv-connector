package output.csv.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import output.BillHandler;
import output.csv.CSVWriter;

import common.FileOperations;

/**
 * Testclass for CSVWriter.
 * 
 * @author Florian Arfert
 * @author Christian Gläser
 *
 */
public class TestCSVWriter {
	/**
	 * Used for the tests.
	 */
	private CSVWriter writer;

	/**
	 * Setup before every test.
	 */
	@Before
	public void setUp() {
		writer = null;
	}

	/**
	 * Test for getter and setter. Setter will be tested first, due the fact
	 * that setters are used in constructors. A CSVWriter-object is going to be
	 * initialized with null as arguments and there won't be a directory (null
	 * is not a valid directory). After the object has been created, there will
	 * be tests for null and then tests for getter and setter. Values will be
	 * set(via setter) and then compared with the getter and the expected
	 * results.
	 * 
	 * @throws IOException
	 *             No directory
	 */
	@Test
	public void testGetterAndSetter() throws IOException {

		// Test null
		writer = new CSVWriter(null, false, null, null);
		assertNotNull(writer);
		assertNull(writer.getCSVDelimiter());
		// assertEquals(writer.getCurrentDirectory()+"/" +
		// null,writer.getDirectory());
		assertNull(writer.getFileExtension());

		// Test CSVDelimiter
		writer.setCSVDelimiter(";");
		assertEquals(";", writer.getCSVDelimiter());

		// Test Directory
		writer.setDirectory("TMP", false);
		File file = new File("TMP");
		assertEquals(FileOperations.getProgramDirectory() + "\\" + "TMP",
				writer.getDirectory());

		/*
		 * Test if directory exists. -> No (Except user created a directory with
		 * that name and / or set createDir true).
		 */
		assertFalse(file.exists());

		/*
		 * setDirectory with argument true, so there will be a new directory.
		 * Exception: There is already a directory with that name... (See
		 * "File.mkdirs()" )
		 */
		writer.setDirectory("beta", true); // create directory
		file = new File("beta");
		assertEquals(FileOperations.getProgramDirectory() + "\\" + "beta",
				writer.getDirectory());
		assertTrue(file.exists());
		assertTrue(file.isDirectory());

		// Delete directory
		file.delete();

		// Test FileExtension
		writer.setFileExtension("csv");
		assertEquals("csv", writer.getFileExtension());
	}

	/**
	 * Test for constructor with creating a directory if nonexisting.
	 * 
	 * @throws IOException
	 *             No directory
	 */
	@Test
	public void testCSVWriterKonstruktor() throws IOException {
		writer = new CSVWriter("abc", "gif", "++");
		assertEquals(FileOperations.getProgramDirectory() + "\\" + "abc",
				writer.getDirectory());
		assertEquals("gif", writer.getFileExtension());
		assertEquals("++", writer.getCSVDelimiter());
		File file = new File("abc");
		assertTrue(file.exists());
		file.delete(); // Delete directory
	}

	/**
	 * Test for constructor with option to create a directory if nonexisting.
	 * 
	 * @throws IOException
	 *             Verzeichnis kann nicht angelegt werden
	 */
	@Test
	public void testCSVWriterStringBooleanStringString() throws IOException {
		writer = new CSVWriter("ccc", false, "la", "*");
		assertEquals(FileOperations.getProgramDirectory() + "\\" + "ccc",
				writer.getDirectory());
		assertEquals("la", writer.getFileExtension());
		assertEquals("*", writer.getCSVDelimiter());
		File file = new File("c");
		assertFalse(file.exists());
	}

	/**
	 * Test for writing bills as files in a directory.
	 * 
	 * @throws IOException Error occurred during execution...
	 */
	@Test
	public void writeCSVFiles() throws IOException {
		try {
			BillHandler billHandler = new BillHandler();
			CSVWriter csvWriter = new CSVWriter();
			csvWriter.createBillsAsCSV(billHandler.getBills());

			// Delete directory and its files.
			File fDir = new File(csvWriter.getDirectory());
			for (String s : fDir.list()) {
				new File(csvWriter.getDirectory() + File.separator + s)
						.delete();
			}
			fDir.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test
	 * 
	 * @throws IOException Error occurred during execution...
	 */
	@Test
	public void openZip() throws IOException {
		BillHandler billHandler = new BillHandler();
		CSVWriter csvWriter = new CSVWriter();
		try {
			csvWriter.createBillsAsCSV(billHandler.getBills());
		} catch (IOException e) {
			System.out.println(e);
			fail();
		}
	}
}
