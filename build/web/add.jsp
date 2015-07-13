

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
            <a href='index.jsp'> Home </a> > <a href='add.jsp'> Add Flight </a><br><br>

            <div class="bodytext">
                <b>
                    Add Flight
                </b>
                <hr>
                 <%String message = null;
                message = request.getParameter("message");
                

                if(message!=null && message.equals("addSuccess")){
                    message="Flight Add Successfully.";
                %>
                <p><%=message%></p>
                <%}
                if(message!=null && message.equals("error")){
                    message="Flight already exist with this Flight ID.";
                %>
                <p><%=message%></p>
                <%}%>
                <form action="AdminController" method="get">
                    <br>
                    <br>
                    <table>
                        <tr>
                            <td>
                                <span>Flight ID</span>
                            </td>
                            <td>
                                <input type="text" id="flightID" name="flightID" maxlenght="5" value=""required />

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Source</span>
                            </td>
                            <td>
                                <br><input type="text" name="source" id="source" maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Destination</span>
                            </td>
                            <td>
                                <br><input type="text" name="destination" id="destination" maxlength="20" required /> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Departure</span>
                            </td>
                            <td>
                                <input type="date" name="depDate" id="depDate" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Arrival</span>
                            </td><td>
                                <input type="date" name="arrDate" id="arrDate" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Fare</span>
                            </td> 
                            <td>
                                <br><input type ="number" name="fare" id="fare" step="any" maxlength="6" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>&nbsp;</span>
                            </td> 
                            <td>
                                <br><input type="hidden" name="action" value="addFlight" />
                                <input type="submit" name="submit" value="Add Flight" />
                            </td>
                        </tr>
                    </table>    
                </form>
            </div>
        </div>

        <%@include file="/footer.jsp" %>
    </body>

</html>
