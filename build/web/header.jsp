
<%@page import="Reservation.model.Employee"%>
<%@page import="Reservation.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

            <img src="images/Logo.jpg" alt="K Adam's Grooming" height="100" width="700">
            <span class="Signin">
                <%  String userName = null;
                    if(session.getAttribute("userTypeID")!=null){
                    int userTypeID = (Integer) session.getAttribute("userTypeID");
                    if(userTypeID==1){
                        Customer sessionUser = (Customer) session.getAttribute("theUser");
                        if (sessionUser != null) {
                            userName = sessionUser.getName();
                        }
                    }else if(userTypeID==2){
                        Employee sessionUser = (Employee) session.getAttribute("theUser");
                        if (sessionUser != null) {
                            userName = sessionUser.getName();
                        }
                    }
                    if (userName != null){%>
                    Hello&nbsp;<%=userName%>,&nbsp;&nbsp;&nbsp;&nbsp;<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="OrderController?action=logout">Log Out</a>
                    <%}else{%>
                    Not Signed In
                    <%}}else{%>
                    Not Signed In
                    <%}%>
            </span>

