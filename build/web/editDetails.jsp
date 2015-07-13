
<%@page import="Reservation.model.UserAccount"%>
<%@page import="java.util.List"%>
<%@page import="Reservation.model.Cart"%>
<%@page import="Reservation.model.Ticket"%>
<%@page import="Reservation.connection.UserAccountDB"%>
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
                <%
                int userTypeID = (Integer) session.getAttribute("userTypeID");
                Customer customer = (Customer) session.getAttribute("theUser");
                if(customer==null){
                    response.sendRedirect("login.jsp");
                    return;
                }
                int customerID = customer.getCustomerID();
                int userAccountID = customer.getUserAccountID();
                UserAccountDB userDB = new UserAccountDB();
                UserAccount userAccount = userDB.getUser(userAccountID);
                
                %>
                
                <b>
                    Update Details
                </b>
                <hr>
                <% String message = null;
                message = request.getParameter("message");
                

                if(message!=null && message.equals("updateSuccess")){
                    message="Details updated successfully.";
                %>
                <p><%=message%></p>
                <%}%>
                <form action="OrderController" method="post">
                    <table>
                        <tr>
                            <td>
                                <br><span>Username:</span>
                            </td>
                            <td>
                                <br><input type="email" name="username" id="username" value="<%=userAccount.getEmailAddress()%>" placeholder="Enter email"  maxlength="30" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Password:</span>
                            </td>
                            <td>
                                <br><input type="password" name="passcard" id="passcard" value="<%=userAccount.getPassword()%>" placeholder="Enter password"  maxlength="15" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Name:</span>
                            </td>
                            <td>
                                <br><input type="text" name="name" id="name" value="<%=customer.getName()%>" placeholder="Enter name"   maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Contact Number</span>
                            </td>
                            <td>
                                <br><input type="number" name="contactNumber" id="contactNumber" value="<%=customer.getContactNumber()%>" placeholder="Enter number"  maxlength="11" required  />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Street:</span>
                            </td>
                            <td>
                                <br><input type="text" name="street" id="street" value="<%=customer.getStreet()%>" placeholder="Enter address"  maxlength="30" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>City:</span>
                            </td>
                            <td>
                                <br><input type="text" name="city" id="city" value="<%=customer.getCity()%>" placeholder="Enter city"  maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>State:</span>
                            </td>
                            <td>
                                <br><input type="text" name="state" id="state" value="<%=customer.getState()%>" placeholder="Enter state"  maxlength="20" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Zip Code:</span>
                            </td>
                            <td>
                                <br><input type="text" name="zipCode" id="zipCode" value="<%=customer.getZipCode()%>" placeholder="Enter zip code"  maxlength="11" required />
                            </td>
                        </tr>
                        <tr>
                            <td><br>
                                <br><span>&nbsp;</span>
                            </td>
                            <td><br>
                                <input type="hidden" name="customerID" value=<%=customerID%> />
                                <input type="hidden" name="userAccountID" value=<%=userAccountID%> />
                                <input type="hidden" name="action" value="updateDetails" />
                                <input type="submit" value="Update Details" />
                            </td>
                        </tr>
                    </table>    
                </form>         
        </div>
    </div>
    <%@include file="/footer.jsp" %>
</body>

</html>