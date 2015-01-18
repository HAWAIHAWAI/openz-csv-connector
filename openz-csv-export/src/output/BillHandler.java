package output;

import java.io.File;
import java.io.IOException;
import java.util.List;

import output.csv.CSVWriter;
import pojo.Bill;
import database.BillUtil;

/**
 * 
 * The objects of BillHandler provide methods to look up bills in OpenZ, to
 * create records and to save the records in csv- or zip-files. The files are
 * exported via http-interface.
 * 
 * @author Christian Gläser
 *
 */
public class BillHandler {
	/**
	 * Directory of the files. Should be set via config-file.
	 */
	private String directory;

	/**
	 * Constructor for BillHandler. Default value for folder is "outputFiles".
	 * <p>
	 * To specify a different directory use the method
	 * {@link #setFolder(String)} or use the constructor
	 * {@link #BillHandler(String)}.
	 * 
	 * @see #BillHandler(String)
	 */
	public BillHandler() {
		this("outputFiles");
	}

	/**
	 * Constructor for Billhandler with an option to set the output-directory.
	 * 
	 * @param folder
	 *            Directory for output.
	 */
	public BillHandler(String folder) {
		setFolder(folder);
	}

	/**
	 * Returns a zip-file with the bills.
	 * 
	 * @return Zip-file with bills.
	 * @throws IOException Error occurred during execution...
	 */
	public File flushCSV() throws IOException {
		common.FileOperations.deleteDirectory(new File(getDirectory()));
		CSVWriter csvWriter = new CSVWriter(getDirectory(), "csv", ";");
		csvWriter.createBillsAsCSV(getBills());
		return csvWriter.getZip();
	}

	/**
	 * Returns the current directory, where the files are stored.
	 * 
	 * @return Current directory.
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * Sets the directory, where the files are stored.
	 * 
	 * @param directory
	 *            New directory.
	 */
	public void setFolder(String directory) {
		this.directory = directory;
	}

	/**
	 * Returns a list with bills from OpenZ.
	 * 
	 * @return List with bills.
	 */
	public List<Bill> getBills() {
		return BillUtil.getAllBills();
	}

}
