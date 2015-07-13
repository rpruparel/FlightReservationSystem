/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Rohit Ruparel
 */
@Entity
@Table(name="flights")
public class Flight implements Serializable {
    
    @Id
    @Column(name="flight_id")
    private String flightID;
    
    @Column(name="source")
    private String source; 
    
    @Column(name="destination")
    private String destination;
    
    @Column(name="arrival")
    @Temporal(TemporalType.DATE)
    private Date arrival;

    @Column(name="departure")
    @Temporal(TemporalType.DATE)
    private Date departure;
    
    @Column(name="fare")
    private float fare;
    
    public Flight() {
    }

    public Flight(String flightID, String source, String destination, Date departure, Date arrival, float fare) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.fare = fare;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDeparture() {
        return departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public float getFare() {
        return fare;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
}
