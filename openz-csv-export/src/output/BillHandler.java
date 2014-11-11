package output;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import output.csv.CSVWriter;
import database.BillUtil;
import pojo.Bill;

/**
 * @author Christian Gläser
 * 
 * Sucht Belege aus openz, erstellt Records (Belege) und speichert diese in einer csv-Datei ab, die über eine http-Schnittstelle ausgegeben wird.
 *
 */
public class BillHandler {
	/**
	 * Der Name der CSV-Datei, sollte in Config-Datei festgelegt werden und dann beim Starten von OpenZ in
	 */
	private String folder;
	
	/**
	 * RecordHandler - Dateirückgabe: records.csv 
	 */
	public BillHandler(){
		this("outputFiles");
	}
	
	
	public BillHandler(String folder){
		setFolder(folder);
	}

	public File flushCSV() throws IOException {
		CSVWriter csvWriter = new CSVWriter(getFolder(),"csv",";");
		csvWriter.createBillsAsCSV(getBills());
		return csvWriter.getZip();
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public static List<Bill> getBills() {
		return BillUtil.getAllBills();
	}
	
}
