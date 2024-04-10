<%-- 
    Document   : stock_edit_page
    Created on : Mar 31, 2024, 1:31:14 AM
    Author     : Sithuruwan
--%>

<%@page import="StockManagement.ProductStock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Stocks</title>
        
        <!-- Bootstrap -->
	<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
	<link href="css/stock_edit_page.css" rel="stylesheet" type="text/css">
        
        <!-- Javascript validation -->
        <script src="js/javascript_validation.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="container">
                
                <%
                    if(request.getAttribute("product_details") != null){
        
                        ProductStock ps = (ProductStock)request.getAttribute("product_details");
                %>
                        <center>
                            <table>
                                <tr>
                                    <td class="heading">Product ID : </td>
                                    <td><%=ps.getProductId() %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Product Name : </td>
                                    <td><%=ps.getProductName() %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Main Category : </td>
                                    <td><%=ps.getProductMainCategory() %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Sub Category : </td>
                                    <td><%=ps.getProductSubCategory() %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Stock Level: </td>
                                    <td><%=ps.getProductStock() %></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <form name="add_stock_form" action="StockEditServlet" method="POST" onsubmit="return add_stock_validate()">
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="add_stock" placeholder="Add Stock">
                                            </div>
                                            <input type="hidden" name="product_id" value="<%=ps.getProductId() %>">
                                            <button class="btn btn-warning my-2 my-sm-0" type="submit" name="submit" value="Add">Add</button>
                                        </form>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <form name="remove_stock_form" action="StockEditServlet" method="POST" onsubmit="return remove_stock_validate()">
                                            <div class="form-group"> 
                                                <input type="text" class="form-control" name="remove_stock" placeholder="Remove Stock">
                                            </div>
                                            <input type="hidden" name="product_id" value="<%=ps.getProductId() %>">
                                            <button class="btn btn-warning my-2 my-sm-0" type="submit" name="submit" value="Remove">Remove</button>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </center>
                <%
                    }
                %>
                
            </div>
        </div>
    
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-3.4.1.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/popper.min.js"></script> 
        <script src="js/bootstrap-4.4.1.js"></script>
    </body>
</html>