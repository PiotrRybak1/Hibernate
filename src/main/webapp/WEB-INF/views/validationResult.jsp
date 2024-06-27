<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Validation Results</title>
</head>
<body>
<h2>Validation Errors</h2>
<c:if test="${not empty violations}">
    <ul>
        <c:forEach var="violation" items="${violations}">
            <li>${violation.validatedClass}.${violation.property}: ${violation.message}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty violations}">
    <p>No validation errors found.</p>
</c:if>
</body>
</html>