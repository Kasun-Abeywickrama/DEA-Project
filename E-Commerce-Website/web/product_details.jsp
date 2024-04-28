<%-- 
    Document   : product_details
    Created on : Apr 26, 2024, 3:35:34 PM
    Author     : HP
--%>

<%@page import="ProductView.ProductViewDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Details</title>
        <link href="css/bootstrap-4.4.1.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/product_details.css">
    </head>

    <body>
        <%  if(request.getAttribute("product_details") != null){

                ProductViewDetails p = (ProductViewDetails)request.getAttribute("product_details");
                String img_name = "images/product_images/"+p.getProductId()+".png";
        %>
        
        <script>
            function checkCart() {
                if(document.add_cart_form.input.value > document.add_cart_form.stock.value){
                    alert("Not Enough Stock");
                    return false;
                }
                
                return true;
            }
        </script>
        
        
                <div class="row">
                    <div class="col-lg-6"><img src="<%=img_name %>" width="550" height="400" alt=""/>
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

                                    <div class="btn">
                                        <input type="hidden" id="stock" value="<%=p.getTotalAvailableQuantity() %>">
                                        <input type="hidden" name="product_id" value="<%=p.getProductId() %>">
                                        <button class="btn2" name="submit" value="add_to_cart">Add to Cart</button>
                                    </div>
                                </form>
                        </div>
                  </div>
                </div>
                <script src="js/product_details.js"></script>
        <%    
            }
        %>         
    </body>
</html>