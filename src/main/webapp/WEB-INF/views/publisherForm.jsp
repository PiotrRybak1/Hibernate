<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Publisher Form</title>
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
<h2>Publisher Form</h2>
<form:form action="/publishers/form" modelAttribute="publisher" method="post">
    <form:hidden path="id" id="id"/>

    <label for="name">Name:</label>
    <form:input path="name" id="name"/>
    <form:errors path="name" cssClass="error"/><br><br>

    <label for="nip">NIP:</label>
    <form:input path="nip" id="nip"/>
    <form:errors path="nip" cssClass="error"/><br><br>

    <label for="regon">REGON:</label>
    <form:input path="regon" id="regon"/>
    <form:errors path="regon" cssClass="error"/><br><br>

    <button type="submit">Submit</button>
</form:form>
</body>
</html>

