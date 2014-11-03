package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	public static EntityManager getEntityManager() {
		try{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("openz-csv-export");
		EntityManager em = emf.createEntityManager();
		System.out.println("em.getProperties(): " + em.getProperties());
		em.getTransaction().begin();
		System.out.println("Entity Manager Transaction started");
		return em;
		}
		catch(javax.persistence.PersistenceException ex){
			System.err.println("Error thrown in EntityManagerUtil, reason:" + ex);
			throw ex;
		}
	}

}