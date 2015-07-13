/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation.controller;

import Reservation.connection.CustomerDB;
import Reservation.connection.EmployeeDB;
import Reservation.connection.FlightDB;
import Reservation.connection.OrderDB;
import Reservation.connection.PasswordUtil;
import Reservation.connection.UserAccountDB;
import Reservation.model.Cart;
import Reservation.model.CreditCard;
import Reservation.model.Customer;
import Reservation.model.Employee;
import Reservation.model.Flight;
import Reservation.model.Order;
import Reservation.model.Ticket;
import Reservation.model.UserAccount;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rohit Ruparel
 */
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Loads the database of products.*/
        FlightDB flightDB = new FlightDB();
        List<Flight> flightGroup = flightDB.getProducts();

        /*check if not a valid shopping cartList ******************************/
        HttpSession session = request.getSession();
        Cart theShoppingCart = (Cart) session.getAttribute("theShoppingCart");
        int cartCheck = 0;
        if (theShoppingCart == null) {
            theShoppingCart = new Cart();
            session.setAttribute("theShoppingCart", theShoppingCart);
            cartCheck = 1;
        }
        /*Checks the http request for a parameter called “action”*/
        String action = null;
        int quantity = 0;
        int productCount = 0;
        int validQuantity = 0;
        List<Ticket> cartList;
        cartList = (List<Ticket>) ((Cart) session.getAttribute("theShoppingCart")).getItems();

        action = (String) request.getParameter("action");
        if (action == null) {
            response.sendRedirect("cart.jsp");
            return;
        }

        if (action.equals("updateCart") || action.equals("checkout") || action.equals("confirmOrder") || action.equals("viewOrders") || action.equals("Login") || action.equals("logout") || action.equals("register") || action.equals("updateDetails")) {
            if (action.equals("updateCart")) {
                String[] flightList = null;
                flightList = request.getParameterValues("flightList");
                
                    if (flightList == null) {
                        response.sendRedirect("cart.jsp");
                        return;
                    }

                

                for (int n = 0; n < flightList.length; n++) {
                    for (int m = 0; m < flightGroup.size(); m++) {
                        Flight flight = flightGroup.get(m);
                        if (flightList[n].equals(flight.getFlightID())) {
                            if (cartCheck == 1) {
                                if (request.getParameter(flight.getFlightID()) == null) {
                                    Ticket orderItem = new Ticket(flight, 1);
                                    theShoppingCart.addItem(flight, 1);
                                }
                            } else {
                                if (request.getParameter(flight.getFlightID()) == null || request.getParameter(flight.getFlightID()) == "") {
                                    quantity = 1;
                                    productCount = 1;
                                } else if (request.getParameter(flight.getFlightID()).matches("[0-9]+")) {
                                    quantity = Integer.parseInt((String) request.getParameter(flight.getFlightID()));
                                } else {
                                    break;
                                }

                                if (quantity == 0) {
                                    for (Ticket oi : cartList) {
                                        if (oi.getFlight().getFlightID().equals(flight.getFlightID())) {
                                            theShoppingCart.removeItem(oi.getFlight());
                                            break;

                                        }
                                    }

                                } else if (quantity == 1 && productCount == 1) {

                                    int itemExistsCheck = 0;
                                    for (Ticket oi : cartList) {
                                        if (oi.getFlight().getFlightID().equals(flight.getFlightID())) {
                                            oi.setQuantity(oi.getQuantity() + 1);
                                            itemExistsCheck = 1;
                                            break;

                                        }
                                    }
                                    if (itemExistsCheck == 0) {
                                        Ticket orderItem = new Ticket(flight, 1);
                                        theShoppingCart.addItem(flight, 1);
                                    }

                                } else {
                                    for (Ticket oi : cartList) {
                                        if (oi.getFlight().getFlightID().equals(flight.getFlightID())) {
                                            if (oi.getQuantity() != quantity) {
                                                oi.setQuantity(quantity);
                                                break;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                response.sendRedirect("cart.jsp");
                return;
            } else if (action.equals("checkout")) {
                int userTypeID = 0;
                Customer customer = null;
                customer = (Customer) session.getAttribute("theUser");
                         if (customer == null) {
                            response.sendRedirect("login.jsp");
                            return;
                }
                
                userTypeID = (Integer) session.getAttribute("userTypeID");
                if(userTypeID == 0){
                    response.sendRedirect("login.jsp");
                    return;
                }

                float totalCost = 0;
                for (Ticket orderItem : cartList) {
                    totalCost = totalCost + orderItem.getTotal();
                }
               
                Date now = new Date();
                Order currentOrder = new Order(0, now, customer, cartList, (float) 10.7, totalCost, true);
                session.setAttribute("currentOrder", currentOrder);
                response.sendRedirect("order.jsp");
                return;
            } else if (action.equals("confirmOrder")) {
               
                Customer customer;
                OrderDB orderDB = new OrderDB();
                String creditCardType = request.getParameter("creditCard");
                BigInteger creditCardNumber = new BigInteger(request.getParameter("ccnumber"));
                int expiryDate = Integer.parseInt(request.getParameter("expiryDate"));
                int cvv =  Integer.parseInt( request.getParameter("cvv"));
                customer = (Customer) session.getAttribute("theUser");

                
                CreditCard creditCardExists = new CreditCard();
                creditCardExists = orderDB.getCreditCard(creditCardNumber);

                CreditCard creditCard = new CreditCard();
                creditCard.setCreditCardNumber(creditCardNumber);
                creditCard.setCardType(creditCardType);
                creditCard.setExpiryDate(expiryDate);
                creditCard.setCvv(cvv);
                
//                creditCard.setCustomerID(customer.getCustomerID());
//                creditCard.setCustomer(customer);
                
                if(creditCardExists==null){
                    orderDB.addCreditCard(creditCard);
                }
                
                Order order = (Order) session.getAttribute("currentOrder");
                order.setCreditCardNumber(creditCardNumber);
                order.setCreditCard(creditCard);
                orderDB.addOrder(order);
                response.sendRedirect("order.jsp?message=Paid in full");
                return;
            } else if (action.equals("viewOrders")) {
                Customer customer = (Customer) session.getAttribute("theUser");
                if (customer != null) {
                    OrderDB orderDB = new OrderDB();
                    List<Order> orderGroup;
                    orderGroup = orderDB.getCustomerOrders(customer.getCustomerID());
                    if(orderGroup==null){
                    request.setAttribute("theOrders", null);
                    RequestDispatcher userOrders;
                    userOrders = request.getRequestDispatcher("orderlist.jsp");
                    userOrders.forward(request, response);
                    }
                    request.setAttribute("theOrders", orderGroup);
                    RequestDispatcher userOrders;
                    userOrders = request.getRequestDispatcher("orderlist.jsp");
                    userOrders.forward(request, response);
                } else {
                    response.sendRedirect("search.jsp");
                    return;
                }
            } else if (action.equals("Login")) {
                String userName = request.getParameter("username");
                String password = request.getParameter("passcard");         
                
                UserAccountDB userDB = new UserAccountDB();
                UserAccount userAccount = userDB.getUser(userName);
                if(userAccount == null) {
                    response.sendRedirect("login.jsp?message=error");
                    return;
                }
                
                String dbPasscard = userAccount.getPassword();
                if (dbPasscard.equals(password)) {
                     int userType = userAccount.getUserType();
                     if(userType==1){
                         CustomerDB customerDB = new CustomerDB();
                         Customer customer = customerDB.getUser(userAccount.getUserAccountID());
                         session.setAttribute("theUser", customer);
                         session.setAttribute("userTypeID", userAccount.getUserType());
                         response.sendRedirect("OrderController?action=checkout");
                         return;
                     }else{
                         EmployeeDB employeeDB = new EmployeeDB();
                         Employee employee = employeeDB.getUser(userAccount.getUserAccountID());
                         session.setAttribute("theUser", employee);
                         session.setAttribute("userTypeID", userAccount.getUserType());
                         int employeeType = employee.getEmployeeType();
                         if(employeeType == 1){
                            response.sendRedirect("index.jsp");
                            return;
                         }else{
                            response.sendRedirect("index.jsp");
                            return;
                         }
                     }
                     
                }
                else{
                    response.sendRedirect("login.jsp?message=error");
                    return;
                }
            } else if (action.equals("logout")) {
                int userType = (Integer) session.getAttribute("userTypeID");
                if(userType==1){
                    Customer user = (Customer) session.getAttribute("theUser");
                    user = null;
                    session.setAttribute("theUser", user);
                }else{
                    Employee user = (Employee) session.getAttribute("theUser");
                    user = null;
                    session.setAttribute("theUser", user);
                }
                session.setAttribute("userTypeID", null);
                response.sendRedirect("index.jsp");
                return;
            } else if (action.equals("register")) {
                
                String username = request.getParameter("username");
                String passcard = request.getParameter("passcard");
                String name = request.getParameter("name");
                BigInteger contactNumber = new BigInteger(request.getParameter("contactNumber"));
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                int zipCode = Integer.parseInt(request.getParameter("zipCode"));
                
                

                UserAccountDB userAccountDB = new UserAccountDB();
                UserAccount userAccount = new UserAccount();
                userAccount = userAccountDB.getUser(username);
                if(userAccount==null){
                userAccount = new UserAccount();
                userAccount.setEmailAddress(username);
                userAccount.setPassword(passcard);
                userAccount.setUserType(1);
                Customer customer = new Customer(name, contactNumber, street, city, state, zipCode);
                userAccountDB.addUser(userAccount,customer);
                
                session.setAttribute("theUser", customer);
                session.setAttribute("userTypeID", 1);

                response.sendRedirect("OrderController?action=checkout");
                return;
                
                }
                else{
                response.sendRedirect("registration.jsp?message=userExists");
                return;
                }
                
            }
            else if (action.equals("updateDetails")) {
                
                String username = request.getParameter("username");
                String passcard = request.getParameter("passcard");
                String name = request.getParameter("name");
                BigInteger contactNumber = new BigInteger(request.getParameter("contactNumber"));
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                int zipCode = Integer.parseInt(request.getParameter("zipCode"));
                int userAccountID = Integer.parseInt(request.getParameter("userAccountID"));
                int customerID = Integer.parseInt(request.getParameter("customerID"));

                UserAccountDB userAccountDB = new UserAccountDB();
                UserAccount userAccount = new UserAccount();
                userAccount.setUserAccountID(userAccountID);
                userAccount.setEmailAddress(username);
                userAccount.setPassword(passcard);
                userAccount.setUserType(1);
                userAccountDB.update(userAccount);
                
                Customer customer = new Customer(name, contactNumber, street, city, state, zipCode);
                customer.setCustomerID(customerID);
                customer.setUserAccountID(userAccountID);
                customer.setUserAccount(userAccount);
                
                CustomerDB customerDB = new CustomerDB();
                customerDB.update(customer);
                
                
                session.setAttribute("theUser", customer);
                session.setAttribute("userTypeID", 1);

                response.sendRedirect("editDetails.jsp?message=updateSuccess");
                return;
            }
        } else {
            response.sendRedirect("cart.jsp");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
