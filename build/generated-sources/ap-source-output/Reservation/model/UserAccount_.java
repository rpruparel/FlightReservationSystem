package Reservation.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-27T17:55:00")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ { 

    public static volatile SingularAttribute<UserAccount, Integer> userAccountID;
    public static volatile SingularAttribute<UserAccount, String> emailAddress;
    public static volatile SingularAttribute<UserAccount, String> password;
    public static volatile SingularAttribute<UserAccount, Integer> userType;

}