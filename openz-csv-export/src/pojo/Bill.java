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
 * 
 * Class for OpenZ-invoices. Maps a single invoice to a Bill-object.
 * 
 * @author Christian Gläser
 * 
 *         Invoice SQL Request requires restraint like time or sth. else...
 *
 */
@Entity
@Table(name = "c_invoice")
@NamedQueries({
// NamedQuery for a single bill
		@NamedQuery(name = Bill.FIND_BILL_BY_ID, query = "SELECT b "
				+ "FROM Bill b " + "WHERE b.invoiceNumber = :" + Bill.PARAM_ID),

		// NamedQuery for a list of bills
		@NamedQuery(name = Bill.FIND_BILLS, query = "SELECT b " + "FROM Bill b") })
public class Bill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3112757207106932963L;

	String invoiceNumber;

	String documentNumber;

	String date;

	String bookingText;

	BigDecimal amount;

	// PARAMETERS
	public static final String PARAM_ID = "c_invoice_id";
	public static final String PARAM_DOCNO = "documentno";
	public static final String PARAM_AMOUNT = "grandtotal";
	public static final String PARAM_DATE = "created";
	public static final String PARAM_DESCRIPTION = "description";

	// QUERY NAMES
	public static final String FIND_BILL_BY_ID = "findBillByID";
	public static final String FIND_BILLS = "findBills";

	/**
	 * Returns the number of the invoice.
	 * 
	 * @return Number of invoice.
	 */
	@Id
	@Column(name = PARAM_ID)
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * Sets the number of the invoice.
	 * 
	 * @param invoiceNumber
	 *            The new number.
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * Returns the number of the document.
	 * 
	 * @return Number of document.
	 */
	@Column(name = PARAM_DOCNO)
	public String getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * Sets the number of the document.
	 * 
	 * @param documentNumber
	 *            The documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	/**
	 * Returns the date of the invoice.
	 * 
	 * @return Date of invoice.
	 */
	@Column(name = PARAM_DATE)
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the invoice.
	 * 
	 * @param date
	 *            The new date.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the booking text of the invoice.
	 * 
	 * @return Booking text of invoice.
	 */
	@Column(name = PARAM_DESCRIPTION)
	public String getBookingText() {
		return bookingText;
	}

	/**
	 * Sets the booking text of the invoice.
	 * 
	 * @param bookingText
	 *            The new booking text.
	 */
	public void setBookingText(String bookingText) {
		this.bookingText = bookingText;
	}

	/**
	 * Returns the amount of the invoice.
	 * 
	 * @return Amount of invoice.
	 */
	@Column(name = PARAM_AMOUNT)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the invoice.
	 * 
	 * @param amount
	 *            The new amount.
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	@Override
	public String toString() {
		return "Bill [invoiceNumber=" + invoiceNumber + ", documentNumber= "
				+ documentNumber + ", date=" + date + ", bookingText="
				+ bookingText + ", amount=" + amount + "]";
	}
}