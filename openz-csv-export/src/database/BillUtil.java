package database;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pojo.Bill;

public class BillUtil {

	public static Bill getBill(String billID) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		TypedQuery<Bill> query = em.createNamedQuery(Bill.FIND_BILL_BY_ID, Bill.class);
		query.setParameter(Bill.PARAM_ID, billID);
		System.out.println(query.toString());
		
		return query.getSingleResult();
	}

}
