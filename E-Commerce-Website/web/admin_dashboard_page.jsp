<%-- 
    Document   : admin_dashboard_page
    Created on : Apr 18, 2024, 12:57:57 AM
    Author     : Sithuruwan
--%>

<%@page import="AdminDashboard.AdminDashboardOrderDetails"%>
<%@page import="AdminDashboard.AdminDashboardProductDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Dashboard</title>
        
        <!-- Bootstrap -->
	<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
	<link href="css/admin_dashboard_page.css" rel="stylesheet" type="text/css">
        
        <script>
            window.onload = function(){
                
                var xhr = new XMLHttpRequest();
              
                xhr.open("GET", "AdminDashboardDataRetrieveServlet", true);

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
        <%@include file="admin_header.jsp" %>
                
            <div class="row">   
                <div class="col-lg-6" id="col1">
                    
                    <%
                        if(request.getAttribute("month_total_orders") != null && request.getAttribute("month_delivered_orders") != null){
                            
                            String month_total_orders = (String)request.getAttribute("month_total_orders");
                            String month_delivered_orders = (String)request.getAttribute("month_delivered_orders");
                    %>
                    
                            <div class="row">
                                <div class="col-lg-6">

                                    <center>
                                        <div class="heading">TOTAL ORDERS OF THE MONTH</div>
                                        <br>
                                        <div id="total_orders_container">
                                            <span><%=month_total_orders %></span>
                                        </div>
                                    </center>

                                </div>

                                <div class="col-lg-6">

                                    <center>
                                        <div class="heading">DELIVERED ORDERS OF THE MONTH</div>
                                        <br>
                                        <div id="delivered_orders_container">
                                            <span><%=month_delivered_orders %></span>
                                        </div>
                                    </center>

                                </div>
                            </div>
                    
                    <%
                        }
                    %>
                </div>
                
                <div class="col-lg-6" id="col2">
                    
                    <h5 style="text-align: center; color: #E97000">MONTHLY TOP ORDERED PRODUCTS</h5>
                    <br>
                    
                    <%
                        if(request.getAttribute("month_top_ordered_product_list") != null){
                            
                            @SuppressWarnings("unchecked")
                            ArrayList<AdminDashboardProductDetails> month_top_ordered_product_list = (ArrayList<AdminDashboardProductDetails>)request.getAttribute("month_top_ordered_product_list");

                            if(month_top_ordered_product_list.size() != 0){             
                    %>
                                <center>
                                    <table class="table-style">
                                        <tr>
                                            <th>Product ID</th>
                                            <th>Product Name</th>
                                            <th>Sold Quantity</th>
                                        </tr>
                    <%    
                                        for(AdminDashboardProductDetails i: month_top_ordered_product_list){
                    %>
                                            <tr>
                                                <td style="text-align: center"><%=i.getProductId() %></td>
                                                <td><%=i.getProductName() %></td>
                                                <td style="text-align: center"><%=i.getMonthSoldQuantity() %></td>
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
                                <center>
                                    <br><br>
                                    <h6>No Top Ordered Products To Display</h6>
                                </center>
                    <%
                            }
                        }
                    %> 
                </div>
            </div>
                
            <br><br>
        
            <div class="row">
                <div class="col-lg-6" id="col3">
                    
                    <h5 style="text-align: center; color: #E97000">PRODUCT STOCK LEVELS</h5>
                    <br>
                    
                    <%
                        if(request.getAttribute("low_stock_product_list") != null){

                            @SuppressWarnings("unchecked")
                            ArrayList<AdminDashboardProductDetails> low_stock_product_list = (ArrayList<AdminDashboardProductDetails>)request.getAttribute("low_stock_product_list");

                            if(low_stock_product_list.size() != 0){             
                    %>
                                <center>
                                    <table class="table-style">
                                        <tr>
                                            <th>Product ID</th>
                                            <th>Product Name</th>
                                            <th>Stock Level</th>
                                        </tr>
                    <%    
                                        for(AdminDashboardProductDetails i: low_stock_product_list){
                    %>
                                            <tr>
                                                <td style="text-align: center"><%=i.getProductId() %></td>
                                                <td><%=i.getProductName() %></td>
                                                <% 
                                                    if(i.getTotalAvailableQuantity() < 3){
                                                %>
                                                        <td style="text-align: center" bgcolor="#FF474D"><%=i.getTotalAvailableQuantity() %></td>
                                                <%
                                                    }
                                                    else if(i.getTotalAvailableQuantity() <5){
                                                %>
                                                        <td style="text-align: center" bgcolor="#EFD52D"><%=i.getTotalAvailableQuantity() %></td>
                                                <%
                                                    }
                                                    else{
                                                %>
                                                        <td style="text-align: center" bgcolor="#69D850"><%=i.getTotalAvailableQuantity() %></td>
                                                <%
                                                    }
                                                %>
                                            </tr>
                    <%
                                        }  
                    %>
                                    </table>
                                    <form action="inventory_management_page.jsp" method="POST">
                                        <button type="submit" class="btn btn-warning my-2 my-sm-0" value="submit" name="submit">Manage</button>
                                    </form>
                                </center>
                    <%
                            }
                            else{
                    %>
                                <center>
                                    <br><br>
                                    <h6>No Product Stocks To Display</h6>
                                </center>
                    <%
                            }
                        }
                    %> 
                </div>
                
                <div class="col-lg-6" id="col4">
                    
                    <h5 style="text-align: center; color: #E97000">HIGHEST PRIORITY ORDERS</h5>
                    <br>
                    
                    <%
                        if(request.getAttribute("status_order_list") != null){

                            @SuppressWarnings("unchecked")
                            ArrayList<AdminDashboardOrderDetails> status_order_list = (ArrayList<AdminDashboardOrderDetails>)request.getAttribute("status_order_list");

                            if(status_order_list.size() != 0){             
                    %>
                                <center>
                                    <table class="table-style">
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Order Date</th>
                                            <th>Total Price</th>
                                            <th>Status</th>
                                        </tr>
                    <%    
                                        for(AdminDashboardOrderDetails i: status_order_list){
                    %>
                                            <tr>
                                                <td style="text-align: center"><%=i.getOrderId() %></td>
                                                <td style="text-align: center"><%=i.getOrderDate() %></td>
                                                <td style="text-align: center"><%=i.getTotalPrice() %></td>
                                                <%
                                                    if("Pending".equals(i.getOrderStatus())){
                                                %>
                                                        <td style="text-align: center" bgcolor="#FF474D"><%=i.getOrderStatus() %></td>
                                                <%
                                                    }
                                                    else if("Dispatched".equals(i.getOrderStatus())){
                                                %>
                                                        <td style="text-align: center" bgcolor="#EFD52D"><%=i.getOrderStatus() %></td>
                                                <%
                                                    }
                                                    else{
                                                %>
                                                        <td style="text-align: center" bgcolor="#69D850"><%=i.getOrderStatus() %></td>
                                                <%
                                                    }
                                                %>
                                            </tr>
                    <%
                                        }  
                    %>
                                    </table>
                                    <form action="#" method="POST">
                                        <button type="submit" class="btn btn-warning my-2 my-sm-0" value="#" name="submit">Manage</button>
                                    </form>
                                </center>
                    <%
                            }
                            else{
                    %>
                                <center>
                                    <br><br>
                                    <h6>No Orders To Display</h6>
                                </center>
                    <%
                            }
                        }
                    %> 
                </div>
            </div>
            
        <%@include file="admin_footer.jsp" %>
    
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-3.4.1.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/popper.min.js"></script> 
        <script src="js/bootstrap-4.4.1.js"></script>
    </body>
</html>