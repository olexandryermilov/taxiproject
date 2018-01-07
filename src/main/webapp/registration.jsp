<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration | TaxiSystem</title>
</head>
<body>
	<form name ="RegistrationForm" action="controller" method="POST">
        <input type="hidden" name="command" value="registration" />
		Email:<input type ="email" name="email"/>
		<br/>
		Password:<input type ="password" name="password"/>
		<br/>
		Name:<input type ="text" name="name"/>
		<br/>
		Surname:<input type ="text" name="surname"/>
		<br/>
		<input type="submit"/>
	</form>
</body>
</html>