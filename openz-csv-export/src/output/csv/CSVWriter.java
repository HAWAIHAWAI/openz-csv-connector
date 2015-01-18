package output.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.zeroturnaround.zip.ZipUtil;

import output.xml.BillList;
import common.FileOperations;
import pojo.Bill;

/**
 * A CSVWriter-object writes text in a (csv-)file. It is possible to set the
 * directory, the delimiter and the file extension via methods.
 * 
 * @author Florian Arfert
 * @author Christian Gläser
 *
 */
public class CSVWriter {

	/**
	 * Required to write text in a file.
	 */
	private BufferedWriter writer;

	/**
	 * Directories for saving (csv-)files.
	 */
	private File storageDirectory;
	private String zipFilePath;

	/**
	 * File extension.
	 */
	private String fileExtension;

	/**
	 * Delimiter for the data in the output file.
	 */
	private String csvDelimiter;

	/**
	 * Constructor with default arguments.
	 * <p>
	 * Default directory: CSVfiles <br>
	 * Default file extension: csv <br>
	 * Default delimiter: ;
	 * 
	 * @throws IOException
	 *             Thrown if it is not possible to create the directory.
	 * @see #CSVWriter(String directory, String fileExtension, String
	 *      csvDelimiter)
	 * @see #CSVWriter(String directory, boolean createDir, String
	 *      fileExtension, String csvDelimiter)
	 */
	public CSVWriter() throws IOException {
		this("CSVfiles", "csv", ";");
	}

	/**
	 * Constructor, which creates the directory, if nonexistent.
	 * 
	 * @param directory
	 *            Directory, where files are created.
	 * @param fileExtension
	 *            Extension of files.
	 * @param csvDelimiter
	 *            Delimiter between the data in a file.
	 * @throws IOException
	 *             Thrown if it is not possible to create the directory.
	 * @see #CSVWriter(String directory, boolean createDir, String
	 *      fileExtension, String csvDelimiter)
	 */
	public CSVWriter(String directory, String fileExtension, String csvDelimiter)
			throws IOException {
		this(directory, true, fileExtension, csvDelimiter);
	}

	/**
	 * Constructor with option to create a directory, if nonexistent.
	 * <p>
	 * <b>Important</b> <br>
	 * If a directory is nonexistent all methods to create files will throw an
	 * IO-Exception.
	 * 
	 * @param directory
	 *            Directory, where files are created.
	 * @param createDir
	 *            true, if directory shall be created. Else false.
	 * @param fileExtension
	 *            Extension of files.
	 * @param csvDelimiter
	 *            Delimiter between the data in a file.
	 * @throws IOException
	 *             Thrown if it is not possible to create the directory.
	 */
	public CSVWriter(String directory, boolean createDir, String fileExtension,
			String csvDelimiter) throws IOException {
		setDirectory(directory, createDir);
		setFileExtension(fileExtension);
		setCSVDelimiter(csvDelimiter);
		String zipfilepath = FileOperations.getProgramDirectory()
				+ File.separator + "bills.zip";
		// System.out.println("Zipfilepath: " + zipfilepath);
		setZipFilePath(zipfilepath);
	}

	/**
	 * Sets the delimiter to seperate the data in a file.
	 * 
	 * @param del
	 *            Delimiter between
	 */
	public void setCSVDelimiter(String del) {
		csvDelimiter = del;
	}

	/**
	 * Returns the current delimiter.
	 * 
	 * @return Current delimiter.
	 */
	public String getCSVDelimiter() {
		return csvDelimiter;
	}

	/**
	 * Sets the extension of files.
	 * 
	 * @param extension
	 *            Extension of files.
	 */
	public void setFileExtension(String extension) {
		fileExtension = extension;
	}

	/**
	 * Returns the current file extension.
	 * 
	 * @return Current file extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Sets the directry. It is possible to create the directory, if
	 * nonexistent, via setting the argument for "createIfNonexistent" to true.
	 * 
	 * @param path
	 *            Path for the directory.
	 * @param createIfNonexistent
	 *            True, if the directory shall be create if nonexistent. Else
	 *            false.
	 * @throws IOException
	 *             Thrown, if the path can't be resolved.
	 */
	public void setDirectory(String path, boolean createIfNonexistent)
			throws IOException {
		storageDirectory = new File(FileOperations.getProgramDirectory()
				+ File.separator + path);
		if (createIfNonexistent) {
			storageDirectory.mkdirs();
		}
	}

	/**
	 * Returns the current directory.
	 * @return String with canonical path of the directory. 
	 * @throws IOException
	 *             Thrown, if directory is not set or not correct.
	 */
	public String getDirectory() throws IOException {
		return storageDirectory.getCanonicalPath();
	}

	/**
	 * Writes the bills in a list as files in the specified directory. For more
	 * information {@link #createBillAsCSV(Bill)}.
	 * 
	 * @param listOfBills
	 *            List of bills.
	 * @throws IOException Error occurred during execution...
	 * 
	 * @see #createBillAsCSV(Bill)
	 */
	public void createBillsAsCSV(List<Bill> listOfBills) throws IOException {
		System.out.println("voidWriteFiles - list Of Bills" + listOfBills);
		for (int i = 0; i < listOfBills.size(); i++) {
			createBillAsCSV(listOfBills.get(i));
		}
	}

	/**
	 * Writes a single bill in the specified directory. Every bill is stored as
	 * a file consisting of a name, which is the same as the invoice number. The
	 * file contains the invoice number, the billing date, the relevant posting
	 * text and the billing amount. <br>
	 * If the file already exists, the file won't be created again.
	 * @param bill The bill, which shall be written as csv.
	 * @throws IOException Error occurred during execution...
	 */
	public void createBillAsCSV(Bill bill) throws IOException {
		File csvFile = new File(storageDirectory + File.separator
				+ bill.getDocumentNumber() + "." + fileExtension);
		if (!csvFile.exists()) {
			writer = new BufferedWriter(new FileWriter(csvFile));
			writer.write(bill.getDocumentNumber() + csvDelimiter
					+ bill.getDate() + csvDelimiter + bill.getBookingText()
					+ csvDelimiter
					// Sollkonto
					+ csvDelimiter
					// Sollkontoname
					+ csvDelimiter + bill.getAmount() + csvDelimiter
					// Habenkonto
					+ csvDelimiter
					// Habenkontoname
					+ csvDelimiter + bill.getAmount());
			writer.flush();
			writer.close();
		}

	}

	/**
	 * Zips directory with an xml file of all files included.
	 */
	private void zipDirectory() {
		// Add xml with file list to directory
		BillList.convertBillsToXMLFile(storageDirectory);
		ZipUtil.pack(storageDirectory, new File("bills.zip"));
	}

	/**
	 * Return the file path of the zip.
	 * 
	 * @return File path of the zip.
	 */
	public String getZipFilePath() {
		return zipFilePath;
	}

	/**
	 * Sets the file path of the zip.
	 * 
	 * @param zipFilePath
	 *            File path of the zip.
	 */
	private void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}

	/**
	 * Returns the zip file as file object.
	 * 
	 * @return Zip file as file object.
	 */
	public File getZip() {
		zipDirectory();
		return new File(zipFilePath);
	}
}
