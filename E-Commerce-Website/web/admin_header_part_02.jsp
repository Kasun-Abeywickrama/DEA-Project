<%-- 
    Document   : admin_header_part_02
    Created on : Apr 21, 2024, 11:55:19 PM
    Author     : Sithuruwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

</head>
<body>
    <div class="container-fluid d-flex wrapper">

        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
            <div class="offcanvas-header">
                <button type="button" class="close-btn" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"><i class='bx bx-menu-alt-left'></i></button>
            </div>
            <div class="offcanvas-body">
                <ul>
                    <li title="Dashboard"><a href="admin_dashboard_page.jsp" class="side-bar-lnks"><i class='bx bxs-dashboard'></i><span>Dashboard</span></a></li>
                    <li title="Orders"><a href="orders_page.jsp" class="side-bar-lnks"><i class='bx bx-list-check'></i><span>Orders</span></a></li>
                    <li title="Products"><a href="view_all_products_page.jsp" class="side-bar-lnks"><i class='bx bxs-package'></i><span>Products</span></a></li>
                    <li title="Purchase"><a href="#" class="side-bar-lnks"><i class='bx bx-purchase-tag-alt'></i><span>Purchase</span></a></li>
                    <li title="Inventory"><a href="inventory_management_page.jsp" class="side-bar-lnks"><i class='bx bx-candles' ></i><span>Inventory</span></a></li>
                    <li class="log-out-btn" title="Log out">
                        <form action="LogoutServlet" method="POST" class="logout-form" style="width: 100%">
                            <button type="submit" class="side-bar-lnks" style="background-color: transparent; border: none; width: 100%;">
                                <i class='bx bx-log-out'></i><span>Log out</span>
                            </button>
                        </form>
                    </li>                
                </ul>
            </div>
        </div>

        <div class="" style="width: 100%;">
            <div class="top-section container">
                <div class="logo h-100">Furniture Shop</div>
                <div class="acc h-100">
                    <i class='bx bxs-user-circle'></i>
                </div>
            </div>


            <div class="container content">
