<%-- 
    Document   : moreDetail
    Created on : Jun 22, 2021, 5:45:31 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MoreD Detail Page</title>
    </head>
    <body>
        <a href="MainController?action=user"> Back to home </a> <br>
        <table border="1">
            <thead>
                <tr>
                    <th>Detail ID</th>
                    <th>Price</th>
                    <th>quantity</th>

                    <th>Book Name</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.LIST_DETAIL}" var="o">                
                <tr>

                    <td>${o.detailID}</td>
                    <td>${o.price} </td>
                    <td>${o.quantity}</td>

                    <td>
                        ${o.book.title}

                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table> 


</body>
</html>
