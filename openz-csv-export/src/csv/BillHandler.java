package csv;

import java.util.ArrayList;
import java.util.List;

import database.BillUtil;
import pojo.Bill;

/**
 * @author Christian Gl�ser
 * 
 * Sucht Belege aus openz, erstellt Records (Belege) und speichert diese in einer csv-Datei ab, die �ber eine http-Schnittstelle ausgegeben wird.
 *
 */
public class BillHandler {
	/**
	 * Der Name der CSV-Datei, sollte in Config-Datei festgelegt werden und dann beim Starten von OpenZ in
	 */
	private String folder;
	
	/**
	 * RecordHandler - Dateir�ckgabe: records.csv 
	 */
	public BillHandler(){
		this("outputFiles");
	}
	
	
	public BillHandler(String folder){
		setFolder(folder);
		flushCSV();
	}

	private void flushCSV() {
		/*TODO: Write CSV-Files into Folder*/
		/*
		 * Wenn CSV noch nicht in Ordner gespeichert...generiere CSV ( if !file.exists()--->output)
		 *
		 * */
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
