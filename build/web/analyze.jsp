

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

            <div class="bodytext">        
                <form action="CatalogController" method="get">
                    <br>
                    <br>
                    <table>
                        <tr>
                            <td>
                                <br><span>Source:</span>
                            </td>
                            <td>
                                <br><input type="text" name="source" id="source" placeholder="Enter source" maxlength="15" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Destination:</span>
                            </td>
                            <td>
                                <br><input type="text" name="destination" id="destination" placeholder="Enter destination" maxlength="15" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br><span>Date</span>
                            </td>
                            <td>
                                <br><input type="date" name="date" id="date" maxlength="15" required />
                            </td>
                        </tr>
                        <tr>
                            <td><br>
                                <br><span>&nbsp;</span>
                            </td>
                            <td><br>
                                <input type="submit" name="action" value="Search" />
                            </td>
                        </tr>
                    </table>    
                </form>
            </div>
        </div>

        <%@include file="/footer.jsp" %>
    </body>

</html>
