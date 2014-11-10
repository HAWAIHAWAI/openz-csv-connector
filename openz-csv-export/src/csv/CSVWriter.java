package csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import pojo.Bill;

/**
 * @author HAWAI
 *
 */
public class CSVWriter {
  

  private BufferedWriter writer;
  private String directory;
  private String fileExtension;
  private String delimiter;
  
  public CSVWriter(String directory, String fileExtension, String delimiter){
	  setDirectory(directory);
	  setFileExtension(fileExtension);
	  setDelimiter(delimiter);
  }
  
  public void setDelimiter(String del){
    delimiter = del;
  }
  
  public String getDelimiter(){
    return delimiter;
  }
  
  public void setFileExtension(String extension){
    fileExtension = extension;
  }
  
  public String getFileType(){
    return fileExtension;
  }
  
  public void setDirectory(String path){
    directory = path;
  }
  
  public String getDirectory(){
    return directory;
  }
  
  public void voidWriteFiles(List<Bill> listOfBills) throws IOException{
	System.out.println("voidWriteFiles - list Of Bills" + listOfBills);
    Bill bill;
    for(int i = 0; i < listOfBills.size(); i++){
      bill = listOfBills.get(i);
      writer = new BufferedWriter(new FileWriter(new File(directory + "\"" + bill.getInvoiceNumber() + "." + fileExtension)));
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
