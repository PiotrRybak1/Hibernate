<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Publisher List</title>
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
    <caption>Publishers</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>books</th>
        <th>links</th>
    </tr>
    <c:forEach var="publisher" items="${publishers}">
        <tr>
            <td>${publisher.id}</td>
            <td>${publisher.name}</td>
            <td>
                <c:forEach var="book" items="${publisher.books}">
                    <p>${book.id}. ${book.title}</p>
                </c:forEach>
            </td>
            <td>
                <form:form action="/publishers/form/edit/${publisher.id}" method="post" style="display:inline;">
                    <input type="submit" value="Edit"/>
                </form:form>
                <form:form action="/publishers/form/delete/${publisher.id}" method="post" style="display:inline;"
                           onClick="return confirm('Are you sure?')">
                    <input type="submit" value="Delete"/>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

