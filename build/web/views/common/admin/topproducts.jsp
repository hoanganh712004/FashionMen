<%-- 
    Document   : topproducts
    Created on : Mar 3, 2024, 6:02:07 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="card card-default ec-card-top-prod">
    <div class="card-header justify-content-between">
        <h2>Top Products</h2>
    </div>

    <div class="card-body mt-10px mb-10px py-0">

        <c:forEach items="${top10ProductBestSelling}" var="product">
            <div class="row media d-flex pt-15px pb-15px">
                <div
                    class="col-lg-3 col-md-3 col-2 media-image align-self-center rounded"
                    >
                    <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}"/>
                    <img
                        src="${pageContext.request.contextPath}/${product.image}"
                        alt="customer image"
                        /></a>
                </div>
                <div
                    class="col-lg-9 col-md-9 col-10 media-body align-self-center ec-pos"
                    >
                    <a href="${pageContext.request.contextPath}/productdetail?pid=${product.productId}">
                        <h6 class="mb-10px text-dark font-weight-medium">
                            ${product.productName}
                        </h6>
                    </a>
                    <p class="float-md-right sale">
                        <span class="mr-2">${product.quantitySold}</span>Sales
                    </p>
                    <p class="d-none d-md-block">
                        ${product.desciption}
                    </p>
                    <p class="mb-0 ec-price">
                        <span class="text-dark">${product.price}&nbsp;VND</span>
                        <del>${product.price + product.price * product.discount}&nbsp;VND</del>
                    </p>
                </div>
            </div>
        </c:forEach>

    </div>
</div>