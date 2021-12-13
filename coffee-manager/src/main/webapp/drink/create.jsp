<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/10/2021
  Time: 8:26 AM
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
        <a href="drinks?action=users">List All Drinks</a>
    </h2>
</center>
<div align="center">
    <h2>Add New Drink</h2>
    <span class="${requestScope["classCss"]}">${requestScope["message"]}</span>
    <form method="post" class="needs-validation" novalidate>
        <table border="1" cellpadding="5">
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45" class="form-control" required pattern="(^([A-Z]+[a-z]*[ ]?)+$)\b" placeholder="Tra / Tra Xanh">

                    <div class="invalid-feedback">
                        Please enter name (for example: Tra / Tra Xanh ).
                    </div>
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
                <th>Price:</th>
                <td>
                    <input type="text" name="price" required class="form-control" id="price" size="45"/>
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
                if (document.getElementById("name").value.trim().length === 0)
                    document.getElementById("name").value = "";
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

