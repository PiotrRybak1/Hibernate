<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author List</title>
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
<table>
    <nav>
        <a href="${pageContext.request.contextPath}/books/form">Add New Book</a> |
        <a href="${pageContext.request.contextPath}/books/form/all">Book List</a> |
        <a href="${pageContext.request.contextPath}/authors/form">Add New Author</a> |
        <a href="${pageContext.request.contextPath}/authors/form/all">Author List</a> |
        <a href="${pageContext.request.contextPath}/publishers/form">Add New Publisher</a> |
        <a href="${pageContext.request.contextPath}/publishers/form/all">Publisher List</a>
    </nav>
    <caption>Authors</caption>
    <tr>
        <th>id</th>
        <th>first name</th>
        <th>last name</th>
        <th>books</th>
        <th>links</th>
    </tr>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td>${author.id}</td>
            <td>${author.firstName}</td>
            <td>${author.lastName}</td>
            <td>
                <c:forEach var="book" items="${author.books}">
                    <p>${book.id}. ${book.title}</p>
                </c:forEach>
            </td>
            <td>
                <form:form action="/authors/form/edit/${author.id}" method="post" style="display:inline;">
                    <input type="submit" value="Edit"/>
                </form:form>
                <form:form action="/authors/form/delete/${author.id}" method="post" style="display:inline;"
                           onClick="return confirm('Are you sure?')">
                    <input type="submit" value="Delete"/>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
