package Reservation.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-06-27T17:55:00")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> orderID;
    public static volatile SingularAttribute<Ticket, String> flightID;
    public static volatile SingularAttribute<Ticket, Float> totalFare;
    public static volatile SingularAttribute<Ticket, Integer> ticketID;
    public static volatile SingularAttribute<Ticket, Integer> quantity;

}