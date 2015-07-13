
<%@page import="Reservation.connection.CustomerDB"%>
<%@page import="Reservation.connection.EmployeeDB"%>
<%@page import="Reservation.model.Cart"%>
<%@page import="Reservation.model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <a href='orderlist.jsp'> Orders </a>

            <br>
            <div class="bodytext">
                <% 
                    List<Order> orderGroup = (List) request.getAttribute("theOrders");
                    if(orderGroup==null || orderGroup.size()==0){
                %>
                <br>
                <b>
                    Order List
                </b>
                <hr>
                <p>No Orders Found</p>
                <%}else {%>
                <br>
                <b>
                    Order List
                </b>
                <hr>
                    <table class="Table">
                        <tr>
                            <td>
                                <b>
                                    Order Number
                                </b>
                            </td>
                            <td>
                                <b>
                                    Customer
                                </b>
                            </td>
                            <td>
                                <b>
                                    Order Date
                                </b>
                            </td>
                            <td>
                                <b>
                                    Total
                                </b>
                            </td>
                        </tr>
                        <% for(int n = 0; n<orderGroup.size(); n++) {
                        CustomerDB userDB = new CustomerDB();
                        Order order = orderGroup.get(n); 
                        Customer user = userDB.getUser(order.getUserID(),1);
                        String fullName = user.getName();%>           
                        <tr>
                            <td>
                                <%=order.getOrderID()%>
                            </td>
                            <td>
                                <%=fullName%>
                            </td>
                            <td><%SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
                                String date = dateFormat.format(order.getOrderCreated());%>
                                <%=date%>
                            </td>
                            <td><%double roundO = (double) (Math.round((order.getTotalCost() + order.getTaxRate()) * 100.0) / 100.0);%>
                                $<%=roundO%>
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