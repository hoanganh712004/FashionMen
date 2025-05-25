<%-- 
    Document   : maincategory
    Created on : Mar 7, 2024, 12:28:53 AM
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
        <meta name="description" content="Ekka - Admin Dashboard HTML Template." />

        <title>Main Category</title>

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
                            <div
                                class="breadcrumb-wrapper breadcrumb-wrapper-2 breadcrumb-contacts"
                                >
                                <h1>Main Category</h1>
                                <p class="breadcrumbs">
                                    <span><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></span>
                                <span><i class="mdi mdi-chevron-right"></i></span>Main Category
                            </p>
                        </div>
                        <div class="row">
                            <div class="col-xl-4 col-lg-12">

                                <div class="ec-cat-list card card-default mb-24px">

                                    <div class="card-body">
                                        <div class="ec-cat-form">
                                            <h4>Add New Category</h4>
                                            <span style="color: red">${requestScope.errorAddCategory}</span>
                                            <span style="color: green">${requestScope.addCategorySucess}</span>

                                            <form action="${pageContext.request.contextPath}/addcategory" method="POST" enctype="multipart/form-data">
                                                <div class="form-group row">
                                                    <input
                                                        type="file"
                                                        class="custom-file-input"
                                                        id="coverImage"
                                                        name="image"
                                                        value="${param.image}"
                                                        required/>
                                                    <label class="custom-file-label" for="coverImage"
                                                           >Choose file...</label
                                                    >

                                                    <label for="text" class="col-12 col-form-label"
                                                           >Name</label
                                                    >
                                                    <div class="col-12">
                                                        <input
                                                            id="text"
                                                            name="nameCategory"
                                                            class="form-control here slug-title"
                                                            type="text"
                                                            value="${param.categoryName}"
                                                            />
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <label class="col-12 col-form-label"
                                                           >Sort Description</label
                                                    >
                                                    <div class="col-12">
                                                        <textarea
                                                            id="sortdescription"
                                                            name="sortDescription"
                                                            cols="40"
                                                            rows="2"
                                                            class="form-control"
                                                            >${param.desciption}</textarea>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-12">
                                                        <button
                                                            name="submit"
                                                            type="submit"
                                                            class="btn btn-primary"
                                                            >
                                                            Submit
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <c:if test="${checkEditCategory != null}">
                                    <div class="ec-cat-list card card-default mb-24px">
                                        <div class="card-body">
                                            <div class="ec-cat-form">
                                                <h4>Edit Category</h4>
                                                <span style="color: red">${requestScope.errorUpdateCategory}</span>
                                                <span style="color: green">${requestScope.updateCategorySucess}</span>
                                                <form action="${pageContext.request.contextPath}/updatecategory?action=edit" method="POST">
                                                    <input type="hidden" name="categoryId" value="${checkEditCategory.categoryId}"/>
                                                    <input type="hidden" name="image" value="${checkEditCategory.image}"/>
                                                    <div class="form-group row">
                                                        <label for="text" class="col-12 col-form-label"
                                                               >Name</label
                                                        >
                                                        <div class="col-12">
                                                            <input
                                                                id="text"
                                                                name="updateNameCategory"
                                                                class="form-control here slug-title"
                                                                type="text"
                                                                value="${checkEditCategory.categoryName}"
                                                                />
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <label class="col-12 col-form-label"
                                                               >Sort Description</label
                                                        >
                                                        <div class="col-12">
                                                            <textarea
                                                                id="sortdescription"
                                                                name="updateSortDescription"
                                                                cols="40"
                                                                rows="2"
                                                                class="form-control"
                                                                value="${checkEditCategory.desciption}"
                                                                >${checkEditCategory.desciption}</textarea>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-12">
                                                            <button
                                                                name="submit"
                                                                type="submit"
                                                                class="btn btn-primary"
                                                                >
                                                                Submit
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </c:if>


                            </div>
                            <div class="col-xl-8 col-lg-12">
                                <span style="color: green">${requestScope.deleteCategorySucc}</span>
                                <div class="ec-cat-list card card-default">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="responsive-data-table" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Image</th>
                                                        <th>Category</th>
                                                        <th>Description</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach items="${mainCategory}" var="category">
                                                        <tr>
                                                            <td><img src="${pageContext.request.contextPath}/${category.image}"
                                                                     style="width: 100px"/></td>
                                                            <td>${category.categoryName}</td>

                                                            <td>${category.desciption}</td>
                                                            <td>
                                                                <div class="btn-group">
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
                                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/updatecategory?action=checkedit&categoryId=${category.categoryId}">Edit</a>
                                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/updatecategory?action=delete&categoryId=${category.categoryId}">Delete</a>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>

                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
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
        <script src="${pageContext.request.contextPath}/js/bootstrap-tagsinput.js"></script>
        <script src="${pageContext.request.contextPath}/js/simplebar.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/delete.js"></script>

        <!-- Data Tables -->
        <script src="${pageContext.request.contextPath}/js/jquery.datatables.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.bootstrap5.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.responsive.min.js"></script>

        <!-- Option Switcher -->
        <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

        <!-- Ekka Custom -->
        <script src="${pageContext.request.contextPath}/js/ekka.js"></script>
    </body>

</html>

