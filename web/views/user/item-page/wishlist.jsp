<%-- 
    Document   : wishlist
    Created on : Mar 6, 2024, 1:48:56 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="zxx">
    <!-- Mirrored from ethemestudio.com/demo/evani/wishlist.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:03:05 GMT -->
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/awesome.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/unpkg.swiper.css" />

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/leaflet.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/webfonts/all.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/webfonts/fontawesome.min.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />

        <title>Wish List</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">
    </head>
    <body>
        <!-- HEADER-SECTION START  -->
        <jsp:include page="../../common/user/header.jsp"></jsp:include>

            <!-- HEADER-SECTION END  -->

            <!-- BANNER-SECTION START  -->

            <section
                class="hero-section ev-common-hero"
                style="background-image: url(images/common-banner/shape-1.png)"
                >
                <div class="container-fluid">
                    <div class="ev-hero-content">
                        <h2>Wish List</h2>
                        <span><a href="${pageContext.request.contextPath}/home">Home</a> - Wishlist</span>
                </div>
            </div>
        </section>

        <!-- BANNER-SECTION END  -->

        <!-- `WISHLIST-SECTION START  -->

        <section class="wishlist-section">
            <div class="container">
                <div class="wishlist-item">
                    <h5>Your Wishlist</h5>
                    <div class="wishlist-table">
                        <table class="table-wrapper">
                            <thead class="t-head">
                                <tr>
                                    <th><span>Item Name</span></th>
                                    <th><span>Price</span></th>
                                    <th><span>Category</span></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody class="t-body">

                                <c:forEach items="${sessionScope.wishList}" var="product">
                                    <tr class="wishlist-tr">
                                        <td class="d-lg-flex d-lg-block align-items-center">
                                            <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}">
                                                <img width="84px" height="96px" style="border-radius: 5px" src="${pageContext.request.contextPath}/${product.thumbnails.get(0)}" alt="wishlist" />
                                            </a>
                                            <h2>
                                                <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}">${product.productName}</a>
                                            </h2>
                                        </td>
                                        <td><span>${product.price}&nbsp;VNĐ</span></td>
                                        <td>${product.category.categoryName}</td>
                                        <td>
                                            <ul
                                                class="product-cart d-flex align-items-center justify-content-between"
                                                >
                                                <li class="common-btn table-btn">
                                                    <button>
                                                        <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}"><span>Add to Cart</span></a><i class="fa-solid fa-cart-shopping"></i>
                                                    </button>
                                                </li>
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/wishlist?action=delete&pid=${product.productId}"><i class="fa-solid fa-xmark"></i></a>
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                </c:forEach>



                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

        <!-- `WISHLIST-SECTION END  -->

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

    <!-- Mirrored from ethemestudio.com/demo/evani/wishlist.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:03:06 GMT -->
</html>
