package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pojo.Bill;

/**
 * Utility-class for providing bills from OpenZ.
 * 
 * @author Christian Gläser
 *
 */
public class BillUtil {

	/**
	 * Provides a single bill from OpenZ by its id as an object.
	 * 
	 * @param billID
	 *            The id of the bill
	 * @return The bill as an object.
	 */
	public static Bill getBill(String billID) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<Bill> query = em.createNamedQuery(Bill.FIND_BILL_BY_ID,
				Bill.class);
		System.out.println("Query" + query);
		query.setParameter(Bill.PARAM_ID, billID);
		Bill bill = query.getSingleResult();
		em.close();
		return bill;
	}

	/**
	 * Provides a list of all bills in OpenZ.
	 * 
	 * @return List with all bills.
	 */
	public static List<Bill> getAllBills() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<Bill> query = em.createNamedQuery(Bill.FIND_BILLS,
				Bill.class);
		System.out.println("Query" + query);
		List<Bill> listBill = query.getResultList();
		em.close();
		return listBill;
	}

}
