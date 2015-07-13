package Reservation.controller;

import Reservation.connection.FlightDB;
import Reservation.connection.OrderDB;
import Reservation.model.Flight;
import Reservation.model.Order;
import Reservation.model.Ticket;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class AnalystController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = null;
        action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        if (action.equals("loadOptions")) {
            FlightDB flightDB = new FlightDB();
            List < Flight > flightGroup = new ArrayList < Flight > ();
            flightGroup = flightDB.getDestinations();

            request.setAttribute("flightGroup", flightGroup);
            RequestDispatcher userOrders;
            userOrders = request.getRequestDispatcher("analyzeData.jsp");
            userOrders.forward(request, response);
        } else if (action.equals("analyzeDestination")) {
            String destination = request.getParameter("destination");
            List < Flight > flightGroup = new ArrayList();
            FlightDB flightDB = new FlightDB();
            flightGroup = flightDB.getDestinationFlights(destination);
            if (flightGroup == null) {
                response.sendRedirect("analyzeData.jsp?message=error");
            }

            int totalPassengers = 0;
            float totalRevenue = 0;
            for (int n = 0; n < flightGroup.size(); n++) {
                Flight flight = flightGroup.get(n);
                OrderDB orderDB = new OrderDB();
                List < Ticket > ticketGroup = orderDB.getFlightTickets(flight.getFlightID());

                for (int m = 0; m < ticketGroup.size(); m++) {
                    Ticket ticket = ticketGroup.get(m);
                    totalPassengers = totalPassengers + ticket.getQuantity();
                    totalRevenue = totalRevenue + ticket.getTotalFare();
                }
            }
            flightGroup = flightDB.getDestinations();
            request.setAttribute("destination", destination);
            request.setAttribute("flightGroup", flightGroup);
            request.setAttribute("totalPassengers", totalPassengers);
            request.setAttribute("totalRevenue", totalRevenue);
            RequestDispatcher userOrders;
            userOrders = request.getRequestDispatcher("analyzeData.jsp");
            userOrders.forward(request, response);


        } else if (action.equals("analyzeMonthYear")) {
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));

            OrderDB orderDB = new OrderDB();

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String recDate = year + "-" + month + "-01";
            Date fromDate = null;
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.YEAR, year);
            Date date = calendar.getTime();
            System.out.println(date);

            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);

            Date newDate = calendar.getTime();
            System.out.println(newDate);
            List < Order > orderGroup = new ArrayList < Order > ();

            orderGroup = orderDB.getOrdersByMonth(date, newDate);
            if (orderGroup == null) {
                response.sendRedirect("analyzeByMonth?message=error");
            }

            float revenue = 0;
            int passengers = 0;
            for (int n = 0; n < orderGroup.size(); n++) {
                Order order = orderGroup.get(n);
                int orderID = order.getOrderID();

                List < Ticket > ticketGroup = new ArrayList < Ticket > ();
                ticketGroup = orderDB.getTickets(orderID);

                if (ticketGroup == null) {
                    continue;
                }
                for (int m = 0; m < ticketGroup.size(); m++) {
                    Ticket ticket = ticketGroup.get(m);
                    passengers = passengers + ticket.getQuantity();
                }
                revenue = revenue + order.getTotalCost() + order.getTaxRate();
            }

            request.setAttribute("totalPassengers", passengers);
            request.setAttribute("totalRevenue", revenue);
            RequestDispatcher userOrders;
            userOrders = request.getRequestDispatcher("analyzeByMonth.jsp");
            userOrders.forward(request, response);




        }
    }
}