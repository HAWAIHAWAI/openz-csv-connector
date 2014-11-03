package csv;

import java.util.ArrayList;
import java.util.List;

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
	private String fileName;
	private List<Bill> records;
	
	/**
	 * RecordHandler - Dateir�ckgabe: records.csv 
	 */
	public BillHandler(){
		this("records.csv");
	}
	
	/**
	 * @param filename Der Name der zur�ckzugebenden Datei
	 */
	public BillHandler(String filename){
		records = new ArrayList<Bill>();
		//Read records;
		setFileName(filename);
		
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		fileName = this.fileName;
	} 
}
