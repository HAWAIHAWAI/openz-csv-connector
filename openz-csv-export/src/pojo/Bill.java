package pojo;
import java.math.BigDecimal;


/**
 * @author Christian Gläser
 * 
 * Buchungsbeleg
 *
 */
public class Bill {
	
	/**
	 * Belegnummer
	 */
	String invoiceNumber;
	/**
	 * Datum
	 */
	String date;
	/**
	 * Buchungstext
	 */
	String bookingText;
	/**
	 * Sollkonto
	 */
	String creditAccount;
	/**
	 * Sollkontoname
	 */
	String creditAccountName;
	/**
	 * Sollbetrag
	 */
	String debitAccount;
	/**
	 * Habenkontoname
	 */
	String debitAccountName;
	/**
	 * Habenbetrag
	 */
	BigDecimal debitAccountAmount;

}
