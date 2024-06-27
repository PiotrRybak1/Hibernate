<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author Form</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<nav>
    <a href="${pageContext.request.contextPath}/books/form">Add New Book</a> |
    <a href="${pageContext.request.contextPath}/books/form/all">Book List</a> |
    <a href="${pageContext.request.contextPath}/authors/form">Add New Author</a> |
    <a href="${pageContext.request.contextPath}/authors/form/all">Author List</a> |
    <a href="${pageContext.request.contextPath}/publishers/form">Add New Publisher</a> |
    <a href="${pageContext.request.contextPath}/publishers/form/all">Publisher List</a>
</nav>
<h2>Author Form</h2>
<form:form action="${pageContext.request.contextPath}/authors/form" modelAttribute="author" method="post">
    <form:hidden path="id" id="id"/>

    <label for="firstName">First Name:</label>
    <form:input path="firstName" id="firstName"/>
    <form:errors path="firstName" cssClass="error"/><br><br>

    <label for="lastName">Last Name:</label>
    <form:input path="lastName" id="lastName"/>
    <form:errors path="lastName" cssClass="error"/><br><br>

    <label for="pesel">PESEL:</label>
    <form:input path="pesel" id="pesel"/>
    <form:errors path="pesel" cssClass="error"/><br><br>

    <label for="email">Email:</label>
    <form:input path="email" id="email"/>
    <form:errors path="email" cssClass="error"/><br><br>

    <button type="submit">Submit</button>
</form:form>
</body>
</html>

