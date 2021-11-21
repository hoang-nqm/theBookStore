<%-- 
    Document   : viewInsert
    Created on : Jun 16, 2021, 5:24:47 PM
    Author     : Minh Hoang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <h5>INSERT BOOK</h5>
        <c:set var="error" value="${requestScope.ERROR_CREATE}"/>
        <c:set var="b" value="${requestScope.BOOK}"/>
        <a href="MainController?action=admin"> Back to admin page </a> <br>
        <form action="MainController" method="POST" enctype="multipart/form-data">
            Book ID: <input type="text" name="bookID" value="${b.bookID}"/> <font color="red">${error.bookIDError}</font> <br> 
            Title: <input type="text" name="title" value="${b.title}"/><font color="red">${error.titleError}</font> <br>
            Price: <input type="text" name="price" value="${requestScope.PRICE}"/><font color="red">${error.priceError}</font> <br>
            Author: <input type="text" name="author" value="${b.author}"/><font color="red">${error.authorError}</font> <br>
            Quantity: <input type="text" name="quantity" value="${requestScope.QUANTITY}"/><font color="red">${error.quantityError}</font> <br>
            Photo: <input type="file" name="file"><br>
            Description: <textarea name="description" rows="4" cols="50">${b.description}</textarea> <font color="red">${error.descriptionError}</font> <br>
            Category:
            <select name="category">
                <c:forEach items="${requestScope.LIST_CATEGORY}" var="cate">
                    <option value="${cate.categoryID}">${cate.categoryName}</option>
                </c:forEach>
            </select><br>
            <input type="submit" name="action" value="Insert">
        </form>
    </body>
</html>
