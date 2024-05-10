<%-- 
    Document   : order_page
    Created on : Apr 17, 2024, 6:14:02 PM
    Author     : HP
--%>

<%@page import="OrderManagement.OrderProductsDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="OrderManagement.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getAttribute("order") != null) {
        Order order = (Order) request.getAttribute("order");
        String cusName = (String) request.getAttribute("userName");
        String orderId = String.valueOf(order.getOrderId());
        String dateTime = order.getDateTime();
        double totalPrice = order.getTotalPrice();
        String orderShippingAddress = order.getShippingAddress();
        String receiverName = order.getReceiverName();
        String receiverPhoneNumber = order.getReceiverPhoneNumber();
        String orderStatus = order.getStatus();

%>

<%@include file="admin_header_part_01.jsp" %>
<title>Order Details</title>
<%@include file="admin_header_part_02.jsp" %>

<p style="color: #E97000;"><i>Orders &nbsp;>&nbsp; Order</i></p>  

<div class="row">
    <div class="col-md d-flex align-items-center justify-content-center">
        <div style="width: 80px; height: 80px; border: 1px solid gray; border-radius: 8px">
            <p id="orderId" style="margin: 0; text-align: center; line-height: 72px; font-size: 60px; font-weight: 600;"><%=orderId%></p>
        </div>
    </div>
    <div class="col-md d-flex align-items-center">
        <span class="h-100" style="line-height: 80px;">Update Status : &nbsp;&nbsp;</span> 
        <form action="UpdateOrderServlet?order_id=<%= orderId%>" method="POST" id="updateOrderStatusForm">
            <select name="order_status" onchange="submitOrderStatusForm()" class="form-select">
                <option value="Pending" id="Pending" class="status">Pending</option>
                <option value="Dispatched" id="Dispatched" class="status">Dispatched</option>
                <option value="Delivered" id="Delivered" class="status">Delivered</option>
            </select>
        </form>
    </div>
</div>
<br>
<div class="w-100">
    <h4>Order Details</h4>
    <table class="table table-bordered">
        <thead></thead>
        <tbody>
            <tr>
                <td>Date Time</td>
                <td id="dateTime"><%=dateTime%></td> 
            </tr>
            <tr>
                <td>Total Price</td>
                <td id="totalPrice"><%=totalPrice%></td>
            </tr>
            <tr>
                <td>Shipping Address</td>
                <td id="shippingAddress"><%=orderShippingAddress%></td>
            </tr>
            <tr>
                <td>Receiver Name</td>
                <td id="receiverName"><%=receiverName%></td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td id="phoneNumber"><%=receiverPhoneNumber%></td>
            </tr>
            <tr>
                <td>Customer name</td>
                <td id="customerName"><%=cusName%></td>
            </tr>
        </tbody>
    </table>
</div>
<br>
<div class="w-100">
    <h4>Order Products</h4>
    <table class="table table-bordered">
        <thead>
        <td>Product Name</td>
        <td>Qty</td>
        <td>Selling Price</td>
        </thead>
        <tbody id="tbody">
            <%
                ArrayList<OrderProductsDetails> productsList = (ArrayList<OrderProductsDetails>) request.getAttribute("productsList");
                if (productsList != null) {
                    for (OrderProductsDetails product : productsList) {
            %>
            <tr>
                <td><%= product.getProductName()%></td>
                <td><%= product.getQuantity()%></td>
                <td><%= product.getSellingPrice()%></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">No products found</td>
            </tr>
        </tbody>
    </table>
</div>

<%
    }
%>
<script>
    //Set current status
    var options = document.querySelectorAll('.status');
    options.forEach(function (option) {
        option.removeAttribute('selected');
    });

    var selectedOption = document.getElementById("<%=orderStatus%>");
    selectedOption.setAttribute('selected', '');
</script>
<script>
    function submitOrderStatusForm() {
        document.getElementById("updateOrderStatusForm").submit();
    }
</script>
<% }%>

<%@ include file="admin_footer.jsp" %>

