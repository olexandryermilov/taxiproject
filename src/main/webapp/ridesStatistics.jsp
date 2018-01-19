<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
            <table class="w3-table-all w3-light-green">
                <tr>
                    <td>Car number</td>
                    <td>Cost</td>
                    <td>Distance</td>
                    <td>Start time</td>
                    <td>End time</td>
                </tr>
                <jsp:useBean id="rides" type="java.util.List<com.yermilov.domain.Ride>" scope="request"/>
                <jsp:useBean id="carNumbers" type="java.util.List<java.lang.String>" scope = "request"/>
                <c:forEach var="ride" items="${rides}" varStatus="ridesCount"><tr>
                    <td>${carNumbers[ridesCount.count-1]}</td>
                    <td>${ride.cost}</td>
                    <td>${ride.distance}</td>
                    <td>${ride.rideStart}</td>
                    <td>${ride.rideFinish}</td>
                </tr></c:forEach>
            </table>
        <c:if test="${param.pageNumber>1}"><a href="controller?command=users&pageNumber=${param.pageNumber-1}">Previous page</a></c:if>
        <c:if test="${param.pageNumber<pageAmount}"><a href="controller?command=ridesStatistics&pageNumber=${param.pageNumber+1}">Next page</a></c:if>

        <label class="w3-text-red">${errorMessage}</label>

        </form>

        <br><br>
</div>
</body>
</html>


