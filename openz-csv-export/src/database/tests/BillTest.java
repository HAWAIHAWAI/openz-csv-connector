package database.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import pojo.Bill;
import database.BillUtil;

/**
 * Simple testclass for BillUtil.java
 * 
 * @author Christian Gläser
 *
 */
public class BillTest {

	/**
	 * Test: Get a single bill.
	 */
	@Test
	public void getSingleBill() {
		Bill bill = BillUtil.getBill("96D07D1226434DB99675B9FD80344A44");
		System.out.println(bill);
		assertEquals(bill.getInvoiceNumber(),
				"96D07D1226434DB99675B9FD80344A44");
		assertEquals(bill.getBookingText(), "Rechnungstext Beschreibung");
	}

	/**
	 * Test: Get all bills as list.
	 */
	@Test
	public void getAllBills() {
		List<Bill> bill = BillUtil.getAllBills();
		System.out.println(bill);

	}
}
