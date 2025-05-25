<%-- 
    Document   : productdetail
    Created on : Feb 27, 2024, 4:21:12 PM
    Author     : PC
--%>
<%@page import="model.Product"%>
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

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />

        <title> Product Detail</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">

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
                <h2>Product Detail</h2>
                <span><a href="${pageContext.request.contextPath}/home">Home</a> - <a href="${pageContext.request.contextPath}/filterproduct"> product - </a> Detail</span>
            </div>
        </div>
    </section>

    <!-- BANNER-SECTION END  -->


    <section class="single-product-section">
        <div class="container">
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-md-12 col-12">
                    <div class="product-img-glarry">
                        <div class="product-glarry-slider">
                            <div class="slider__flex">
                                <div class="slider__col">
                                    <div class="slider__thumbs">
                                        <!--anh thu nho ben canh-->
                                        <div class="swiper-container"> 
                                            <div class="swiper-wrapper">
                                                <c:forEach items="${product.thumbnails}" var="i">
                                                    <div class="swiper-slide">
                                                        <div class="slider__image"><img src="${pageContext.request.contextPath}/${i}" alt="products"></div>
                                                    </div> 
                                                </c:forEach>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="slider__images">
                                    <!--thanh truot-->
                                    <div class="swiper-container">
                                        <div class="swiper-wrapper">
                                            <c:forEach items="${product.thumbnails}" var="i">
                                                <div class="swiper-slide">
                                                    <div class="slider__image"><img src="${pageContext.request.contextPath}/${i}" alt="products"></div>
                                                </div>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-xl-6 col-lg-6 col-md-12 col-12">
                    <div class="product-summery">
                        <div class="single-product-inner">
                            <h6><a href="${pageContext.request.contextPath}/filterproduct?category=${product.category.categoryId}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0">Fashion, ${product.category.categoryName}</a></h6>
                            <h3><a href="#">${product.productName}</a></h3>
                            <ul class="single-product-price d-flex">
                                <li><h3>${product.price}&nbsp;VND</h3></li>
                                <li><h4>${(product.price + (product.price*product.discount/100))}&nbsp;VND</h4></li>
                            </ul>
                            <div class="product-review d-flex justify-content-between">
                                <ul class="d-flex">
                                    <c:forEach begin="${1}" end="${productRating}">
                                        <!--have start-->
                                        <li><i class="fa-sharp fa-solid fa-star"></i></li>
                                        </c:forEach>
                                        <c:forEach begin="${productRating + 1}" end="${5}">
                                        <li><i class="fa-sharp fa-regular fa-star"></i></li>
                                        </c:forEach>

                                    <li><a href="#">${numberRating} Review</a></li>
                                </ul>
                                <div class="product-available" id="product-available"><span> Available : </span> <span>
                                        ${product.quantityStock}
                                    </span></div>
                                <div class="product-available"><span> Sold : </span> <span>
                                        ${product.quantitySold}
                                    </span></div>
                            </div>
                            
                            <form id="f" action="${pageContext.request.contextPath}/productdetail" method="post">

                                <ul class="product-size d-flex align-items-center">
                                    <li><span>Color</span></li>
                                        <c:forEach items="${listColor}" var="i">
                                        <li class="color${i.colorId} colors" onclick="userOptionColor(this, ${i.colorId}, ${product.productId})">
                                            <a class="click-color" href="javascript:void(0)"><span>${i.color}</span></a>
                                        </li>
                                    </c:forEach>
                                </ul>

                                <ul class="product-size d-flex align-items-center">
                                    <li><span>Size</span></li>
                                        <c:forEach items="${listSize}" var="size">
                                            <li class="size${size.sizeId} sizes" onclick="userOptionSize(this, ${size.sizeId}, ${product.productId})"><a class="click-size" href="javascript:void(0)"><span>${size.sizeOption}</span></a></li>
                                        </c:forEach>
                                </ul>

                            </form>


                            <span style="font-size: 16px;color: red">${requestScope.errorAddToCart}</span>
                            <span style="font-size: 16px;color: green">${requestScope.addCartSucc}</span>
                            <form action="${pageContext.request.contextPath}/shoppingcart" method="get" id="formAddToCart">

                                <ul class="product-add-cart d-flex align-items-center">
                                    <li>
                                        <div class="pro-counter d-flex align-items-center justify-content-between">
                                            <button type="button" onclick="selectQuantityTru()">-</button>
                                            <input name="quantity"  data-value type="number" id="quantity" value="${param.quantity != null ? param.quantity : 0}" min="${0}" readonly>
                                            <button type="button" onclick="selectQuantityCong()">+</button>
                                        </div>
                                    </li>
                                    <li class="btn_box add-cart-btn">
                                        <a onclick="addToCart()" href="javascript:void(0)">Add to Cart <i class="fa-solid fa-cart-shopping"></i></a>
                                    </li>
                                    <li class="btn_box heart-btn">
                                        <a href="${pageContext.request.contextPath}/wishlist?action=add&pid=${param.pid != null ? param.pid : param.productid}"><i class="fa-solid fa-heart"></i></a>
                                    </li>

                                </ul>

                                <ul class="product-social d-flex align-items-center">
                                    <li><h6>Share</h6></li>
                                    <li><a href="https://www.facebook.com/"><i class="fa-brands fa-facebook-f"></i></a></li>
                                    <li><a href="https://twitter.com/"><i class="fa-brands fa-twitter"></i></a></li>
                                    <li><a href="https://www.google.com/"><i class="fa-brands fa-google-plus-g"></i></a></li>
                                    <li><a href="https://linkedin.com/"><i class="fa-brands fa-linkedin-in"></i></a></li>
                                </ul>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="product-tabs">
                <div class="menu-fulter">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation"><a href="#" class="nav-link active" id="pills-home-tab" data-bs-toggle="tab" data-bs-target="#pills-home" role="tab" aria-selected="true">Item Description</a></li>

                        <li class="nav-item" role="presentation"><a href="#" class="nav-link " id="pills-contact-tab" data-bs-toggle="tab" data-bs-target="#pills-contact" role="tab" aria-selected="true">Item Review</a></li>
                    </ul>
                </div>
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                        <div class="product-discription">
                            <ul class="single-product-features">
                                <%
                                    Product p = (Product) request.getAttribute("product");
                                    String des = p.getDesciption();
                                    if (des != null) {
                                        String[] listDes = des.split("\\*");
                                        for (int i = 0; i < listDes.length; i++) {
                                %>
                                <li><i class="fa-solid fa-angles-right"></i><span> <%= listDes[i]%> </span></li>
                                        <%
                                                }
                                            }
                                        %>
                            </ul>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">

                        <div class="review-form">
                            <div class="ev-comments">
                                <div class="comment-wrapper">
                                    <div class="comment-headding">
                                        <h3>${listComment.size()} Review</h3>
                                    </div>

                                    <div class="comment-form">
                                        <h4>Để lại bình luận của bạn</h4>
                                        <form action="${pageContext.request.contextPath}/addcomment?pid=${product.productId}" method="POST">
                                            <div class="form-group">
                                                <label for="content">Bình luận</label>
                                                <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="rating">Đánh giá</label>
                                                <select class="form-control" id="rating" name="rating">
                                                    <option value="5">⭐⭐⭐⭐⭐</option>
                                                    <option value="4">⭐⭐⭐⭐</option>
                                                    <option value="3">⭐⭐⭐</option>
                                                    <option value="2">⭐⭐</option>
                                                    <option value="1">⭐</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Gửi bình luận</button>
                                        </form>
                                    </div>

                                    <c:forEach items="${listComment}" var="comment">
                                        <div class="comment-inner  d-lg-flex d-md-block">
                                            <div class="comment-img">
                                                <img class="rounded-circle img-fluid" style="max-width: 80px"  src="${pageContext.request.contextPath}/${comment.user.image}" alt="product">
                                            </div>
                                            <div class="comment-text">
                                                <ul class="comment-top d-flex justify-content-between">
                                                    <li>
                                                        <h6></h6>
                                                        <span></span>

                                                        <c:forEach begin="${1}" end="${comment.rating}">
                                                        <li><i class="fa-sharp fa-solid fa-star"></i></li>
                                                        </c:forEach>
                                                        <c:forEach begin="${comment.rating + 1}" end="${5}">
                                                        <li><i class="fa-sharp fa-regular fa-star"></i></li>
                                                        </c:forEach>
                                                    </li>

                                                </ul>
                                                <p class="preview-text">${comment.content}</p>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- `PRODUCT-SECTION START  -->


    <section class="popular-product-section single-popular-product-section">
        <div class="container">
            <div class="heading-text">
                <h3>Related Items</h3>
            </div>
            <div class="swiper products single-product-slider">
                <div class="swiper-wrapper">
                    <c:forEach items="${relatedProduct}" var="product">
                        <div class="swiper-slide product single-slide">
                            <div class="product-img">
                                <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}">
                                    <img src="${pageContext.request.contextPath}/${product.image}" alt="product">
                                </a>
                                <div class="product-labels d-flex justify-content-between">
                                    <span class="ev-offer">${product.discount}%</span>
                                    <span class="ev-hot">
                                        <c:if test="${product.discount >= 50}">
                                            hot
                                        </c:if>
                                    </span>
                                </div>
                                <div class="p-option">
                                    <ul class="d-flex align-items-center justify-content-end">
                                        <li class="anim-hidden"><a href="#"><i class="fa-regular fa-eye"></i></a></li>
                                        <li class="anim-hidden"><a href="${pageContext.request.contextPath}/wishlist?action=add&pid=${product.productId}"><i class="fa-solid fa-heart"></i></a></li>
                                        <li class="anim-hidden"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="product-text">
                                <ul class="product-stars d-flex">
                                    <c:forEach begin="${1}" end="${product.rating}">
                                        <li><i class="fa-sharp fa-solid fa-star"></i></li>
                                        </c:forEach>
                                        <c:forEach begin="${product.rating + 1}" end="${5}">
                                        <li><i class="fa-sharp fa-regular fa-star"></i></li>
                                        </c:forEach>
                                </ul>
                                <h2 class="product-title"><a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}">${product.productName}</a></h2>
                                <ul class="d-flex align-items-center">
                                    <li><span>${product.price/1000}K&nbsp;VND</span></li>
                                    <li> <del><span>${(product.price + (product.price*product.discount/100))/1000}K&nbsp;VND</span></del></li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="swiper-pagination"></div>
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
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/addtocart.js"></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
    <script src="${pageContext.request.contextPath}/js/delete.js"></script>
    <!-- JS-SCRIPT END  -->


</body>


<!-- Mirrored from ethemestudio.com/demo/evani/single-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 20:03:05 GMT -->
</html>
