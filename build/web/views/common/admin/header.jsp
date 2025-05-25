<%-- 
    Document   : header
    Created on : Mar 3, 2024, 5:45:34 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="ec-main-header" id="header">
    <nav class="navbar navbar-static-top navbar-expand-lg">
        <!-- Sidebar toggle button -->
        <button id="sidebar-toggler" class="sidebar-toggle"></button>
        <!-- search form -->
        <div class="search-form d-lg-inline-block">
            
        </div>

        <!-- navbar right -->
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <!-- User Account -->
                <li class="dropdown user-menu">
                    <button
                        class="dropdown-toggle nav-link ec-drop"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                        >
                        <img
                            src="${pageContext.request.contextPath}/${account.image}"
                            class="user-image"
                            alt="User Image"
                            />
                    </button>
                    <ul
                        class="dropdown-menu dropdown-menu-right ec-dropdown-menu"
                        >
                        <!-- User image -->
                        <li class="dropdown-header">
                            <img
                                src="${pageContext.request.contextPath}/${account.image}"
                                class="img-circle"
                                alt="User Image"
                                />
                            <div class="d-inline-block">
                                ${account.userName}
                                <small class="pt-1">${account.email}</small>
                            </div>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/views/user/item-page/userprofile.jsp">
                                <i class="mdi mdi-account"></i> My Profile
                            </a>
                        </li>
                        
                        <li class="dropdown-footer">
                            <a href="${pageContext.request.contextPath}/logout">
                                <i class="mdi mdi-logout"></i> Log Out
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>