<%-- 
    Document   : orderhistory
    Created on : Mar 9, 2024, 2:49:19 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>Ekka - Admin Dashboard HTML Template.</title>

        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.googleapis.com/" />
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;400;500;600;700;800&amp;family=Poppins:wght@300;400;500;600;700;800;900&amp;family=Roboto:wght@400;500;700;900&amp;display=swap"
            rel="stylesheet"
            />

        <link
            href="${pageContext.request.contextPath}/css/materialdesignicons.min.css"
            rel="stylesheet"
            />

        <!-- PLUGINS CSS STYLE -->
        <link href="${pageContext.request.contextPath}/css/simplebar.css" rel="stylesheet" />

        <!-- Data-Tables -->
        <link
            href="${pageContext.request.contextPath}/css/datatables.bootstrap5.min.css"
            rel="stylesheet"
            />
        <link
            href="${pageContext.request.contextPath}/css/responsive.datatables.min.css"
            rel="stylesheet"
            />

        <!-- Ekka CSS -->
        <link id="ekka-css" rel="stylesheet" href="${pageContext.request.contextPath}/css/ekka.css" />

        <!-- FAVICON -->
        <link href="${pageContext.request.contextPath}/images/favicon.png" rel="shortcut icon" />
    </head>

    <body
        class="ec-header-fixed ec-sidebar-fixed ec-sidebar-dark ec-header-light"
        id="body"
        >
        <!-- WRAPPER -->
        <div class="wrapper">
            <!-- LEFT MAIN SIDEBAR -->
            <jsp:include page="../../common/admin/leftmainsidebar.jsp"></jsp:include>

                <!-- PAGE WRAPPER -->
                <div class="ec-page-wrapper">
                    <!-- Header -->
                <jsp:include page="../../common/admin/header.jsp"></jsp:include>
                    <!-- CONTENT WRAPPER -->
                    <div class="ec-content-wrapper">
                        <div class="content">
                            <div class="breadcrumb-wrapper breadcrumb-wrapper-2">
                                <h1>Orders History</h1>
                                <p class="breadcrumbs">
                                    <span><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></span>
                                <span><i class="mdi mdi-chevron-right"></i></span>History
                            </p>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card card-default">
                                    <div class="card-body">

                                        <div class="table-responsive">
                                            <table
                                                id="responsive-data-table"
                                                class="table"
                                                style="width: 100%"
                                                >
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Customer</th>
                                                        <th>Total Quantity</th>
                                                        <th>Date</th>
                                                        <th>Order_Cost</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach items="${ListAllOrderAndOrderDetail}" var="order">

                                                        <tr>
                                                            <td>${order.orderId}</td>
                                                            <td>${order.fullName}</td>
                                                            <td>${order.totalQuantity}</td>
                                                            <td>${order.orderDate}</td>
                                                            <td>${order.totalMoney}&nbsp;VND</td>

                                                            <td>
                                                                <c:if test="${order.status == 0}">
                                                                    <span class="badge badge-warning">Pending</span>
                                                                </c:if>

                                                                <c:if test="${order.status == 1}">
                                                                    <span class="badge badge-success">Completed</span>
                                                                </c:if>

                                                                <c:if test="${order.status == 2}">
                                                                    <span class="badge badge-danger">Cancelled</span>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <div class="btn-group mb-1">
                                                                    <button
                                                                        type="button"
                                                                        class="btn btn-outline-success"
                                                                        >
                                                                        Info
                                                                    </button>
                                                                    <button
                                                                        type="button"
                                                                        class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                                        data-bs-toggle="dropdown"
                                                                        aria-haspopup="true"
                                                                        aria-expanded="false"
                                                                        data-display="static"
                                                                        >
                                                                        <span class="sr-only">Info</span>
                                                                    </button>

                                                                    <div class="dropdown-menu">

                                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/orderdetailadmin?orderId=${order.orderId}">Invoice</a>
                                                                        <c:if test="${order.status == 0}">
                                                                            <li class="dropdown-item">
                                                                                <a href="${pageContext.request.contextPath}/updateorder?orderId=${order.orderId}&link=1&status=2">cancel order</a>
                                                                            </li>
                                                                        </c:if>
                                                                        <c:if test="${order.status == 0}">
                                                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/updateStatus?orderId=${order.orderId}&status=${1}">Completed</a>
                                                                        </c:if>
                                                                        <c:if test="${order.status == 2}">
                                                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/deleteStatus?orderId=${order.orderId}&status=${2}">Deleted</a>
                                                                        </c:if>

                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>

                                                    </c:forEach>



                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Content -->
                </div>
                <!-- End Content Wrapper -->

                <!-- Footer -->
                <footer class="footer mt-auto">
                    <div class="copyright bg-white">
                        <p>
                            Copyright &copy; <span id="ec-year"></span
                            ><a
                                class="text-primary"
                                href="https://themeforest.net/user/ashishmaraviya"
                                target="_blank"
                                >
                                Ekka Admin Dashboard</a
                            >. All Rights Reserved.
                        </p>
                    </div>
                </footer>
            </div>
            <!-- End Page Wrapper -->
        </div>
        <!-- End Wrapper -->

        <!-- Common Javascript -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>

        <!-- Data-Tables -->
        <script src="${pageContext.request.contextPath}/js/jquery.datatables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.bootstrap5.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.responsive.min.js"></script>

        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
    </body>

</html>
