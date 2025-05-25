<%-- 
    Document   : sign-in
    Created on : Mar 10, 2024, 10:13:18 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>/* 🌊 Hiệu ứng gradient động cho background */
    @keyframes backgroundAnimation {
        0% {
            background-position: 0% 50%;
        }
        25% {
            background-position: 50% 100%;
        }
        50% {
            background-position: 100% 50%;
        }
        75% {
            background-position: 50% 0%;
        }
        100% {
            background-position: 0% 50%;
        }
    }

    body {
        background: linear-gradient(45deg, #a2d2ff, #00b4d8, #48cae4, #0096c7, #0077b6, #023e8a);
        background-size: 300% 300%;
        animation: backgroundAnimation 6s infinite linear;
        font-family: 'Poppins', sans-serif;
        transition: background 0.8s ease-in-out;
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
    }

    /* 🟦 Tạo hiệu ứng nổi và đổ bóng nhẹ nhàng cho form */
    .card {
        border-radius: 12px;
        box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
        background: #ffffff;
        animation: fadeIn 0.8s ease-in-out;
    }

    /* 🎭 Animation vào form */
    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(-20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    /* 🎨 Header form với màu gradient đẹp */
    .card-header {
        background: linear-gradient(45deg, #0077b6, #0096c7);
        border-top-left-radius: 12px;
        border-top-right-radius: 12px;
        text-align: center;
        padding: 20px;
    }

    .card-header .ec-brand a img {
        width: 60px !important;
    }

    /* ✨ Hiệu ứng focus khi nhập input */
    .form-control {
        border-radius: 8px;
        border: 1px solid #0096c7;
        padding: 10px;
        transition: all 0.3s ease-in-out;
    }

    .form-control:focus {
        border-color: #00b4d8;
        box-shadow: 0 0 8px rgba(0, 180, 216, 0.5);
        transform: scale(1.02);
    }

    /* 🌟 Nút Sign In với hiệu ứng hover */
    .btn-primary {
        background: linear-gradient(45deg, #00b4d8, #0077b6);
        border: none;
        border-radius: 8px;
        padding: 12px;
        font-size: 18px;
        font-weight: bold;
        position: relative;
        overflow: hidden;
        transition: all 0.3s ease-in-out;
    }

    /* 🎇 Hiệu ứng ripple (sóng nước) khi bấm nút */
    .btn-primary:before {
        content: "";
        position: absolute;
        width: 300%;
        height: 300%;
        top: 50%;
        left: 50%;
        background: rgba(255, 255, 255, 0.3);
        transition: width 0.5s ease-in-out, height 0.5s ease-in-out, top 0.5s ease-in-out, left 0.5s ease-in-out;
        transform: translate(-50%, -50%);
        border-radius: 50%;
        opacity: 0;
    }

    .btn-primary:active:before {
        width: 0;
        height: 0;
        opacity: 1;
    }

    /* 🎭 Hover hiệu ứng bóng và sáng dần */
    .btn-primary:hover {
        background: linear-gradient(45deg, #0096c7, #005f73);
        box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.3);
        transform: scale(1.05);
    }

    /* ✅ Hiệu ứng checkbox */
    input[type="checkbox"] {
        accent-color: #00b4d8;
        transform: scale(1.2);
        transition: all 0.3s ease-in-out;
    }

    /* 🚀 Hover khi checkbox được chọn */
    input[type="checkbox"]:checked {
        transform: scale(1.4);
    }

    /* 🏷️ Link đăng ký có hiệu ứng */
    .sign-upp a {
        color: #0077b6;
        font-weight: bold;
        transition: color 0.3s ease-in-out;
    }

    .sign-upp a:hover {
        color: #00b4d8;
        text-decoration: underline;
    }

    /* Hiệu ứng lỗi */
    .error-message {
        color: white;
        background: #ff4d4d;
        padding: 10px;
        border-radius: 5px;
        font-weight: bold;
        box-shadow: 0 4px 10px rgba(255, 77, 77, 0.4);
        display: inline-block;
        animation: fadeIn 0.5s ease-in-out;
        margin-left: 33%;
        margin-bottom: 5%;
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(-10px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    /* Hiệu ứng rung nếu có lỗi */
    @keyframes shake {
        0% {
            transform: translateX(0);
        }
        25% {
            transform: translateX(-5px);
        }
        50% {
            transform: translateX(5px);
        }
        75% {
            transform: translateX(-5px);
        }
        100% {
            transform: translateX(0);
        }
    }

    #errorBox:empty {
        display: none;

    }
</style>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>Login In</title>

        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.googleapis.com/" />
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;400;500;600;700;800&amp;family=Poppins:wght@300;400;500;600;700;800;900&amp;family=Roboto:wght@400;500;700;900&amp;display=swap"
            rel="stylesheet"
            />

        <link
            href="${pageContext.request.contextPath}/css/materialdesignicons.min.css"
            rel="stylesheet"
            />

        <!-- Ekka CSS -->
        <link id="ekka-css" rel="stylesheet" href="${pageContext.request.contextPath}/css/ekka.css" />

        <!-- FAVICON -->
        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">
    </head>

    <body class="sign-inup" id="body">

        <% 
        String usernameCookieSaved = "";
        String passwordCookieSaved = "";
       
        Cookie[] cookies = request.getCookies(); // get data cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("COOKIE_USERNAME")) {
                    usernameCookieSaved = cookie.getValue();
                }
                if (cookie.getName().equals("COOKIE_PASSWORD")) {
                    passwordCookieSaved = cookie.getValue();
                }
            }
        }
        %>
        <div class="container d-flex align-items-center justify-content-center form-height-login pt-24px pb-24px">

            <div class="row justify-content-center">

                <div class="col-lg-6 col-md-10">

                    <div class="card">
                        <div class="card-header bg-primary">
                            <div class="ec-brand">
                                <a href="${pageContext.request.contextPath}/home" title="Ekka">
                                    <img
                                        width="50px !important"
                                        class="ec-brand-icon"
                                        src="${pageContext.request.contextPath}/images/head/logo/shape-1.png"
                                        alt=""
                                        />
                                </a>
                            </div>
                        </div>

                        <div class="card-body p-5">
                            <h4 class="text-dark mb-5">Login In</h4>

                            <form action="${pageContext.request.contextPath}/login" method="POST">
                                <div class="row">
                                    <c:if test="${username == null}">
                                        <div class="form-group col-md-12 mb-4">
                                            <input placeholder="Username" class="form-control" type="text" name="username" value="<%=usernameCookieSaved%>" required/>
                                        </div>
                                    </c:if>
                                    <c:if test="${username != null}">
                                        <div class="form-group col-md-12 mb-4">
                                            <input placeholder="Username" class="form-control" type="text" name="username" value="${username}" required/>
                                        </div>
                                    </c:if>

                                    <div class="form-group col-md-12">
                                        <input class="form-control" placeholder="Password" type="password" name="password" value="<%=passwordCookieSaved%>" required/>
                                    </div>

                                    <div class="col-md-12">
                                        <!-- Sử dụng d-flex để căn chỉnh các phần tử ngang nhau -->
                                        <div class="d-flex justify-content-between my-2">
                                            <div class="d-inline-block">
                                                <input name="rememberMe" id="remember" type="checkbox"/>
                                                <label for="remember"> Remember me</label>
                                            </div>
                                            <div class="d-inline-block">
                                                <a href="${pageContext.request.contextPath}/views/common/user/forget.jsp">Forgot password</a>
                                            </div>
                                        </div>

                                        <c:if test="${errorLogin != null}">
                                            <div id="errorBox" class="error-message">${errorLogin}</div>
                                        </c:if>

                                        <button type="submit" class="btn btn-primary btn-block mb-4">
                                            Sign In
                                        </button>

                                        <p class="sign-upp">
                                            Don't have an account yet?
                                            <a class="text-blue" href="${pageContext.request.contextPath}/views/common/user/register.jsp">Sign Up</a>
                                        </p>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>



        <!-- Javascript -->
        <script>
            // Nếu có lỗi, thêm hiệu ứng rung
            document.addEventListener("DOMContentLoaded", function () {
                let errorBox = document.getElementById("error-message");
                if (errorBox) {
                    errorBox.style.animation = "shake 0.5s ease-in-out";
                }
            });
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
    </body>

</html>