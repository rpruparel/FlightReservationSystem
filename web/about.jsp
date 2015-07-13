<%-- 
    Document   : about
    Created on : 15 Sep, 2014, 8:57:39 PM
    Author     : Acer
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <title> Neal Adam's </title>
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
                        <a href='about.jsp'> About </a>

                        <div class="bodytext">
                            <h2> About Flight Reservation</h2>
                            <br>
                            <p> Our aim is to make your ticket booking experience easy.
                                <br>
                                <br> Our system is ranked no. 1 for ticket booking in entire USA.
                                <br>
                                <br>
                            </p>

                        </div>
                    </div>
                    <%@include file="/footer.jsp" %>
        </body>

        </html>