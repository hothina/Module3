<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/10/2021
  Time: 9:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Drink Management Application</title>
    <style>
        .message.success {
            color: green;
        }

        .message.error {
            color: red;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<center>
    <h1>Drink Management</h1>
    <h2>
        <a href="drinks?action=drinks">List All Drinks</a>
    </h2>
</center>
<div align="center">
    <span class="${requestScope["classCss"]}">${requestScope["message"]}</span>
    <form method="post" class="needs-validation">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Drink
                </h2>
            </caption>
            <c:if test="${drink != null}">
                <input type="hidden" name="id" value="<c:out value='${drink.id}' />"/>
            </c:if>
            <tr>
                <th> Name:</th>
                <td>
                    <input type="text" name="name"  required class="form-control" size="45"
                           value="<c:out value='${drink.name}' />" pattern="(^([A-Z]+[a-z]*[ ]?)+$)\b"
                           readonly
                    />
                </td>
            </tr>
            <tr>
                <th> Quantity:</th>
                <td>
                    <input type="text" name="quantity" required class="form-control"  size="45"
                           value="<c:out value='${drink.quantity}' />"
                    />
                </td>
            </tr>

            <tr>
                <th> Price:</th>
                <td>
                    <input type="text" name="price" required class="form-control"  size="45"
                           value="<c:out value='${drink.price}' />"
                    />
                </td>
            </tr>
            <tr>
            <tr>
                <td colspan="2" align="center">
                    <button class="btn btn-outline-primary" type="submit">Save</button>
                </td>
            </tr>
            </tr>
        </table>
    </form>
</div>
