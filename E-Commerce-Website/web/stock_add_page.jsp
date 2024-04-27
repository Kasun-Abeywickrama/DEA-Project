<%-- 
    Document   : stock_add_page
    Created on : Apr 11, 2024, 5:59:41 PM
    Author     : Sithuruwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <%@include file="admin_header_part_01.jsp" %>
    
        <title>Add Stocks</title>
    
	<link href="css/stock_add_page.css" rel="stylesheet" type="text/css">
        
        <!-- Javascript validation -->
        <script src="js/javascript_validation.js"></script>
    
    <%@include file="admin_header_part_02.jsp" %> 
    
        <p style="color: #E97000;"><i>Inventory &nbsp;>&nbsp; Add New Product Stock </i></p>
                
            <%  if(request.getParameter("product_id") != null && request.getParameter("product_name") != null){
                    
                    String product_id = request.getParameter("product_id");
                    String product_name = request.getParameter("product_name");
            %>
                
                    <center>
                        <table class="details-table">
                            <tr>
                                <td class="heading">Product ID : </td>
                                <td><%=product_id %></td>
                            </tr>
                            <tr>
                                <td class="heading">Product Name : </td>
                                <td><%=product_name %></td>
                            </tr>
                        </table>
                    </center>
                    <br><br>
            
            
                    <form name="stock_add_form" action="ProductStockAddServlet" method="POST" onsubmit="return stock_add_validate()">
                        <center>
                            <table class="stock_add">
                                <tr>
                                    <td id="h3_heading_td">
                                        <center>
                                            <h3>ADD STOCK</h3>
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
                                            <input type="text" class="form-control" name="supplier_name">
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
                                            <input type="text" class="form-control" name="buying_price">
                                        </div>
                                        <br>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Stock Quantity : </th>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="form-group"> 
                                            <input type="text" class="form-control" name="quantity">
                                        </div>
                                        <br>
                                    </td>
                                </tr>
                                <tr>
                                    <td id="add_button_td">
                                        <br>
                                        <input type="hidden" name="product_id" value="<%=product_id %>"> 
                                        <button class="btn btn-primary btn-sm" id="btn1" type="submit" name="submit" value="stock_add">Add Stock</button>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </form>
            <%
                }
            %>
            
    <%@include file="admin_footer.jsp" %>
    
