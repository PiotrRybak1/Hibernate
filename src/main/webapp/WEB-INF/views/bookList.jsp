<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            padding: 0.2rem;
            border: 1px solid black;
            text-align: center;
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
<table>
    <caption>Books</caption>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>rating</th>
        <th>description</th>
        <th>publisher</th>
        <th>authors</th>
        <th>links</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.rating}</td>
            <td>${book.description}</td>
            <td>${book.publisher.name}</td>
            <td>
                <c:forEach var="author" items="${book.authors}">
                    <p>${author.fullName}</p>
                </c:forEach>
            </td>
            <td>
                <form:form action="/books/form/edit/${book.id}" method="post" style="display:inline;">
                    <input type="submit" value="Edit"/>
                </form:form>
                <form:form action="/books/form/delete/${book.id}" method="post" style="display:inline;"
                           onClick="return confirm('Are you sure?')">
                    <input type="submit" value="Delete"/>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

