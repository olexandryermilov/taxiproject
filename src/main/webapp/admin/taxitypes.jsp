<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <table class="w3-table-all w3-light-green">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Fare</td>
                <td>To Edit</td>
            </tr>
            <jsp:useBean id="taxitypes" type="java.util.List<com.yermilov.domain.TaxiType>" scope="request"/>

            <c:forEach var="clienttype" items="${taxitypes}"><tr>
                <td>${clienttype.taxiTypeId}</td>
                <td>${clienttype.taxiTypeName}</td>
                <td>${clienttype.fare}</td>
                <td>
                    <form action="updateTaxiType.jsp" method="post">
                        <input type="hidden" name="taxitypeid" value="${clienttype.taxiTypeId}" >
                        <input type="hidden" name="taxitypename" value="${clienttype.taxiTypeName}" />
                        <input type="hidden" name="fare" value="${clienttype.fare}" />
                        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Edit taxitype</button>
                    </form>
                </td>
            </tr></c:forEach>
        </table>
        <form action="addTaxiType.jsp" method="get">
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Add taxitype</button>
        </form>
        ${errorMessage}
        </form>

        <br><br>
    </div>
</div>
</body>
</html>

