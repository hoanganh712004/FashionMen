<%-- 
    Document   : invoice
    Created on : Mar 9, 2024, 2:55:49 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>Dashboard Invoice</title>

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

        <!-- Ekka CSS -->
        <link id="ekka-css" href="${pageContext.request.contextPath}/css/ekka.css" rel="stylesheet" />

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
                            <h1>Invoice</h1>
                            <p class="breadcrumbs">
                                <span><a href="${pageContext.request.contextPath}/views/admin/item-page/orderhistory.jsp">Orders History</a></span>
                                <span><i class="mdi mdi-chevron-right"></i></span>Invoice
                            </p>
                        </div>
                        <div class="card invoice-wrapper border-radius border bg-white p-4">
                            <div class="d-flex justify-content-between">
                                <h3 class="text-dark font-weight-medium">Invoice #${requestScope.order.orderId}</h3>

                                <div class="btn-group">
                                    <button class="btn btn-sm btn-primary">
                                        <i class="mdi mdi-content-save"></i> Save
                                    </button>

                                    <button class="btn btn-sm btn-primary">
                                        <i class="mdi mdi-printer"></i> Print
                                    </button>
                                </div>
                            </div>

                            <div class="row pt-5">
                                <div class="col-xl-3 col-lg-4 col-sm-6">
                                    <p class="text-dark mb-2">From</p>

                                    <address>
                                        <span>360 Fashion Men VN</span>
                                        <br />
                                        Đại học FPT Hà Nội
                                          <br />
                                        <span>Email:</span> 360fashionmenvn@gmail.com <br />
                                        <span>Phone:</span> +84 9696 52156
                                    </address>
                                </div>
                                <div class="col-xl-3 col-lg-4 col-sm-6">
                                    <p class="text-dark mb-2">To</p>

                                    <address>
                                        <span>${order.fullName}</span>
                                        <br />
                                        ${order.address} <br />
                                        <span>Email</span>: ${order.email} <br />
                                        <span>Phone:</span> ${order.phoneNumber}
                                    </address>
                                </div>
                                <div class="col-xl-4 disp-none"></div>
                                <div class="col-xl-2 col-lg-4 col-sm-6">
                                    <p class="text-dark mb-2">Details</p>

                                    <address>
                                        <span>Invoice ID:</span>
                                        <span class="text-dark">#${order.orderId}</span>
                                        <br /><span>Date :</span> ${order.orderDate} <br />
                                    </address>
                                </div>
                            </div>

                            <div class="table-responsive">
                                <table
                                    class="table mt-3 table-striped table-responsive table-responsive-large inv-tbl"
                                    style="width: 100%"
                                    >
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Image</th>
                                            <th>Item</th>
                                            <th>Color</th>
                                            <th>Size</th>
                                            <th>Quantity</th>
                                            <th>Price/Unit</th>
                                            <th>Sub Total</th>
                                        </tr>
                                    </thead>

                                    <tbody>


                                        <c:set var="stt" value="${1}"/>
                                        <c:forEach items="${requestScope.listOrderDetail}" var="orderdetail">
                                            <tr>
                                                <td>${stt}</td>
                                                <td>
                                                    <img
                                                        class="invoice-item-img"
                                                        src="${pageContext.request.contextPath}/${orderdetail.product.thumbnails.get(0)}"
                                                        alt="product-image"
                                                        />
                                                </td>
                                                <td>${orderdetail.product.productName}</td>
                                                <td>${orderdetail.color.color}</td>
                                                <td>${orderdetail.size.sizeOption}</td>
                                                <td>${orderdetail.quantity}</td>
                                                <td>${orderdetail.product.price}</td>
                                                <td>${orderdetail.product.price * orderdetail.quantity}&nbsp;VND</td>
                                                <c:set var="stt" value="${stt + 1}"/>
                                            </tr>


                                        </c:forEach>
                                        <tr>
                                            <td colspan="6"></td>
                                            
                                            <td>Total</td>
                                            <td>${requestScope.order.totalMoney}&nbsp;VND</td>
                                        </tr>


                                    </tbody>
                                </table>
                            </div>

<!--                            <div class="row justify-content-end inc-total">
                                <div class="col-lg-3 col-xl-3 col-xl-3 ml-sm-auto">
                                    <ul class="list-unstyled mt-3">

                                        <li class="pb-3 text-dark">
                                            Total
                                            <span class="d-inline-block float-right">${requestScope.order.totalMoney}&nbsp;VND</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>-->
                        </div>
                    </div>
                    <!-- End Content -->
                </div>
                <!-- End Content Wrapper -->

                <!-- Footer -->
                <jsp:include page="../../common/admin/footer.jsp"></jsp:include>
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

        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
    </body>

</html>
