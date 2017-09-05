<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <style>
        <%@include file="../../resources/css/home.css"%>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <form:form method="post" commandName="user" action="check-user" class="box Login">
        <fieldset class="boxBody">
            <form:label path="name">Name</form:label>
            <form:input path="name"/>

            <form:label path="password">Password</form:label>
            <form:password path="password"/>
        </fieldset>
        <footer>
            <label>
                <input type="checkbox" tabindex="3"/>
                Keep me logged in
            </label>
            <input type="submit" class="btnLogin" value="Login" tabindex="4"/>
        </footer>
    </form:form>
</body>
</html>
