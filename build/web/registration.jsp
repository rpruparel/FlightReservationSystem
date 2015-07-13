
<%@page import="java.util.List"%>
<%@page import="Reservation.model.Cart"%>
<%@page import="Reservation.model.Ticket"%>
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
            <a href='index.jsp'> Home </a>
            >
            <a href='registration.jsp'> Registration </a>
            <br>
            <br>
            <div class="bodytext">
                
                <b>
                    Registration
                </b>
                <hr>
                 <%String message = null;
                message = request.getParameter("message");
                

                if(message!=null && message.equals("userExists")){
                    message="User with the specified Username already exists.";
                %>
                <p><%=message%></p>
                <%}%>
                <form action="OrderController?action=register" method="post">
                     <table>
                        <tr>
                            <td>
                                <span><b>Already Registered?</b></span>
                            </td>
                            <td>
                                <a href="login.jsp">Login Here</a>
                            </td>
                        </tr>
                    </table>
                    <hr>
                    <table>
                        <tr>
                            <td>
                                <br><span>Username:</span>
                            </td>
                            <td>
                                <br><input type="email" name="username" id="username" placeholder="Enter email"  maxlength="30" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Password:</span>
                            </td>
                            <td>
                                <br><input type="password" name="passcard" id="passcard" placeholder="Enter password"  maxlength="15" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Name:</span>
                            </td>
                            <td>
                                <br><input type="text" name="name" id="name" placeholder="Enter name"  maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Contact Number</span>
                            </td>
                            <td>
                                <br><input type="number" name="contactNumber" id="contactNumber" placeholder="Enter number"  maxlength="11" required  />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Street:</span>
                            </td>
                            <td>
                                <br><input type="text" name="street" id="street" placeholder="Enter address"  maxlength="30" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>City:</span>
                            </td>
                            <td>
                                <br><input type="text" name="city" id="city" placeholder="Enter city"  maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>State:</span>
                            </td>
                            <td>
                                <br><input type="text" name="state" id="state" placeholder="Enter state"  maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Zip Code:</span>
                            </td>
                            <td>
                                <br><input type="text" name="zipCode" id="zipCode" placeholder="Enter zip code"  size="6" maxlength="11" required />
                            </td>
                        </tr>
                        <tr>
                            <td><br>
                                <br><span>&nbsp;</span>
                            </td>
                            <td><br>
                                <input type="submit" value="Register" />
                            </td>
                        </tr>
                    </table>    
                </form>  
        </div>
    </div>
    <%@include file="/footer.jsp" %>
</body>

</html>