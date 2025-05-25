<%-- 
    Document   : category
    Created on : Feb 26, 2024, 6:27:19 PM
    Author     : PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="categories-section">
    <div class="container">
        <div class="ev-top-content">
            <span>Explore them all</span>
            <h2>Categories</h2>
            <p>Explore our extensive collection of men's clothing, featuring stylish and versatile options for every occasion</p>
        </div>
        <div class="categories-grid">
            <c:forEach items="${numberOfProductByCategory}" var="category">
                <div class="single-categorie">
                    <a href="${pageContext.request.contextPath}/filterproduct?category=${category.categoryId}&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0" class="categorie-link">
                        <div class="categorie-img">
                            <img src="${pageContext.request.contextPath}/${category.image}" alt="categories">
                        </div>
                        <div class="categorie-text">
                            <h5>${category.categoryName}</h5>
                            <c:forEach var="item" items="${productItems}">
                                <c:if test="${item.categoryId == category.categoryId}">
                                    <span>${item.totalProuduct} items</span>
                                </c:if>
                            </c:forEach>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
        <a href="${pageContext.request.contextPath}/filterproduct?category=0&minPrice=${sessionScope.minPrice}&maxPrice=${sessionScope.maxPrice}&orderBy=0" class="view-all">View All</a>
    </div>
</section>