<%-- 
    Document   : header
    Created on : Apr 16, 2024, 7:54:21 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/admin/header.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
    <div class="container-fluid d-flex wrapper">

        <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
            <div class="offcanvas-header">
                <button type="button" class="close-btn" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"><i class='bx bx-menu-alt-left'></i></button>
            </div>
            <div class="offcanvas-body">
                <ul>
                    <li title="Dashboard"><a href="#"><i class='bx bxs-dashboard'></i><span>Dashboard</span></a></li>
                    <li title="Orders"><a href="#"><i class='bx bx-list-check'></i><span>Orders</span></a></li>
                    <li title="Products"><a href="view_all_products_page.jsp"><i class='bx bxs-package'></i><span>Products</span></a></li>
                    <li title="Purchase"><a href="#"><i class='bx bx-purchase-tag-alt'></i><span>Purchase</span></a></li>
                    <li title="Inventory"><a href="inventory_management_page.jsp"><i class='bx bx-candles' ></i><span>Inventory</span></a></li>
                    <li class="log-out-btn" title="Log out"><a href="#"><i class='bx bx-log-out'></i><span>Log out</span></a></li>
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