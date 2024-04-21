<%-- 
    Document   : order_page
    Created on : Apr 17, 2024, 6:14:02 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String orderId = request.getParameter("order_id");
%>
<%@ include file="admin_header.jsp" %>
<p style="color: #E97000;"><i>Orders &nbsp;>&nbsp; Order</i></p>  

<div class="row">
    <div class="col-md d-flex align-items-center justify-content-center">
        <div style="width: 80px; height: 80px; border: 1px solid gray; border-radius: 8px">
            <p id="orderId" style="margin: 0; text-align: center; line-height: 72px; font-size: 60px; font-weight: 600;"></p>
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
                <td id="dateTime"></td> 
            </tr>
            <tr>
                <td>Total Price</td>
                <td id="totalPrice"></td>
            </tr>
            <tr>
                <td>Shipping Address</td>
                <td id="shippingAddress"></td>
            </tr>
            <tr>
                <td>Receiver Name</td>
                <td id="receiverName"></td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td id="phoneNumber"></td>
            </tr>
            <tr>
                <td>Customer name</td>
                <td id="customerName"></td>
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
        </tbody>
    </table>
</div>

<script>
    function getOrderDetails(orderId) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadOrderServlet?orderId=" + encodeURIComponent(orderId), true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                document.getElementById("orderId").innerText = responseData.orderId;
                document.getElementById("dateTime").innerText = responseData.dateTime;
                document.getElementById("totalPrice").innerText = responseData.totalPrice;
                document.getElementById("shippingAddress").innerText = responseData.shippingAddress;
                document.getElementById("receiverName").innerText = responseData.receiverName;
                document.getElementById("phoneNumber").innerText = responseData.receiverPhoneNumber;
                document.getElementById("customerName").innerText = responseData.customerName;


                //Set current status
                var options = document.querySelectorAll('.status');
                options.forEach(function (option) {
                    option.removeAttribute('selected');
                });

                var selectedOption = document.getElementById(responseData.status);
                selectedOption.setAttribute('selected', '');
                getOrderProductsDetails(<%= orderId%>);
            }
        };
        xhr.send();
    }


    getOrderDetails(<%= orderId%>);
</script>


<script>
    function displayOrderProductsDetails(orderProducts) {
        var tbody = document.getElementById("tbody");

        tbody.innerHTML = "";

        orderProducts.forEach(function (orderProduct) {
            var row = document.createElement("tr");

            var nameCell = document.createElement("td");
            nameCell.textContent = orderProduct.product_name;
            row.appendChild(nameCell);

            var qtyCell = document.createElement("td");
            qtyCell.textContent = orderProduct.quantity;
            row.appendChild(qtyCell);

            var PriceCell = document.createElement("td");
            PriceCell.textContent = orderProduct.selling_price;
            row.appendChild(PriceCell);

            tbody.appendChild(row);
        });
    }

    function getOrderProductsDetails(orderId) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadOrderServlet?orderProductsDetails=true&orderId=" + encodeURIComponent(orderId), true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
//                console.log(responseData);
                displayOrderProductsDetails(responseData);
            }else{
            }
        };
        xhr.send();
    }

    
</script>










<script>
    function submitOrderStatusForm() {
        document.getElementById("updateOrderStatusForm").submit();
    }
</script>


<%@ include file="admin_footer.jsp" %>

