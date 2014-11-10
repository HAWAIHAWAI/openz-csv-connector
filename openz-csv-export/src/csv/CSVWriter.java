package csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import pojo.Bill;

public class CSVWriter {
  
  private static BufferedWriter writer;
  private static String directory;
  private static String fileType;
  private static String delimiter;
  
  public static void setDelimiter(String del){
    delimiter = del;
  }
  
  public static String getDelimiter(){
    return delimiter;
  }
  
  public static void setFileType(String type){
    fileType = type;
  }
  
  public static String getFileType(){
    return fileType;
  }
  
  public static void setDirectory(String path){
    directory = path;
  }
  
  public static String getDirectoy(){
    return directory;
  }
  
  public static void voidWriteFiles(List<Bill> listBills) throws IOException{
    Bill bill;
    for(int i = 0; i < listBills.size(); i++){
      bill = listBills.get(i);
      writer = new BufferedWriter(new FileWriter(new File(directory + bill.getInvoiceNumber() + File.separator + fileType)));
      writer.write(bill.getInvoiceNumber() + delimiter 
          + bill.getDate() + delimiter 
          + bill.getBookingText() + delimiter 
          // Sollkonto
          + delimiter
          // Sollkontoname
          + delimiter
          + bill.getAmount() + delimiter
          // Habenkonto
          +delimiter
          // Habenkontoname
          +delimiter
          + bill.getAmount()
          );
    }
  }
}
