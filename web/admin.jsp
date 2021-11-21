<%-- 
    Document   : admin
    Created on : Jun 16, 2021, 4:21:50 PM
    Author     : Minh Hoang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
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

        <h1>Admin page</h1>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            Hello ${sessionScope.LOGIN_USER.fullName}
           <a href="LogoutController">Logout</a>
        </c:if>
        <label class="tl"> Thể loại :</label>
        <a class="cate" href="MainController?action=admin">ALL</a>
        <c:forEach items="${requestScope.LIST_CATEGORY}" var="cate">
            <a class="cate" href="GetBookByCateIDADController?cateID=${cate.categoryID}">${cate.categoryName}</a>
        </c:forEach>
        <form action="MainController" method="post">
            Name: <input type="text" name="txtName" value="">     
            Range of money from: <input type="text" name="from" id="from" value="" />
            To: <input type="text" name="to" id="to"  value="" /> 
            <input type="submit" name="action" value="SearchAD"/>
            <font color="red" >${sessionScope.ERROR} </font>
        </form><br>
      <a href="MainController?action=insertBook">Insert new Book</a>

        <table border="1">
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Author</th>
                    <th>Type of book</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Quantity</th>
                    <th>Import Date</th>

                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.LIST_BOOK}" var="o">


                    <tr>
                        <td>${o.bookID}</td>
                        <td>${o.title}</td>
                        <td><img src="image/${o.image}" width="80" height="150"> </td>
                        <td>${o.description}</td>
                        <td>${o.author}</td>
                        <td>${o.categoryID}</td>
                        <td>
                            ${o.price}
                        </td>

                        <td>
                            ${o.status}
                        </td>
                        <td>
                            ${o.quantity}
                        </td>
                        <td>
                            ${o.importDate}
                        </td>
                        <td>
                            <c:if test="${o.status eq 'Active'}">
                              <a href="MainController?action=delete&&bookID=${o.bookID}" onclick="return confirm('Are you sure delete Book?')">Delete</a>
                            </c:if>
                        </td>
                        <td> 
                           <a href="MainController?action=viewUpdate&&bookID=${o.bookID}" onclick="return confirm('Are you sure update Book?')"> Update </a>
                        </td>


                    </tr>


                </c:forEach>
            </tbody>
        </table>
        <c:if test="${requestScope.NO_DATA != null}">
            <script>
                alert('${requestScope.NO_DATA}');
            </script>
        </c:if>
        <c:if test="${requestScope.SUCCESS != null}">
            <script>
                alert('${requestScope.SUCCESS}');
            </script>
        </c:if>
        <c:if test="${requestScope.ERROR_SEARCH != null}">
            <script>
                alert('${requestScope.ERROR_SEARCH}');
            </script>
        </c:if>
    </body>
</html>