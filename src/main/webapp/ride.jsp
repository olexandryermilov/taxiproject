<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<c:choose>
    <c:when test="${empty cost}">
        <div class="w3-card-4">
            <div class="w3-container ">
                <form name="loginForm" method="POST" action="controller">
                    <input type="hidden" name="command" value="calculateCost" />
                    <div class="w3-text-black">From</div><br/>
                    <input type="text" name="from" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
                    <br/><div class="w3-text-black">To</div><br/>
                    <input type="text" name="to" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
                    <br/>
                    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Calculate cost</button>
                </form>
            </div>
        </div>
    </c:when>
    <c:when test="${not empty cost}">
        <c:import url="rideInformation.jsp"/>
    </c:when>
</c:choose>

</body>
</html>
