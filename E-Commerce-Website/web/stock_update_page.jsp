<%-- 
    Document   : stock_update_page
    Created on : Apr 11, 2024, 12:04:12 AM
    Author     : Sithuruwan
--%>

<%@page import="InventoryManagement.ProductStock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <%@include file="admin_header_part_01.jsp" %>
    
        <title>Update Stocks</title>
        
	<link href="css/stock_update_page.css" rel="stylesheet" type="text/css">
        
        <!-- Javascript validation -->
        <script src="js/javascript_validation.js"></script>
    
    <%@include file="admin_header_part_02.jsp" %>
    
        <p style="color: #E97000;"><i>Inventory &nbsp;>&nbsp; Update Product Stock </i></p>
                
                <%
                    if(request.getAttribute("stock_details") != null 
                            && request.getAttribute("product_name") != null){
        
                        ProductStock ps = (ProductStock)request.getAttribute("stock_details");
                        String product_name = (String)request.getAttribute("product_name");
                %>
                
                        <center>
                            <table>
                                <tr>
                                    <td class="heading">Product ID : </td>
                                    <td><%=ps.getProductId() %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Product Name : </td>
                                    <td><%=product_name %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Stock ID : </td>
                                    <td><%=ps.getStockId() %></td>
                                </tr>
                                <tr>
                                    <td class="heading">Last Updated : </td>
                                    <td><%=ps.getdateTime() %></td>
                                </tr>
                            </table>
                        </center>
                        <br><br>
                        
                        <div class="row">
                            <div class="col-lg-6">
                                <form name="stock_details_update_form" action="ProductStockUpdateServlet" method="POST" onsubmit="return stock_update_details_validate()">
                                    <center>
                                        <table class="stock_details_update_table">
                                            <tr>
                                                <td id="h3_heading_details_td">
                                                    <center>
                                                        <h3>UPDATE DETAILS</h3>
                                                        <br>
                                                    </center>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Supplier Name : </th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="form-group"> 
                                                        <input type="text" class="form-control" name="supplier_name" value="<%=ps.getSupplierName() %>">
                                                    </div>
                                                    <br>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Buying Price : </th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div class="form-group"> 
                                                        <input type="text" class="form-control" name="buying_price" value="<%=ps.getBuyingPrice() %>">
                                                    </div>
                                                    <br>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td id="update_button_td">
                                                    <br>
                                                    <input type="hidden" name="stock_id" value="<%=ps.getStockId() %>">
                                                    <button class="btn btn-primary btn-sm" id="btn1" type="submit" name="submit" value="update_details">Update</button>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                </form>
                            </div>
                                                
                            <div class="col-lg-6">  
                                <center>
                                    <table class="quantity_update_table">
                                        <tr>
                                            <td colspan="2" id="h3_heading_quantity_td">
                                                <center>
                                                    <h3>UPDATE QUANTITY</h3>
                                                    <br>
                                                </center>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="heading">Supplied Quantity : </td>
                                            <td><%=ps.getSuppliedQuantity() %></td>
                                        </tr>
                                        <tr>
                                            <td class="heading">Available Quantity : </td>
                                            <td><%=ps.getAvailableQuantity() %></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <form name="add_quantity_form" action="ProductStockUpdateServlet" method="POST" onsubmit="return stock_update_add_quantity_validate()">
                                                    <br>
                                                    <div class="form-group"> 
                                                        <input type="text" class="form-control" name="add_quantity" placeholder="Add Quantity">
                                                    </div>
                                                    <input type="hidden" name="stock_id" value="<%=ps.getStockId() %>">
                                                    <button class="btn btn-primary btn-sm" id="btn2" type="submit" name="submit" value="add_quantity">Add</button>
                                                </form>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" id="quantity_remove_td">
                                                <form name="remove_quantity_form" action="ProductStockUpdateServlet" method="POST" onsubmit="return stock_update_remove_quantity_validate()">
                                                    <br>
                                                    <div class="form-group"> 
                                                        <input type="text" class="form-control" name="remove_quantity" placeholder="Remove Quantity">
                                                    </div>
                                                    <input type="hidden" name="stock_id" value="<%=ps.getStockId() %>">
                                                    <button class="btn btn-primary btn-sm" id="btn3" type="submit" name="submit" value="remove_quantity">Remove</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </table>
                                </center>
                            </div>
                        </div>
   
                <%
                    }
                %>
                
    <%@include file="admin_footer.jsp" %>
    
        

