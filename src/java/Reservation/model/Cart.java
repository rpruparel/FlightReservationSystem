package Reservation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Rohit Ruparel
 */
public class Cart implements Serializable {

    private List < Ticket > cart = new ArrayList < Ticket > ();

    public Cart() {
        this.cart = cart;
    }

    public Cart(List < Ticket > cart) {
        this.cart = cart;
    }

    public List < Ticket > getCart() {
        return cart;
    }

    public void setCart(List < Ticket > cart) {
        this.cart = cart;
    }

    public void addItem(Flight flight, int quantity) {
        int cartItemCheck = 0;
        for (int n = 0; n < cart.size(); n++) {
            Ticket orderItem = cart.get(n);
            Flight addedProduct = orderItem.getFlight();
            if (flight.getFlightID().equals(addedProduct.getFlightID())) {
                orderItem.setQuantity(quantity);
                cartItemCheck = 1;
            }

            if (cartItemCheck == 1) {
                break;
            }
        }
        if (cartItemCheck == 0) {
            Ticket orderItem = new Ticket(flight, quantity);
            if (cart == null) {
                cart = new ArrayList < Ticket > ();
            }
            cart.add(orderItem);
        }
    }

    public boolean removeItem(Flight flight) {
        String flightId = flight.getFlightID();
        for (int n = 0; n < cart.size(); n++) {
            Ticket orderItem = cart.get(n);
            Flight addedProduct = orderItem.getFlight();
            if (flight.getFlightID().equals(addedProduct.getFlightID())) {
                cart.remove(orderItem);
                return true;
            }

        }
        return false;
    }

    public List < Ticket > getItems() {
        return cart;
    }

    public void emptyCart() {
        cart.clear();
    }

    public int getCount() {
        return cart.size();
    }
}