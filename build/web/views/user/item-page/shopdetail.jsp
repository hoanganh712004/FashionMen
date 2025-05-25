<%-- 
    Document   : allproduct
    Created on : Feb 28, 2024, 5:11:57 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx">

    <!-- Mirrored from ethemestudio.com/demo/evani/shop-with-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:02:46 GMT -->
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/awesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/unpkg.swiper.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/leaflet.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/webfonts/all.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/webfonts/fontawesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />
        <title> Shop Detail</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">

        <style>
            /* Định dạng cho thẻ <a> trong phần tử có lớp "active" */
            .ev-pagination li.active a {
                font-weight: bold; /* Làm đậm chữ */
                color: white; /* Màu chữ trắng để tương phản với nền cam */
                background-color: #FFA500; /* Nền cam */
                padding: 5px 10px; /* Thêm khoảng cách xung quanh để nền cam hiển thị rõ ràng */
                border-radius: 3px; /* Bo góc nhẹ cho phần tử */
            }

            /* Định dạng cho các thẻ <a> trong phần tử không có lớp "active" */
            .ev-pagination li a {
                color: #000; /* Màu chữ mặc định */
                background-color: transparent; /* Nền trong suốt khi không active */
            }



        </style>
    </head>
    <body>

        <!-- HEADER-SECTION START  -->


        <jsp:include page="../../common/user/header.jsp"></jsp:include>

            <!-- HEADER-SECTION END  -->

            <!-- BANNER-SECTION START  -->

            <section class="hero-section ev-common-hero" style="background-image: url(${pageContext.request.contextPath}/images/common-banner/shape-1.png);"> 
            <div class="container-fluid">
                <div class="ev-hero-content">
                    <h2>Shop Details</h2>
                    <span><a href="${pageContext.request.contextPath}/home">Home</a> - Shop</span>
                </div>
            </div>
        </section>

        <!-- BANNER-SECTION END  -->


        <!-- `PRODUCT-SECTION START  -->

        <section class="product-section shop-product-section single-shop-section">
            <div class="container">
                <div class="row">
                    <div class="col-xl-3 col-lg-3 col-12  order-lg-0 order-1">
                        <div class="shop-product-sidebar">


                            <form id="f" action="${pageContext.request.contextPath}/filterproduct" method="GET">


                                <div class="sidebar-categories">
                                    <h6>Item Categories</h6>
                                    <div class="section-wrapper">
                                        <nav class="accordion-wrapper">

                                            <li  style="font-size: 16px; padding: 5px 0px">
                                                <input onclick="" class="allCategory" type="checkbox" id="allCategory" name="category" value="${0}" checked/>
                                                <label for="allCategory"><span>ALL</span></label>
                                            </li>

                                            <c:forEach var="c" items="${listCategory}">
                                                <li style="font-size: 16px; padding: 5px 0px">
                                                    <input class="category" type="checkbox" id="category_${c.categoryName}" name="category" value="${c.categoryId}" />
                                                    <label for="category_${c.categoryName}"><span>${c.categoryName}</span></label>
                                                </li>
                                            </c:forEach>



                                        </nav>
                                    </div>
                                </div>

                                <div class="prise-range">
                                    <h6>Price Range</h6>
                                    <div style="font-size: 16px; padding: 5px 0px" class="price-range-slider">
                                        <label for="min-price"><span>Min Price:</span></label>
                                        <input type="number" id="min-price" name="minPrice" value="${minPrice}" />

                                        <label for="max-price"><span>Max Price:</span></label>
                                        <input type="number" id="max-price" name="maxPrice" value="${maxPrice}" />


                                        <p class="range-value align-items-center">

                                        </p>
                                    </div>                     
                                </div>


                                <input style="width: 100px;
                                       height: 35px;
                                       border-radius: 15px;
                                       background-color: #F2AD4A;
                                       font-size: 16px;
                                       font-weight: 400;margin-bottom: 15px;" type="submit" value="Filter"/>

                        </div>
                    </div>

                    <div class="col-xl-9 col-lg-9 col-12 order-lg-1 order-0">
                        <div class="item-wrapper">


                            <div class="before-shop-loop d-lg-flex d-lg-block align-items-center justify-content-between">


                                <div class="product-items d-flex align-items-center">
                                    <c:set var="total" value=""/>
                                    <p>Showing <span></span> 
                                        of <span></span> Items</p>
                                </div>


                                <div class="product-views-buttons d-flex align-items-center">

                                    <div class="dropdown  d-flex align-items-center">
                                        <span>Short By :</span>
                                        <select onchange="optionSort()" id="orderBy" name="orderBy" style="padding: 10px;
                                                font-size: 16px;
                                                font-family: Open Sans, sans-serif;
                                                max-width: 130px;
                                                font-weight: 700;">
                                            <option value="0" ${requestScope.orderBy == 0 || requestScope.orderBySearch == 0 ? 'selected' : ''}>Default</option> 
                                            <option value="1" ${requestScope.orderBy == 1 || requestScope.orderBySearch == 1 ? 'selected' : ''}>price low</option>
                                            <option value="2" ${requestScope.orderBy == 2 || requestScope.orderBySearch == 2 ? 'selected' : ''}>price high</option>
                                            <option value="3" ${requestScope.orderBy == 3 || requestScope.orderBySearch == 3 ? 'selected' : ''}>Rating</option>


                                        </select>
                                    </div>

                                    </form>
                                    <div class="menu-fulter">
                                        <ul class="product-list justify-content-center nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item" role="presentation"><a href="#" class="nav-link active" id="pills-product-one-tab" data-bs-toggle="tab" data-bs-target="#pills-product-one" role="tab" aria-selected="true"><i class="fa-solid fa-list"></i></a></li>
                                        </ul>
                                    </div>


                                </div>
                            </div>

                            <div class="tab-content" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-product-one" role="tabpanel" aria-labelledby="pills-product-one-tab">
                                    <div class="products ev-products">

                                        <c:forEach items="${listProduct}" var="p">

                                            <div class="product">
                                                <div class="product-img">

                                                    <a href="${pageContext.request.contextPath}/productdetail?pid=${p.productId}">
                                                        <img src="${pageContext.request.contextPath}/${p.image}" alt="product">
                                                    </a>

                                                    <div class="product-labels d-flex justify-content-between">
                                                        <span class="ev-offer">${p.discount}%</span>
                                                    </div>

                                                    <div class="p-option">
                                                        <ul class="d-flex align-items-center justify-content-end">
                                                            <li class="anim-hidden"><a href="#"><i class="fa-regular fa-eye"></i></a></li>
                                                            <li class="anim-hidden"><a href="#"><i class="fa-solid fa-heart"></i></a></li>
                                                            <li class="anim-hidden"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></li>
                                                        </ul>
                                                    </div>

                                                </div>
                                                <div class="product-text">

                                                    <ul class="product-stars d-flex">
                                                        <!--have start-->
                                                        <c:forEach begin="${1}" end="${p.rating}">
                                                            <li><i class="fa-sharp fa-solid fa-star"></i></li>
                                                            </c:forEach>

                                                        <!--have no start-->
                                                        <c:forEach begin="${p.rating + 1}" end="${5}">
                                                            <li><i class="fa-sharp fa-regular fa-star"></i></li>
                                                            </c:forEach>
                                                    </ul>

                                                    <h2 class="product-title"><a href="${pageContext.request.contextPath}/productdetail?pid=${p.productId}">${p.productName}</a></h2>
                                                    <ul class="d-flex"> 
                                                        <li><span>${p.price/1000}K&nbsp;VND</span></li>
                                                        <li> <del><span>${(p.price + (p.price*p.discount/100))/1000}K</span></del></li>
                                                    </ul>
                                                </div>
                                            </div>

                                        </c:forEach>

                                    </div>
                                </div>


                            </div>
                            <div class="ev-pagination">
                                <ul class="d-flex align-items-center">
                                    <li><a href="${requestURL}page=${totalPage}" class="angle"><i class="fa fa-angle-left"></i></a></li>
                                            <c:forEach begin="1" end="${pageControl.totalPage}" var="pageNumber">
                                        <li class="${pageNumber == pageControl.page ? 'active' : ''}">
                                            <a href="${pageControl.urlPattern}page=${pageNumber}">${pageNumber}</a>
                                        </li>
                                    </c:forEach>
                                    <li><a href="${requestURL}page=1" class="angle"><i class="fa fa-angle-right"></i></a></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- PRODUCT-SECTION END  -->


        <!--    FOOTER-SECTION START -->

        <jsp:include page="../../common/user/footer.jsp"></jsp:include>

            <!-- FOOTER-SECTION END  -->


            <!-- FOOTER-ICONTOP START  -->

            <div class="footer_iconTop">
                <a href="#" id="button"><i class="fa-solid fa-arrow-up"></i></a>
            </div>

            <!-- FOOTER-ICONTOP END  -->


            <!-- JS-SCRIPT START  -->

            <script src="${pageContext.request.contextPath}/js/jquery-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrop-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/swiper.js"></script>
        <script src="${pageContext.request.contextPath}/js/mmenu.js"></script>
        <script src="${pageContext.request.contextPath}/js/leaflet.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/userclick.js"></script>
        <script src="${pageContext.request.contextPath}/js/optionfilterorsort.js"></script>
        <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <!-- JS-SCRIPT END  -->

    </body>



    <!-- Mirrored from ethemestudio.com/demo/evani/shop-with-sidebar.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:02:50 GMT -->
</html>
