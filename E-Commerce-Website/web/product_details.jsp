<%-- 
    Document   : product_details
    Created on : Apr 26, 2024, 3:35:34 PM
    Author     : HP
--%>

<%@page import="ProductView.ProductViewDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <%@include  file="header_part_01.jsp" %>
    
        <title>Product Details</title>
        <link rel="stylesheet" href="css/product_details.css">
    
    <%@include  file="header_part_02.jsp" %>
    
        <%  if(request.getAttribute("product_details") != null){

                ProductViewDetails p = (ProductViewDetails)request.getAttribute("product_details");
                String img_name = "images/product_images/"+p.getProductId()+".png";
        %>
        
        <script>
            function checkCart() {
                if(parseInt(document.add_cart_form.quantity.value) === 0){
                    alert("Please Provide A Quantity");
                    return false;
                }
                
                if(parseInt(document.add_cart_form.quantity.value) > parseInt(document.add_cart_form.stock.value)){
                    alert("Not Enough Stock");
                    return false;
                }
                
                return true;
            }
        </script>
        
        
                <div class="row" style="margin-top: 40px">
                    <div class="col-lg-6"><img src="<%=img_name %>" width="550" height="400" alt="" style="margin-left: 120px"/>
                    </div>

                    <div class="col-lg-6">
                        <div class="box01">
                            
                                <h3><%=p.getProductName() %></h3>
                                
                                <p style="font-weight: bold"><%=p.getSubCategory() %></p>
                                
                                <%  if(p.getTotalAvailableQuantity() < 5){  
                                %>
                                        <p style="color: red">Stock : <%=p.getTotalAvailableQuantity() %></p>
                                <%
                                    }
                                    else{
                                %>
                                        <p style="color: green">Stock : <%=p.getTotalAvailableQuantity() %></p>
                                <%
                                    }
                                %>
                                
                                <table id="con">
                                    <tr>
                                        <td><%=p.getDescription() %></td>
                                    </tr>
                                </table>
                                <br>
                                
                                <form name="add_cart_form" action="ProductViewServlet" method="post" onsubmit="return checkCart()"> 
                                    <p><strong>Rs. <%=p.getSellingPrice() %></strong></p><br>
                                    <div class="input-group">
                                        Quantity: &nbsp;
                                        <button id="decrement" type="button">-</button>
                                        <input type="number" id="input" value="0" name="quantity" readonly>
                                        <button id="increment" type="button">+</button>
                                    </div>
                                    <input type="hidden" name="stock" value="<%=p.getTotalAvailableQuantity() %>">
                                    <input type="hidden" name="product_id" value="<%=p.getProductId() %>">
                                    <center><button class="btn2" name="submit" value="add_to_cart">Add to Cart</button></center> 
                                </form>
                        </div>
                  </div>
                </div>
                <script src="js/product_details.js"></script>
        <%    
            }
        %>      
        <br><br>
    <%@include  file="footer.jsp" %>