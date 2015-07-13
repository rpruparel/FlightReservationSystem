<%-- Document : item Created on : 15 Sep, 2014, 8:56:47 PM Author : Acer--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Reservation.model.Flight"%>
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
        <% Flight flight = (Flight) request.getAttribute("flight");
        Date date = flight.getDeparture();%>
        <div class="Main">
            <a href='index.jsp'> Home </a>
            >
            <a href=''> Search </a>
            >
            <a href='CatalogController?action=flightDetail&flightCode=<%=flight.getFlightID()%>'> Item </a>
            <br>
            <br>
            
            <div class="bodytext">
                <b>
                Flight Details:
            </b>
            <hr>
                <table style="width:300px">
                    <tr>
                        <th>
                            Flight No.
                        </th>
                        <th>
                            Source
                        </th>
                        <th>
                            Destination
                        </th>
                        <th>
                            Departure
                        </th>
                        <th>
                            Arrival
                        </th>
                        <th>
                            Fare
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <%= flight.getFlightID()%>
                        </td>
                        <td>
                            <%= flight.getSource()%>
                        </td>
                        <td>
                            <%= flight.getDestination()%>
                        </td>
                        <td><%SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/YYYY");
                            String newDate = dateFormat.format(flight.getDeparture());%>
                            <%=newDate%>
                        </td>
                        <td><%newDate = dateFormat.format(flight.getArrival());%>
                            <%= newDate%>
                        </td>
                        <td>
                            $<%=flight.getFare()%>
                        </td>
                    </tr>
                </table><br>
                <table>
                    <tr>
                        <td>
                            <form action="OrderController" method="GET">
                                <input type="hidden" name="action" value="updateCart">
                                <input type="hidden" name="flightList" value="<%= flight.getFlightID()%>"/>
                                <input type="Submit" value="Add to cart" />
                            </form>
                            <br>
                            <%dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            newDate = dateFormat.format(flight.getDeparture());%>
                            <form action="CatalogController" method="GET">
                                <input type="hidden" name="source" value="<%= flight.getSource()%>">
                                <input type="hidden" name="destination" value="<%= flight.getDestination()%>">
                                <input type="hidden" name="date" value="<%=newDate%>">
                                <input type="hidden" name="action" value="Search">
                                <input type="submit" value="Return To Search" />
                            </form>
                        </td>
                    </tr>
                </table>
                &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <%@include file="/footer.jsp" %>
    </body>

</html>