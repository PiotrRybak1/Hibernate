<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Form</title>
</head>
<body>
<h2>Student Form</h2>
<form:form method="post" modelAttribute="student">

    <label for="firstName">FirstName:</label>
    <form:input path="firstName" name="firstName" id="firstName" required="true"/><br><br>

    <label for="lastName">LastName:</label>
    <form:input path="lastName" name="lastName" id="lastName" required="true"/><br><br>
    <label for="Male">Male</label>
    <form:radiobutton path="gender" value="M" id="Male" required="true"/><br><br>
    <label for="Female">Female</label>
    <form:radiobutton path="gender" value="F" id="Female" required="true"/><br><br>
    <label for="select">Country</label>
    <form:select id = "select" path="country" items="${countries}"/><br><br>
    <label for="notes">Notes</label>
    <form:textarea path="notes" rows="3" cols="20" id = "notes"/><br><br>
    <label for="mailingList">mailingList</label>
    <form:checkbox path="mailingList" id = "mailingList"/><br><br>
    <label for="programmingSkills">ProgrammingSkills</label>
    <form:select path="programmingSkills" id="programmingSkills" items="${programmingSkills}" multiple="true"/><br><br>

    Running: <form:checkbox path="hobbies"
                        value="running"/>
    Programming: <form:checkbox path="hobbies"
                         value="programming"/>
    Reading books: <form:checkbox path="hobbies"
                         value="reading books"/>
    <button type="submit">Submit</button>

</form:form>
</body>
</html>
