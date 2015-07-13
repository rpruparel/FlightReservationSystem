package Reservation.model;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-27T17:55:00")
@StaticMetamodel(Order.class)
public class Order_ { 

    public static volatile SingularAttribute<Order, Boolean> paid;
    public static volatile SingularAttribute<Order, Integer> orderID;
    public static volatile SingularAttribute<Order, Float> totalCost;
    public static volatile SingularAttribute<Order, Float> taxRate;
    public static volatile SingularAttribute<Order, BigInteger> creditCardNumber;
    public static volatile SingularAttribute<Order, Date> orderCreated;
    public static volatile SingularAttribute<Order, Integer> customerID;

}