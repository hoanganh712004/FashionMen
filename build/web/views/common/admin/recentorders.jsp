<%-- 
    Document   : recentorders
    Created on : Mar 3, 2024, 6:01:04 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div
    class="card card-table-border-none card-default recent-orders"
    id="recent-orders"
    >
    <div class="card-header justify-content-between">
        <h2>Recent Orders</h2>
    </div>
    <div class="card-body pt-0 pb-5">
        <table
            class="table card-table table-responsive table-responsive-large"
            style="width: 100%">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Customer Name</th>
                    <th class="d-none d-lg-table-cell">Total Product</th>
                    <th class="d-none d-lg-table-cell">Order Date</th>
                    <th class="d-none d-lg-table-cell">Order Cost</th>
                    <th>Status</th>
                    <th></th>
                </tr>
            </thead>


            <tbody>

                <c:forEach items="${top1OrderRecent}" var="order">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>
                          <a class="text-dark" href="${pageContext.request.contextPath}/edituser?userId=${order.userId}">${order.fullName}</a>
                        </td>
                        <td class="d-none d-lg-table-cell">${order.totalQuantity} item</td>
                        <td class="d-none d-lg-table-cell">${order.orderDate}</td>
                        <td class="d-none d-lg-table-cell">${order.totalMoney}&nbsp;VND</td>
                        <td>
                            <c:if test="${order.status == 1}">
                                <span class="badge badge-success">Completed</span>
                            </c:if>
                            <c:if test="${order.status == 0}">
                                <span class="badge badge-warning">On Hold</span>
                            </c:if>
                            <c:if test="${order.status == 2}">
                                <span class="badge badge-danger">Cancelled</span>
                            </c:if>
                        </td>

                </c:forEach>

            </tbody>
        </table>
    </div>
</div>