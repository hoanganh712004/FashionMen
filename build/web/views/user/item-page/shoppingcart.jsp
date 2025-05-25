<%-- 
    Document   : shoppingcart
    Created on : Mar 5, 2024, 12:48:11 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="zxx">

    <!-- Mirrored from ethemestudio.com/demo/evani/shopping-cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:01:57 GMT -->
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/awesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/unpkg.swiper.css">

        <link rel="stylesheet" type="${pageContext.request.contextPath}/text/css" href="css/leaflet.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/webfonts/all.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/webfonts/fontawesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

        <title>Shopping Cart</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />

    </head>
    <body>

        <!-- HEADER-SECTION START  -->

        <jsp:include page="../../common/user/header.jsp"></jsp:include>

            <!-- HEADER-SECTION END  -->

            <!-- BANNER-SECTION START  -->

            <section class="hero-section ev-common-hero" style="background-image: url(images/common-banner/shape-1.png);"> 
                <div class="container-fluid">
                    <div class="ev-hero-content">
                        <h2>Shopping Cart</h2>
                        <span><a href="${pageContext.request.contextPath}/home">Home -</a> <a href="${pageContext.request.contextPath}/filterproduct?category=0&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0">Shop</a> - Cart</span>
                </div>
            </div>
        </section>

        <!-- BANNER-SECTION END  -->


        <!-- `SHOPPING-CART-SECTION START  -->



        <section class="wishlist-section shopping-section">
            <div class="container">
                <div class="wishlist-item">
                    <h5>Your Cart Items</h5>
                    <div class="wishlist-table">

                        <table class="table-wrapper">
                            <thead class="t-head">
                                <tr>
                                    <th><span>Item Name</span></th>
                                    <th><span>Size</span></th>
                                    <th><span>Color</span></th>
                                    <th><span>Price</span></th>
                                    <th><span>Quantity</span></th>
                                    <th><span>Total</span></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody class="t-body">

                                <c:set var="stt" value="0"/>
                                <c:forEach var="c" items="${cartItemList}">
                                    <tr class="wishlist-tr">
                                        <td class="d-lg-flex d-lg-block align-items-center">
                                            <a href="${pageContext.request.contextPath}/productdetail?pid=${c.productId}">
                                                <img style="border-radius: 5%" width="84px" height="96px" src="${pageContext.request.contextPath}/${c.itemName}" alt="shopping">
                                            </a>
                                            <h2>
                                                <a href="${pageContext.request.contextPath}/productdetail?pid=${c.productId}">${c.productName}</a>


                                            </h2>

                                        </td>

                                        <td style="color: black"><span>${c.size.sizeOption}</span></td>
                                        <td style="color: black"><span>${c.color.color}</span></td>
                                        <td style="color: black"><span>${c.price}</span></td>
                                        <td>
                                            <c:set var="stt" value="${stt+1}"/>
                                            <form id="f${c.productId}" action="${pageContext.request.contextPath}/shoppingcart" method="POST">
                                                <input type="hidden" name="pid" value="${c.productId}"/>
                                                <input type="hidden" name="qid" value="${c.quantity}"/>
                                                <input type="hidden" name="sttid" value="${stt}"/>
                                                <div class="pro-counter align-items-center ">
                                                    <input type="number" name="quantity" value="${c.quantity}" readonly> 
                                                </div>

                                                <span style="color: red"></span>

                                                <td><span class="cart-total">${c.totalMoney}&nbsp;VNĐ</span></td>
                                                <td>
                                                    <a onclick="deleteProduct(${c.productId})" href="javascript:void(0)" class="cart-btn"><i class="fa-solid fa-xmark"></i></a>
                                                </td>
                                            </form>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="shoping-cart-btn d-md-flex d-md-block align-items-center justify-content-md-between justify-content-md-start">
                        <div class="shoping-cart-btn-left">
                            <div class="checkout-text">
                                <div class="btn_box shoping-btn">
                                    <a href="${pageContext.request.contextPath}/filterproduct">Continue Shopping</a>
                                </div>
                            </div>    
                        </div>
                        <c:if test="${cartItemList != null}">
                            <form id="fclear" action="${pageContext.request.contextPath}/shoppingcart?pid=0" method="post">
                                <div class="shoping-cart-btn-right d-flex align-items-center">
                                    <a onclick="clearAll()" href="javascript:void(0)" class="view-all-two shoping-btn-2">Clear All</a>
                                </div>
                            </form>

                        </c:if>
                    </div>
                </div>
            </div>
        </section>

        <!-- `SHOPPING-CART-SECTION END  -->


        <section class="calculate-shipping">
            <div class="container">
                <div class="row">
                    <div class="col-xl-4 col-md-6 col-sm-12 col-12">
                    </div>
                    <div class="col-xl-4 col-md-6 col-sm-12 col-12">
                        <div class="calculate-item coupon-code-items">
                        </div>
                    </div>
                    <div class="col-xl-4 col-md-6 col-sm-12 col-12">
                        <div class="calculate-item">
                            <div class="select-item">
                                <div class="chectout-cart">

                                    <ul class="grand-total">
                                        <li class="d-flex align-items-center justify-content-between"><h6>Grand Total</h6><span>${sessionScope.totalAllPrice}&nbsp;VNĐ</span></li>
                                    </ul>
                                    <div class="checkout-text">
                                        <div class="btn_box checkout-btn">

                                            <a href="<%= request.getContextPath() %>/checkout">Proceed To Checkout</a>

                                        </div>
                                        <span >Checkout with multiple address</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- PRODUCT-SECTION END  -->

        <!--    FOOTER-SECTION START -->

        <jsp:include page="../../common/user/footer.jsp"></jsp:include>

            <!--    FOOTER-SECTION END  -->

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
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/shoppingcart.js"></script>

        <!-- JS-SCRIPT END  -->


    </body>


    <!-- Mirrored from ethemestudio.com/demo/evani/shopping-cart.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:01:58 GMT -->
</html>
