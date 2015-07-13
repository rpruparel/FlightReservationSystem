package Reservation.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Rohit Ruparel
 */
@
Entity@ Table(name = "Customers")
public class Customer implements Serializable {

    @
    Id@ Column(name = "customer_id")@ GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID = 0;

    @
    Column(name = "name")
    private String name;

    @
    Column(name = "contact_number")
    private BigInteger contactNumber;

    @
    Column(name = "street")
    private String street;

    @
    Column(name = "city")
    private String city;

    @
    Column(name = "state")
    private String state;

    @
    Column(name = "zip_code")
    private int zipCode;

    @
    Column(name = "user_account_id")
    private int userAccountID;

    @
    Transient
    private UserAccount userAccount;

    public Customer() {}

    public Customer(String name, BigInteger contactNumber, String street, String city, String state, int zipCode) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public BigInteger getContactNumber() {
        return contactNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(BigInteger contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}