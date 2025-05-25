<%-- 
    Document   : addproduct
    Created on : Mar 7, 2024, 8:19:55 PM
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

        <title>Dashboard Add Product</title>

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
                                class="breadcrumb-wrapper d-flex align-items-center justify-content-between row"
                                >
                                <div>
                                    <h1>Report</h1>
                                    <p class="breadcrumbs">
                                        <span><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></span>
                                    <span><i class="mdi mdi-chevron-right"></i></span>Review Report chart
                                </p>
                            </div>
                            <script
                                src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
                            </script>
                            <br/>
                            <center>
                                <canvas class="row" id="reviewReport" style="width:100%;max-width:700px"></canvas>
                                <form id="revennueByMonth" style="margin-top: 20px" action="${pageContext.request.contextPath}/reviewreportchart" method="get">
                                    <select onchange="submitForm()" name="year">
                                        <c:forEach var="y" items="${yearList}">
                                            <option value="${y}" ${selected == y ? 'selected' : ''}>${y}</option>
                                        </c:forEach>
                                    </select>
                                </form>

                            </center>


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
            <script src="${pageContext.request.contextPath}/js/optionaddnewproduct.js"></script>
            <!-- Option Switcher -->
            <script src="${pageContext.request.contextPath}/js/optionswitcher.js"></script>

            <!-- Ekka Custom -->
            <script src="${pageContext.request.contextPath}/js/ekka.js"></script>




            <script type="text/javascript">

        var reviewReport = document.getElementById("reviewReport").getContext('2d');


        if (reviewReport != null) {
            var xValue = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
            var yValue5 = [${requestScope.month1star5},${requestScope.month2star5}, ${requestScope.month3star5}, ${requestScope.month4star5}, ${requestScope.month5star5},${requestScope.month6star5}, ${requestScope.month7star5}, ${requestScope.month8star5}, ${requestScope.month9star5}, ${requestScope.month10star5}, ${requestScope.month11star5}, ${requestScope.month12star5}];
            var yValue4 = [${requestScope.month1star4},${requestScope.month2star4}, ${requestScope.month3star4}, ${requestScope.month4star4}, ${requestScope.month5star4},${requestScope.month6star4}, ${requestScope.month7star4}, ${requestScope.month8star4}, ${requestScope.month9star4}, ${requestScope.month10star4}, ${requestScope.month11star4}, ${requestScope.month12star4}];
            var yValue3 = [${requestScope.month1star3},${requestScope.month2star3}, ${requestScope.month3star3}, ${requestScope.month4star3}, ${requestScope.month5star3},${requestScope.month6star3}, ${requestScope.month7star3}, ${requestScope.month8star3}, ${requestScope.month9star3}, ${requestScope.month10star3}, ${requestScope.month11star3}, ${requestScope.month12star3}];
            var yValue2 = [${requestScope.month1star2},${requestScope.month2star2}, ${requestScope.month3star2}, ${requestScope.month4star2}, ${requestScope.month5star2},${requestScope.month6star2}, ${requestScope.month7star2}, ${requestScope.month8star2}, ${requestScope.month9star2}, ${requestScope.month10star2}, ${requestScope.month11star2}, ${requestScope.month12star2}];
            var yValue1 = [${requestScope.month1star1},${requestScope.month2star1}, ${requestScope.month3star1}, ${requestScope.month4star1}, ${requestScope.month5star1},${requestScope.month6star1}, ${requestScope.month7star1}, ${requestScope.month8star1}, ${requestScope.month9star1}, ${requestScope.month10star1}, ${requestScope.month11star1}, ${requestScope.month12star1}];
            var color = [
                "#FF5733",
                "#9B59B6",
                "#2ECC71",
                "#3498DB",
                "#F1C40F",
                "#E74C3C",
                "#1ABC9C",
                "#F39C12",
                "#34495E",
                "#E67E22",
                "#8E44AD",
                "#27AE60"
            ];

            var myChartOrderOverview = new Chart(reviewReport, {
                type: "line",
                data: {
                    labels: xValue,
                    datasets: [{
                            label: '5 sao',
                            borderColor: '#F1C40F',
                            backgroundColor: "#F1C40F",
                            data: yValue5,
                            fill: false
                        }, {
                            label: '4 sao',
                            borderColor: '#3498DB',
                            backgroundColor: "#3498DB",
                            data: yValue4,
                            fill: false
                        }, {
                            label: '3 sao',
                            borderColor: '#2ECC71',
                            backgroundColor: "#2ECC71",
                            data: yValue3,
                            fill: false
                        }, {
                            label: '2 sao',
                            borderColor: '#9B59B6',
                            backgroundColor: "#9B59B6",
                            data: yValue2,
                            fill: false
                        }, {
                            label: '1 sao',
                            borderColor: '#FF5733',
                            backgroundColor: "#FF5733",
                            data: yValue1,
                            fill: false
                        }
                    ]
                },
                options: {
                    legend: {display: true},
                    responsive: true,
                    title: {
                        display: true,
                        text: "Review Report Chart"
                    }
                }
            });

        }

        function submitForm() {
            var form = document.getElementById("revennueByMonth");
            form.submit();
        }

            </script>
    </body>

</html>
