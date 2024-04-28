<%-- 
    Document   : category_products_page
    Created on : Apr 29, 2024, 12:02:40 AM
    Author     : HP
--%>

<%@page import="ProductManagement.ReadProductsByCategoriesFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String mainCategoryId = request.getParameter("main_category_id");
    String mainCategoryName = request.getParameter("main_category_name");
%>

<%@ include file="header_part_01.jsp"%>
<style>
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

<h4 class="text-center mt-5 mb-4"><%=mainCategoryName%></h4>
<%
    ArrayList<String> subCategoryList = (ArrayList<String>) request.getAttribute("sub_category_list");

    for (String subCategory : subCategoryList) {
        String[] subCategoryDetails = subCategory.split(", ");
%>
<h5 class=""><%= subCategoryDetails[1]%></h5>
<hr class="mb-5">
<div class="row">
    <%
        ReadProductsByCategoriesFunctions rbco = new ReadProductsByCategoriesFunctions();
        ArrayList<String> productList = rbco.getProductsByCategories(Integer.parseInt(subCategoryDetails[0]), Integer.parseInt(mainCategoryId));
        for (String product : productList) {
            String[] productDetails = product.split(", ");
    %>
    <div class="col-md-3 mb-5">
        <img src="images/product_images/<%= productDetails[0]%>.png" alt="..." class="img-fluid mb-2">
        <h6><%= productDetails[1]%></h6>
        <h6><%= productDetails[4]%></h6>
        <h5>Rs. <%= productDetails[2]%></h5>

        <form action="ProductViewServlet" method="post" class="w-100 px-1 mt-3">
            <input type="hidden" name="product_id" value="<%= productDetails[0]%>">
            <input type="submit" name="submit" value="Add to Cart" class="btn btn-outline-primary btn-sm w-100 font-weight-bold">
        </form>
    </div>
    <%
        }
    %>
</div>
<%
    }
%>
<%@ include file="footer.jsp"%>
