<%@page import="java.text.SimpleDateFormat"%>
    <%@page import="Reservation.model.Flight"%>
        <%@page import="Reservation.model.Ticket"%>
            <%@page import="java.util.List"%>
                <%@page import="Reservation.model.Cart"%>
                    <%@page import="java.util.ArrayList"%>
                        <%@page contentType="text/html" pageEncoding="UTF-8" %>
                            <!DOCTYPE html>
                            <html>

                            <head>
                                <title>
                                    Neal Adam's
                                </title>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <link href="css/Style.css" type='text/css' rel='stylesheet' />
                            </head>

                            <body class="Background">
                                <div class="Headers">
                                    <%@include file="/header.jsp" %>
                                </div>
                                <%@include file="/user-navigation.jsp" %>
                                    <%@include file="/site-navigation.jsp" %>
                                        <div class="Main">
                                            <a href='index.jsp'> Home </a> >
                                            <a href='AdminController?action=viewFlight'> Manage Flights </a>

                                            <br>
                                            <br>
                                            <div>
                                                <b>
                    &nbsp;Available Flights
                </b>
                                                <hr>
                                                <%String message = null;
                message = request.getParameter("message");
                
                List<Flight> flightGroup = null;
                if(message!=null && message.equals("delete")){
                    message="Flight Deleted Successfully.";
                %>
                                                    <p>&nbsp;&nbsp;
                                                        <%=message%>
                                                    </p>
                                                    <%}if(message!=null && message.equals("error")){
                    message="Tickets are already booked for the flight, so flight cannot be deleted.";%>
                                                        <p>&nbsp;&nbsp;
                                                            <%=message%>
                                                        </p>
                                                        <%}   

                             flightGroup = (List<Flight>) request.getAttribute("flightGroup");
                                if (flightGroup == null) {%>
                                                            <p>&nbsp;&nbsp;No Flights Found.</p>
                                                            <% }else{%>

                                                                <table class="Table">
                                                                    <tr>
                                                                        <td>
                                                                            <b>
                                    Flight ID
                                </b>
                                                                        </td>
                                                                        <td>
                                                                            <b>
                                    Source
                                </b>
                                                                        </td>
                                                                        <td>
                                                                            <b>
                                    Destination
                                </b>
                                                                        </td>
                                                                        <td>
                                                                            <b>
                                    Departure
                                </b>
                                                                        </td>
                                                                        <td>
                                                                            <b>
                                    Arrival
                                </b>
                                                                        </td>
                                                                        <td>
                                                                            <b>
                                    Fare
                                </b>
                                                                        </td>
                                                                        <td>
                                                                            <b>
                                    Actions
                                </b>
                                                                        </td>
                                                                    </tr>
                                                                    <% for (int p = 0; p < flightGroup.size(); p++) {
                            Flight flight = new Flight();
                            flight = flightGroup.get(p);%>
                                                                        <tr>
                                                                            <td>
                                                                                <%=flight.getFlightID()%>
                                                                            </td>
                                                                            <td>
                                                                                <%=flight.getSource()%>
                                                                            </td>
                                                                            <td>
                                                                                <%=flight.getDestination()%>
                                                                            </td>
                                                                            <td>
                                                                                <%SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/YYYY");
                                  String date = dateFormat.format(flight.getDeparture());%>
                                                                                    <%=date%>
                                                                            </td>
                                                                            <td>
                                                                                <%
                                  date = dateFormat.format(flight.getArrival());%>
                                                                                    <%=date%>
                                                                            </td>
                                                                            <td>
                                                                                <%=flight.getFare()%>
                                                                            </td>
                                                                            <td>
                                                                                <a href="AdminController?action=editFlight&flightID=<%=flight.getFlightID()%>">Edit</a> &nbsp;
                                                                                <a href="AdminController?action=deleteFlight&flightID=<%=flight.getFlightID()%>">Delete</a>
                                                                            </td>
                                                                        </tr>

                                                                        <%}%>
                                                                </table>
                                                                <%}%>
                                            </div>
                                        </div>
                                        <%@include file="/footer.jsp" %>
                            </body>

                            </html>