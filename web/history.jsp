<%-- 
    Document   : history
    Created on : Jun 22, 2021, 5:11:35 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <a href="MainController?action=user"> Back to home </a> <br>
        <form action="MainController" method="POST">
            Order Date(yyyy-MM-dd):<input type="text" name="orderDate" value="">
            <input type="submit" name="action" value="SearchH">
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Date Order</th>
                    <th>Total</th>

                    <th>More Detail</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.LIST_ORDER}" var="o">                
                    <tr>

                        <td>${o.orderID}</td>
                        <td>${o.dateOrder} </td>
                        <td>${o.total}</td>

                        <td>
                            <a href="MainController?action=moreDetail&&orderID=${o.orderID}">More Detail</a>

                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table> 
        <c:if test="${requestScope.ERROR_SEARCH != null}">
            <script>
                alert('${requestScope.ERROR_SEARCH}');
            </script>
        </c:if>

    </body>
</html>
