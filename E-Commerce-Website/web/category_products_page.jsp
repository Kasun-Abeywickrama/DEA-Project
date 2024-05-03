<%-- 
    Document   : category_products_page
    Created on : Apr 29, 2024, 12:02:40 AM
    Author     : dewmi
--%>

<%@page import="ResourcePaths.ResourcePaths"%>
<%@page import="ProductManagement.ReadProductsByCategoriesFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String mainCategoryId = request.getParameter("main_category_id");
    String mainCategoryName = request.getParameter("main_category_name");
    
    ResourcePaths p1 = new ResourcePaths();
    String PRODUCT_IMAGES_PATH = p1.getRelativeProductImagePath();
%>

<%@ include file="header_part_01.jsp"%>
<style>
    /* Styles for the "Add to Cart" button */
    .btn-outline-primary{
        border: 1px solid gray;
        color: black;
    }
    .btn-outline-primary:hover{
        border: 1px solid #FC6B03;
        background-color: #FC6B03;
    }

    .btn-outline-primary:focus{
        border: 1px solid #FC6B03;
        background-color: #FC6B03;
    }

</style>
<%@ include file="header_part_02.jsp"%>

<div class="container">
    <h4 class="text-center mt-5 mb-4"><%=mainCategoryName%></h4>
    <%
        // Retrieve sub-category list from request attribute
        ArrayList<String> subCategoryList = (ArrayList<String>) request.getAttribute("sub_category_list");

        for (String subCategory : subCategoryList) {
            String[] subCategoryDetails = subCategory.split(", ");
    %>
    <h5 class=""><%= subCategoryDetails[1]%></h5>
    <hr class="mb-5">
    <div class="row">
        <%
            // Create an instance of ReadProductsByCategoriesFunctions class
            ReadProductsByCategoriesFunctions rbco = new ReadProductsByCategoriesFunctions();
            // Get products for this sub-category and main category
            ArrayList<String> productList = rbco.getProductsByCategories(Integer.parseInt(subCategoryDetails[0]), Integer.parseInt(mainCategoryId));
            // Loop through each product in the list
            for (String product : productList) {
                String[] productDetails = product.split(", ");
        %>
        <div class="col-md-3 mb-5 ">
            <div class="cart-container mb-2" style="width: 100%; height: 300px; background-image: url('<%=PRODUCT_IMAGES_PATH + productDetails[0]%>.png'); background-size: cover; background-position: center;"></div>
            <h6><%= productDetails[1]%></h6>
            <h6><%= productDetails[4]%></h6>
            <h5>Rs. <%= productDetails[2]%></h5>

            <form action="ProductViewServlet" method="post" class="w-100 px-1 mt-3">
                <input type="hidden" name="product_id" value="<%= productDetails[0]%>">
                <button class="btn btn-outline-primary btn-sm w-100 font-weight-bold" name="submit" value="get_details">More Details</button>
            </form>
        </div>
        <%
            }
        %>
    </div>
    <%
        }
    %>
</div>


<script>
    var tabs = document.querySelectorAll('.nav-link');
    tabs.forEach(function (tab) {
        tab.classList.remove('active');
    });
    document.getElementById('nav-link-categories').classList.add('active');
</script>
<%@ include file="footer.jsp"%>
