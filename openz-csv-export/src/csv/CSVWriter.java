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

  /**
   * Fuer das Schreiben von Text in eine Datei.
   */
  private BufferedWriter writer;
  
  /**
   * Das Verzeichnis fuer die Dateien.
   */
  private String directory;
  
  /**
   * Dateiendung
   */
  private String fileExtension;
  
  /**
   * Trennzeichen zwischen den einzelnen Inhalten in der CSV-Datei.
   */
  private String csvDelimiter;

  /**
   * Konstruktor, der das Verzeichnis erstellt, falls es nicht vorhanden ist.
   * @param directory Verzeichnis, in dem die Dateien erstellt werden.
   * @param fileExtension Endung der Datei.
   * @param csvDelimiter Trennzeichen zwischen den einzelnen Inhalten in der CSV-Datei.
   */
  public CSVWriter(String directory, String fileExtension, String csvDelimiter) {
    this(directory, true, fileExtension, csvDelimiter);
  }

  /**
   * Konstruktor mit Wahlmoeglichkeit, ob das Zielverzeichnis angelegt wird, falls
   * es nicht vorhanden ist. Hier ist zu beachten, dass wenn das Verzeichnis nicht
   * existent ist, die Methoden fuer das Erstellen der Dateien eine IO-Exceptionw werfen!
   * @param directory Verzeichnis, in dem die Dateien erstellt werden.
   * @param createDir Angabe, ob ein Verzeichnis erstellt werden soll, falls nicht existent.
   * @param fileExtension Endung der Datei.
   * @param csvDelimiter Trennzeichen zwischen den einzelnen Inhalten in der CSV-Datei.
   */
  public CSVWriter(String directory, boolean createDir, String fileExtension, String csvDelimiter) {
    setDirectory(directory, createDir);
    setFileExtension(fileExtension);
    setCSVDelimiter(csvDelimiter);
  }

  /**
   * Setzt das Trennzeichen zwischen den Inhalten der Datei.
   * @param del Trennzeichen zwischen Inhalten.
   */
  public void setCSVDelimiter(String del) {
    csvDelimiter = del;
  }

  /**
   * Liefert das aktuell verwendete Trennzeichen.
   * @return Aktuelles Trennzeichen.
   */
  public String getCSVDelimiter() {
    return csvDelimiter;
  }

  /**
   * Setzt die Endung der zu erstellenden Dateien.
   * @param extension Endung der zu erstellenden Dateien.
   */
  public void setFileExtension(String extension) {
    fileExtension = extension;
  }

  
  /**
   * Liefert den aktuellen Dateityp.
   * @return Aktueller Dateityp.
   */
  public String getFileExtension() {
    return fileExtension;
  }

  /**
   * Setzt das zu verwendende Verzeichnis auf den uebergebenen Pfad.
   * Es besteht die Moeglichkeit mittels "createIfNonexistent" ein Verzeichnis
   * anzulegen, falls es nicht vorhanden ist und der Anwender es will.
   * @param path Pfad des zu verwendenden Verzeichnisses.
   * @param createIfNonexistent True, wenn das Verzeichnis erzeugt werden soll, falls
   * es noch nicht vorhanden ist. Andernfalls false.
   */
  public void setDirectory(String path, boolean createIfNonexistent) {
    if (createIfNonexistent) {
      File dir = new File(path);
      dir.mkdirs();
    }
    directory = path;
  }

  /**
   * Liefert das aktuell verwendete Verzeichnis.
   * @return Pfad des aktuell verwendeten Verzeichnisses
   */
  public String getDirectory() {
    return directory;
  }

  /**
   * Schreibt eine Liste von Bills ("Rechnungen") in das vorher angegebene Verzeichnis.
   * <br>
   * Fuer weitere Informationen {@link #createBillAsCSV(Bill)}.
   * @param listOfBills Liste mit Bills("Rechnungen").
   * @throws IOException Fehler beim Dateisystem (Y_Y)
   * @see {@link #createBillAsCSV(Bill)}
   */
  public void createBillsAsCSV(List<Bill> listOfBills) throws IOException {
    System.out.println("voidWriteFiles - list Of Bills" + listOfBills);
    for (int i = 0; i < listOfBills.size(); i++) {
      createBillAsCSV(listOfBills.get(i));
    }
  }

  /**
   * Schreibt eine einzelne Rechnung in das vorher angegebene Verzeichnis.
   * Jede Bill ("Rechnung") erhaelt eine Datei, wobei der Dateiname der
   * Rechnungsnummer entspricht. Die Datei enthaelt hierbei die jeweilige Rechnungsnummer,
   * das Rechnungsdatum, den dazugehoerige Buchungstext sowie den Rechnungsbetrag.
   * <br>
   * Ist die Rechnung schon vorhanden, so wird sie <i> nicht </i> nochmal erstellt.
   * @param bill Die Bill ("Rechnung"), die als CSV-Datei erstellt werden soll.
   * @throws IOException Fehler beim Dateisystem (Y_Y)
   */
  public void createBillAsCSV(Bill bill) throws IOException {
    File csvFile = new File(directory + File.separator + bill.getInvoiceNumber() + "." + fileExtension);
    if (!csvFile.exists()) {
      writer = new BufferedWriter(new FileWriter(csvFile));
      writer.write(bill.getInvoiceNumber() + csvDelimiter + bill.getDate() + csvDelimiter + bill.getBookingText() + csvDelimiter
      // Sollkonto
      + csvDelimiter
      // Sollkontoname
      + csvDelimiter + bill.getAmount() + csvDelimiter
      // Habenkonto
      + csvDelimiter
      // Habenkontoname
      + csvDelimiter + bill.getAmount());
      writer.flush();
    }

  }
}