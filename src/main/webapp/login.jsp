<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="styles/w3.css">
    <title>Login | TaxiSystem</title>
</head>
<body class="w3-sand">
<div class="w3-container w3-green w3-opacity ">
    <h1>Taxi system</h1>
</div>
${empty user}
<div class="w3-card-4">
    <div class="w3-container ">
        <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        <div class="w3-text-black">Login:<br/></div>
        <input type="text" name="email" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
        <br/>Password:<br/>
        <input type="password" name="password" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
        <br/>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Log in</button>
        <div class="w3-text-red">${errorMessageLogin}<br/></div>
    </form>
    </div>
</div>
<div class="w3-container w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-green w3-round-large w3-border" onclick="location.href='/taxiproject'">Back to main</button>
</div>

</body>
</html>
