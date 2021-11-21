<%-- 
    Document   : home.jsp
    Created on : Jun 10, 2021, 10:48:11 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <style>
            a.cate:link, a.cate:visited {
                background-color: white;
                color: black;
                border: 2px solid green;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }

            a.cate:hover, a.cate:active {
                background-color: green;
                color: white;
            }
            h1{
                text-align: center;
                color: black;             
            }
            label.tl{
                font-size: 150%;
            }
        </style>
    </head>

    <body>

        <h1>Book Store</h1>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <a class="cate"  href="MainController?action=loginUser">Login</a>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            Hello ${sessionScope.LOGIN_USER.fullName}
           <a class="cate" href="LogoutController">Logout</a>
           <a class="cate"  href="cart.jsp">Cart</a>
           <a class="cate" href="MainController?action=history">History</a>
        </c:if>
        <label class="tl"> Thể loại :</label>
        <c:forEach items="${requestScope.LIST_CATEGORY}" var="cate">
            <a class="cate" href="GetBookByCateIDController?cateID=${cate.categoryID}">${cate.categoryName}</a>
        </c:forEach>
        <form action="MainController" method="post">
            Name: <input type="text" name="txtName" value="">     
            Range of money from: <input type="text" name="from" id="from" value="" />
            To: <input type="text" name="to" id="to"  value="" /> 
            <input type="submit" name="action" value="Search"/>

        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Author</th>
                    <th>Type of book</th>
                    <th>Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.LIST_BOOK}" var="o">

                    <c:if test="${o.status eq 'Active' and o.quantity!=0 }">
                        <tr>

                            <td>${o.title}</td>
                            <td><img src="image/${o.image}" width="80" height="150"> </td>
                            <td>${o.description}</td>
                            <td>${o.author}</td>
                            <td>${o.categoryID}</td>
                            <td>
                                ${o.price}
                            </td>

                            <td>
                                <a href="MainController?action=addCart&&bookID=${o.bookID}">Add to cart</a>

                            </td>

                        </tr>
                    </c:if>

                </c:forEach>
            </tbody>
        </table>
        <c:if test="${requestScope.NO_DATA != null}">
            <script>
                alert('${requestScope.NO_DATA}');
            </script>
        </c:if>
        <c:if test="${requestScope.ERROR_SEARCH != null}">
            <script>
                alert('${requestScope.ERROR_SEARCH}');
            </script>
        </c:if>
        <c:if test="${requestScope.OUT != null}">
            <script>
                alert('${requestScope.OUT}');
            </script>
        </c:if>
             <c:if test="${requestScope.CHECK_OUT != null}">
            <script>
                alert('${requestScope.CHECK_OUT}');
            </script>
        </c:if>
    </body>
</html>
