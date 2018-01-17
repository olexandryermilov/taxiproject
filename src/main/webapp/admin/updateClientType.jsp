<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../header.jsp"/>

<div class="w3-card-4">
    <div class="w3-container ">

        <form name="updateClientTypeForm" method="POST" action="controller">
            <input type="hidden" name="command" value="updateClientType" />
            <input type="hidden" name="clienttypeid" value="${param.clienttypeid}" >
            <div class="w3-text-black">Clienttype name<br/></div>
            <input type="text" name="name" value="${param.name}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br/>Discount (0-100)<br/>
            <input type="text" name="discount" value="${param.discount}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br/>Minimum money spent<br/>
            <input type="text" name="moneyspent" value="${param.moneyspent}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
            <br/>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Edit clienttype</button>
            <div class="w3-text-red">${errorMessage}<br/></div>
        </form>
    </div>
</div>
<div class="w3-container w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-green w3-round-large w3-border" onclick="location.href='/taxiproject/admin/controller?command=taxitype'">Back to taxitypes</button>
</div>
</body>
</html>
