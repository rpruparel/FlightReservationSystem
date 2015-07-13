<%@page import="Reservation.model.Ticket"%>
    <%@page import="java.util.List"%>
        <%@page import="java.text.SimpleDateFormat"%>
            <%@page import="Reservation.model.Customer"%>
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
                                                <div class="Headers">
                                                    <%@include file="/header.jsp" %>
                                                </div>
                                                <%@include file="/user-navigation.jsp" %>
                                                    <%@include file="/site-navigation.jsp" %>
                                                        <div class="Main">
                                                            <a href='index.jsp'> Home </a> >
                                                            <a href='cart.jsp'> My Cart </a> >
                                                            <a href='order.jsp'> Order </a>
                                                            <div class="bodytext">
                                                                <%  float cartTotal = 0;
                List <Ticket> cartList = null;
                String messagePaid = "";
                messagePaid = (String) request.getParameter("message");
                
                if(messagePaid==null){
                    messagePaid="";
                }
                    Cart cart = (Cart) session.getAttribute("theShoppingCart");
                    if (cart != null) 
                    {
                        cartList = (List<Ticket>) cart.getItems();
                    }
                                 
                Order currentOrder = (Order) session.getAttribute("currentOrder");
                if(currentOrder!=null && cartList != null && cart.getCount() != 0)
                {   
                    Customer user = (Customer) currentOrder.getCustomer();
                    if (user != null) 
                    { 
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/YYYY HH:mm:ss");
                String date = dateFormat.format(currentOrder.getOrderCreated());
                %>
                                                                    <p>
                                                                        <b>
                        Invoice:
                    </b>
                                                                        <br> Date:
                                                                        <%=date%>
                                                                            <br>
                                                                            <u>
                    Ship to/ Bill to
                </u>
                                                                            <br>
                                                                            <%=user.getName()%>
                                                                                <br>
                                                                                <%=user.getStreet()%>
                                                                                    <br>
                                                                                    <%=user.getCity()%>,&nbsp;
                                                                                        <%=user.getState()%>
                                                                                            <br>
                                                                                            <%=user.getZipCode()%>
                                                                    </p>
                                                                    <% }
                                

               if (cartList != null && cart.getCount() != 0)  {%>
                                                                        Your Cart:
                                                                        <hr>
                                                                        <table class="Table">
                                                                            <tr>
                                                                                <td>
                                                                                    <b>
                                Item
                            </b>
                                                                                </td>
                                                                                <td>
                                                                                    <b>
                                Price
                            </b>
                                                                                </td>
                                                                                <td>
                                                                                    <b>
                                Quantity
                            </b>
                                                                                </td>
                                                                                <td>
                                                                                    <b>
                                Total
                            </b>
                                                                                </td>
                                                                            </tr>

                                                                            <% for (int p = 0; p < cartList.size(); p++) {%>
                                                                                <tr>
                                                                                    <td>
                                                                                        <%=cartList.get(p).getFlight().getSource()%>
                                                                                            <input type="hidden" name="flightList" value="<%=cartList.get(p).getFlight().getSource()%>" />
                                                                                    </td>
                                                                                    <td>
                                                                                        $
                                                                                        <%=cartList.get(p).getFlight().getFare()%>
                                                                                    </td>
                                                                                    <td>
                                                                                        <%=cartList.get(p).getQuantity()%>
                                                                                    </td>
                                                                                    <td>
                                                                                        <%double roundO = (double) (Math.round(cartList.get(p).getTotal() * 100.0) / 100.0);%>
                                                                                            $
                                                                                            <%=roundO%>
                                                                                                <%cartTotal = cartTotal + cartList.get(p).getTotal();%>
                                                                                    </td>
                                                                                </tr>
                                                                                <%}%>
                                                                                    <tr>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                            <b>
                                SubTotal
                            </b>
                                                                                        </td>
                                                                                        <td>
                                                                                            <%double roundOf = (double) (Math.round(cartTotal * 100.0) / 100.0);%>
                                                                                                $
                                                                                                <%=roundOf%>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                            <b>
                                Tax
                            </b>
                                                                                        </td>
                                                                                        <td>
                                                                                            $
                                                                                            <%=currentOrder.getTaxRate()%>
                                                                                                <%cartTotal = cartTotal + currentOrder.getTaxRate();%>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                        </td>
                                                                                        <td>
                                                                                            <b>
                                Total
                            </b>
                                                                                        </td>
                                                                                        <td>
                                                                                            <%double roundOff = (double) (Math.round(cartTotal * 100.0) / 100.0);%>
                                                                                                $
                                                                                                <%=roundOff%>
                                                                                        </td>
                                                                                    </tr>
                                                                        </table>
                                                                        <hr>
                                                                        <% if(messagePaid.equals("Paid in full")){ %>
                                                                            <span><b>Congratulations!, your order is placed successfully.<br>
                        Order amount is paid in full.</b></span>
                                                                            <%  Cart theShoppingCart = (Cart) session.getAttribute("theShoppingCart");
                    theShoppingCart.emptyCart();
                } else{%>
                                                                                <div class="bodytextleft">
                                                                                    <form action="cart.jsp" method="GET">
                                                                                        <table>
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <input type="Submit" value="Back to cart" />
                                                                                                </td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </form>
                                                                                    <form action='payment.jsp' method='GET'>
                                                                                        <table>
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <input type="Submit" value="Purchase" />
                                                                                                </td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </form>
                                                                                </div>
                                                                                <%}}} else {
                response.sendRedirect("search.jsp");
                return;
                }%>
                                                            </div>
                                                        </div>
                                                        <%@include file="/footer.jsp" %>
                                            </body>

                                            </html>