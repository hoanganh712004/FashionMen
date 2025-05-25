<%-- 
    Document   : leftmainsidebar
    Created on : Mar 3, 2024, 5:43:10 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="ec-left-sidebar ec-bg-sidebar">
    <div id="sidebar" class="sidebar ec-sidebar-footer">

        <div class="ec-brand">
            <a href="${pageContext.request.contextPath}/home" title="Ekka">
                <img class="ec-brand-icon" src="${pageContext.request.contextPath}/images/head/logo/shape-1.png" alt=""/>
                <span class="ec-brand-name text-truncate">360 Fashion</span>
            </a>
        </div>

        <!-- begin sidebar scrollbar -->
        <div class="ec-navigation" data-simplebar>

            <!-- sidebar menu -->
            <ul class="nav sidebar-inner" id="sidebar-menu">

                <!-- Dashboard -->
                <li class="active">
                    <a class="sidenav-item-link" href="${pageContext.request.contextPath}/dashboard">
                        <i class="mdi mdi-view-dashboard-outline"></i>
                        <span class="nav-text">Dashboard</span>
                    </a>
                    <hr />
                </li>

                <!-- Users -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-account-group"></i>
                        <span class="nav-text">Users</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/views/admin/item-page/userlist.jsp">
                                    <span class="nav-text">User List</span>
                                </a>
                            </li>
                            
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/walletAdmin">
                                    <span class="nav-text">Wallets</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <hr />
                </li>

                <!-- Category -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-dns-outline"></i>
                        <span class="nav-text">Categories</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul
                            class="sub-menu"
                            id="categorys"
                            data-parent="#sidebar-menu"
                            >
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/categoryAdmin">
                                    <span class="nav-text">Main Category</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Products -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-palette-advanced"></i>
                        <span class="nav-text">Products</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul
                            class="sub-menu"
                            id="products"
                            data-parent="#sidebar-menu"
                            >
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/addProduct">
                                    <span class="nav-text">Add Product</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/productListAdmin">
                                    <span class="nav-text">List Product</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Orders -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-cart"></i>
                        <span class="nav-text">Orders</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="orders" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/orderHistory">
                                    <span class="nav-text">Order History</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-chart-arc"></i>
                        <span class="nav-text">Chart</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        
                        <ul class="sub-menu" id="orders" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/reviewreportchart">
                                    <span class="nav-text">Review Report</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Reviews -->
                <li>
                    <a class="sidenav-item-link" href="${pageContext.request.contextPath}/reviewProduct">
                        <i class="mdi mdi-star-half"></i>
                        <span class="nav-text">Reviews</span>
                    </a>
                </li>

                <!--feetback-->

                <li>
                    <a class="sidenav-item-link" href="${pageContext.request.contextPath}/feedbackUser">
                        <i class="mdi mdi-star-half"></i>
                        <span class="nav-text">Feedback</span>
                    </a>
                </li>

            </ul>
        </div>
    </div>
</div>