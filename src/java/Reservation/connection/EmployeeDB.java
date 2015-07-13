package Reservation.connection;

import Reservation.model.Customer;
import Reservation.model.Employee;
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
public class EmployeeDB {

    public List < Employee > userGroup;

    public static void addUser(Employee user) {
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
        //    public static Employee getUser(int userID){
        //        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        //        try{
        //            Employee user = em.find(Employee.class, userID);
        //            return user;
        //        }
        //        finally{
        //            em.close();
        //        } 
        //    }
        //    
    public static List < Employee > getUsers() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Employee u";
        TypedQuery < Employee > q = em.createQuery(qString, Employee.class);

        List < Employee > userGroup = new ArrayList < Employee > ();
        try {
            userGroup = (List < Employee > ) q.getResultList();
            if (userGroup == null || userGroup.isEmpty())
                userGroup = null;
        } finally {
            em.close();
        }
        return userGroup;
    }


    public static Employee getUser(int userAccountID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from Employee u " +
            "WHERE u.userAccountID = :userAccountID";
        TypedQuery < Employee > q = em.createQuery(qString, Employee.class);
        q.setParameter("userAccountID", userAccountID);

        Employee user = new Employee();
        try {
            if (q.getSingleResult() != null) {
                user = (Employee) q.getSingleResult();
            } else {
                return null;
            }

        } finally {
            em.close();
        }
        return user;
    }

    public static void update(Employee user) {
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

    public static void delete(Employee user) {
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