<%@page import="Reservation.model.Ticket"%>
    <%@page import="java.util.List"%>
        <%@page import="java.text.SimpleDateFormat"%>
            <%@page import="Reservation.model.Cart"%>
                <%@page import="Reservation.model.Cart"%>
                    <%@page import="Reservation.model.Order"%>
                        <%@page import="java.util.ArrayList"%>
                            <%@page import="java.text.SimpleDateFormat"%>
                                <%@page import="java.util.Date"%>
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
                                            <% Customer customer = (Customer) session.getAttribute("theUser");
        List<Ticket> cartList = null;

                            Cart cart = (Cart) session.getAttribute("theShoppingCart");
                            if (cart != null) {
                                cartList = (List<Ticket>) cart.getItems();
                            }
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        if(customer == null || currentOrder == null || cartList == null || cart.getCount() == 0) {
            response.sendRedirect("search.jsp");
            return;
        }%>
                                                <div class="Headers">
                                                    <%@include file="/header.jsp" %>
                                                </div>
                                                <%@include file="/user-navigation.jsp" %>
                                                    <%@include file="/site-navigation.jsp" %>
                                                        <div class="Main">

                                                            <a href='index.jsp'> Home </a> >
                                                            <a href='cart.jsp'> My Cart </a> >
                                                            <a href='order.jsp'> Order </a> >
                                                            <a href='payment.jsp'> Payment </a>
                                                            <div class="bodytext">
                                                                <br>
                                                                <b>
                    Enter your payment information:
                </b>
                                                                <form action="OrderController" method="post">
                                                                    <hr>
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                <span>Credit Card Type</span>
                                                                            </td>
                                                                            <td>
                                                                                <select name="creditCard" id="creditCard">
                                                                                    <option value="1">Visa</option>
                                                                                    <option value="1">MasterCard</option>
                                                                                </select>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <span>Credit Card Number</span>
                                                                            </td>
                                                                            <td>
                                                                                <input type="number" id="ccnumber" name="ccnumber" maxlength="16" size="16" required />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <span>Expiration Date</span>
                                                                            </td>
                                                                            <td>
                                                                                <input type="number" name="expiryDate" id="expiryDate" maxlength="4" size="4" required />
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <span>CVV (3 digit pin)</span>
                                                                            </td>
                                                                            <td>
                                                                                <input type="password" id="cvv" name="cvv" maxlength="3" size="3" required />
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                    <hr>
                                                                    <%  float cartTotal = 0;
                if(currentOrder!=null && cartList != null && cart.getCount() != 0)
                {   
                    Customer theUser = (Customer) currentOrder.getCustomer();
                    if (theUser != null) 
                    { 
                        cartTotal = (currentOrder.getTotalCost() + currentOrder.getTaxRate());
                    }
                }
                %>
                                                                        Your card will charged with a total of
                                                                        <%=cartTotal%>
                                                                            <br>
                                                                            <br>
                                                                            <div class="bodytextleft">
                                                                                <input type="hidden" name="action" id="action" value="confirmOrder" />
                                                                                <input type="submit" value="Confirm Payment" name="submit" />
                                                                            </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                        <%@include file="/footer.jsp" %>
                                        </body>

                                        </html>