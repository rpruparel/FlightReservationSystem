package Reservation.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Rohit Ruparel
 */
@
Entity@ Table(name = "orders")
public class Order implements Serializable {

    @
    Id@ Column(name = "order_id")@ GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID = 0;

    @
    Column(name = "order_created")@ Temporal(TemporalType.TIMESTAMP)
    private Date orderCreated;

    @
    Column(name = "total_cost")
    private float totalCost;

    @
    Column(name = "tax_rate")
    private float taxRate;

    @
    Column(name = "paid")
    private boolean paid = false;

    @
    Column(name = "customer_id")
    private int customerID;

    @
    Column(name = "credit_card_number")
    private BigInteger creditCardNumber;

    @
    Transient
    private CreditCard creditCard;

    @
    Transient
    private Customer customer;

    @
    Transient
    private List < Ticket > tickets;

    public Order() {}

    public Order(int orderID, Date orderCreated, Customer customer, List < Ticket > tickets, float taxRate, float totalCost, boolean paid) {
        this.orderID = orderID;
        this.orderCreated = orderCreated;
        this.customer = customer;
        this.tickets = tickets;
        this.taxRate = taxRate;
        this.totalCost = totalCost;
        this.paid = paid;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderCreated() {
        return orderCreated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List < Ticket > getTickets() {
        return tickets;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    public void setTickets(List < Ticket > tickets) {
        this.tickets = tickets;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return customerID;
    }

    public void setCreditCardNumber(BigInteger creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public BigInteger getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}