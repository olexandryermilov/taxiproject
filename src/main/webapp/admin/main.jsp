
Hi, ${admin.name}
<br>
<form action="controller" method="GET">
    <input type="hidden" name="command" value="users"/>
    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">
    Watch users list
</button>
</form>
<form action="controller" method="GET">
    <input type="hidden" name="command" value="taxitypes"/>
    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">
        Watch taxitypes list
    </button>
</form>
<form name="logoutForm" method="POST" action="controller" class="w3-right-align" >
        <input type="hidden" name="command" value="logout" />
        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-top">Log out</button>
</form>