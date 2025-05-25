<%-- 
    Document   : product
    Created on : Feb 26, 2024, 6:30:05 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="product-section">
    <div class="container">
        <div class="ev-top-content">
            <h2>Top 6 New Products</h2>
            <p>Our store has just launched an exciting new line of men's clothing!</p>
        </div>


        <div class="menu-fulter">
            <ul class=" justify-content-center nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation"><a href="#" class="nav-link active" id="pills-all-tab" data-bs-toggle="tab" data-bs-target="#pills-home" role="tab" aria-selected="true">ALL</a></li>
                    <c:forEach items="${top6NewProductByCategory}" var="category">
                    <li class="nav-item" role="presentation">
                        <a href="#" class="nav-link" id="pills-profile-tab" data-bs-toggle="tab" data-bs-target="#pills-profile${category.key.categoryId}" role="tab" aria-selected="true">
                            ${category.key.categoryName}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- tab all-->
        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-all-tab">
                <div class="products ev-products">
                    <c:forEach items="${listTop6NewProductAll}" var="p">

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
                                        <c:forEach begin="${1}" end="${p.rating}">
                                        <li><i class="fa-sharp fa-solid fa-star"></i></li>
                                        </c:forEach>
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

            <!--cac tab con lai-->
            <c:forEach items="${top6NewProductByCategory}" var="category">
                <div class="tab-pane fade" id="pills-profile${category.key.categoryId}" role="tabpanel" aria-labelledby="pills-profile-tab">
                    <div class="products ev-products">
                        <c:forEach items="${category.value}" var="p">

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
                                            <li class="anim-hidden"><a href="${pageContext.request.contextPath}/wishlist?action=add&productid=${p.productId}"><i class="fa-solid fa-heart"></i></a></li>
                                            <li class="anim-hidden"><a href="#"><i class="fa-solid fa-cart-shopping"></i></a></li>
                                        </ul>
                                    </div>

                                </div>
                                <div class="product-text">

                                    <ul class="product-stars d-flex">
                                        <c:forEach begin="${1}" end="${p.rating}">
                                            <li><i class="fa-sharp fa-solid fa-star"></i></li>
                                            </c:forEach>
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
            </c:forEach>
        </div>

        <a href="${pageContext.request.contextPath}/filterproduct?category=0&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0" class="view-all">View All</a>
    </div>
</section>