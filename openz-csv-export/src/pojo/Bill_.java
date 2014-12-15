package pojo;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-01T21:46:48.963+0100")
@StaticMetamodel(Bill.class)
public class Bill_ {
	public static volatile SingularAttribute<Bill, String> invoiceNumber;
	public static volatile SingularAttribute<Bill, String> documentNumber;
	public static volatile SingularAttribute<Bill, String> date;
	public static volatile SingularAttribute<Bill, String> bookingText;
	public static volatile SingularAttribute<Bill, BigDecimal> amount;
}
