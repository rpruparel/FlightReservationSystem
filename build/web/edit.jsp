

<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Reservation.controller.CatalogController"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Reservation.model.Flight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Neal Adam's </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/Style.css" type='text/css' rel='stylesheet'/>
    </head>

    <body class="Background">

        <div class="Headers">
            <%@include file="/header.jsp" %>
        </div>

        <%@include file="/user-navigation.jsp" %>
        <%@include file="/site-navigation.jsp" %>


        <div class="Main"> 
            <a href='index.jsp'> Home </a> > <a href='search.jsp'> Search </a><br><br>

            <div class="bodytext">        
               <b>
                    Update Flight
                </b>
                <hr>
                 <%
                Flight flight = new Flight();
                flight = (Flight) request.getAttribute("flight");
                if(flight==null){
                    response.sendRedirect("index.jsp");
                    return;
                }
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date depDate=null;
                        Date arrDate=null;

                        
                        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                        Date departDate = (Date)formatter.parse(flight.getDeparture().toString());
                        Date arriveDate = (Date)formatter.parse(flight.getDeparture().toString());
                        System.out.println(departDate);    
                        System.out.println(arriveDate);    

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(departDate);
                        String formatedDepartDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) + 1)+ "-" +cal.get(Calendar.DATE);
                        System.out.println("formatedDate : " + formatedDepartDate);
                        cal.setTime(arriveDate);
                        String formatedArriveDate = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) + 1)+ "-" +cal.get(Calendar.DATE);
                        System.out.println("formatedDate : " + formatedArriveDate);
                String message = null;
                message = request.getParameter("message");
                

                if(message!=null && message.equals("updateSuccess")){
                    message="Flight Updated Successfully.";
                %>
                <p><%=message%></p>
                <%}if(message!=null && message.equals("error")){
                    message="Flight already exist with this Flight ID. Please use a different flight ID";
                %>
                <p><%=message%></p>
                <%}%>
                
                <form action="AdminController" method="get">
                    <br>
                    <br>
                    <table>
                        <tr>
                            <td>
                                <br><span>Source</span>
                            </td>
                            <td>
                                <input type="hidden" id="flightID" name="flightID" value=<%=flight.getFlightID()%> />
                                <br><input type="text" name="source" id="source" value=<%=flight.getSource()%> maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Destination</span>
                            </td>
                            <td>
                                <br><input type="text" name="destination" id="destination" value=<%=flight.getDestination()%> maxlength="20" required /> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Departure</span>
                            </td>
                            <td>
                                <input type="date" name="depDate" id="depDate" value=<%=formatedDepartDate%> required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Arrival</span>
                            </td><td>
                                <input type="date" name="arrDate" id="arrDate" value=<%=formatedArriveDate%> required />               
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Fare</span>
                            </td> 
                            <td>
                                <br><input type ="number" name="fare" id="fare" value=<%=flight.getFare()%> step="any" maxlength="6" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>&nbsp;</span>
                            </td> 
                            <td>
                                <br><input type="hidden" name="action" value="updateFlight" />
                                <input type="submit" name="submit" value="Update Flight" />
                            </td>
                        </tr>
                    </table>    
                </form>
            </div>
        </div>

        <%@include file="/footer.jsp" %>
    </body>

</html>
