<%-- 
    Document   : view_product_page
    Created on : Apr 10, 2024, 8:25:22 PM
    Author     : HP
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="DatabaseConnection.DBConnectionManager"%>

<%
    String productId = request.getParameter("product_id");
    int subCategoryId = Integer.parseInt(request.getParameter("sub_category_id"));
%>

<%@ include file="admin_header_part_01.jsp" %>
<%@ include file="admin_header_part_02.jsp" %>

<div style="display: flex; align-items: center; justify-content: space-between;" class="mb-3 mt-2">
    <p style="color: #E97000;"><i>Products &nbsp;>&nbsp; Edit & Delete Product</i></p> 
    <form action="DeleteProductServlet?product_id=<%= productId%>" method="POST" onsubmit="return deleteProductConfirmation()">          
        <input type="submit" value="Delete Product" class="btn btn-primary btn-sm"/>
    </form>
</div>
    <form id="myForm" name="productForm" action="UpdateProductServlet?product_id=<%= productId%>"  method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">

    <div class="row">
        <div class="col-md">
            <div class="form-group mb-4">
                <%
                    String imagePath = "images/product_images/" + productId + ".png";
                    String timestamp = String.valueOf(System.currentTimeMillis());
                    String imageUrl = imagePath + "?timestamp=" + timestamp;
                %>                    
                <img id="image" src="<%= imageUrl%>" class="img-thumbnail rounded mx-auto d-block w-25"  alt="Image">
            </div>

            <div class="form-group mb-4">
                <label for="name" class="mb-2">Product name</label>
                <input type="text" class="form-control" name="name" id="name">    
            </div>
            <div class="form-group mb-4">
                <label for="buyingPrice" class="mb-2">Buying Price</label>
                <input type="text" class="form-control" name="buying_price" id="buyingPrice">
            </div>
            <div class="form-group mb-4">
                <label for="sellingPrice" class="mb-2">Selling Price</label>
                <input type="text" class="form-control" name="selling_price" id="sellingPrice">
            </div>
        </div>

        <div class="col-md">    
            <div class="form-group mb-4">
                <label for="description" class="mb-2">Description</label>
                <textarea name="description" class="form-control" id="description" rows="3"></textarea>
            </div>
            <div class="form-group mb-4">
                <label for="image" class="mb-2">Image</label>
                <input type="file" name="image" accept="image/png" class="form-control" id="image" onchange="previewImage(event)">   
            </div>
            
            <div class="form-group mb-4" style="display: none;" id="imagePreviewWrapper">
                <img id="imagePreview" src="#" class="img-thumbnail rounded mx-auto d-block w-25"  alt="Image Preview">
            </div>
            

            <div class="form-group mb-4">
                <label for="exampleFormControlSelect2">Example multiple select</label>
                <select class="form-control" name="sub_cat">
                    <%
                        DBConnectionManager dbc = new DBConnectionManager();
                        Connection connection = null;
                        PreparedStatement statement = null;

                        try {
                            connection = dbc.getDBConnection();
                            statement = connection.prepareStatement("select * from sub_category");
                            ResultSet resultSet = statement.executeQuery();
                            while (resultSet.next()) {
                                int subCategoryIdNew = resultSet.getInt("sub_category_id");
                                String subCategory = resultSet.getString("name");
                                if (subCategoryId == subCategoryIdNew) {
                    %>
                    <option value="<%= subCategoryIdNew%>" selected><%= subCategory%></option>
                    <%
                    } else {
                    %>
                    <option value="<%= subCategoryIdNew%>"><%= subCategory%></option>
                    <%
                                }
                            }

                        } catch (SQLException e) {
                            System.out.println("Error : " + e);
                        } finally {
                            try {
                                if (statement != null) {
                                    statement.close();
                                }
                                if (connection != null) {
                                    dbc.closeDBConnection();
                                }
                            } catch (SQLException e) {
                                System.out.println("Error : Connection closing error");
                            }
                        }
                    %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-sm float-end mt-2 mb-4 px-5">Save</button>
        </div>
    </div>
</form>

<script>
    function previewImage(event) {
        const input = event.target;
            if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
            const imagePreview = document.getElementById('imagePreview');
            const imagePreviewWrapepr = document.getElementById('imagePreviewWrapper');
            imagePreview.src = e.target.result;
            imagePreviewWrapepr.style.display = 'block';
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

<script>
    function getData(productId) {

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "ReadProductServlet?productId=" + encodeURIComponent(productId), true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
    var responseData = JSON.parse(xhr.responseText);
    document.getElementById("name").value = responseData.name;
    document.getElementById("buyingPrice").value = responseData.buyingPrice;
    document.getElementById("sellingPrice").value = responseData.sellingPrice;
    document.getElementById("description").value = responseData.description;
    document.getElementById("image").value = responseData.image;
    }
    };
    xhr.send();
    }

    getData(<%= productId%>);</script>
<script>
    function deleteProductConfirmation() {
    var result = confirm("Are you sure you want to delete this product?");
    if (result) {
    return true;
    } else {
    return false;
    }
    }
</script>

<script>
    function validateForm() {
    var productName = document.forms["productForm"]["name"].value.trim();
    var buyingPrice = document.forms["productForm"]["buying_price"].value.trim();
    var sellingPrice = document.forms["productForm"]["selling_price"].value.trim();
    var description = document.forms["productForm"]["description"].value.trim();
    var image = document.forms["productForm"]["image"].value.trim();
    var subCategory = document.forms["productForm"]["sub_cat"].value;
    // Validate product name
    if (productName == "") {
    alert("Please enter a product name");
    return false;
    }

    // Validate buying price
    if (!/^\d*\.?\d+$/.test(buyingPrice) || buyingPrice == "") {
    alert("Buying price must be a valid number");
    return false;
    }

    // Validate selling price
    if (!/^\d*\.?\d+$/.test(sellingPrice || sellingPrice == "")) {
    alert("Selling price must be a valid number");
    return false;
    }

    // Validate description
    if (description == "") {
    alert("Please enter a description");
    return false;
    }

    // Validate sub category
    if (subCategory == "") {
    alert("Please select a sub category");
    return false;
    }

    return true;
    }
</script>

<%@ include file="admin_footer.jsp" %>
