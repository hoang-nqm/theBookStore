<%-- 
    Document   : error
    Created on : Mar 7, 2021, 9:32:09 PM
    Author     : nds72
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Something wrong</h1>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <a href="MainController">Back to Home </a>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER != null}">
            <c:if test="${sessionScope.LOGIN_USER.roleID eq '2'}">
                <a href="MainController?action=user">Back to Home </a>
            </c:if>
        </c:if>
                <c:if test="${sessionScope.LOGIN_USER != null}">
            <c:if test="${sessionScope.LOGIN_USER.roleID eq '1'}">
                <a href="MainController?action=admin">Back to Home  </a>
            </c:if>
        </c:if>
    </body>
</html>
