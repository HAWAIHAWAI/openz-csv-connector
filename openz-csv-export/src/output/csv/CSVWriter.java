package output.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.zeroturnaround.zip.ZipUtil;

import pojo.Bill;

/**
 * @author HAWAI
 *
 */
public class CSVWriter {

	private BufferedWriter writer;
	private File currentDirectory;
	private File storageDirectory;
	private String zipFilePath;

	private String fileExtension;
	private String delimiter;

	public CSVWriter() {
		this("CSVfiles", "csv", ";");
	}

	/**
	 * @param directory
	 *            The subdirectory relative to the current application path
	 * @param fileExtension
	 *            The file extension of the files
	 * @param delimiter
	 *            The csv delimiter to be used
	 */
	public CSVWriter(String directory, String fileExtension, String delimiter){
	  setDirectory(new File(directory));
	  setFileExtension(fileExtension);
	  setDelimiter(delimiter);
	  try {
		createDirectory();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.err.println("Error while trying to create directory for csv files");
	}
	  String zipfilepath = currentDirectory + "/" + "bills.zip";
	  System.out.println("Zipfilepath: " + zipfilepath);
	  setZipFilePath(zipfilepath);
  }

	public void setDelimiter(String del) {
		delimiter = del;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setFileExtension(String extension) {
		fileExtension = extension;
	}

	public String getFileType() {
		return fileExtension;
	}

	public void setDirectory(File path) {
		storageDirectory = path;
	}

	public File getDirectory() {
		return storageDirectory;
	}

	public void createDirectory() throws IOException {
		String current = "";
		current = new java.io.File(".").getCanonicalPath();
		this.currentDirectory = new File(current);

		File directory = new File(current + "/" + this.getDirectory());
		System.out.println(directory);
		if (!directory.exists()) {
			directory.mkdir();
		}

	}

	public void voidWriteFiles(List<Bill> listOfBills) throws IOException {
		System.out.println("voidWriteFiles - list Of Bills" + listOfBills);
		Bill bill;
		for (int i = 0; i < listOfBills.size(); i++) {
			bill = listOfBills.get(i);
			String filename = storageDirectory + "/" + bill.getInvoiceNumber()
					+ "." + fileExtension;
			System.out.println("Added file " + filename);
			writer = new BufferedWriter(new FileWriter(new File(filename)));
			writer.write(bill.getInvoiceNumber() + delimiter + bill.getDate()
					+ delimiter + bill.getBookingText() + delimiter
					// Sollkonto
					+ delimiter
					// Sollkontoname
					+ delimiter + bill.getAmount() + delimiter
					// Habenkonto
					+ delimiter
					// Habenkontoname
					+ delimiter + bill.getAmount());
			// Added flush
			writer.flush();
		}
		zipDirectory();
	}

	/**
 * 
 */
	private void zipDirectory() {
		ZipUtil.pack(storageDirectory, new File("bills.zip"));
	}

	public String getZipFilePath() {
		return zipFilePath;
	}
	
	private void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}

	public File getZip() {
		return new File(zipFilePath);

	}
}
