<%-- 
    Document   : popular_product
    Created on : Feb 26, 2024, 6:35:28 PM
    Author     : PC
--%>

<%@page import="model.Product"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<section class="popular-product-section">

    <div class="container">
        <div class="ev-top-content">
            <h2>Top 10 Products Best Selling </h2>
            <p>Discover our bestselling products, curated to meet your needs and exceed your expectations</p>
        </div>
        <div class="swiper products">
            <div class="product-slider">
                <div class="swiper-wrapper">

                    <c:forEach items="${top10Bestseller}" var="p">
                        <div class="swiper-slide product single-slide">
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
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </div>
</section>

