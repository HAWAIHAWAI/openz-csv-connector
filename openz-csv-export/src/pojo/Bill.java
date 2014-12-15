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
            		"WHERE b.invoiceNumber = :" + Bill.PARAM_ID ),
	@NamedQuery(name = Bill.FIND_BILLS,
    query = "SELECT b " +
            "FROM Bill b" )
})
public class Bill implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3112757207106932963L;

	/*** Belegnummer */
	String invoiceNumber;
	
	String documentNumber;
	
	/** Datum */
	String date;
	
	/** Buchungstext*/
	String bookingText;
	
	/** Betrag */
	BigDecimal amount;
	
	//PARAMETERS
	public static final String PARAM_ID = "c_invoice_id";
	public static final String PARAM_DOCNO = "documentno";
	public static final String PARAM_AMOUNT = "grandtotal";
	public static final String PARAM_DATE = "created";
	public static final String PARAM_DESCRIPTION = "description";
	
	//QUERY NAMES
	public static final String FIND_BILL_BY_ID = "findBillByID";
	public static final String FIND_BILLS = "findBills";
	
	@Id
	@Column(name=PARAM_ID)
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	/**
	 * @return the documentNumber
	 */
	@Column(name=PARAM_DOCNO)
	public String getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * @param documentNumber the documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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

	@Override
	public String toString() {
		return "Bill [invoiceNumber=" + invoiceNumber + ", documentNumber= " + documentNumber + ", date=" + date
				+ ", bookingText=" + bookingText + ", amount=" + amount + "]";
	}
}