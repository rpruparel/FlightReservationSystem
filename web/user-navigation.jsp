<%@page import="Reservation.model.Employee"%>
    <%if(session.getAttribute("userTypeID")!=null){
    int userTypeID = (Integer) session.getAttribute("userTypeID");
    if(userTypeID==1){
%>
        <div class='UserSpecific'>
            <ul>
                <li>
                    <a class="stylea" href='search.jsp'>Search</a>
                </li>
                <li>
                    <a class="stylea" href='cart.jsp'>My Cart</a>
                </li>
                <li>
                    <a class="stylea" href='OrderController?action=viewOrders'>My Orders</a>
                </li>
                <li>
                    <a class="stylea" href='editDetails.jsp'>Account</a>
                </li>
            </ul>
        </div>
        <%}else if(userTypeID==2){
Employee employee = (Employee) session.getAttribute("theUser");
int employeeType = (Integer) employee.getEmployeeType();
if(employeeType==1){
%>
            <div class='UserSpecific'>
                <ul>
                    <li>
                        <a class="stylea" href='add.jsp'>Add Flight</a>
                    </li>
                    <li>
                        <a class="stylea" href='AdminController?action=viewFlight'>Manage Flights</a>
                    </li>
                    <li>
                        <a class="stylea" href='AdminController?action=viewOrders'>View Orders</a>
                    </li>
                </ul>
            </div>
            <%}else if (employeeType==2){%>
                <div class='UserSpecific'>
                    <ul>
                        <li>
                            <a class="stylea" href='AnalystController?action=loadOptions'>Destination-wise</a>
                        </li>
                        <li>
                            <a class="stylea" href='analyzeByMonth.jsp'>Month-wise</a>
                        </li>
                    </ul>
                </div>
                <%}}}
else{%>
                    <div class='UserSpecific'>
                        <ul>
                            <li>
                                <a class="stylea" href='login.jsp'>Sign In</a>
                            </li>
                            <li>
                                <a class="stylea" href='search.jsp'>Search</a>
                            </li>
                            <li>
                                <a class="stylea" href='cart.jsp'>My Cart</a>
                            </li>
                        </ul>
                    </div>
                    <%}%>