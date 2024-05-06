<%-- 
    Document   : checkout_page
    Created on : Apr 25, 2024, 11:56:24 AM
    Author     : HP
--%>

<%@page import="ShoppingCart.ShoppingCartDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <%@include  file="header_part_01.jsp" %>

        <title>Shopping Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/remixicon@4.2.0/fonts/remixicon.css" rel="stylesheet" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/checkout_page.css">

        <script>
            window.onload = function(){

                var xhr = new XMLHttpRequest();

                xhr.open("GET", "ShoppingCartServlet", true);

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        
                        tempContainer = document.createElement('div');
                        tempContainer.innerHTML = xhr.responseText;
            
                        extractedSection = tempContainer.querySelector('#section').innerHTML;
                        document.getElementById("section").innerHTML = extractedSection;
                    }
                };

                xhr.send();
            };
        </script>
        
        <script>
            function cartAlert(event){
                event.preventDefault();
                alert("Cart is Empty or Product stock unavailabaility");
            }
        </script>
        
        <!-- Displaying the alert message -->
        <%
            if(request.getParameter("alert_message") != null){
        
                String alert_message = request.getParameter("alert_message");
        %>        
                <script>alert("<%=alert_message %>");</script>
        <%  
            }
        %>

    <%@include  file="header_part_02.jsp" %>
        
        <section style="margin-left: 100px" id="section">
            <div class="box">
                <div class="d-p">
                    <button><i class="ri-add-circle-line"></i>&nbsp;Add New Delivery Address</button>
                    <button><i class="ri-add-circle-line"></i>&nbsp;Select Payment Method</button>
                </div>
                <div class="content">
                    <div class="row">
                        <% int can_checkout = 1; %>
                        <!--Item with image-->
                        <div class="col-lg-7">
                            <div class="con-de">
                                <table  border="0" cellspacing="20" cellpadding="0">
                                    <tbody>
                                <%
                                    if(request.getAttribute("shopping_cart_product_list") != null){

                                        @SuppressWarnings("unchecked")
                                        ArrayList<ShoppingCartDetails> sharray = (ArrayList<ShoppingCartDetails>)request.getAttribute("shopping_cart_product_list");

                                        if(sharray.size() != 0){

                                            for(ShoppingCartDetails i: sharray){
                                                
                                                String stock;
                                                
                                                if(i.getTotalAvailableQuantity() < 10){
                                                    stock = "0"+i.getTotalAvailableQuantity();
                                                }
                                                else{
                                                    stock = ""+i.getTotalAvailableQuantity();
                                                }
                                                
                                                String img_path = "images/product_images/"+i.getProductId()+".png";
                                %>
                                                <tr>
                                                    <td width="212" height="153">
                                                        <img src="<%=img_path %>" width="193" height="153" alt=""/>
                                                    </td>
                                                    <td width="396">
                                                        <table>
                                                            <tr>
                                                                <td>
                                                                    <form action="ProductViewServlet" method="post">
                                                                        <h3>
                                                                            <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                                            <button name="submit" value="get_details" style="outline: none; border: none; background-color: transparent"><%=i.getProductName() %></button>
                                                                        </h3>
                                                                    </form>
                                                                </td>
                                                                <td>
                                                                    <form action="ShoppingCartServlet" method="post">
                                                                        <h3>
                                                                            &nbsp;
                                                                            <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                                            <button id="remove-btn" type="submit" name="submit" value="remove_item"><i class='bx bx-trash'></i></button>
                                                                        </h3>
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <p>&nbsp;&nbsp; <%=i.getSubCategoryName() %>	  </p><br>
                                                        <table width="393" border="0" cellspacing="0" cellpadding="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td><strong>Rs. <%=i.getSellingPrice() %></strong></td>
                                                                    <td width="100">
                                                                        <div class="row">
                                                                            <div class="col-lg-3">
                                                                                <form action="ShoppingCartServlet" method="post">
                                                                                    <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                                                    <button id="plus_minus_1" type="submit" name="submit" value="reduce_item"><i class='bx bx-minus-circle'></i></button>
                                                                                </form>
                                                                            </div>
                                                                            <div class="col-lg-3">
                                                                                <button id="non_plus_minus"><%=i.getQuantity() %></button>
                                                                            </div>
                                                                            <div class="col-lg-3">
                                                                                <form action="ShoppingCartServlet" method="post">
                                                                                    <input type="hidden" name="product_id" value="<%=i.getProductId() %>">
                                                                                    <button id="plus_minus_2" type="submit" name="submit" value="increase_item"><i class='bx bx-plus-circle'></i></button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    
                                                                    <%  if(i.getQuantity() <= i.getTotalAvailableQuantity()){ %>
                                                                    
                                                                            <td style="color: green; text-align: right">Stock : <%=stock %></td>
                                                                    <%
                                                                        }
                                                                        else{
                                                                    %>
                                                                            <td style="color: red; text-align: right">Stock : <%=stock %></td>
                                                                    <%
                                                                            can_checkout = 0;
                                                                        }
                                                                    %>                                                                 
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <p>&nbsp;</p>
                                                    </td>
                                                </tr>

                                <%
                                            }
                                        }
                                        else{
                                %>
                                            <tr>
                                                <td colspan="2" width="608" height="300" style="text-align: center">Your Shopping Cart is Empty</td>
                                            </tr>
                                <%
                                            can_checkout = 0;
                                        }
                                    }
                                    else{
                                %>
                                        <tr>
                                            <td colspan="2" width="608" height="300" style="text-align: center">Your Shopping Cart is Empty</td>
                                        </tr>
                                <%
                                        can_checkout = 0;
                                    }
                                %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                                    
                        <!--Order Summary-->
                        <div class="col-lg-5">
                            <div class="c-s">
                                <div class="c-s-t">
                                    <table  border="0" cellspacing="0" cellpadding="0">
                                        <tbody>
                                            <tr>
                                                <td width="219" height="87"><h3 style="font-size: 24px">Order Summary</h3></td>
                                                <td width="199">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>Sub Total</td>
                                                <td align="right">
                                                    <blockquote>
                                                        <%  if(request.getAttribute("sub_total") != null) { %>
                                                                <p>Rs. <%=request.getAttribute("sub_total") %></p>
                                                        <%
                                                            }
                                                            else{
                                                        %>
                                                                <p>Rs. 0.0</p>
                                                        <%
                                                            }
                                                        %>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="50">Delivery Fee</td>
                                                <td align="right">
                                                    <blockquote>
                                                    <p>Rs. 12000.0</p>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Discount</td>
                                                <td align="right">
                                                    <blockquote>
                                                    <p>Rs. 0.0</p>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="84"><strong>Total</strong></td>
                                                <td align="right">
                                                    <blockquote>
                                                        <%  if(request.getAttribute("sub_total") != null) { 
                                                                float tot = Float.parseFloat((String)request.getAttribute("sub_total"))+12000.0f;
                                                        
                                                        %>
                                                                <p><strong>Rs. <%=tot %></strong></p>
                                                        <%
                                                            }
                                                            else{
                                                        %>
                                                                <p><strong>Rs. 0.0</strong></p>
                                                        <%
                                                            }
                                                        %>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                    <%  
                                        HttpSession ses = request.getSession(false);
                                        
                                        if(ses != null){
                                            
                                            if(ses.getAttribute("user_id") != null){
                                                
                                                if(can_checkout == 1){ 
                                    %>
                                                    <form action="Delivery_details.jsp" method="POST">
                                                        <center><button>Checkout</button></center>
                                                    </form>
                                    <%
                                                }
                                                else{
                                    %>
                                                    <form action="temp" method="POST" onsubmit="return cartAlert(event)">
                                                        <center><button>Place Order</button></center>
                                                    </form>
                                    <%
                                                }
                                            }
                                            else{
                                    %>
                                                <form action="sign_in_page.jsp" method="POST">
                                                    <center><button>Place Order</button></center>
                                                </form>
                                    <%
                                            }    
                                        }
                                        else{
                                    %>
                                            <form action="sign_in_page.jsp" method="POST">
                                                <center><button>Place Order</button></center>
                                            </form>
                                    <%
                                        }
                                    %>       
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
            <br><br>
        </section>
    
    <%@include  file="footer.jsp" %>




