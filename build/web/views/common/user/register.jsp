<%-- 
    Document   : sign-up
    Created on : Mar 10, 2024, 10:33:18 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
    <!-- Mirrored from maraviyainfotech.com/projects/ekka/ekka-v37/ekka-admin/sign-up.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 02 Mar 2024 22:07:43 GMT -->
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>Register</title>

        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.googleapis.com/" />
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;400;500;600;700;800&amp;family=Poppins:wght@300;400;500;600;700;800;900&amp;family=Roboto:wght@400;500;700;900&amp;display=swap"
            rel="stylesheet"
            />

        <link
            href="${pageContext.request.contextPath}/css/materialdesignicons.min.css"rel="stylesheet"
            />

        <!-- Ekka CSS -->
        <link id="ekka-css" rel="stylesheet" href="${pageContext.request.contextPath}/css/ekka.css" />

        <!-- FAVICON -->
        <link rel="icon" href="${pageContext.request.contextPath}/images/head/logo/shape-1.png">

        <style>
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

            /* 📌 Hiệu ứng nổi và đổ bóng cho form */
            .card {
                border-radius: 12px;
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                background: #ffffff;
                padding: 20px;
                width: 400px;
                animation: fadeIn 0.8s ease-in-out;
            }

            /* 🎭 Animation khi form xuất hiện */
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

            /* ✨ Hiệu ứng khi nhập input */
            .form-control {
                border-radius: 8px;
                border: 1px solid #0096c7;
                padding: 12px;
                transition: all 0.3s ease-in-out;
                font-size: 16px;
            }

            .form-control:focus {
                border-color: #00b4d8;
                box-shadow: 0 0 8px rgba(0, 180, 216, 0.5);
                transform: scale(1.02);
            }

            /* 🌟 Nút Sign Up với hiệu ứng hover */
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

            /* 🏷️ Link chuyển sang trang đăng nhập */
            .sign-upp a {
                color: #0077b6;
                font-weight: bold;
                transition: color 0.3s ease-in-out;
            }

            .sign-upp a:hover {
                color: #00b4d8;
                text-decoration: underline;
            }

            /* 🚨 Hiển thị lỗi (màu đỏ) */
            .error-message {
                color: white;
                background: #ff4d4d;
                padding: 10px;
                border-radius: 5px;
                font-weight: bold;
                box-shadow: 0 4px 10px rgba(255, 77, 77, 0.4);
                text-align: center;
                animation: fadeIn 0.5s ease-in-out, shake 0.3s ease-in-out;
                display: inline-block;
                width: 55%;
                margin-left: 23%;
                margin-bottom: 10px;
            }

            /* ✅ Hiển thị thành công (màu xanh lá) */
            .success-message {
                color: white;
                background: #28a745;
                padding: 10px;
                border-radius: 5px;
                font-weight: bold;
                box-shadow: 0 4px 10px rgba(40, 167, 69, 0.4);
                text-align: center;
                animation: fadeIn 0.5s ease-in-out;
                display: inline-block;
                width: 55%;
                margin-left: 23%;
                margin-bottom: 10px;
            }

            /* 🎭 Animation hiển thị dần */
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

            /* 🔄 Animation rung nếu có lỗi */
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

            /* Ẩn hộp thông báo khi không có nội dung */
            #errorBox:empty, #successBox:empty {
                display: none;
            }


        </style>

    </head>

    <body class="sign-inup" id="body">
        <div
            class="container d-flex align-items-center justify-content-center form-height pt-24px pb-24px"
            >
            <div class="row justify-content-center">
                <div class="col-lg-4 col-md-10" style="width: 385px;">
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
                            <h4 class="text-dark mb-5">Sign Up</h4>

                            <form action="${pageContext.request.contextPath}/register" method="POST">
                                <div class="row">

                                    <div class="form-group col-md-12 mb-4">
                                        <input placeholder="Username" class="form-control" type="text" name="username" value="" required/>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <input placeholder="Password" class="form-control" type="password" name="password" value="" required/>
                                    </div>


                                    <div class="col-md-12">
                                        <c:if test="${errorRegister != null}">
                                            <div id="errorBox" class="error-message" >${errorRegister}</div>

                                        </c:if>

                                        <c:if test="${successRegister != null}">
                                            <div id="successBox" class="success-message" >${successRegister}</div>
                                        </c:if>

                                        <button type="submit" class="btn btn-primary btn-block mb-4">
                                            Sign Up
                                        </button>

                                        <p class="sign-upp">
                                            Already have an account?
                                            <a class="text-blue" href="${pageContext.request.contextPath}/views/common/user/login.jsp">Sign in</a>
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
            document.addEventListener("DOMContentLoaded", function () {
                setTimeout(function () {
                    let errorBox = document.getElementById("errorBox");
                    let successBox = document.getElementById("successBox");

                    if (errorBox) {
                        errorBox.style.opacity = "0";
                        setTimeout(() => errorBox.style.display = "none", 500);
                    }

                    if (successBox) {
                        successBox.style.opacity = "0";
                        setTimeout(() => successBox.style.display = "none", 500);
                    }
                }, 4000); // Ẩn sau 4 giây
            });
        </script>



        <script src="${pageContext.request.contextPath}/js/query-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
    </body>

    <!-- Mirrored from maraviyainfotech.com/projects/ekka/ekka-v37/ekka-admin/sign-up.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 02 Mar 2024 22:07:43 GMT -->
</html>
