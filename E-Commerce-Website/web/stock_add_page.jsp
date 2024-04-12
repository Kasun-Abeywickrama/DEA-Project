<%-- 
    Document   : stock_add_page
    Created on : Apr 11, 2024, 5:59:41 PM
    Author     : Sithuruwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Add Stocks</title>
        
        <!-- Bootstrap -->
	<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
	<link href="css/stock_add_page.css" rel="stylesheet" type="text/css">
        
        <!-- Javascript validation -->
        <script src="js/javascript_validation.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="container">
                
            <%  if(request.getParameter("product_id") != null){
                    
                    String product_id = request.getParameter("product_id");
            %>
                
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
                                    </td>
                                </tr>
                                <tr>
                                    <td id="add_button_td">
                                        <br>
                                        <input type="hidden" name="product_id" value="<%=product_id %>"> 
                                        <button class="btn btn-warning my-2 my-sm-0" type="submit" name="submit" value="stock_add">Add Stock</button>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </form>
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
