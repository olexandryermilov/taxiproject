<%--
  Created by IntelliJ IDEA.
  User: Олександр
  Date: 15.01.2018
  Time: 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>

<div class="w3-card-4">
    <div class="w3-container ">
        <form name="addCarForm" method="POST" action="controller">
            <input type="hidden" name="command" value="addCar" />
            <input type="hidden" name="userid" value=${param.userid} >
            <div class="w3-text-black">Car number<br/></div>
            <input type="text" name="carnumber" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br/>Car type<br/>
            <input type="text" name="cartype" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br/>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Add Car</button>
            <label class="w3-text-red">${errorMessage}<br/></div>
        </form>
    </div>
</div>
<div class="w3-container w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-green w3-round-large w3-border" onclick="location.href='/taxiproject/admin/controller?command=users'">Back to users</button>
</div>
</body>
</html>
