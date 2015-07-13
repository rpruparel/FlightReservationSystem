/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reservation.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Rohit Ruparel
 */
@Entity
@Table(name="user_accounts")
public class UserAccount implements Serializable {
    
    @Id
    @Column(name="user_account_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userAccountID=0;
    
    @Column(name="email_address")
    private String emailAddress;
    
    @Column(name="password")
    private String password;
    
    @Column(name="user_type")
    private int userType;
   
    public UserAccount(){
    }
    
    public int getUserAccountID() {
        return userAccountID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
    
    public int getUserType(){
        return userType;
    }

    public void setUserAccountID(int userAccountID){
        this.userAccountID=userAccountID;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserType(int userType) {
        this.userType = userType;
    }
    
}
