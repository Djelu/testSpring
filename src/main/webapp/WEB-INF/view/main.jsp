<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <p>Hello ${user.name}!</p>
    <p>Your password is ${user.password}!</p>
    <p>Admin: ${user.admin}!</p>
    <p>Time: ${}</p>
</body>
</html>