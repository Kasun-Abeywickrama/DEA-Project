<%-- 
    Document   : orders_page
    Created on : Apr 17, 2024, 1:37:19 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="admin_header_part_01.jsp" %>
<%@include file="admin_header_part_02.jsp" %>

<style>
    .nav-link{
        color: black;
    }

    .nav-link:hover{
        color: #E97000;
    }

    .active{
        background-color: #E97000 !important;
    }
    .nav-link{
        padding: 4px 90px;
    }
</style>

<div class="container">

    <p style="color: #E97000;"><i>Orders &nbsp;>&nbsp; New Orders </i></p>  


    <div class="w-100">    
        <ul class="nav nav-tabs">
            <li class="nav-item" onclick="getPendingOrders()">
                <a class="nav-link active" href="#pending">Pending</a>
            </li>
            <li class="nav-item" onclick="getDispatchedOrders()">
                <a class="nav-link" href="#dispatched">Dispatched</a>
            </li>
            <li class="nav-item" onclick="getDeliveredOrders()">
                <a class="nav-link" href="#delivered">Delivered</a>
            </li>
            <li class="nav-item" onclick="getAllOrders()">
                <a class="nav-link" href="#all">All</a>
            </li>
        </ul>
    </div>


    <div class="w-100 mt-4">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">Order Id</th>
                    <th scope="col">Date</th>
                    <th scope="col">Total price</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>





</div>

<script>
    function setActiveTab(tabName) {
        var tabs = document.querySelectorAll('.nav-link');
        tabs.forEach(function (tab) {
            tab.classList.remove('active');
        });

        var activeTab = document.querySelector('a[href="#' + tabName + '"]');
        activeTab.classList.add('active');
    }

    function displayOrders(orders) {
        var tbody = document.querySelector("tbody");

        tbody.innerHTML = "";

        orders.forEach(function (order) {
            var row = document.createElement("tr");

            var idCell = document.createElement("td");
            idCell.textContent = order.orderId;
            row.appendChild(idCell);

            var dateTimeCell = document.createElement("td");
            dateTimeCell.textContent = order.dateTime;
            row.appendChild(dateTimeCell);

            var totalPriceCell = document.createElement("td");
            totalPriceCell.textContent = order.totalPrice;
            row.appendChild(totalPriceCell);

            var actionCell = document.createElement("td");
            var viewLink = document.createElement("a");
            viewLink.href = "ReadOrderServlet?orderId=" + order.orderId;
            viewLink.textContent = "View";
            actionCell.appendChild(viewLink);
            row.appendChild(actionCell);

            tbody.appendChild(row);
        });
    }

    function getPendingOrders() {
        setActiveTab('pending');
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadOrderServlet?orderStatus=Pending", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                displayOrders(responseData);
            }
        };
        xhr.send();
    }

    getPendingOrders();
</script>

<script>
    function getDispatchedOrders() {
        setActiveTab('dispatched');
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadOrderServlet?orderStatus=Dispatched", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                displayOrders(responseData);
            }
        };
        xhr.send();
    }
</script>

<script>
    function getDeliveredOrders() {
        setActiveTab('delivered');
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadOrderServlet?orderStatus=Delivered", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                displayOrders(responseData);
            }
        };
        xhr.send();
    }
</script>

<script>
    function getAllOrders() {
        setActiveTab('all');
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadOrderServlet", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                displayOrders(responseData);
            }
        };
        xhr.send();
    }
</script>

<%@ include file="admin_footer.jsp" %>

