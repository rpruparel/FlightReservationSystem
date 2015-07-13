package Reservation.connection;

import Reservation.model.CreditCard;
import Reservation.model.Customer;
import Reservation.model.Order;
import Reservation.model.Ticket;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
/**
 *
 * @author Rohit Ruparel
 */
public class OrderDB {

    public List < Order > orderGroup;

    public static void addOrder(int orderNumber, Date date, Customer customer, List < Ticket > orderItem, float taxRate, float totalCost, boolean paid) {
        Order order;
        order = new Order(orderNumber, date, customer, orderItem, taxRate, totalCost, paid);
        addOrder(order);
    }
    public static void addOrder(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            int customerID = order.getCustomer().getCustomerID();
            order.setCustomerID(customerID);
            em.persist(order);
            trans.commit();
            addOrderItem(order);
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {

            em.close();
        }
    }

    public static void addCreditCard(CreditCard creditCard) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(creditCard);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static Order getOrder(int orderNumber) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Order order = em.find(Order.class, orderNumber);
            return order;
        } finally {
            em.close();
        }
    }

    public static CreditCard getCreditCard(BigInteger creditCardNumber) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CreditCard creditCard = em.find(CreditCard.class, creditCardNumber);
            return creditCard;
        } finally {
            em.close();
        }
    }

    public static List < Order > getOrders() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Order u";
        TypedQuery < Order > q = em.createQuery(qString, Order.class);

        List < Order > orderGroup = new ArrayList < Order > ();
        try {
            orderGroup = (List < Order > ) q.getResultList();
            if (orderGroup == null || orderGroup.isEmpty())
                orderGroup = null;
        } finally {
            em.close();
        }
        return orderGroup;
    }

    public static List < Ticket > getTickets(int orderID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Ticket u " +
            " WHERE u.orderID = :orderID";
        TypedQuery < Ticket > q = em.createQuery(qString, Ticket.class);
        q.setParameter("orderID", orderID);

        List < Ticket > orderGroup = new ArrayList < Ticket > ();
        try {
            orderGroup = (List < Ticket > ) q.getResultList();
            if (orderGroup == null) {
                return null;
            } else {
                return orderGroup;
            }
        } catch (NullPointerException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List < Ticket > getTickets(String flightID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Ticket u " +
            " WHERE u.flightID = :flightID";
        TypedQuery < Ticket > q = em.createQuery(qString, Ticket.class);
        q.setParameter("flightID", flightID);

        List < Ticket > orderGroup = new ArrayList < Ticket > ();
        try {
            orderGroup = (List < Ticket > ) q.getResultList();
            if (orderGroup.size() == 0) {
                return null;
            } else {
                return orderGroup;
            }
        } catch (NoResultException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List < Order > getCustomerOrders(int customerID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Order u " +
            "WHERE u.customerID = :customerID";
        TypedQuery < Order > q = em.createQuery(qString, Order.class);
        q.setParameter("customerID", customerID);

        List < Order > orderGroup = new ArrayList < Order > ();
        try {
            orderGroup = q.getResultList();
            if (orderGroup == null) {
                return null;
            } else {
                return orderGroup;
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }



    public static List < Order > getOrdersByMonth(Date fromDate, Date toDate) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Order u " +
            "WHERE u.orderCreated >= :fromDate " +
            "AND u.orderCreated <= :toDate";
        TypedQuery < Order > q = em.createQuery(qString, Order.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);

        List < Order > orderGroup = new ArrayList < Order > ();
        try {
            orderGroup = q.getResultList();
            if (orderGroup == null) {
                return null;
            } else {
                return orderGroup;
            }
        } catch (NullPointerException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static void update(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(order);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(order));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void addOrderItem(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        List < Ticket > tickets = new ArrayList < Ticket > ();
        tickets = order.getTickets();
        try {

            for (Ticket ticket: tickets) {
                ticket.setOrderID(order.getOrderID());
                ticket.setFlightID(ticket.getFlight().getFlightID());
                ticket.setTotalFare(ticket.getTotal());
                em.persist(ticket);
            }
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List < Order > getFlightCustomers(int customerID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Order u " +
            "WHERE u.customerID = :customerID";
        TypedQuery < Order > q = em.createQuery(qString, Order.class);
        q.setParameter("customerID", customerID);

        List < Order > orderGroup = new ArrayList < Order > ();
        try {
            orderGroup = (List < Order > ) q.getResultList();
            if (orderGroup == null || orderGroup.isEmpty())
                orderGroup = null;
        } finally {
            em.close();
        }
        return orderGroup;
    }

    public static List < Ticket > getFlightTickets(String flightID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Ticket u " +
            "WHERE u.flightID = :flightID";
        TypedQuery < Ticket > q = em.createQuery(qString, Ticket.class);
        q.setParameter("flightID", flightID);

        List < Ticket > orderGroup = new ArrayList < Ticket > ();
        try {
            orderGroup = (List < Ticket > ) q.getResultList();
            if (orderGroup == null) {
                return null;
            } else {
                return orderGroup;
            }
        } catch (NullPointerException e) {
            return null;
        } finally {
            em.close();
        }
    }


}