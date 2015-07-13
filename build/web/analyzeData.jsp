

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
            <a href='index.jsp'> Home </a> > <a href='search.jsp'> Search </a>
<br>
            <br>
            <div class="bodytext">  

                <b>
                    Destination-wise Ticket Sale and Revenue:
                </b>
                <hr>
                <%  
                    String message = null;
                message = request.getParameter("message");
                

                if(message!=null && message.equals("addSuccess")){
                    message="Flight Add Successfully.";
                %>
                <p><%=message%></p>
                <%}
                    String destination = (String) request.getAttribute("destination");
                    List<Flight> flightGroup = new ArrayList<Flight>();
                    flightGroup = (List<Flight>) request.getAttribute("flightGroup");
                    StringBuilder options =new StringBuilder();
                    options.append("<select name='destination' id='destination' required>");
                    for(int n=0; n<flightGroup.size();n++){
                        Flight flight = flightGroup.get(n);
                        if(destination!=null){
                            if(destination.equals(flight.getDestination()))
                            {
                                 options.append("<option value='"+flight.getDestination()+"' selected>"+flight.getDestination()+"</option>");
                            }
                            else{
                                 options.append("<option value='"+flight.getDestination()+"'>"+flight.getDestination()+"</option>");
                            }
                        }else{
                        options.append("<option value='"+flight.getDestination()+"'>"+flight.getDestination()+"</option>");
                        }
                    }
                    options.append("</select>");

                    String optionsOutput = options.toString();
                %>
                <form action="AnalystController" method="get">
                    
                    <table>
                        <tr>
                            <td>
                                <br><%=optionsOutput%>
                            </td>
                            <td><br>
                                <input type="hidden" name="action"  value="analyzeDestination" />
                                <input type="submit" value="Analyze" />
                            </td>
                        </tr>
                    </table>    
                </form>
                            <%if(request.getAttribute("totalPassengers")!=null ||  request.getAttribute("totalRevenue")!=null){
                            int totalPassengers = (Integer) request.getAttribute("totalPassengers");
                            float totalRevenue = (Float)request.getAttribute("totalRevenue");
                            %>
                            <hr>
                            <table>
                                <tr>
                                    <th>
                                        Total Tickets Sold
                                    </th>
                                    <th>
                                        Total Revenue Generated
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <%=totalPassengers%>
                                    </td>
                                    <td><%double roundO = (double) (Math.round(totalRevenue * 100.0) / 100.0);%>
                                        $<%=roundO%>
                                    </td>
                                </tr>
                            </table>
                            
                            <%}%>
            </div>
                            
        </div>

        <%@include file="/footer.jsp" %>
    </body>

</html>
