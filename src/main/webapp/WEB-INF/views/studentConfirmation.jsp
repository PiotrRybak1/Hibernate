<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Confirmation</title>
</head>
<body>
<h2>Student Confirmation</h2>
<p>First Name: ${student.firstName}</p>
<p>Last Name: ${student.lastName}</p>
<p>Gender: ${student.gender}</p>
<p>Country: ${student.country}</p>
<p>Notes: ${student.notes}</p>
<p>Mailing List: ${student.mailingList ? "Yes" : "No"}</p>
<p>Programming Skills: ${student.programmingSkills}</p>
<p>Hobbies: ${student.hobbies}</p>
</body>
</html>
