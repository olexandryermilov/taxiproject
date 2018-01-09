<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration | TaxiSystem</title>
	<link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-sand">
<div class="w3-container w3-green w3-opacity ">
	<h1>Taxi system</h1>
</div>
<div class="w3-card-4">
	<div class="w3-container ">
	<form name ="RegistrationForm" action="controller" method="POST">
        <input type="hidden" name="command" value="registration" />
		Email:<input type ="email" name="email" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
		<br/>
		Password:<input type ="password" name="password" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
		<br/>
		Name:<input type ="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
		<br/>
		Surname:<input type ="text" name="surname" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"/>
		<br/>
		<button class="w3-btn w3-green w3-round-large w3-margin-bottom" type="submit">Sign up</button>
	</form>
	</div>
</div>
<div class="w3-container w3-opacity w3-right-align w3-padding">
	<button class="w3-btn w3-green w3-round-large w3-border" onclick="location.href='/taxiproject'">Back to main</button>
</div>

</body>
</html>