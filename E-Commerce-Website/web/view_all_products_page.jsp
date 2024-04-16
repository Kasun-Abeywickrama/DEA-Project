<%-- 
    Document   : view_all_products_page
    Created on : Apr 11, 2024, 8:01:08 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="DatabaseConnection.DBConnectionManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>

<%@ include file="admin_header.jsp" %>

<div style="display: flex; align-items: center; justify-content: space-between;" class="mb-3 mt-2">
    <p style="color: #E97000;"><i>Products &nbsp;>&nbsp; All Products</i></p>  
    <a href="add_product_page.jsp" class="btn btn-primary btn-sm">Add New Product</a>
</div>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Name</th>
            <th>Selling Price</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
    function getData() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadProductServlet", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                displayProducts(responseData);
            }
        };
        xhr.send();
    }

    function displayProducts(products) {
        var tbody = document.querySelector("tbody");

        tbody.innerHTML = "";

        products.forEach(function (product) {
            var row = document.createElement("tr");

            var nameCell = document.createElement("td");
            nameCell.textContent = product.name;
            row.appendChild(nameCell);

            var priceCell = document.createElement("td");
            priceCell.textContent = product.buyingPrice;
            row.appendChild(priceCell);

//                    var stockCell = document.createElement("td");
//                    stockCell.textContent = product.stock;
//                    row.appendChild(stockCell);

            var actionCell = document.createElement("td");
            var viewLink = document.createElement("a");
            viewLink.href = "view_product_page.jsp?product_id=" + product.productId + "&sub_category_id=" + product.subCategoryId;
            viewLink.textContent = "View";
            actionCell.appendChild(viewLink);
            row.appendChild(actionCell);

            tbody.appendChild(row);
        });
    }

    getData();
</script>

<%@ include file="admin_footer.jsp" %>

