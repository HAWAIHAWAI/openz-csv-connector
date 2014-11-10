package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pojo.Bill;

public class BillUtil {

	public static Bill getBill(String billID) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<Bill> query = em.createNamedQuery(Bill.FIND_BILL_BY_ID, Bill.class);
		System.out.println("Query" + query);
		query.setParameter(Bill.PARAM_ID, billID);
		
		return query.getSingleResult();
	}
	
	public static List<Bill> getAllBills() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		TypedQuery<Bill> query = em.createNamedQuery(Bill.FIND_BILLS, Bill.class);
		System.out.println("Query" + query);
		return query.getResultList();
	}

}
