package csv.tests;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import csv.BillHandler;
import csv.CSVWriter;

public class CSVTests {

	@Test
	public void writeCSVFiles(){
		CSVWriter csvWriter = new CSVWriter("", "csv", ";");
		try {
			csvWriter.voidWriteFiles(BillHandler.getBills());
		} catch (IOException e) {
			System.out.println(e);
			fail();
		}
	}
}
