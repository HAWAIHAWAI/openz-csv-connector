package output.csv.tests;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import output.BillHandler;
import output.csv.CSVWriter;

public class CSVTests {

	@Test
	public void writeCSVFiles(){
		CSVWriter csvWriter = new CSVWriter();
		try {
			csvWriter.voidWriteFiles(BillHandler.getBills());
		} catch (IOException e) {
			System.out.println(e);
			fail();
		}
	}
	
	@Test
	public void openZip(){
		CSVWriter csvWriter = new CSVWriter();
		try {
			csvWriter.voidWriteFiles(BillHandler.getBills());
			java.awt.Desktop.getDesktop().open(csvWriter.getZip());
		} catch (IOException e) {
			System.out.println(e);
			fail();
		}
	}
	

}
