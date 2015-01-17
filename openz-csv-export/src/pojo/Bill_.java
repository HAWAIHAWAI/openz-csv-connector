package pojo;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * For more informations about Static Metamodel:
 * http://docs.jboss.org/hibernate/stable/entitymanager/reference/en/html/metamodel.html
 * @author HAWAI
 *
 */
@Generated(value="Dali", date="2015-01-11T21:49:27.601+0100")
@StaticMetamodel(Bill.class)
public class Bill_ {
	public static volatile SingularAttribute<Bill, String> invoiceNumber;
	public static volatile SingularAttribute<Bill, String> documentNumber;
	public static volatile SingularAttribute<Bill, String> date;
	public static volatile SingularAttribute<Bill, String> bookingText;
	public static volatile SingularAttribute<Bill, BigDecimal> amount;
}
