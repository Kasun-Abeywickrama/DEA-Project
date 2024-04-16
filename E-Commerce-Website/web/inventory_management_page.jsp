<%-- 
    Document   : inventory_management_page
    Created on : Apr 10, 2024, 5:23:17 AM
    Author     : Sithuruwan
--%>

<%@page import="InventoryManagement.ProductDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Inventory Management</title>
        
        <!-- Bootstrap -->
	<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
	<link href="css/inventory_management_page.css" rel="stylesheet" type="text/css">
        
        <!-- Sending a GET request when the page is loading -->
        <!-- Updating the body of the document according to the response -->
        <script>
            window.onload = function(){
                
                var xhr = new XMLHttpRequest();
              
                xhr.open("GET", "ProductDetailsRetrieveServlet", true);

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        document.body.innerHTML = xhr.responseText;
                    }
                };
                
                xhr.send();
            };
        </script>
    </head>
    <body>
        
        <!-- Displaying the alert message -->
        <%
            if(request.getAttribute("alert_message") != null){
        
                String alert_message = (String)request.getAttribute("alert_message");
        %>        
                <script>alert("<%=alert_message %>");</script>
        <%  
            }
        %>
        
        <!-- Function to be executed when something searched -->
        <script>
            
            function searchProduct(event) {
 
                event.preventDefault();
                
                var searchString = document.getElementById("search_input").value;
                
                var xhr = new XMLHttpRequest();
                
                xhr.open("POST", "ProductDetailsRetrieveServlet");
                
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
              
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        document.body.innerHTML = xhr.responseText;
                        document.getElementById("search_input").value = searchString;
                    }
                };
                
                xhr.send("search_string="+searchString);
            };
        </script>
        
  	<div class="container-fluid">
            <div class="container">
               
                <form id="search_form" onsubmit="searchProduct(event)">
                    <center>
                        <table class="search_table">
                            <tr>
                                <td>
                                    <input class="form-control mr-sm-2" type="search" id="search_input" placeholder="Search Product" aria-label="Search">
                                </td>
                                <td>&nbsp;</td>
                                <td>
                                    <button class="btn btn-warning my-2 my-sm-0" type="submit">Search</button>
                                </td>
                            </tr>
                        </table>
                    </center>      
                </form>
                
                <br><br>
                <%
                    if(request.getAttribute("product_details_list") != null){

                        @SuppressWarnings("unchecked")
                        ArrayList<ProductDetails> product_details_list = (ArrayList<ProductDetails>)request.getAttribute("product_details_list");

                        if(product_details_list.size() != 0){        
                %>
                            <center>
                                <table class="details_table">
                                    <tr>
                                        <th>Product ID</th>
                                        <th>Product Name</th>
                                        <th>Main Category</th>
                                        <th>Sub Category</th>
                                        <th>Total Available Quantity</th>
                                    </tr>
                
                <%
                                    for(ProductDetails i:  product_details_list){
                %>
                                        <tr>
                                            <td>
                                                <%=i.getProductId() %>
                                            </td>
                                            <td>
                                                <%=i.getProductName() %>
                                            </td>
                                            <td>
                                                <%=i.getProductMainCategory() %>
                                            </td>
                                            <td>
                                                <%=i.getProductSubCategory() %>
                                            </td>
                                            <td>
                                                <%=i.getTotalAvailableQuantity() %>
                                            </td>
                                            <td>
                                                <form action="ProductStockRetrieveServlet" method="POST">
                                                    <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                    <button type="submit" class="btn btn-warning my-2 my-sm-0" value="stock_list" name="submit">Manage</button>
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
                                <h6>No Available Products</h6>
                            </center>
                <%
                        }
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