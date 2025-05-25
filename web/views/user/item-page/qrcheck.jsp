<%-- 
    Document   : contactus
    Created on : Mar 10, 2024, 1:18:26 PM
    Author     : PC
--%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx">
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

        <title>Contact Us</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />
        <style>
            .feedback-message {
                display: block;
                padding: 12px 16px;
                margin: 10px 0;
                border-radius: 8px;
                font-size: 20px;
                font-weight: bold;
                text-align: center;
                max-width: 400px;
                margin-left: auto;
                margin-right: auto;
                transition: all 0.3s ease-in-out;
            }

            .success {
                background-color: #d4edda; /* Xanh nh?t */
                color: #155724; /* Xanh ??m */
                border: 1px solid #c3e6cb;
            }

            .error {
                background-color: #f8d7da; /* ?? nh?t */
                color: #721c24; /* ?? ??m */
                border: 1px solid #f5c6cb;
            }

            .fade-out {
                opacity: 0;
                transition: opacity 1s ease-in-out;
            }

        </style>
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
                        <h2>Check QR</h2>
                        <span><a href="${pageContext.request.contextPath}/home">Home</a></span>
                </div>
                <div>
                    <c:if test="${feedbackSuccess != null}">
                        <div class="feedback-message success" id="feedbackMessage">
                            ${feedbackSuccess}
                        </div>
                    </c:if>
                </div>
            </div>
        </section>

        <!-- BANNER-SECTION END  -->

        <!--  CONTACT-US-SECTION START  -->

        <section class="ev-contact-us-section" style="text-align: center;">
            <img src="${pageContext.request.contextPath}/images/checkqr/nap.jpg" alt="alt" style="display: block; margin: 0 auto; width: 30%;"/>
        </section>


        <!--  CONTACT-US-SECTION END  -->

        <!-- CONTACT-MAP-SECTION START  -->

        <!-- CONTACT-MAP-SECTION START  -->

        <!--    FOOTER-SECTION START -->

        <jsp:include page="../../common/user/footer.jsp"></jsp:include>

            <!--    FOOTER-SECTION END  -->

            <!-- FOOTER-ICONTOP START  -->

            <div class="footer_iconTop">
                <a href="#" id="button"><i class="fa-solid fa-arrow-up"></i></a>
            </div>

            <!-- FOOTER-ICONTOP END  -->

            <!-- JS-SCRIPT START  -->
            <script>
                // T? ??ng ?n thông báo sau 3 giây
                setTimeout(function () {
                    let message = document.getElementById("feedbackMessage");
                    if (message) {
                        message.classList.add("fade-out");
                        setTimeout(() => message.style.display = "none", 1000);
                    }
                }, 10000);
            </script>
            <script src="${pageContext.request.contextPath}/js/jquery-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrop-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/js/swiper.js"></script>
        <script src="${pageContext.request.contextPath}/js/mmenu.js"></script>
        <script src="${pageContext.request.contextPath}/js/leaflet.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/profile.js"></script>
        <script></script>

        <!-- JS-SCRIPT END  -->
    </body>

</html>
