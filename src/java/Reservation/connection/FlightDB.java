package Reservation.connection;

import Reservation.model.Flight;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rohit Ruparel
 */
public class FlightDB {

    public List < Flight > productGroup;

    public static void addProduct(String flightID, String source, String destination, Date departure, Date arrival, float fare) {
        Flight flight;
        flight = new Flight(flightID, source, destination, departure, arrival, fare);
        addProduct(flight);
    }
    public static void addProduct(Flight flight) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(flight);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static Flight getProduct(String flightID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Flight flight = em.find(Flight.class, flightID);
            if (flight == null) {
                return null;
            } else {
                return flight;
            }
        } catch (NullPointerException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List < Flight > getProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Flight u";
        TypedQuery < Flight > q = em.createQuery(qString, Flight.class);

        List < Flight > flightGroup = new ArrayList < Flight > ();
        try {
            flightGroup = (List < Flight > ) q.getResultList();
            if (flightGroup == null || flightGroup.isEmpty())
                flightGroup = null;
        } finally {
            em.close();
        }
        return flightGroup;
    }

    public static List < Flight > getFlights(String source, String destination, Date departure) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Flight u " +
            " WHERE u.source = :source AND " +
            " u.destination = :destination AND " +
            " u.departure = :departure";

        TypedQuery < Flight > q = em.createQuery(qString, Flight.class);
        q.setParameter("source", source);
        q.setParameter("destination", destination);
        q.setParameter("departure", departure);

        List < Flight > flightGroup = new ArrayList();
        try {

            flightGroup = (List < Flight > ) q.getResultList();
            if (flightGroup == null || flightGroup.isEmpty())
                flightGroup = null;
        } finally {
            em.close();
        }
        return flightGroup;
    }

    public static void update(Flight flight) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(flight);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Flight flight) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(flight));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List < Flight > getDestinations() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Flight u " +
            " GROUP BY u.destination " +
            " ORDER BY u.destination ";

        TypedQuery < Flight > q = em.createQuery(qString, Flight.class);

        List < Flight > flightGroup = new ArrayList();
        try {

            flightGroup = (List < Flight > ) q.getResultList();
            if (flightGroup == null || flightGroup.isEmpty())
                flightGroup = null;
        } finally {
            em.close();
        }
        return flightGroup;
    }

    public static List < Flight > getDestinationFlights(String destination) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Flight u " +
            " WHERE u.destination= :destination";

        TypedQuery < Flight > q = em.createQuery(qString, Flight.class);
        q.setParameter("destination", destination);

        List < Flight > flightGroup = new ArrayList();
        try {

            flightGroup = (List < Flight > ) q.getResultList();
            if (flightGroup == null || flightGroup.isEmpty())
                flightGroup = null;
        } finally {
            em.close();
        }
        return flightGroup;
    }

}