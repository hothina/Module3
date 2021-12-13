<%--
  Created by IntelliJ IDEA.
  User: LAPTOP T&T
  Date: 12/9/2021
  Time: 11:31 AM
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
    <span class="${requestScope["classCss"]}">${requestScope["message"]}</span>
    <form method="post" class="needs-validation">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit User
                </h2>
            </caption>
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
            </c:if>
            <tr>
                <th> fullName:</th>
                <td>
                    <input type="text" name="fullName"  required class="form-control" size="45"
                           value="<c:out value='${user.fullName}' />" pattern="(^([A-Z]+[a-z]*[ ]?)+$)\b"
                    />
                </td>
            </tr>
            <tr>
                <th> username:</th>
                <td>
                    <input type="text" name="username" required class="form-control"  size="45"
                           value="<c:out value='${user.username}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>status:</th>
                <td>
                    <select  type="text" name="status">
                        <option value="${user.status.value}" selected>${user.status}</option>
                        <c:forEach items="${statusList}" var="item">
                            <c:if test="${item != user.status}">
                                <option value="${item.value}">${item}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>birthday:</th>
                <td>
                    <input type="date" name="birthDay" required class="form-control"  size="15"
                           value="<c:out value='${user.birthDay}' />"
                    />
                </td>
            </tr>
            <tr>
                <th> phoneNumber:</th>
                <td>
                    <input type="text" name="phoneNumber" required class="form-control"  size="45"
                           value="<c:out value='${user.phoneNumber}' />" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b"
                    />
                </td>
            </tr>
            <tr>
                <th> address:</th>
                <td>
                    <input type="text" name="address" required class="form-control"  size="45"
                           value="<c:out value='${user.address}' />"
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

</body>
</html>
