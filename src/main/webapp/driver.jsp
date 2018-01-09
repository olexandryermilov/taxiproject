<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="styles/w3.css">
    <title>Become a driver | TaxiSystem</title>
</head>
<body class="w3-sand">
<div class="w3-container w3-green w3-opacity ">
    <h1>Taxi system</h1>
</div>
<div class="w3-card-4">
    <div class="w3-container ">
        <form name ="DriverRegistrationForm" action="controller" method="POST">
            <input type="hidden" name="command" value="driverRegistration" />
            <button class="w3-btn w3-green w3-round-large w3-margin-bottom" type="submit">Sign up</button>
        </form>
    </div>
</div>
<div class="w3-container w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-green w3-round-large w3-border" onclick="location.href='/taxiproject'">Back to main</button>
</div>

</html>

