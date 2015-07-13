package Reservation.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-27T17:55:00")
@StaticMetamodel(Flight.class)
public class Flight_ { 

    public static volatile SingularAttribute<Flight, String> flightID;
    public static volatile SingularAttribute<Flight, String> source;
    public static volatile SingularAttribute<Flight, Date> arrival;
    public static volatile SingularAttribute<Flight, Date> departure;
    public static volatile SingularAttribute<Flight, Float> fare;
    public static volatile SingularAttribute<Flight, String> destination;

}