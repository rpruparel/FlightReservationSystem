package Reservation.model;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-27T17:55:00")
@StaticMetamodel(CreditCard.class)
public class CreditCard_ { 

    public static volatile SingularAttribute<CreditCard, Integer> expiryDate;
    public static volatile SingularAttribute<CreditCard, BigInteger> creditCardNumber;
    public static volatile SingularAttribute<CreditCard, Integer> cvv;
    public static volatile SingularAttribute<CreditCard, String> cardType;

}