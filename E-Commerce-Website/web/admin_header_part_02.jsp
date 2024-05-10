<%-- 
    Document   : admin_header_part_02
    Created on : Apr 21, 2024, 11:55:19 PM
    Author     : Sithuruwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String userName = "Plase sign in";
    HttpSession session1 = request.getSession(false);

    if (session1.getAttribute("user_name") != null) {
        userName = (String) session1.getAttribute("user_name");
    }
%>
</head>
<body>
    <div class="container-fluid d-flex wrapper">

        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
            <div class="offcanvas-header">
                <button type="button" class="close-btn" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"><i class='bx bx-menu-alt-left'></i></button>
            </div>
            <div class="offcanvas-body">
                <ul>
                    <li title="Dashboard"><a href="AdminDashboardDataRetrieveServlet"><i class='bx bxs-dashboard'></i><span>Dashboard</span></a></li>
                    <li title="Orders"><a href="orders_page.jsp"><i class='bx bx-list-check'></i><span>Orders</span></a></li>
                    <li title="Products"><a href="ReadProductServlet"><i class='bx bxs-package'></i><span>Products</span></a></li>
                    <li title="Inventory"><a href="ProductDetailsRetrieveServlet"><i class='bx bx-candles' ></i><span>Inventory</span></a></li>
                    <li title="Categories"><a href="MainCategoryDetailsRetrieveServlet"><i class='bx bx-category'></i><span>Categories</span></a></li>
                    <form action="LogoutServlet" method="POST">
                        <button type="submit" class="log-out-button" style="background-color: transparent; border: none; padding: 0; width: 100%;">                        
                            <li class="log-out-btn" title="Log out"><a href="#"><i class='bx bx-log-out'></i><span>Log out</span></a></li>
                        </button>
                    </form>
                </ul>
            </div>
        </div>

        <div class="" style="width: 100%;">
            <div class="top-section container">
                <div class="logo h-100"><a href="AdminDashboardDataRetrieveServlet" style="color: black;text-decoration:none">Furniture Shop</a></div>
                <div class="acc h-100">
                    Hi, <%=userName%>
                </div>
            </div>


            <div class="container content">
