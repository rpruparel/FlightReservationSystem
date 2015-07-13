/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reservation.controller;

import Reservation.connection.FlightDB;
import Reservation.connection.OrderDB;
import Reservation.model.Customer;
import Reservation.model.Flight;
import Reservation.model.Order;
import Reservation.model.Ticket;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = null;
        action = request.getParameter("action");
        if(action == ""){
            response.sendRedirect("index.jsp");
            return;
        }
            
        if(action.equals("viewOrders")){
        OrderDB orderDB = new OrderDB();
        List<Order> orderGroup;
        orderGroup = orderDB.getOrders();
        request.setAttribute("theOrders", orderGroup);
        RequestDispatcher userOrders;
        userOrders = request.getRequestDispatcher("orderlist.jsp");
        userOrders.forward(request, response);      
        }
        else if(action.equals("addFlight")){
            String flightID = request.getParameter("flightID");
            String source = request.getParameter("source");
            String destination = request.getParameter("destination");
            
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date depDate=null;
            Date arrDate=null;
            
            
            try {
                depDate = format.parse(request.getParameter("depDate"));
                arrDate = format.parse(request.getParameter("arrDate"));
            } catch (ParseException ex) {
                Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            float fare = Float.parseFloat(request.getParameter("fare"));
            
            Flight flight = new Flight(flightID, source, destination, depDate, arrDate, fare);
            FlightDB flightDB = new FlightDB();
            
            Flight flightExist = flightDB.getProduct(flightID);
            if(flightExist==null){
            flightDB.addProduct(flight);
            response.sendRedirect("add.jsp?message=addSuccess");
            return;
            }
            else{
            response.sendRedirect("add.jsp?message=error");
            return;
            }
            }
        else if(action.equals("viewFlight")){

            FlightDB flightDB = new FlightDB();
            List<Flight> flightGroup = new ArrayList<Flight>();
            flightGroup = (List<Flight>) flightDB.getProducts();
            if(flightGroup==null){
            response.sendRedirect("view.jsp");
            return;
            }
            else{
            request.setAttribute("flightGroup", flightGroup);
            RequestDispatcher userOrders;
            userOrders = request.getRequestDispatcher("view.jsp");
            userOrders.forward(request, response);
            }
            }
         else if(action.equals("editFlight")){

            FlightDB flightDB = new FlightDB();
            Flight flight = new Flight();
            String flightID = request.getParameter("flightID");
            flight = flightDB.getProduct(flightID);

            request.setAttribute("flight", flight);
            RequestDispatcher userOrders;
            userOrders = request.getRequestDispatcher("edit.jsp");
            userOrders.forward(request, response);
            }
          else if(action.equals("updateFlight")){
            String flightID = request.getParameter("flightID");
            String source = request.getParameter("source");
            String destination = request.getParameter("destination");
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date depDate=null;
            Date arrDate=null;
            
            
            try {
                depDate = format.parse(request.getParameter("depDate"));
                arrDate = format.parse(request.getParameter("arrDate"));
            } catch (ParseException ex) {
                Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            float fare = Float.parseFloat(request.getParameter("fare"));
            
            Flight flight = new Flight(flightID, source, destination, depDate, arrDate, fare);
            FlightDB flightDB = new FlightDB();
            
            
            flightDB.update(flight);
            response.sendRedirect("AdminController?action=editFlight&message=updateSuccess&flightID="+flightID);
            return;
            }
            else if(action.equals("deleteFlight")){
                        
            FlightDB flightDB = new FlightDB();
            String flightID = request.getParameter("flightID");
            Flight flight;
            flight = flightDB.getProduct(flightID);
            OrderDB orderDB = new OrderDB();
            List<Ticket> ticketGroup = new ArrayList<Ticket>();
            ticketGroup = null;
            ticketGroup = orderDB.getTickets(flightID);
            
            if(ticketGroup==null){
            flightDB.delete(flight);
            response.sendRedirect("AdminController?action=viewFlight&message=delete");
            return;
            }
            else{
            response.sendRedirect("AdminController?action=viewFlight&message=error");
            return; 
            }}
         
            else{
            response.sendRedirect("index.jsp");
            return;
            }
             }
}
