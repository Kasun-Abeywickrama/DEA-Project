<%-- 
    Document   : stock_management_page
    Created on : Apr 10, 2024, 5:25:41 AM
    Author     : Sithuruwan
--%>

<%@page import="InventoryManagement.ProductStock"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Product Stock Management</title>
        
        <!-- Bootstrap -->
	<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
	<link href="css/stock_management_page.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <script>
            function confirm_remove_stock (){
                
                if(confirm("Are you sure ?")){
                    return true;
                }
                return false;
            };
        </script>
        
  	<%@include file="admin_header.jsp" %>
               
                <%
                    if(request.getAttribute("product_stock_list") != null 
                        && request.getAttribute("product_id") != null 
                        && request.getAttribute("product_name") != null 
                        && request.getAttribute("total_available_quantity") != null){
                        
                        String product_id = (String)request.getAttribute("product_id");
                        String product_name = (String)request.getAttribute("product_name");
                        String total_available_quantity = (String)request.getAttribute("total_available_quantity");
                        
                %>
                        <center>
                            <table class="details_table">
                                <tr>
                                    <th>Product ID :</th> 
                                    <td><%=product_id %></td>
                                </tr>
                                <tr>
                                    <th>Product Name :</th>
                                    <td><%=product_name %></td>
                                </tr>
                                <tr>
                                    <th>Total Available Quantity :</th>
                                    <td><%=total_available_quantity %></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <center>
                                            <form action="stock_add_page.jsp" method="POST">
                                                <input type="hidden" name="product_id" value="<%=product_id %>">
                                                <button type="submit" class="btn btn-warning my-2 my-sm-0" value="add_stock" name="submit">Add Stock</button>
                                            </form>
                                        </center>
                                    </td>
                                </tr>
                            </table>
                        </center>
                        <br><br>
                
                <%
                        @SuppressWarnings("unchecked")
                        ArrayList<ProductStock> product_stock_list = (ArrayList<ProductStock>)request.getAttribute("product_stock_list");
                        
                        if(product_stock_list.size() != 0){        
                %>
                            <center>
                                <table class="stock_table">
                                    <tr>
                                        <th>Stock ID</th>
                                        <th>Supplier Name</th>
                                        <th>Last Updated</th>
                                        <th>Buying Price</th>
                                        <th>Supplied Quantity</th>
                                        <th>Available Quantity</th>
                                    </tr>
                
                <%
                                    for(ProductStock i:  product_stock_list){
                %>
                                        <tr>
                                            <td>
                                                <%=i.getStockId() %>
                                            </td>
                                            <td>
                                                <%=i.getSupplierName() %>
                                            </td>
                                            <td>
                                                <%=i.getdateTime() %>
                                            </td>
                                            <td>
                                                <%=i.getBuyingPrice() %>
                                            </td>
                                            <td>
                                                <%=i.getSuppliedQuantity() %>
                                            </td>
                                            <td>
                                                <%=i.getAvailableQuantity() %>
                                            </td>
                                            <td id="edit_button_td">
                                                <form action="ProductStockRetrieveServlet" method="POST">
                                                    <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                    <input type="hidden" name="stock_id" value="<%=i.getStockId() %>">
                                                    <button type="submit" class="btn btn-warning my-2 my-sm-0" value="stock_details" name="submit">Update</button>
                                                </form>
                                            </td>
                                            <td id="remove_button_td">
                                                <form id="remove_stock_form" action="ProductStockRemoveServlet" method="POST" onsubmit="return confirm_remove_stock(event)">
                                                    <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                    <input type="hidden" name="stock_id" value="<%=i.getStockId() %>">
                                                    <button type="submit" class="btn btn-warning my-2 my-sm-0" value="remove_stock" name="submit">Remove</button>
                                                </form>
                                            </td>
                                        </tr>
                <%    
                                    }
                %>
                                </table>
                            </center>
                <%
                        }
                        else{
                %>          
                            <br><br><br>
                            <center>
                                <h6>No Available Stocks</h6>
                            </center>
                <%
                        }
                    }
                %> 
            
        <%@include file="admin_footer.jsp" %>
                
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-3.4.1.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/popper.min.js"></script> 
        <script src="js/bootstrap-4.4.1.js"></script>
    </body>
</html>
