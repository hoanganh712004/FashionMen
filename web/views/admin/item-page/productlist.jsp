<%-- 
    Document   : productlist
    Created on : Mar 7, 2024, 8:00:09 PM
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

        <title>Dashboard Product List</title>

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

        <!-- No Extra plugin used -->

        <link
            href="${pageContext.request.contextPath}/css/datatables.bootstrap5.min.css"
            rel="stylesheet"
            />
        <link
            href="${pageContext.request.contextPath}/css/responsive.datatables.min.css"
            rel="stylesheet"
            />

        <!-- ekka CSS -->
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
                            <div
                                class="breadcrumb-wrapper d-flex align-items-center justify-content-between"
                                >
                                <div>
                                    <h1>Product</h1>
                                    <p class="breadcrumbs">
                                        <span><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></span>
                                    <span><i class="mdi mdi-chevron-right"></i></span>Product
                                </p>
                            </div>
                            <div>
                                <span style="color: green">${requestScope.deleteSuccess}</span>
                                <a href="${pageContext.request.contextPath}/views/admin/item-page/addproduct.jsp" class="btn btn-primary">
                                    Add Porduct</a
                                >
                            </div>
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
                                                        <th>Product</th>
                                                        <th>Name</th>
                                                        <th>Price</th>
                                                        
                                                        <th>Purchased</th>
                                                        <th>Stock</th>
                   
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach items="${productAll}" var="product">
                                                        <tr>
                                                            <td>
                                                                <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}">
                                                                    <img class="tbl-thumb" src="${pageContext.request.contextPath}/${product.image}" alt="product image"/>
                                                                </a>
                                                            </td>
                                                            
                                                            <td>${product.productName}</td>
                                                            <td>${product.price}&nbsp;VNĐ</td>
                                 
                                                            <td>${product.quantitySold}</td>
                                                            <td>${product.quantityStock}</td>
                                                            
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
                                                                        
                                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/editproduct?productId=${product.productId}">Edit</a>
                                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/deleteProduct?productId=${product.productId}">Delete</a>
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

        <!-- Datatables -->
        <script src="${pageContext.request.contextPath}/js/jquery.datatables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.bootstrap5.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.responsive.min.js"></script>

        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>
        <script src="${pageContext.request.contextPath}/js/delete.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
    </body>

</html>
