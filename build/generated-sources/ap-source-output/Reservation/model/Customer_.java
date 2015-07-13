package Reservation.model;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-27T17:55:00")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, BigInteger> contactNumber;
    public static volatile SingularAttribute<Customer, Integer> userAccountID;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile SingularAttribute<Customer, String> street;
    public static volatile SingularAttribute<Customer, Integer> zipCode;
    public static volatile SingularAttribute<Customer, String> state;
    public static volatile SingularAttribute<Customer, Integer> customerID;
    public static volatile SingularAttribute<Customer, String> city;

}