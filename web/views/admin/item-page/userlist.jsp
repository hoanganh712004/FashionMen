<%-- 
    Document   : userlist
    Created on : Mar 6, 2024, 9:57:30 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en" dir="ltr">
    <!-- Mirrored from maraviyainfotech.com/projects/ekka/ekka-v37/ekka-admin/user-list.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 02 Mar 2024 22:07:33 GMT -->
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>User List</title>

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

        <!-- PLUGINS CSS STYLE -->
        <link href="${pageContext.request.contextPath}/css/simplebar.css" rel="stylesheet" />

        <!-- Data Tables -->
        <link
            href="${pageContext.request.contextPath}/css/datatables.bootstrap5.min.css"
            rel="stylesheet"
            />
        <link
            href="${pageContext.request.contextPath}/css/responsive.datatables.min.css"
            rel="stylesheet"
            />

        <!-- ekka CSS -->
        <link id="ekka-css" rel="stylesheet" href="${pageContext.request.contextPath}/css/ekka.css" />

        <!-- FAVICON -->
        <link href="${pageContext.request.contextPath}/images/favicon.png" rel="shortcut icon" />
    </head>

    <body
        class="ec-header-fixed ec-sidebar-fixed ec-sidebar-dark ec-header-light"
        id="body"
        >
        <!-- WRAPPER -->
        <div class="wrapper">
            <!-- LEFT MAIN SIDEBAR -->  
            <jsp:include page="../../common/admin/leftmainsidebar.jsp"></jsp:include>

                <!-- PAGE WRAPPER -->
                <div class="ec-page-wrapper">
                    <!-- Header -->
                <jsp:include page="../../common/admin/header.jsp"></jsp:include>

                    <!-- CONTENT WRAPPER -->
                    <div class="ec-content-wrapper">
                        <div class="content">
                            <div class="breadcrumb-wrapper breadcrumb-contacts">
                                <div>
                                    <h1>User List</h1>
                                    <p class="breadcrumbs">
                                        <span><a href="../dashboard/dashboard.jsp">Home</a></span>
                                        <span><i class="mdi mdi-chevron-right"></i></span>User
                                    </p>
                                </div>
                                <div>
                                    <span style="color: red">${requestScope.insertUserError}</span>
                                <span style="color: green">${requestScope.insertUserSucc}</span>
                                <span style="color: green">${requestScope.deleteUserSuccess}</span>
                                <button
                                    type="button"
                                    class="btn btn-primary"
                                    data-bs-toggle="modal"
                                    data-bs-target="#addUser"
                                    >
                                    Add User
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="ec-vendor-list card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Profile</th>
                                                        <th>Name</th>
                                                        <th>Email</th>
                                                        <th>Phone</th>
                                                        <th>Total Buy</th>
                                                        <th>Role</th>
                                                        <th>Join On</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach items="${sessionScope.totalBuyByUser}" var="user">
                                                        <c:if test="${ account.roleId == 3 }">
                                                            <tr>
                                                                <td>
                                                                    <img
                                                                        class="vendor-thumb"
                                                                        src="${pageContext.request.contextPath}/${user.image}"
                                                                        alt="user profile"
                                                                        />
                                                                </td>
                                                                <td>${user.userName != null ? user.userName : 'empty'}</td>
                                                                <td>${user.email != null ? user.email : 'empty'}</td>
                                                                <td>${user.phoneNumber != null ? user.phoneNumber : 'empty'}</td>
                                                                <td>${user.totalBuy}</td>
                                                                <td>${user.role.name}</td>
                                                                <td>${user.createdAt}</td>
                                                                <td>
                                                                    <div class="btn-group mb-1">
                                                                        <button
                                                                            type="button"
                                                                            class="btn btn-outline-success"
                                                                            >
                                                                            Info
                                                                        </button>
                                                                        <button
                                                                            type="button"
                                                                            class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                                            data-bs-toggle="dropdown"
                                                                            aria-haspopup="true"
                                                                            aria-expanded="false"
                                                                            data-display="static"
                                                                            >
                                                                            <span class="sr-only">Info</span>
                                                                        </button>

                                                                        <div class="dropdown-menu">

                                                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/edituser?userId=${user.userId}">Edit</a>

                                                                            <a class="dropdown-item" onclick="deleteUser('${pageContext.request.contextPath}/deleteuser?userId=${user.userId}', '${user.userName}')" href="javascript:void(0)">Delete</a>

                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>  
                                                        </c:if> 
                                                        <c:if test="${ account.roleId == 1 }">
                                                            <c:if test="${user.role.roleId != 3}">
                                                                <tr>
                                                                    <td>
                                                                        <img
                                                                            class="vendor-thumb"
                                                                            src="${pageContext.request.contextPath}/${user.image}"
                                                                            alt="user profile"
                                                                            />
                                                                    </td>
                                                                    <td>${user.userName != null ? user.userName : 'empty'}</td>
                                                                    <td>${user.email != null ? user.email : 'empty'}</td>
                                                                    <td>${user.phoneNumber != null ? user.phoneNumber : 'empty'}</td>
                                                                    <td>${user.totalBuy}</td>
                                                                    <td>${user.role.name}</td>
                                                                    <td>${user.createdAt}</td>
                                                                    <td>
                                                                        <div class="btn-group mb-1">
                                                                            <button
                                                                                type="button"
                                                                                class="btn btn-outline-success"
                                                                                >
                                                                                Info
                                                                            </button>
                                                                            <button
                                                                                type="button"
                                                                                class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                                                data-bs-toggle="dropdown"
                                                                                aria-haspopup="true"
                                                                                aria-expanded="false"
                                                                                data-display="static"
                                                                                >
                                                                                <span class="sr-only">Info</span>
                                                                            </button>

                                                                            <div class="dropdown-menu">
                                                                               

                                                                                <c:if test="${account.roleId == 1 && user.role.roleId == 2}">
                                                                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/edituser?userId=${user.userId}">Edit</a>

                                                                                    <a class="dropdown-item" onclick="deleteUser('${pageContext.request.contextPath}/deleteuser?userId=${user.userId}', '${user.userName}')" href="javascript:void(0)">Delete</a>
                                                                                </c:if> 
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                </tr>  

                                                            </c:if>
                                                        </c:if> 

                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Add User Modal  -->
                        <div
                            class="modal fade modal-add-contact"
                            id="addUser"
                            tabindex="-1"
                            role="dialog"
                            aria-labelledby="exampleModalCenterTitle"
                            aria-hidden="true"
                            >
                            <div
                                class="modal-dialog modal-dialog-centered modal-lg"
                                role="document"
                                >
                                <div class="modal-content">
                                    <form action="${pageContext.request.contextPath}/adduser" method="POST" enctype="multipart/form-data">
                                        <div class="modal-header px-4">
                                            <h5 class="modal-title" id="exampleModalCenterTitle">
                                                Add New User
                                            </h5>
                                        </div>

                                        <div class="modal-body px-4">
                                            <div class="form-group row mb-6">
                                                <label
                                                    for="coverImage"
                                                    class="col-sm-4 col-lg-2 col-form-label"
                                                    >User Image</label
                                                >

                                                <div class="col-sm-8 col-lg-10">
                                                    <div class="custom-file mb-1">
                                                        <input
                                                            type="file"
                                                            class="custom-file-input"
                                                            id="coverImage"
                                                            name="image"
                                                            required/>
                                                        <label class="custom-file-label" for="coverImage"
                                                               >Choose file...</label
                                                        >
                                                        <div class="invalid-feedback">
                                                            Example invalid custom file feedback
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row mb-2">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label for="role">Role</label>
                                                        <select class="form-control"
                                                                id="role" name="role">
                                                            <option value="2">User</option>
                                                            <option value="1">Admin</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="row mb-2">
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label for="firstName">First name</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                id="firstName"
                                                                value="${param.firstName}"
                                                                name="firstName"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label for="lastName">Last name</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                id="lastName"
                                                                name="lastName"
                                                                value="${param.lastName}"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group mb-4">
                                                            <label for="userName">User name</label>
                                                            <input
                                                                required
                                                                type="text"
                                                                class="form-control"
                                                                id="userName"
                                                                name="userName"
                                                                value="${param.userName}"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>


                                                    <div class="col-lg-6">
                                                        <div class="form-group mb-4">
                                                            <label for="userName">Password</label>
                                                            <input
                                                                required
                                                                type="password"
                                                                class="form-control"
                                                                id="password"
                                                                name="passWord"
                                                                value="${param.passWord}"
                                                                placeholder=""
                                                                required repone/>
                                                        </div>
                                                    </div>


                                                    <div class="col-lg-6">
                                                        <div class="form-group mb-4">
                                                            <label for="email">Email</label>
                                                            <input
                                                                type="email"
                                                                class="form-control"
                                                                name="email"
                                                                id="email"
                                                                value="${param.email}"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group mb-4">
                                                            <label for="Birthday">Birthday</label>
                                                            <input
                                                                type="date"
                                                                class="form-control"
                                                                id="Birthday"
                                                                name="birthDate"
                                                                value="${param.birthDate}"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group mb-4">
                                                            <label for="event">Address</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                id="event"
                                                                name="address"
                                                                value="${param.address}"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <div class="form-group mb-4">
                                                            <label for="event1">Phone Number</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                id="event1"
                                                                name="phoneNumber"
                                                                value="${param.phoneNumber}"
                                                                placeholder=""
                                                                required/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="modal-footer px-4">
                                                <button
                                                    type="button"
                                                    class="btn btn-secondary btn-pill"
                                                    data-bs-dismiss="modal"
                                                    >
                                                    Cancel
                                                </button>
                                                <button type="submit" class="btn btn-primary btn-pill">
                                                    Save Contact
                                                </button>
                                            </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Content -->
                </div>
                <!-- End Content Wrapper -->

                <!-- Footer -->
                <jsp:include page="../../common/admin/footer.jsp"></jsp:include>
                </div>
                <!-- End Page Wrapper -->
            </div>
            <!-- End Wrapper -->

            <!-- Common Javascript -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>

        <!-- Data Tables -->
        <script src="${pageContext.request.contextPath}/js/jquery.datatables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.bootstrap5.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.responsive.min.js"></script>

        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
        <script src="${pageContext.request.contextPath}/js/delete.js"></script>
    </body>

</html>
