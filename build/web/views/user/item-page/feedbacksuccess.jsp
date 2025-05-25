<%-- 
    Document   : shoppingcart
    Created on : Mar 5, 2024, 12:48:11 AM
    Author     : PC
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dal.ProductDAO"%>
<%@page import="model.Size"%>
<%@page import="model.Color"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
                        <h4>Cảm ơn bạn đã gửi những lời góp ý chân thành nhất đến chúng tôi, chúng tôi sẽ xem đó như là 1 động lực để ngày càng phát triển hơn nữa. Chúc bạn có 1 ngày tốt lành</h4>
                </div>
            </div>
        </section>

        <!-- BANNER-SECTION END  -->
        <jsp:include page="../../common/user/footer.jsp"></jsp:include>


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


</html>
