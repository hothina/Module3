<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/12/2021
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order Management Application</title>
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
    <h1>Order Management</h1>
    <h2>
        <a href="orders?action=users">List All Orders</a>
    </h2>
</center>
<div align="center">
    <h2>Add New Order</h2>
    <span class="${requestScope["classCss"]}">${requestScope["message"]}</span>
    <form method="post" class="needs-validation" novalidate>
        <table border="1" cellpadding="5">
            <tr>
                <th> NameCustomer:</th>
                <td>
                    <input type="text" name="nameCustomer" id="nameCustomer" size="45" class="form-control" required pattern="(^([A-Z]+[a-z]*[ ]?)+$)\b" placeholder="for example:Hoang/Hoang A">

                    <div class="invalid-feedback">
                        Please enter name customer(for example: Hoang / Tran Hoang).
                    </div>
                </td>
            </tr>
            <tr>
                <th>PhoneNumber:</th>
                <td class="input-group">

                    <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" size="45" required placeholder="for example:0901212121"/>
                    <div class="invalid-feedback">
                        Please enter phoneNumber (for example:0901212121).
                    </div>
                </td>
            </tr>
            <tr>
                <th>Address:</th>
                <td>
                    <input type="text" name="address" required class="form-control" id="address" size="45"/>
                </td>
            </tr>
            <tr>
                <th>CreatedAt:</th>
                <td>
                    <input type="date" name="createdAt" required class="form-control" id="createdAt" size="15" />
                    <div class="invalid-feedback">
                        Please enter createdAt.
                    </div>
                </td>
            <tr>
                <th>IdUser:</th>
                <td>
                    <input type="text" required class="form-control" name="idUser" id="idUser" size="45" />
                    <div class="invalid-feedback">
                        Please enter idUser.
                    </div>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <button class="btn btn-outline-primary" type="submit">Save</button>
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
                if (document.getElementById("nameCustomer").value.trim().length === 0)
                    document.getElementById("nameCustomer").value = "";
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
