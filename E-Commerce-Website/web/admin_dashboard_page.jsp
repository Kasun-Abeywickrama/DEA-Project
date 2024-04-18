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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <table>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Stock Level</th>
            </tr>
            <%
                if(request.getAttribute("low_stock_product_list") != null){
                    
                    ArrayList<AdminDashboardProductDetails> low_stock_product_list = (ArrayList<AdminDashboardProductDetails>)request.getAttribute("low_stock_product_list");
                    
                    for(AdminDashboardProductDetails i: low_stock_product_list){
            %>

                        <tr>
                            <td><%=i.getProductId() %></td>
                            <td><%=i.getProductName() %></td>
                            <td><%=i.getTotalAvailableQuantity() %></td>
                        </tr>
            
            <%
                    }
                    
                }
            %>    
        </table>
        <br><br>
        
        
        <table>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>Status</th>
            </tr>
            <%
                if(request.getAttribute("status_order_list") != null){
                    
                    ArrayList<AdminDashboardOrderDetails> status_order_list = (ArrayList<AdminDashboardOrderDetails>)request.getAttribute("status_order_list");
                    
                    for(AdminDashboardOrderDetails i: status_order_list){
            %>

                        <tr>
                            <td><%=i.getOrderId() %></td>
                            <td><%=i.getOrderDate() %></td>
                            <td><%=i.getTotalPrice() %></td>
                            <td><%=i.getOrderStatus() %></td>
                        </tr>
            
            <%
                    }
                    
                }
            %>    
        </table>
        
        <table>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Sold Quantity</th>
               
            </tr>
            <%
                if(request.getAttribute("month_top_ordered_product_list") != null){
                    
                    ArrayList<AdminDashboardProductDetails> month_top_ordered_product_list = (ArrayList<AdminDashboardProductDetails>)request.getAttribute("month_top_ordered_product_list");
                    
                    for(AdminDashboardProductDetails i: month_top_ordered_product_list){
            %>

                        <tr>
                            <td><%=i.getProductId() %></td>
                            <td><%=i.getProductName() %></td>
                            <td><%=i.getMonthSoldQuantity() %></td>
                        </tr>
            
            <%
                    }
                    
                }
            %> 
            
            <%
                if(request.getAttribute("month_total_orders") != null){
                    
                    String month_total_orders = (String)request.getAttribute("month_total_orders");
            %>
            
                    hhhh <%=month_total_orders %>
            
            <%
                }
            %>
            
            <%
                if(request.getAttribute("month_delivered_orders") != null){
                    
                    String month_delivered_orders = (String)request.getAttribute("month_delivered_orders");
            %>
            
                    hhhh <%=month_delivered_orders %>
            
            <%
                }
            %>
        </table>
    </body>
</html>
