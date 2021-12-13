<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/12/2021
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Management Application</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/assets/bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>

</head>
<body>
<center>
    <h1>Order Management</h1>
    <a href="/home">Back home</a>
    <h2>
        <a href="/orders?action=create">Add New Order</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Orders</h2></caption>
        <tr>
            <th>ID</th>
            <th>NameCustomer</th>
            <th>PhoneNumber</th>
            <th>Address</th>
            <th>CreatedAt</th>
            <th>IdUser</th>
        </tr>
        <c:forEach var="order" items="${listOrder}">
            <tr>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.nameCustomer}"/></td>
                <td><c:out value="${order.phoneNumber}"/></td>
                <td><c:out value="${order.address}"/></td>
                <td><c:out value="${order.createdAt}"/></td>
                <td><c:out value="${order.idUser}"/></td>

            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
