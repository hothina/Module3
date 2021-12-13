<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/12/2021
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OrderDetail Management Application</title>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/assets/bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>

</head>
<body>
<center>
    <h1>OrderDetail Management</h1>
    <a href="/home">Back home</a>
    <h2>
        <a href="/orderdetail?action=create">Add New OrderDetail</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of OrderDetails</h2></caption>
        <tr>

            <th>IdDrink</th>
            <th>IdOrder</th>
            <th>Quantity</th>


        </tr>
        <c:forEach var="orderdetail" items="${listOrderDetail}">
            <tr>

                <td><c:out value="${orderdetail.idDrink}"/></td>
                <td><c:out value="${orderdetail.idOrder}"/></td>
                <td><c:out value="${orderdetail.quantity}"/></td>


            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
