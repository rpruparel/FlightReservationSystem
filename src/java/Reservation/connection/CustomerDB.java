package Reservation.connection;

import Reservation.model.Customer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rohit Ruparel
 */
public class CustomerDB {

    public List < Customer > userGroup;

    public static void addUser(String name, BigInteger contactNumber, String street, String city, String state, int zipCode) {
        Customer user;
        user = new Customer(name, contactNumber, street, city, state, zipCode);
        addUser(user);
    }
    public static void addUser(Customer user) {
            EntityManager em = DBUtil.getEmFactory().createEntityManager();
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            try {
                em.persist(user);
                trans.commit();
            } catch (Exception e) {
                System.out.println(e);
                trans.rollback();
            } finally {
                em.close();
            }
        }
        //    public static Customer getUser(int userID){
        //        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        //        try{
        //            Customer user = em.find(Customer.class, userID);
        //            return user;
        //        }
        //        finally{
        //            em.close();
        //        } 
        //    }

    public static List < Customer > getUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Customer u";
        TypedQuery < Customer > q = em.createQuery(qString, Customer.class);

        List < Customer > userGroup = new ArrayList < Customer > ();
        try {
            userGroup = (List < Customer > ) q.getResultList();
            if (userGroup == null || userGroup.isEmpty())
                userGroup = null;
        } finally {
            em.close();
        }
        return userGroup;
    }


    public static Customer getUser(int userAccountID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Customer u " +
            "WHERE u.userAccountID = :userAccountID";
        TypedQuery < Customer > q = em.createQuery(qString, Customer.class);
        q.setParameter("userAccountID", userAccountID);

        Customer user = new Customer();
        try {
            if (q.getSingleResult() != null) {
                user = (Customer) q.getSingleResult();
            } else {
                return null;
            }

        } finally {
            em.close();
        }
        return user;
    }

    public static Customer getUser(int customerID, int x) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Customer u " +
            "WHERE u.customerID = :customerID";
        TypedQuery < Customer > q = em.createQuery(qString, Customer.class);
        q.setParameter("customerID", customerID);

        Customer user = new Customer();
        try {


            user = q.getSingleResult();
            if (user == null) {
                return null;
            } else {
                return user;
            }


        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static void update(Customer user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Customer user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

}