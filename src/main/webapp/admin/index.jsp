<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<c:choose>
    <c:when test="${empty admin}">
        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">
                <button class="w3-btn w3-green w3-round-large w3-margin-bottom" onclick="location.href='/taxiproject/admin/login.jsp'">Sign in</button>
            </div>
        </div>
    </c:when>
    <c:when test="${not empty admin}">
        <c:import url="main.jsp"/>
    </c:when>
</c:choose>

</body>
</html>