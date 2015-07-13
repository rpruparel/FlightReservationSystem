<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Reservation.model.Flight"%>
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
            <a href='index.jsp'> Home </a> > <a href='search.jsp'> Search </a>
<br>
            <br>
            <div class="bodytext">  

                <b>
                    Month & Year -wise Ticket Sale and Revenue:
                </b>
                <hr>
                <%  
                String selectOption[] = new String[13];
                for(int n=0; n<=12;n++){
                    selectOption[n]="";
                }
                String selectYear[] = new String[2];
                for(int n=0; n<2;n++){
                    selectYear[n]="";
                }

                if(request.getParameter("month")!=null){
                int month = Integer.parseInt(request.getParameter("month"));
                selectOption[month]="selected";
                if(request.getParameter("year")!=null){
                int year = Integer.parseInt(request.getParameter("year"));
                if(year==2014){
                selectYear[0]="selected";
                }else if(year==2015){
                selectYear[1]="selected";
                }
                }
                }
                
                String message = null;
                message = request.getParameter("message");
                
                if(message!=null && message.equals("error")){
                    message="No Data Found.";


                %>
                <p><%=message%></p>
                <%}%>    
                
               
                <form action="AnalystController" method="get">
                    
                    <table>
                        <tr>
                            <td>
                                <br><select name="month" id="month" required>
                                    <option value="1" <%=selectOption[1]%>>
                                        Jan
                                    </option>
                                    <option value="2" <%=selectOption[2]%>>
                                        Feb
                                    </option>
                                    <option value="3" <%=selectOption[3]%>>
                                        Mar
                                    </option>
                                    <option value="4" <%=selectOption[4]%>>
                                        Apr
                                    </option>
                                    <option value="5" <%=selectOption[5]%>>
                                        May
                                    </option>
                                    <option value="6" <%=selectOption[6]%>>
                                        Jun
                                    </option>
                                    <option value="7" <%=selectOption[7]%>>
                                        Jul
                                    </option>
                                    <option value="8" <%=selectOption[8]%>>
                                        Aug
                                    </option>
                                    <option value="9" <%=selectOption[9]%>>
                                        Sep
                                    </option>
                                    <option value="10" <%=selectOption[10]%>>
                                        Oct
                                    </option>
                                    <option value="11" <%=selectOption[11]%>>
                                        Nov
                                    </option>
                                    <option value="12" <%=selectOption[12]%>>
                                        Dec
                                    </option>
                                </select>&nbsp;&nbsp;
                                <select name="year" id="year" required>
                                    <option value="2014"  <%=selectYear[0]%>>
                                        2014
                                    </option>
                                    <option value="2015"  <%=selectYear[1]%>>
                                        2015
                                    </option>
                                </select>
                            </td>
                            <td><br>
                                <input type="hidden" name="action" value="analyzeMonthYear" />
                                <input type="submit" value="Analyze" />
                            </td>
                        </tr>
                    </table>    
                </form>
                            <%if(request.getAttribute("totalPassengers")!=null ||  request.getAttribute("totalRevenue")!=null){
                            int totalPassengers = (Integer) request.getAttribute("totalPassengers");
                            float totalRevenue = (Float)request.getAttribute("totalRevenue");
                            %>
                            <hr>
                            <table>
                                <tr>
                                    <th>
                                        Total Passengers
                                    </th>
                                    <th>
                                        Total Revenue
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <%=totalPassengers%>
                                    </td>
                                    <td><%double roundO = (double) (Math.round(totalRevenue * 100.0) / 100.0);%>
                                        $<%=roundO%>
                                    </td>
                                </tr>
                            </table>
                            <%}%>
            </div>         
        </div>
        <%@include file="/footer.jsp" %>
    </body>

</html>
