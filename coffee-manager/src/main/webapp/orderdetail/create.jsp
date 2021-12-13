<%@ page import="vn.na.ho.coffee.model.Drink" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/12/2021
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>OrderDetail Management Application</title>
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
        <%
            int IDOrder = (int) request.getAttribute("idOrder");
        %>
<body>
<center>
    <h1>OrderDetail Management</h1>
    <h2>
        <a href="orderdetail?action=users">List All OrderDetails</a>
    </h2>
</center>
<div align="center">
    <h2>Add New OrderDetail</h2>
    <span class="${requestScope["classCss"]}">${requestScope["message"]}</span>
    <form action="orderdetail?action=create" method="post" class="needs-validation" novalidate>
        <input type="hidden" name="idOrder" class="form-control" id="idOrder" size="45" required value="<%=IDOrder%>" >
        <table border="1" cellpadding="5">

            <tr>
                <th>NameDrink:</th>
                <td class="input-group">
                    <select name="idDrink">
                        <% List<Drink> drinkList = (List<Drink>) request.getAttribute("listDrink");
                            for (Drink i: drinkList ) {
                        %>
                        <option value="<%=i.getId()%>"><%=i.getName()%></option>
                        <%}%>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td class="input-group">

                    <input type="text" name="quantity" class="form-control" id="quantity" size="45" required />
                    <div class="invalid-feedback">
                        Please enter quantity.
                    </div>
                </td>
            </tr>

            <tr>
                <td colspan="1" align="center">
                    <button class="btn btn-outline-primary" type="submit">order more </button>
                </td>
                <td colspan="1" align="center">
                    <button class="btn btn-outline-primary" type="submit">complete order</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {

                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()</script>
</body>
</html>

