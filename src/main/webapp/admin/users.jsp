<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
            <table class="w3-table-all w3-light-green">
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Surname</td>
                    <td>Email</td>
                    <td>To Delete</td>
                    <td>Register as driver</td>
                    <td>Add car to driver</td>
                </tr>
                <jsp:useBean id="users" type="java.util.List<com.yermilov.domain.User>" scope="request"/>

                <c:forEach var="user" items="${users}"><tr>
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td><form action="controller" method="post">
                        <input type="hidden" name="command" value="delete" />
                        <input type="hidden" name="userid" value=${user.userId} >
                        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Delete user</button>
                    </form></td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="registerDriver"/>
                            <input type="hidden" name="userid" value=${user.userId} >
                            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Make a Driver</button>
                        </form>
                    </td>
                    <td>
                        <form action="addCar.jsp" method="get">
                            <input type="hidden" name="userid" value=${user.userId} >
                            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Add car</button>
                        </form>
                    </td>
                </tr></c:forEach>
            </table>
        <c:if test="${param.pageNumber>1}"><a href="controller?command=users&pageNumber=${param.pageNumber-1}">Previous page</a></c:if>
        <c:if test="${param.pageNumber<pageAmount}"><a href="controller?command=users&pageNumber=${param.pageNumber+1}">Next page</a></c:if>
        <label class="w3-text-red">${errorMessage}</label>
        </form>

        <br><br>
        <!--{users}></!-->
    </div>
</div>
</body>
</html>

