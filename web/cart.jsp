<%-- 
    Document   : cart.jsp
    Created on : Jun 20, 2021, 6:56:44 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    </head>
    <body>
        <a href="MainController?action=user">Back to Home </a>


        <table border="1">

            <tr>
                <th>Book Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Amount</th>
                <th>Delete</th>                   
                <th>Update
                    <font color="green">If you want to change the quantity then enter here.</font></th>  
            </tr>


            <c:forEach items="${sessionScope.CART}" var="o">


                <tr>

                    <td>${o.book.title}</td>

                    <td>${o.price}</td>
                    <td><input type="text" value="${o.quantity}" name="quantity"> 
                    </td>
                    <td> <input type="text" value="${o.quantity*o.price}" name="amount" readonly="" />
                        <c:set var="amount" value="${o.quantity*o.price}"/>
                    </td>
                    <td>

                        <a href="MainController?action=deleteCart&&bookID=${o.book.bookID}" onclick="return confirm('Are you sure delete Item?')">Delete</a>
                    </td> 
                    <td>     <form action="MainController" method="POST">
                            <input type="hidden" name="bookID" value="${o.book.bookID}">                           
                            <input type="text" value="" name="quantity"> 
                            <font color="red">${requestScope.ERROR}</font>
                            <button name="action" value="updateCart">Update</button>
                        </form>

                    </td> 

                    <c:set var="total" value="${total+amount}"/>

                </tr>

            </c:forEach>


            <tr>
                <td  colspan="3">TOTAL:</td>
                <td>${total}</td>
            </tr>

        </table>

        <button style="margin-bottom:15px"><a href="MainController?action=checkOut&&userID=${sessionScope.LOGIN_USER.userID}&&total=${total}" >Buy</a></button>
        <c:if test="${requestScope.ERR != null}">
            <script>
                alert('${requestScope.ERR}');
            </script>
        </c:if>
    </body>

</html>
