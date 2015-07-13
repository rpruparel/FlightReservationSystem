/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation.connection;

import Reservation.model.Customer;
import Reservation.model.UserAccount;
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
public class UserAccountDB {

    public List<UserAccount> userGroup;
    
    public static void addUser(UserAccount user,Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        CustomerDB customerDB = new CustomerDB();
        trans.begin();        
        try {
            em.persist(user);
            trans.commit();
            customer.setUserAccount(user);
            customer.setUserAccountID(user.getUserAccountID());
            customerDB.addUser(customer);
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static UserAccount getUser(int userID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            UserAccount user = em.find(UserAccount.class, userID);
            return user;
        }
        finally{
            em.close();
        } 
    }
    
    public static List<UserAccount> getUsers(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from UserAccount u";
        TypedQuery<UserAccount> q = em.createQuery(qString, UserAccount.class);
        
        List<UserAccount> userGroup = new ArrayList<UserAccount>();
        try{
            userGroup = (List<UserAccount>) q.getResultList();
            if(userGroup == null || userGroup.isEmpty())
                userGroup = null;
        } finally {
            em.close();
        }
        return userGroup;
    }
    
        
    public static UserAccount getUser(String emailAddress){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u from UserAccount u " +
                         "WHERE u.emailAddress = :emailAddress";
        TypedQuery<UserAccount> q = em.createQuery(qString, UserAccount.class);
        q.setParameter("emailAddress", emailAddress);
        
        UserAccount user = new UserAccount();
        try{
                user = q.getSingleResult();
                if(user==null){
                    return null;
                }else{
                    return user;
                }
            
        }catch(NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }
    
    public static void update(UserAccount user) {
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

    public static void delete(UserAccount user) {
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
