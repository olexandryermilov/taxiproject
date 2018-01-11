<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="styles/w3.css">
    <title>TaxiSystem</title>
</head>
<body class="w3-sand">
<div class="w3-container w3-green w3-opacity ">
    <h1>Taxi system</h1>
</div>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="delete"/>
            <table class="w3-table-all w3-light-green">
                <tr>
                    <td>To Delete</td>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Surname</td>
                    <td>Email</td>
                </tr>
                <jsp:useBean id="users" type="java.util.List<com.yermilov.domain.User>" scope="request"/>

                <c:forEach var="user" items="${users}"><tr>
                    <td><input type="checkbox" name="toDelete" value=${user.userId}></td>
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                </tr></c:forEach>
            </table>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Delete</button>
        </form>

        <br><br>
        <!--{users}></!-->
    </div>
</div>
</body>
</html>

