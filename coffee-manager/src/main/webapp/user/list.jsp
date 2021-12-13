<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/assets/bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>


</head>
<body>
<center>

    <h1>User Management</h1>
    <a href="/home"> Back Home</a>
    <h2>
        <a href="/users?action=create">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>fullName</th>
            <th>status</th>
            <th>role</th>
            <th>username</th>
            <th>birthday</th>
            <th>phoneNumber</th>
            <th>address</th>
            <th>action</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.fullName}"/></td>
                <td><c:out value="${user.status}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.birthDay}"/></td>
                <td><c:out value="${user.phoneNumber}"/></td>
                <td><c:out value="${user.address}"/></td>
                <td>
                    <button type="button" class="btn btn-outline-primary" >
                        <a href="/users?action=edit&id=${user.id}">Edit</a>
                    </button>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
