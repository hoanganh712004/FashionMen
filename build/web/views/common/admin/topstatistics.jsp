<%-- 
    Document   : topstatistics
    Created on : Mar 3, 2024, 5:46:59 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-2">
            <div class="card-body">
                <h2 class="mb-1">${totalUser}</h2>
                <p>Total Users</p>
                <span class="mdi mdi-account-clock"></span>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-2">
            <div class="card-body">
                <h2 class="mb-1">${totalSupperliers}</h2>
                <p>Total Supperlier</p>
                <span class="mdi mdi-account-clock"></span>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-1">
            <div class="card-body">
                <h2 class="mb-1">${categoryAll.size()}</h2>
                <p>Total Category</p>
                <span class="mdi mdi-account-arrow-left"></span>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-3">
            <div class="card-body">
                <h2 class="mb-1">${totalOrder}</h2>
                <p>Total Orders</p>
                <span class="mdi mdi-package-variant"></span>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-3">
            <div class="card-body">
                <h2 class="mb-1">${totalProduct}</h2>
                <p>Total Product</p>
                <span class="mdi mdi-package-variant"></span>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-3">
            <div class="card-body">
                <h2 class="mb-1">${totalProductSold}</h2>
                <p>Total Product Sold</p>
                <span class="mdi mdi-package-variant"></span>
            </div>
        </div>
    </div>

    <div class="col-xl-3 col-sm-6 p-b-15 lbl-card">
        <div class="card card-mini dash-card card-4">
            <div class="card-body">
                <h2 class="mb-1">${totalRevenue/1000000}Tr &nbsp;VNĐ</h2>
                <p>Total Revenue</p>
                <span class="mdi mdi-currency-usd"></span>
            </div>
        </div>
    </div>
</div>