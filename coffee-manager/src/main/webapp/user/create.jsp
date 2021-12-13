<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/9/2021
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
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
    <h1>User Management</h1>
    <h2>
        <a href="users?action=users">List All Users</a>
    </h2>
</center>
<div align="center">
    <h2>Add New User</h2>
    <span class="${requestScope["classCss"]}">${requestScope["message"]}</span>
    <form method="post" class="needs-validation" novalidate>
        <table border="1" cellpadding="5">
            <tr>
                <th>User FullName:</th>
                <td>
                    <input type="text" name="fullName" id="fullName" size="45" class="form-control" required pattern="(^([A-Z]+[a-z]*[ ]?)+$)\b" placeholder="Hoang/Hoang A">

                    <div class="invalid-feedback">
                        Please enter full name (for example: Hoang / Tran Hoang).
                    </div>
                </td>
            </tr>
            <tr>
                <th>User Username:</th>
                <td class="input-group">
                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                    <input type="text" name="username" class="form-control" id="username" size="45" required />
                    <div class="invalid-feedback">
                        Please enter username.
                    </div>
                </td>
            </tr>
            <tr>
                <th>User Birthday:</th>
                <td>
                    <input type="date" name="birthDay" required class="form-control" id="birthDay" size="45"/>
                </td>
            </tr>
            <tr>
                <th>User PhoneNumber:</th>
                <td>
                    <input type="text" name="phoneNumber" required class="form-control" id="phoneNumber" size="15" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b"/>
                    <div class="invalid-feedback">
                        Please enter phoneNumber (pattern: 0909343434).
                    </div>
                </td>
            <tr>
                <th>User Address:</th>
                <td>
                    <input type="text" required class="form-control" name="address" id="address" size="45" />
                    <div class="invalid-feedback">
                        Please enter address.
                    </div>
                </td>
            </tr>
            <tr>
                <th>User Password:</th>
                <td>
                    <input type="password" required class="form-control" name="password" id="password" size="15"/>
                    <div class="invalid-feedback">
                        Please enter password.
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
                if (document.getElementById("fullName").value.trim().length === 0)
                    document.getElementById("fullName").value = "";
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
