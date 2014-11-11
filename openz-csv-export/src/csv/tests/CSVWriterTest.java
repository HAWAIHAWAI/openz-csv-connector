package csv.tests;

import java.io.IOException;
import java.math.BigDecimal;

import csv.CSVWriter;
import pojo.Bill;

public class CSVWriterTest {

  public static void main(String[] args) {
    Bill bill = new Bill();
    bill.setInvoiceNumber("HA2GXH");
    bill.setDate("13.10.2014");
    bill.setAmount(new BigDecimal("2500"));
    bill.setBookingText("Unterhosen");
    CSVWriter writer = new CSVWriter(null, "csv", ";");
    try {
      writer.createBillAsCSV(bill);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
