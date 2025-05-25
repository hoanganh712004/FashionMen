<%-- 
    Document   : userlist
    Created on : Mar 6, 2024, 9:57:30 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en" dir="ltr">
    <!-- Mirrored from maraviyainfotech.com/projects/ekka/ekka-v37/ekka-admin/user-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 02 Mar 2024 22:07:33 GMT -->
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>Dashboard Wallets</title>

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

        <!-- Data Tables -->
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
                            <div class="breadcrumb-wrapper breadcrumb-contacts">
                                <div>
                                    <h1>User List</h1>
                                    <p class="breadcrumbs">
                                        <span><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></span>
                                        <span><i class="mdi mdi-chevron-right"></i></span>Wallets
                                    </p>
                                </div>
                                <div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="ec-vendor-list card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Profile</th>
                                                        <th>UserName</th>
                                                        
                                                        <th>Wallets</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach items="${walletAll}" var="w">
                                                        <tr>
                                                            <td>
                                                                <img
                                                                    class="vendor-thumb"
                                                                    src="${pageContext.request.contextPath}/${w.image}"
                                                                    alt="user profile"
                                                                    />
                                                            </td>
                                                            <td>${w.userName}</td>
                                                            
                                                            <td>${w.balance}&nbsp;VND</td>
                                                            
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
                                                                        <c:if test="${account.roleId == 3}">
                                                                           <a onclick="addWallet(${w.walletId}, '${pageContext.request.contextPath}/addwallet?walletId=${w.walletId}&balance=')" class="dropdown-item" href="javascript:void(0)">Add</a>
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

        <!-- Data Tables -->
        <script src="${pageContext.request.contextPath}/js/jquery.datatables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.bootstrap5.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.responsive.min.js"></script>

        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
        <script src="${pageContext.request.contextPath}/js/addwallet.js"></script>
    </body>

    <!-- Mirrored from maraviyainfotech.com/projects/ekka/ekka-v37/ekka-admin/user-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 02 Mar 2024 22:07:34 GMT -->
</html>
