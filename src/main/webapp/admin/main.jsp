
Hi, ${admin.name}
<br>
<form action="controller" method="GET">
    <input type="hidden" name="command" value="users"/>
    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">
    Watch users list
</button>
</form>
<div class="w3-container w3-green w3-opacity ">
    <form name="logoutForm" method="POST" action="controller" class="w3-right-align" >
        <input type="hidden" name="command" value="logout" />
        <label class="w3-left-align"><h1>Taxi system</h1></label>
        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-top">Log out</button>
    </form>
</div>
