<%@page import="java.text.SimpleDateFormat"%>
    <%@page import="java.util.Date"%>
        <%@page import="java.util.List"%>
            <%@page import="java.util.ArrayList" %>
                <%@page import="Reservation.model.Flight" %>
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
                                    <%List<Flight> flightGroup = (List) request.getAttribute("flightGroup");
          String source = request.getParameter("source");
          String destination = request.getParameter("destination");
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          Date date = dateFormat.parse(request.getParameter("date"));
          SimpleDateFormat newdateFormat = new SimpleDateFormat("dd/MMM/YYYY");
          String newdate = newdateFormat.format(date);%>

                                        <div class="Main">
                                            <a href='index.jsp'> Home </a> >
                                            <a href='search.jsp'> Search </a>
                                            <br>
                                            <br>
                                            <div class="bodytext">
                                                <b>
                    Search Results:
            </b>
                                                <hr>
                                                <% if (flightGroup!=null){%>
                                                    <h3>
                    <%=source%> to <%=destination%> on <%=newdate%>
                </h3>
                                                    <div class="bodytext">

                                                        <% for (int n = 0; n < flightGroup.size(); n++){
                     Flight flight = flightGroup.get(n);%>
                                                            <a href="CatalogController?action=flightDetail&flightCode=<%=flight.getFlightID()%>">
                        Flight Code. <%=flight.getFlightID()%> - <%=flight.getSource()%> - <%=flight.getDestination()%>
                    </a>
                                                            <br>
                                                            <% } %>


                                                                <%} else {%>
                                                                    <p> No Flights Found.</p>
                                                                    <%}%>
                                                    </div>
                                            </div>
                                        </div>
                                        <%@include file="/footer.jsp" %>
                        </body>

                        </html>