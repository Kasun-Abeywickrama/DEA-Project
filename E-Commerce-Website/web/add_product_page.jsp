<%-- 
    Document   : create_product_page
    Created on : Mar 28, 2024, 12:53:45 PM
    Author     : HP
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="DatabaseConnection.DBConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="admin_header_part_01.jsp" %>
<%@ include file="admin_header_part_02.jsp" %>

<p style="color: #E97000;"><i>Products &nbsp;>&nbsp; Add Product</i></p>  
<form action="CreateProductServlet" method="post" name="productForm" enctype="multipart/form-data" onsubmit="return validateForm()">
    <div class="row">

        <div class="col-md">
            <div class="form-group mb-4">
                <label for="exampleFormControlInput1" class="mb-2">Product name</label>
                <input type="text" name="name" class="form-control" id="exampleFormControlTextarea1">    
            </div>
            <div class="form-group mb-4">
                <label for="exampleFormControlInput2" class="mb-2">Buying Price</label>
                <input type="text" name="buying_price" class="form-control" id="exampleFormControlTextarea2">
            </div>
            <div class="form-group mb-4">
                <label for="exampleFormControlInput3" class="mb-2">Selling Price</label>
                <input type="text" name="selling_price" class="form-control" id="exampleFormControlTextarea3">   
            </div>
            <div class="form-group mb-4">
                <label for="exampleFormControlInput4" class="mb-2">Description</label>
                <textarea name="description" class="form-control" id="exampleFormControlTextarea4" rows="3"></textarea>  
            </div>

        </div>

        <div class="col-md">

            <div class="form-group mb-4">
                <label for="exampleFormControlInput5" class="mb-2">Image</label>
                <input type="file" name="image" accept="image/png" class="form-control" id="exampleFormControlTextarea5" onchange="previewImage(event)">   
            </div>

            <div class="form-group mb-4" style="display: none;" id="imagePreviewWrapper">
                <img id="imagePreview" src="#" class="img-thumbnail rounded mx-auto d-block w-25"  alt="Image Preview">
            </div>

            <div class="form-group mb-4">

                <label for="exampleFormControlSelect6" class="mb-2">Example multiple select</label>
                <select name="sub_cat" class="form-control" id="exampleFormControlSelect6">
                    <option selected hidden></option>
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

                    %>
                    <option value="<%= subCategoryIdNew%>"><%= subCategory%></option>
                    <%
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
            <button type="submit" class="btn btn-primary btn-sm float-end mt-2 mb-4">Add Product</button>
            <button type="reset" class="btn btn-outline-primary btn-sm float-end mt-2 mb-4 me-2">Cancel</button>
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

    // Validate image
    if (image == "") {
    alert("Please select an image");
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
