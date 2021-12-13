<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/assets/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/assets/bootstrap-5.1.3-dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="/assets/css/order.css">
    <script src="/assets/css/create.js"></script>
</head>
<body>
<form method="post">
    <div class="container">
        <div class="card">
            <div class="card-body">
                <div id="invoice">
                    <div class="toolbar hidden-print">
                        <div class="text-end">
                            <button type="button" class="btn btn-dark"><i class="fa fa-print"></i> Print</button>
                            <button type="button" class="btn btn-danger"><i class="fa fa-file-pdf-o"></i> Export as PDF
                            </button>
                        </div>
                        <hr>
                    </div>
                    <div class="invoice overflow-auto">
                        <div style="min-width: 600px">
                            <header>
                                <div class="row">
                                    <div class="col">
                                        <a href="javascript:;">
                                            <img src="assets/images/logo-icon.png" width="80" alt="">
                                        </a>
                                    </div>
                                    <div class="col company-details">
                                        <h2 class="name">
                                            <a target="_blank" href="javascript:;">
                                                Arboshiki
                                            </a>
                                        </h2>
                                        <div>455 Foggy Heights, AZ 85004, US</div>
                                        <div>(123) 456-789</div>
                                        <div>company@example.com</div>
                                    </div>
                                </div>

                            </header>
                            <main>
                                <div class="row contacts">
                                    <div class="col invoice-to">
                                        <div class="text-gray-light">INVOICE TO:</div>
                                        <%--                                    <h2 class="to">John Doe</h2>--%>
                                        <%--                                    <div class="address">796 Silver Harbour, TX 79273, US</div>--%>
                                        <%--                                    <div class="email"><a href="mailto:john@example.com">john@example.com</a>  </div>--%>
                                        <input class="to" type="text" name="nameCustomer">
                                        <br>
                                        <br/>
                                        <input class="address" type="text" name="address">
                                        <br>
                                        <br/>
                                        <input class="mobile" type="text" name="phoneNumber">

                                    </div>
                                </div>
                                <div class="row">
                                    <select id="drink">
                                        <c:forEach items="${drinks}" var="item">
                                            <option
                                                    price="<c:out value="${item.price}"/>"
                                                    value="<c:out value="${item.id}"/>"
                                            >
                                                <c:out value="${item.name}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <input type="number" id="quantity">
                                    <div onclick="addRow()" style="width: 10%; horiz-align: right"
                                            class="btn-outline-primary">
                                        Add Item
                                    </div>
                                </div>
                                <table>
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th class="text-left">NAME</th>
                                        <th class="text-right">PRICE</th>
                                        <th class="text-right">QUANTITY</th>
                                        <th class="text-right">TOTAL</th>
                                    </tr>
                                    </thead>
                                    <tbody  id="order-detail">
                                    <tr>
                                        <td class="no"><input hidden  type="text" disabled value="01"/>01</td>
                                        <td class="text-left">
                                            <h3>Tra Xanh</h3>
                                        </td>
                                        <td class="unit">$40.00</td>
                                        <td class="qty"><input hidden  type="number" disabled value="30"/>30</td>
                                        <td class="total">$1,200.00</td>
                                    </tr>


                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td colspan="2">SUBTOTAL</td>
                                        <td>$5,200.00</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td colspan="2">TAX 25%</td>
                                        <td>$1,300.00</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td colspan="2">GRAND TOTAL</td>
                                        <td>$6,500.00</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td colspan="2"></td>
                                        <td><button class="btn-outline-primary" type="submit" >PAYMENT</button></td>
                                    </tr>
                                    </tfoot>
                                </table>
                                <div class="thanks">Thank you!</div>
                                <div class="notices">
                                    <div>NOTICE:</div>
                                    <div class="notice">A finance charge of 1.5% will be made on unpaid balances after
                                        30
                                        days.
                                    </div>
                                </div>
                            </main>
                            <footer>Invoice was created on a computer and is valid without the signature and seal.
                            </footer>
                        </div>
                        <!--DO NOT DELETE THIS div. IT is responsible for showing footer always at the bottom-->
                        <div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
