/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Rohit Ruparel
 */
@Entity
@Table(name="tickets")
public class Ticket implements Serializable {

    @Id
    @Column(name="ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketID=0;
    
    @Column(name="order_id")
    private int orderID;
    
    @Column(name="flight_id")
    private String flightID;
    
    @Column(name="booked_seats")
    private int quantity;
    
    @Column(name="total_fare")
    private float totalFare;
    
    @Transient
    private Flight flight;
    
    @Transient
    private Order order;

    public Ticket() {
    }

    public Ticket(Flight flight, int quantity) {
        this.flight = flight;
        this.quantity = quantity;
    }

    public Flight getFlight() {
        return flight;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setFlightID(String flightID){
        this.flightID=flightID;
    }
    
    public String getFlightID(){
        return flightID;
    }
      
    public float getTotal() {
        float totalAmount = 0;
        totalAmount = (flight.getFare() * quantity);
        return totalAmount;
    }
    
    public int getOrderID(){
        return orderID;
    }
    
    public void setOrderID(int orderID){
        this.orderID=orderID;
    }
    
    public void setFlight(Flight flight){
        this.flight=flight;
    }
    
    public Order getOrder(){
        return order;
    }
    
    public void setOrder(Order order){
        this.order=order;
    }
    
    public float getTotalFare(){
        return totalFare;
    }
    
    public void setTotalFare(float totalFare){
        this.totalFare=totalFare;
    }
}
