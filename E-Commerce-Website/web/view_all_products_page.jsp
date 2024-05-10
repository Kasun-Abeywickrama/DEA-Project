<%-- 
    Document   : view_all_products_page
    Created on : Apr 11, 2024, 8:01:08 PM
    Author     : HP
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ProductManagement.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="DatabaseConnection.DBConnectionManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>

<%@include file="admin_header_part_01.jsp" %>
<title>All Product</title>
<%@include file="admin_header_part_02.jsp" %>

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
    <tbody>
        <%
            if (request.getAttribute("products") != null) {

                @SuppressWarnings(  "unchecked")
                ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");

                if (products.size() != 0) {
                    for (Product i : products) {
        %>
        <tr>
            <td><%= i.getName()%></td>
            <td><%= i.getSellingPrice()%></td>
            <td>
                <a href="ReadProductServlet?product_id=<%= i.getProductId()%>">View</a>
            </td>
        </tr>
        <% }

        } else {

        %>
    <br><br><br>
    <center>
        <h6>No Available Products</h6>
    </center>
    <%          }
        }
    %> 
</tbody>
</table>

<%@ include file="admin_footer.jsp" %>

