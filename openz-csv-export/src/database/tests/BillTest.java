package database.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.BillUtil;
import pojo.Bill;


public class BillTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getSingleBill() {
		Bill bill = BillUtil.getBill("96D07D1226434DB99675B9FD80344A44");
		System.out.println(bill);
		assertEquals(bill.getInvoiceNumber(),"96D07D1226434DB99675B9FD80344A44");
		assertEquals(bill.getBookingText(),"Rechnungstext Beschreibung");
	}
	
	@Test
	public void getAllBills() {
		Bill bill = BillUtil.getBill("96D07D1226434DB99675B9FD80344A44");
		System.out.println(bill);
		assertEquals(bill.getInvoiceNumber(),"96D07D1226434DB99675B9FD80344A44");
		assertEquals(bill.getBookingText(),"Rechnungstext Beschreibung");
	}
}
