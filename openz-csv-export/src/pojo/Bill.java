package pojo;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author Christian Gläser
 * 
 * Buchungsbeleg
 * SQL Request benötigt restraint z.B. zeit oder andere einschränkung
 *
 */
@Entity
@Table(name="c_invoice")
@NamedQueries({
	//NamedQuery zur Auflistung aller Artikel
	@NamedQuery(name = Bill.FIND_BILL_BY_ID,
            query = "SELECT b " +
		            "FROM Bill b " +
            		"WHERE b.invoiceNumber = :" + Bill.PARAM_ID )})
public class Bill implements Serializable{
	
	/*** Belegnummer */
	String invoiceNumber;
	
	/** Datum */
	String date;
	
	/** Buchungstext*/
	String bookingText;
	
	/** Betrag */
	BigDecimal amount;
	
	//PARAMETERS
	public static final String PARAM_ID = "c_invoice_id";
	public static final String PARAM_AMOUNT = "grandtotal";
	public static final String PARAM_DATE = "created";
	public static final String PARAM_DESCRIPTION = "description";
	
	//QUERY NAMES
	public static final String FIND_BILL_BY_ID = "findBillByID";
	
	@Id
	@Column(name=PARAM_ID)
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	@Column(name=PARAM_DATE)
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name=PARAM_DESCRIPTION)
	public String getBookingText() {
		return bookingText;
	}
	
	public void setBookingText(String bookingText) {
		this.bookingText = bookingText;
	}
	
	@Column(name=PARAM_AMOUNT)
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}