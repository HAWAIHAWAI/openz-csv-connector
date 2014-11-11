package csv.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pojo.Bill;
import csv.CSVWriter;

public class TestCSVWriter {
  /**
   * CSVWriter, der bei den Testfaellen verwendet wird.
   */
  private CSVWriter writer;

  /**
   * Setup vor jedem Text.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    writer = null;
  }

  /**
   * Testet die Getter und Setter des CSV-Writers. Die Setter werden im Konstruktor verwendet,
   * deswegen werden diese zuerst getestet. Dies geschieht, in dem ein CSVWriter-Objekt mit
   * null-Werten als Argumente initialisiert wird und es wird KEIN Verzeichnis erzeugt (null ist
   * keine gueltige Verzeichnisangabe). Nach der Objekt-Erzeugung wird der Konstruktor
   * auf null-Attribute geprueft und dann erfolgen die Pruefungen der Setter und Getter.
   * Es wird zuerst ein Wert mittels Setter gesetzt und dieser wird dann mittels des
   * Getters abgerufen und mit dem erwarteten Wert verglichen.
   */
  @Test
  public void testGetterAndSetter() {

    // Prüft, ob ein CSVWriter-Objekt erstellt wurde mit null-Belegung
    writer = new CSVWriter(null,false, null, null);
    assertNotNull(writer);
    assertNull(writer.getCSVDelimiter());
    assertNull(writer.getDirectory());
    assertNull(writer.getFileExtension());

    // CSVDelimiter
    writer.setCSVDelimiter(";");
    assertEquals(";", writer.getCSVDelimiter());

    // Directory
    writer.setDirectory("TMP", false);
    File file = new File("TMP");
    assertEquals("TMP", writer.getDirectory());

    /*
     * Testet, ob das Verzeichnis als Dateisystem vorhanden ist. -> Nein, da es nicht vorhanden ist
     * (Annahme, dass der Benutzer nicht schon vor dem Test ein Verzeichnis mit diesem Namen
     * angelegt hat.) und der Benutzer die Erstellung verneint hat(false).
     */
    assertFalse(file.exists());

    /*
     * Nun wird setDirectory so eingestellt, dass ein Verzeichnis erstellt werden soll. Zu beachten
     * ist, dass das Verzeichnis nur erstellt wird, falls es nicht vorhanden ist. (Siehe
     * File.mkdirs()-Spezifikation).
     */
    writer.setDirectory("beta", true); // Verzeichnis wird im Projektordner erstellt
    file = new File("beta");
    assertEquals("beta", writer.getDirectory());
    assertTrue(file.exists());
    assertTrue(file.isDirectory());

    // Verzeichnis wieder aus dem Dateisystem loeschen
    file.delete();

    // FileExtension
    writer.setFileExtension("csv");
    assertEquals("csv", writer.getFileExtension());
  }

  /**
   * Testet den Konstruktor, welcher automatisch
   * das Verzeichnis miterstellt, falls dieses nicht
   * vorhanden ist.
   * Der Konstruktoraufruf ist an sich eine Weiterleitung mit true
   * an den Konstruktor, bei dem man die moegliche Erstellung
   * als Argument setzen kann (der naechste Test).
   */
  @Test
  public void testCSVWriterKonstruktor() {
    writer = new CSVWriter("abc", "gif", "++");
    assertEquals("abc", writer.getDirectory());
    assertEquals("gif", writer.getFileExtension());
    assertEquals("++", writer.getCSVDelimiter());
    File file = new File("abc");
    assertTrue(file.exists());
    file.delete();      // Testverzeichnis wieder entfernen.
  }

  /**
   * Testet den Konstruktor mit der Moeglichkeit, dass Verzeichnis
   * zu erstellen bei Nichtvorhandensein. Hier wird false
   * als Argument uebergeben, da kein Verzeichnis erstellt werden soll.
   */
  @Test
  public void testCSVWriterStringBooleanStringString() {
    writer = new CSVWriter("ccc",false, "la", "*");
    assertEquals("ccc", writer.getDirectory());
    assertEquals("la", writer.getFileExtension());
    assertEquals("*", writer.getCSVDelimiter());
    File file = new File("c");
    assertFalse(file.exists());
  }
  

  /**
   * Testet, ob eine einzelne Rechnung korrekt erstellt wird
   */
  @Test
  public void testCreateBillAsCSV() {
    String dir = "dir";
    String ext = "csv";
    String del = ";";
    String targetString = "INV001;13.10.2014;Armband;;;25;;;25";
    writer = new CSVWriter(dir, ext, del);
    Bill bill = new Bill();
    bill.setInvoiceNumber("INV001");
    bill.setDate("13.10.2014");
    bill.setBookingText("Armband");
    bill.setAmount(new BigDecimal("25"));
    try {
      writer.createBillAsCSV(bill);
      File f = new File(dir + File.separator + bill.getInvoiceNumber() + "." + ext);
      assertTrue(f.exists());
      
      /*
       * Prueft den Inhalt der erstellten CSV-Datei
       */
      BufferedReader reader = new BufferedReader(new FileReader(f));
      int numberOfLines = 0;
      String currentLine = "";
      String lineBeforeNull = "";
      while((currentLine = reader.readLine()) != null){
        lineBeforeNull = currentLine;
        numberOfLines++;
      }
      assertEquals(1, numberOfLines);   // Es soll nur eine Zeile vorhanden sein -> Die Zeile mit Rechnungsdaten
      assertEquals(targetString, lineBeforeNull); // Dateiinhalt vergleichen mit dem Soll-String
      reader.close();
      
      // Entferne Verzeichnis samt Inhalt
      File fDir = new File(dir);
      for(String s : fDir.list()){
        new File(dir + File.separator + s).delete();
      }
      fDir.delete();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Testet eine Liste mit Rechnungen.
   */
  @Test
  public void testCreateBillsAsCSV() {
    String dir = "DIR";
    String ext = "csv";
    String del = ";";
    String targetStringONE = "RE002;12.10.2014;Armband;;;25;;;25";
    String targetStringTWO = "AZ4;9.1.2013;Hose;;;15;;;15";
    writer = new CSVWriter(dir, ext, del);
   
    Bill billONE = new Bill();
    billONE.setInvoiceNumber("RE002");
    billONE.setDate("12.10.2014");
    billONE.setBookingText("Armband");
    billONE.setAmount(new BigDecimal("25"));
    
    Bill billTWO = new Bill();
    billTWO.setInvoiceNumber("AZ4");
    billTWO.setDate("9.1.2013");
    billTWO.setBookingText("Hose");
    billTWO.setAmount(new BigDecimal("15"));
    
    List<Bill> listOfBills = new ArrayList<>();
    listOfBills.add(billONE);
    listOfBills.add(billTWO);
    
    try {
      writer.createBillsAsCSV(listOfBills);
      
      // Testet, ob die ERSTE Rechnung("billONE") erstellt wurde
      File f = new File(dir + File.separator + billONE.getInvoiceNumber() + "." + ext);
      assertTrue(f.exists());
      
      /*
       * Prueft den Inhalt der 1. erstellten CSV-Datei
       */
      BufferedReader reader = new BufferedReader(new FileReader(f));
      int numberOfLines = 0;
      String currentLine = "";
      String lineBeforeNull = "";
      while((currentLine = reader.readLine()) != null){
        lineBeforeNull = currentLine;
        numberOfLines++;
      }
      assertEquals(1, numberOfLines);   // Es soll nur eine Zeile vorhanden sein -> Die Zeile mit Rechnungsdaten
      assertEquals(targetStringONE, lineBeforeNull); // Dateiinhalt vergleichen mit dem Soll-String
      
      reader.close();
      
      // Testet, ob die ZWEITE Rechnung("billTWO") erstellt wurde
      f = new File(dir + File.separator + billTWO.getInvoiceNumber() + "." + ext);
      assertTrue(f.exists());
      
      /*
       * Prueft den Inhalt der 2. erstellten CSV-Datei
       */
      reader = new BufferedReader(new FileReader(f));
      numberOfLines = 0;
      currentLine = "";
      lineBeforeNull = "";
      while((currentLine = reader.readLine()) != null){
        lineBeforeNull = currentLine;
        numberOfLines++;
      }
      assertEquals(1, numberOfLines);   // Es soll nur eine Zeile vorhanden sein -> Die Zeile mit Rechnungsdaten
      assertEquals(targetStringTWO, lineBeforeNull); // Dateiinhalt vergleichen mit dem Soll-String
      
      reader.close();
      
      // Entferne Verzeichnis samt Inhalt
      File fDir = new File(dir);
      for(String s : fDir.list()){
        new File(dir + File.separator + s).delete();
      }
      fDir.delete();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
  }


}
