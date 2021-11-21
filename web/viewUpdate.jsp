<%-- 
    Document   : viewUpdate
    Created on : Jun 19, 2021, 10:36:55 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="b" value="${requestScope.BOOK}"/>
        <c:set var="error" value="${requestScope.ERROR_CREATE}"/>
        <a href="MainController?action=admin"> Back to admin page </a> <br>
        <form action="MainController" method="POST" enctype="multipart/form-data">

            Book ID: <input type="text" name="bookID" value="${b.bookID}" readonly required/> <font color="red">${error.bookIDError}</font> <br> 
            Title: <input type="text" name="title" value="${b.title}"/><font color="red">${error.titleError}</font> <br>
            Price: <input type="text" name="price" value="${requestScope.PRICE}"/><font color="red">${error.priceError}</font> <br>
            Author: <input type="text" name="author" value="${b.author}"/><font color="red">${error.authorError}</font> <br>
            Quantity: <input type="text" name="quantity" value="${requestScope.QUANTITY}"/><font color="red">${error.quantityError}</font> <br>
            Photo: <input type="file" name="file"><br>
            Description: <textarea name="description" rows="4" cols="50">${b.description}</textarea><font color="red">${error.descriptionError}</font>  <br>
            Status (Active or Inactive):<input type="text" name="status" value="${b.status}"/><font color="red">${error.statusError}</font> <br>
            ImportDate (dd/MM/yyyy): <input type="text" name="importDate" value="${requestScope.IMPORT}"/><br>
            Category:
            <select name="category">
                <option value="${b.categoryID}">${requestScope.cateName}</option>
                <c:forEach items="${requestScope.LIST_CATEGORY}" var="cate">
                    <option value="${cate.categoryID}">${cate.categoryName}</option>
                </c:forEach>
            </select><br>
            <input type="submit" name="action" value="Update">
        </form>
    </body>
</html>
