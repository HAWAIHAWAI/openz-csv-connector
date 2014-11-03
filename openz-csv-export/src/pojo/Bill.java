package pojo;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Christian Gläser
 * 
 * Buchungsbeleg
 *
 */
@Entity
@Table(name="c_invoice")
public class Bill {
	
	/**
	 * Belegnummer
	 */
	@Id
	@Column(name="c_invoice_id")
	String invoiceNumber;
	
	/**
	 * Datum
	 */
	@Column(name="created")
	String date;
	
	/**
	 * Buchungstext
	 */
	@Column(name="description")
	String bookingText;


	/**
	 * Betrag
	 */
	@Column(name="grandtotal")
	BigDecimal amount;

}
