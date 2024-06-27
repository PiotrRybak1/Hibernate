
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>bookForm</title>
</head>
<body>
<h2>Book Form</h2>
<form:form action="/books/form" modelAttribute="book" method="post">
    <form:hidden path="id" id="id"/>

    <label for="title">Title:</label>
    <form:input path="title" id="title" required="true"/><br><br>
    <form:errors path="title" element="div" cssClass="error"/>

    <label for="description">Description:</label>
    <form:textarea path="description" id="description" rows="3"/><br><br>
    <form:errors path="description"  element="div" cssClass="error"/>

    <label for="rating">Rating:</label>
    <form:input path="rating" id="rating" type="number" required="true"/><br><br>
    <form:errors path="rating" element="div" cssClass="error"/>

    <label for="publisher">Publisher:</label>
    <form:select path="publisher.id" id="publisher" items="${publishers}" itemLabel="name" itemValue="id" required="true"/><br><br>
    <form:errors path="publisher.id" element="div" cssClass="error"/>

    <label for="authors">Authors:</label>
    <form:select path="authors" id="authors" items="${authors}" itemValue="id" itemLabel="fullName" multiple="true"/>
    <form:errors path="authors"  element="div" cssClass="error"/>

    <button type="submit">Submit</button>
</form:form>
</body>
</html>
