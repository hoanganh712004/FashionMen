<%-- 
    Document   : userprofile
    Created on : Mar 5, 2024, 12:33:09 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">


    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template.">

        <title>User Profile</title>

        <!-- GOOGLE FONTS -->
        <link rel="preconnect" href="https://fonts.googleapis.com/">
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;400;500;600;700;800&amp;family=Poppins:wght@300;400;500;600;700;800;900&amp;family=Roboto:wght@400;500;700;900&amp;display=swap" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />

        <!-- PLUGINS CSS STYLE -->
        <link href="${pageContext.request.contextPath}/css/simplebar.css" rel="stylesheet" />

        <!-- Ekka CSS -->
        <link id="ekka-css" rel="stylesheet" href="${pageContext.request.contextPath}/css/ekka.css" />

        <!-- FAVICON -->
        <link href="${pageContext.request.contextPath}/images/favicon.png" rel="shortcut icon" />

        <style>
            .updateSuccessAccount {
                font-size: 30px; /* Kích thước vừa phải */
                font-weight: bold;
                color: #ff1493; /* Màu hồng neon */
                text-shadow: 0 0 5px #ff1493, 0 0 10px #ff1493, 0 0 15px #ff1493;
                position: relative;
                display: inline-block;
                animation: glow 1.5s infinite alternate;
            }

            /* Hiệu ứng phát sáng nhẹ */
            @keyframes glow {
                0% {
                    text-shadow: 0 0 5px #ff1493, 0 0 10px #ff1493, 0 0 15px #ff1493;
                }
                50% {
                    text-shadow: 0 0 8px #ff1493, 0 0 15px #ff1493, 0 0 20px #ff1493;
                }
                100% {
                    text-shadow: 0 0 5px #ff1493, 0 0 10px #ff1493, 0 0 15px #ff1493;
                }
            }

        </style>

    </head>

    <body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-dark ec-header-light" id="body">

        <!-- WRAPPER -->
        <div class="wrapper">

            <!-- LEFT MAIN SIDEBAR -->

            <!-- PAGE WRAPPER -->
            <div style="padding-left: 0;padding-top: 0;" class="ec-page-wrapper">

                <!-- Header -->

                <!-- CONTENT WRAPPER -->
                <div class="ec-content-wrapper">
                    <div class="content">
                        <div class="breadcrumb-wrapper breadcrumb-contacts">
                            <div>
                                <h1>User Profile</h1>
                                <p class="breadcrumbs"><span><a href="${pageContext.request.contextPath}/home">Home <i class="mdi mdi-chevron-right"></i></span> </a></span>
                                        <c:if test="${sessionScope.accountAdmin.roleId == 1}">
                                        <span><span><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></span>
                                            <span><i class="mdi mdi-chevron-right"></i></span>
                                            </c:if>
                                        Profile
                                </p>
                            </div>

                        </div>


                        <div class="card bg-white profile-content">
                            <div class="row">
                                <div class="col-lg-4 col-xl-3">
                                    <div class="profile-content-left profile-left-spacing">
                                        <div class="text-center widget-profile px-0 border-0">
                                            <div class="card-img mx-auto rounded-circle">
                                                <img src="${pageContext.request.contextPath}/${accountAdmin.image}" alt="user image">
                                            </div>
                                            <div class="card-body">
                                                <h4 class="py-2 text-dark">${accountAdmin.fullName}</h4>

                                                <p>User Name: ${accountAdmin.userName}</p>
                                            </div>
                                        </div>

                                        <div class="d-flex justify-content-between ">
                                            <div class="text-center pb-4">
                                                <h6 class="text-dark pb-2"></h6>
                                                <p></p>
                                            </div>

                                            <div class="text-center pb-4">
                                                <h6 style="margin-top: 10px;" class="text-dark pb-2"></h6>
                                                <p>Items success</p>
                                            </div>

                                            <div class="text-center pb-4">
                                                <h6 class="text-dark pb-2"></h6>
                                                <p></p>
                                            </div>
                                        </div>

                                        <hr class="w-100">

                                        <div class="contact-info pt-4">
                                            <h5 class="text-dark">Information</h5>
                                            <p class="text-dark font-weight-medium pt-24px mb-2">Created at</p>
                                            <p>${accountAdmin.createdAt}</p>
                                            

                                        </div>

                                    </div>
                                </div>



                                <div class="col-lg-8 col-xl-9">
                                    <div class="profile-content-right profile-right-spacing py-5">
                                        <ul style="margin-bottom: 15px;" class="nav nav-tabs px-3 px-xl-5 nav-style-border" id="myProfileTab" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="settings-tab" data-bs-toggle="tab"
                                                        data-bs-target="#settings" type="button" role="tab"
                                                        aria-controls="settings" aria-selected="false">Settings</button>
                                            </li>


                                        </ul>
                                        <div class="tab-content px-3 px-xl-5" id="myTabContent">


                                            <div class="tab-pane fade show active" id="settings" role="tabpanel"
                                                 aria-labelledby="settings-tab">
                                                <div class="tab-pane-content mt-5">
                                                    <form action="${pageContext.request.contextPath}/edituser" method="POST" enctype="multipart/form-data">
                                                        <div class="form-group row mb-6">
                                                            <label for="coverImage"
                                                                   class="col-sm-4 col-lg-2 col-form-label">User Image</label>
                                                            <div class="col-sm-8 col-lg-10">
                                                                <div class="custom-file mb-1">
                                                                    <input name="image" type="file" class="custom-file-input"
                                                                           id="coverImage" value="">
                                                                    <label class="custom-file-label" for="coverImage">Choose
                                                                        file...</label>

                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row mb-2">
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <label for="firstName">Full Name</label>
                                                                    <input type="text" name="fullName" class="form-control" id="firstName"
                                                                           value="${accountAdmin.fullName}" required>
                                                                </div>
                                                            </div>

                                                        </div>


                                                        <div class="form-group mb-4">
                                                            <label for="userName">User name</label>
                                                            <input type="text" class="form-control" id="userName"
                                                                   value="${accountAdmin.userName}" readonly>
                                                        </div>
                                                        <div class="form-group mb-4">
                                                            <label for="balance">Balance</label>
                                                            <input type="text" class="form-control" id="balance"
                                                                   value="${balance}" readonly>
                                                        </div>

                                                        <div class="form-group mb-4">
                                                            <label for="oldPassword">Old password</label>
                                                            <input value="${accountAdmin.passWord}" name="oldPassword" type="password" class="form-control" id="oldPassword" readonly>
                                                        </div>

                                                        <div class="form-group mb-4">
                                                            <label for="newPassword">New password</label>
                                                            <input value="" type="password" name="newPassword" class="form-control" id="newPassword" required>
                                                        </div>


                                                        <div class="form-group mb-4">
                                                            <label for="email">Email</label>
                                                            <input type="email" class="form-control" id="email" name="email"
                                                                   value="${accountAdmin.email}" required>
                                                        </div>

                                                        <div class="form-group mb-4">
                                                            <label for="birthday">BirthDay</label>
                                                            <input type="date" class="form-control" id="birthday" name="birthDay"
                                                                   value="${accountAdmin.birthDay}" required>
                                                        </div>


                                                        <div class="form-group mb-4">
                                                            <label for="address">Address</label>
                                                            <input type="text" class="form-control" id="address" name="address"
                                                                   value="${accountAdmin.address}" required>
                                                        </div>

                                                        <div class="form-group mb-4">
                                                            <label for="phoneNumber">Phone Number</label>
                                                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                                                                   value="${accountAdmin.phoneNumber}" required>
                                                        </div>
                                                        <div class="d-flex justify-content-end mt-5">
                                                            <button type="submit"
                                                                    class="btn btn-primary mb-2 btn-pill">Update Profile</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div> <!-- End Content -->
                </div> <!-- End Content Wrapper -->

                <!-- Footer -->
                <footer class="footer mt-auto">
                    <div class="copyright bg-white">
                        <p>
                            Copyright &copy; <span id="ec-year"></span><a class="text-primary"
                                                                          href="https://themeforest.net/user/ashishmaraviya" target="_blank"> Ekka Admin
                                Dashboard</a>. All Rights Reserved.
                        </p>
                    </div>
                </footer>

            </div> <!-- End Page Wrapper -->
        </div> <!-- End Wrapper -->


        <!-- Common Javascript -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const text = document.querySelector(".updateSuccessAccount");

                function createParticle() {
                    if (!text)
                        return;

                    const particle = document.createElement("div");
                    particle.classList.add("particle");
                    document.body.appendChild(particle);

                    // Tạo vị trí xung quanh chữ
                    let x = text.offsetLeft + Math.random() * text.clientWidth;
                    let y = text.offsetTop - Math.random() * 30; // Bay từ phía trên

                    particle.style.left = `${x}px`;
                    particle.style.top = `${y}px`;
                    particle.style.animationDuration = `${1.5 + Math.random()}s`;

                    setTimeout(() => {
                        particle.remove();
                    }, 2000);
                }

                setInterval(createParticle, 120); // Tạo hạt liên tục
            });



        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/changepassword.js"></script>
        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>

    </body>


</html>