package Reservation.controller;

import Reservation.connection.FlightDB;
import Reservation.model.Flight;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

/**
 *
 * @author Rohit Ruparel
 */
public class CatalogController extends HttpServlet {

    @
    Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {


        String source = null;
        source = request.getParameter("source");
        String destination = null;
        destination = request.getParameter("destination");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if (request.getParameter("flightCode") == null) {
            try {
                date = format.parse(request.getParameter("date"));
            } catch (ParseException ex) {
                Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String action = null;
        FlightDB flightDB = new FlightDB();
        Flight flight;
        String flightCode = null;
        action = request.getParameter("action");
        if (action.equals("Search")) {


            List < Flight > flightGroup = new ArrayList();
            flightGroup = flightDB.getFlights(source, destination, date);
            String x = null;
            request.setAttribute("flightGroup", flightGroup);
            RequestDispatcher selectedCategory;
            selectedCategory = request.getRequestDispatcher("catalog.jsp");
            selectedCategory.forward(request, response);
        } else {
            flightCode = request.getParameter("flightCode");
            flight = flightDB.getProduct(flightCode);
            request.setAttribute("flight", flight);
            RequestDispatcher selectedCategory;
            selectedCategory = request.getRequestDispatcher("item.jsp");
            selectedCategory.forward(request, response);
        }
    }

}