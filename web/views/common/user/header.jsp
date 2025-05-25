<%-- 
    Document   : header
    Created on : Feb 26, 2024, 6:20:14 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header-section">
    <div class="top-bar">
        <div class="container d-flex align-items-center justify-content-between">
            <div class="top-bar-contact d-flex align-items-center">
                <ul class="d-flex align-items-center">
                    <li><a href="#"><i class="fa-solid fa-phone"></i>+84 9696 52156</a></li>
                    <li><a href="mailto: nguyenhoanganh712004qp@gmail.com" target="_blank"><i class="fa-regular fa-envelope"></i>nguyenhoanganh712004qp@gmail.com</a></li>
                    <li><a href="https://www.google.com/maps" target="_blank"><i class="fa-sharp fa-solid fa-location-dot"></i>Locate store</a></li>
                </ul>
            </div>
            <div class="top-bar-social d-flex align-items-center">
                <ul class="view-switcher d-flex align-items-center">
                    <li>
                        <span>VND<i class="fa-solid"></i></span>
                    </li>
                    <li>
                        <span>en<i class="fa-solid"></i></span>
                        <ul class="evani-curency">
                            <li><a href="#">english</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="social-icon d-flex align-items-center">
                    <li><a href="https://www.facebook.com/"><i class="fa-brands fa-facebook-f"></i></a></li>
                    <li><a href="https://www.youtube.com/"><i class="fa-brands fa-youtube"></i></a></li>
                </ul>
            </div>
        </div>
    </div>


    <div class="header-wrapper">

        <div class="header-middle">

            <div class="container d-flex align-items-center justify-content-between">
                <div class="evani-brand">
                    <a href="${pageContext.request.contextPath}/home">
                        <img style="width: 225px;" src="${pageContext.request.contextPath}/images/header/logo/logo.png" alt="header">
                    </a>
                </div>
                <div class="evani-search-form">
                    <form class="search-product d-flex align-items-center justify-content-between" id="search-product" action="${pageContext.request.contextPath}/search" method="GET">
                        <input id="keyword" type="text" name="keyword" placeholder="Search by product" value="${keyword}">
                        <button type="submit">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>

                <div class="inner-wrapper">

                    <ul class="d-flex align-items-center">

                        <li class="search-popup">
                            <a class="header-popup d-flex align-items-center" id="header-popup" href="#"><span class="log-in-text"><i class="fa-solid fa-magnifying-glass"></i></span></a>
                            <div class="popup-wraper">
                                <div class="view-popup">
                                    <label class="close-btn fas fa-times popup-label"></label>
                                    <label class="la-search-pr">Search Product</label>
                                    <form class="search-product d-flex align-items-center justify-content-between" action="https://ethemestudio.com/search" method="GET">
                                        <input type="text" name="keyword" placeholder="Type to search i.e “áo”...">
                                        <button type="submit">
                                            <i class="fa-solid fa-magnifying-glass"></i>
                                        </button>
                                    </form>    
                                </div>
                            </div>
                        </li>

                        <c:if test="${sessionScope.account == null}">
                            <li>
                                <a class="log-in header-popup log-in-btn d-flex align-items-center" id="header-popup1" href="${pageContext.request.contextPath}/views/common/user/login.jsp"><span class="log-in-text">log in</span><i class="fa-solid fa-user"></i></a>
                            </li>


                            <li>
                                <a class="sign-up header-popup d-flex align-items-center" id="header-popup2" href="${pageContext.request.contextPath}/views/common/user/register.jsp"><span class="log-in-text">sign up</span></a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account != null}">

                            <div class="profile-dropdown">
                                <div onclick="toggle()" class="profile-dropdown-btn">
                                    <div class="profile-img" style="    position: relative;
                                         width: 3rem;
                                         height: 3rem;
                                         border-radius: 50%;
                                         background: url(${pageContext.request.contextPath}/${sessionScope.account.image});
                                         background-size: cover;">
                                        <i class="fa-solid fa-circle"></i>
                                    </div>
                                    <span style="font-size: 13px;">${account.userName}</span>
                                </div>

                                <ul class="profile-dropdown-list">
                                    <li class="profile-dropdown-list-item">
                                        <a href="${pageContext.request.contextPath}/views/user/item-page/userprofile.jsp">
                                            <i class="fa-regular fa-user"></i>
                                            Profile
                                        </a>
                                    </li>

                                    <c:if test="${sessionScope.account.roleId == 1 || sessionScope.account.roleId == 3}">
                                        <li class="profile-dropdown-list-item" style="margin-left: 20px;">
                                            <a href="${pageContext.request.contextPath}/dashboard" >
                                                <i class="fa-solid fa-chart-line"></i>
                                                Dashboard
                                            </a>
                                        </li>
                                    </c:if>

                                    <hr />

                                    <li class="profile-dropdown-list-item">
                                        <a href="${pageContext.request.contextPath}/logout">
                                            <i class="fa-solid fa-arrow-right-from-bracket"></i>
                                            Log out
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </c:if>

                        <li><a href="${pageContext.request.contextPath}/views/user/item-page/wishlist.jsp"><i class="fa-solid fa-heart"></i></a></li>

                        <c:if test="${sessionScope.account != null}">
                            <li class="product-cart">

                                <a href="${pageContext.request.contextPath}/views/user/item-page/shoppingcart.jsp" class="cart-icon" id="toggleButton"><i class="fa-solid fa-cart-shopping"></i>
                                    <c:if test="${sessionScope.cartItemList != null}">
                                        <span class="cart-number">${sessionScope.cartItemList.size()}</span>
                                    </c:if>
                                </a>

                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
        <div class="mega-menu-wrapper">
            <div class="container">
                <div class="wrapper-items d-flex align-items-center">
                    <!-- menu start here -->
                    <div class="header-item item-left">
                        <div class="menu-overlay">
                        </div>
                        <nav class="nav-menu">
                            <div class="mobile-menu-head">
                                <div class="go-back"><i class="fa fa-angle-left"></i></div>
                                <div class="current-menu-title"></div>
                                <div class="mobile-menu-close"><i class="fa-solid fa-x"></i></div>
                            </div>
                            <ul class="menu-items">
                                <li class="menu-item-has-children">
                                    <a href="${pageContext.request.contextPath}/home" class="nav-link">Home<i class="fa"></i></a>
                                </li>

                                <li class="menu-item-has-children">
                                    <a href="#" class="nav-link">Best Seller<i class="fa fa-angle-down"></i></a>
                                    <div class="sub-menu mega-menu mega-menu-column-4">
                                        <c:forEach items="${top3ProductByCategory}" var="category">
                                            <div class="list-item">
                                                <h4 class="title">${category.key.categoryName}</h4>
                                                <c:forEach items="${category.value}" var="product">
                                                    <ul>
                                                        <li><a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}"">${product.productName}</a></li>
                                                    </ul>
                                                </c:forEach>
                                                <ul>
                                                    <li><a href="${pageContext.request.contextPath}/filterproduct?category=${category.key.categoryId}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0">More...</a></li>
                                                </ul>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </li>


                                <li>
                                    <a href="${pageContext.request.contextPath}/views/user/item-page/contactus.jsp">FeedBack</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- menu end here -->
                    <div class="header-item item-right">
                        <a href="#"><i class="fa-solid fa-headset"></i>Help & Support</a>
                        <div class="mobile-menu-trigger">
                            <span></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>