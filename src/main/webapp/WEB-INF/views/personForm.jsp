<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Person Form</title>
</head>
<body>
<h2>Person Form</h2>
<form:form method="post" modelAttribute="person">

    <label for="login">Login:</label>
    <form:input path="login" name="login" id="login" required="true"/><br><br>

    <label for="password">Password:</label>
    <form:password path="password" name="password" id="password" required="true"/><br><br>

    <label for="email">Email:</label>
    <form:input path="email" type="email" name="email" id="email" required="true"/><br><br>

    <button type="submit">Submit</button>

</form:form>
</body>
</html>

