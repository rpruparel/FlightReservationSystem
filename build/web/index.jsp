<%-- 
    Document   : index
    Created on : 29 Aug, 2014, 7:22:48 PM
    Author     : Acer
--%>

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


        <div class="Right"> 
            <a href='index.jsp'> Home </a> >  

            <div class="bodytext">        
                <h2> Flight Reservation System </h2>
                <p> <h3>Booking tickets was never so easy!</h3>
                <b><i>Our offers:</i></b><br>

                20% Discount on tickets to Houston and New York!
                <br>
                <br>
                <b><i>Trending Destinations</i></b><br>

                Houston and New York are the most <br>trending destinations this season.<br>
                Group bookings now possible! 
                <br>
                <br>
                Happy Ticket Booking!!

                <span class="arrow previous"></span>
                <span class="arrow next"></span>
            </div>
        </div>

        <%@include file="/footer.jsp" %>
    </body>

</html>
