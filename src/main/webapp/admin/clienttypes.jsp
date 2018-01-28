<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <table class="w3-table-all w3-light-green">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Discount</td>
                <td>Money spent</td>
                <td>To Edit</td>
            </tr>
            <jsp:useBean id="clienttypes" type="java.util.List<com.yermilov.domain.ClientType>" scope="request"/>

            <c:forEach var="clienttype" items="${clienttypes}"><tr>
                <td>${clienttype.clientTypeId}</td>
                <td>${clienttype.name}</td>
                <td>${clienttype.discount}</td>
                <td>${clienttype.moneySpent}</td>
                <td>
                    <form action="updateClientType.jsp" method="post">
                        <input type="hidden" name="clienttypeid" value="${clienttype.clientTypeId}" >
                        <input type="hidden" name="name" value="${clienttype.name}" />
                        <input type="hidden" name="discount" value="${clienttype.discount}" />
                        <input type="hidden" name="moneyspent" value="${clienttype.moneySpent}" />
                        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Edit clienttype</button>
                    </form>
                </td>
            </tr></c:forEach>
        </table>
        <form action="addClientType.jsp" method="get">
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Edit clienttype</button>
        </form>
        <label class="w3-text-red">${errorMessage}</label>
        </form>

        <br><br>
    </div>
</div>
</body>
</html>

