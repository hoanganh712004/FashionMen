<%-- 
    Document   : checkout
    Created on : Mar 6, 2024, 12:04:52 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx">

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

        <title>Checkout</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />

        <style>
            .checkout input {
                font-size: 18px;
                text-transform: capitalize;
                height: 55px;
                width: 100%;
                border: 1px solid #E5E5E5 !important;
                margin-bottom: 10px;
                margin-top: 10px;
                padding-left: 20px;
                border-radius: 5px;
            }


            .checkout textArea {
                font-size: 16px;
                height: 130px;
                width: 100%;
                padding: 15px 20px;
                border: 1px solid #E5E5E5 !important;
                border-radius: 5px;
                margin-top: 10px;
            }


            .thanhToan {
                font-size: 16px;
                font-weight: 800 !important;
                text-transform: uppercase;
                cursor: pointer;
                padding-left: 5px;
                -webkit-transition: all 0.3s ease;
                -moz-transition: all 0.3s ease;
                -ms-transition: all 0.3s ease;
                -o-transition: all 0.3s ease;
                transition: all 0.3s ease-in-out;
            }
        </style>

    </head>
    <body>

        <!-- HEADER-SECTION START  -->
        <jsp:include page="../../common/user/header.jsp"></jsp:include>

            <!-- HEADER-SECTION END  -->

            <!-- BANNER-SECTION START  -->

            <section class="hero-section ev-common-hero" style="background-image: url(images/common-banner/shape-1.png);"> 
                <div class="container-fluid">
                    <div class="ev-hero-content">
                        <h2>Checkout</h2>
                        <span><a href="${pageContext.request.contextPath}/home">Home</a> - <a href="${pageContext.request.contextPath}/filterproductservlet?category=0&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0">Shop</a> - Checkout</span>
                </div>
            </div>
        </section>

        <!-- BANNER-SECTION END  -->


        <!-- `CECKOUT-SECTION START  -->


        <!-- `CECKOUT-SECTION END  -->

        <form action="${pageContext.request.contextPath}/checkout" method="POST">
            <section class="checkout-section">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-6 col-md-6 col-12">
                            <div class="checkout-left">

                                <div class="checkout">
                                    <h4>Your Billing Address</h4>
                                    <input type="text" name="name" placeholder="Full Name" value="${sessionScope.account.fullName}" required>
                                    <input type="tel" name="phone" placeholder="Phone number" value="${sessionScope.account.phoneNumber}" required>
                                    <input type="email" name="email" placeholder="Email Address" value="${sessionScope.account.email}" required>
                                    <input type="text" name="address" placeholder="Address" value="${sessionScope.account.address}" required>
                                    <h4 style="padding-bottom: 0px;">Order Note</h4>
                                    <textarea class="check-message" id="message" name="note" placeholder="Order Note" required></textarea>
                                </div>



                            </div>
                        </div>
                        <div class="col-xl-6 col-md-6 col-12">
                            <div class="order-review-wrapper">

                                <div class="checkout-review-order">

                                    <div class="ev-order-review" style="margin-top: 0px;">
                                        <h4 class="order-title">Your Order</h4>
                                        <c:if test="${requestScope.errorPayment != null}">
                                            <span style="color: red; font-size: 16px">${requestScope.errorPayment}</span> <br>
                                            <span style="color: red; font-size: 16px">Bạn có muốn nạp thêm ko</span> 
                                             <span style="color: green; font-size: 16px">
                                                 <a href="views/user/item-page/qrcheck.jsp">Nạp</a>
                                             </span> 
                                        </c:if>
                                        <div class="ev-shop-table">
                                            <ul class="order-price d-flex align-items-center justify-content-between">
                                                <li><h6>Item</h6></li>
                                                <li><h6>Price</h6></li>
                                            </ul>

                                            <div class="cart-item">
                                                <c:forEach items="${sessionScope.cartItemList}" var="product">
                                                    <ul class="product-name-price d-flex align-items-center justify-content-between">
                                                        <li><span>${product.productName}</span></li>
                                                        <li><span> ${product.price}&nbsp;VNĐ</span></li>
                                                    </ul>
                                                </c:forEach>
                                            </div>


                                            <ul class="product-total d-flex align-items-center justify-content-between">
                                                <li><span>Total </span></li>
                                                <li><span> ${sessionScope.totalAllPrice} &nbsp;VNĐ</span></li>
                                            </ul>

                                            <div class="checkout-payment">
                                                <ul class="radio-items thanhToan">
                                                    <li class="radio-item">
                                                        <span>
                                                            <input type="radio" id="flexRadioDefault1" name="pay" value="1" checked>
                                                            <label for="flexRadioDefault1">Payment by balance</label>
                                                        </span>
                                                    </li>

                                                    <li class="radio-item">
                                                        <span>
                                                            <input type="radio" id="flexRadioDefault2" name="pay" value="2">
                                                            <label for="flexRadioDefault2">Payment on delivery</label>
                                                        </span>
                                                    </li>
                                                </ul>

                                            </div>    
                                            <div class="common-btn">
                                                <button type="submit" value="Submit Payment">Place Order</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>

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
        <!-- JS-SCRIPT END  -->


    </body>



    <!-- Mirrored from ethemestudio.com/demo/evani/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:03:05 GMT -->
</html>
