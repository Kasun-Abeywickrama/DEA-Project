<%-- 
    Document   : order
    Created on : Apr 28, 2024, 5:31:33 PM
    Author     : Geshan Sampath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        body{
    background-color: #FAF9F6;
}
.container-row-1 {
    background-color: #FC6B03;
    width: 48px;
    height: 49px;
    border-radius: 38px;
    margin-left: 436px;
    margin-top: 100px;
}
h3.text1 {
    margin-left: 17px;
    padding-top: 9px;
    font-size: 27px;
    color: white;
}
hr.hr1 {
    width: 138px;
    height: 11px;
    background-color: #FC6B03;
    border-radius: 10px;
    margin-left: 507px;
    margin-top: -26px;
}
.container-row-2 {
    background-color:#FC6B03;
    width: 48px;
    height: 49px;
    border-radius: 38px;
    margin-left: 666px;
    margin-top: -62px;
}
h3.text2 {
    margin-left: 17px;
    padding-top: 9px;
    font-size: 27px;
    color: white;
}
hr.hr2 {
    width: 138px;
    height: 11px;
    background-color: #FC6B03;
    border-radius: 10px;
    margin-left: 734px;
    margin-top: -27px;
}
.container-row-3 {
    background-color: #FC6B03;
    width: 48px;
    height: 49px;
    border-radius: 38px;
    margin-left: 890px;
    margin-top: -67px;
}
h3.text3 {
    margin-left: 17px;
    padding-top: 9px;
    font-size: 27px;
    color: white;
}
.order-successful-1 {
    background-color: white;
    width: 542px;
    margin-top: 67px;
    margin-left: 405px;
    height: 250px;
}
img {
    margin-left: 250px;
    width: 54px;
    margin-top: 14px;
}
h3.order-successful {
    margin-left: 209px;
    margin-top: 0px;
    font-family: Istok -webkit-body;
}
h4.order-id {
    margin-left: 35px;
    margin-top: -16px;
    font-family: Istok -webkit-body;
}
h4.item-name {
    margin-left: 35px;
    margin-top: -30px;
}
h4.Quantity {
    margin-left: 35px;
    margin-top: -29px;
}
h4.sub.tatal {
    margin-left: 36px;
    margin-top: -27px;
}
h4.Discount {
    margin-left: 37px;
    margin-top: -31px;
}
h4.Total {
    margin-left: 36px;
    margin-top: -28px;
}
h4.payment-method {
    margin-left: 37px;
    margin-top: -32px;
}
h4.Address {
    margin-left: 36px;
    margin-top: -32px;
}
h4.Mobile.Number {
    margin-left: 36px;
    margin-top: -32px;
}
.ok {
    background-color: #FC6B03;
    width: 86px;
    height: 36px;
    margin-left: 227px;
    margin-top: 30px;
    border-radius: 10px;
    border: none;
    cursor: pointer;
}
h2.okk {
    font-size: 20px;
    color: white;
    margin-left: 25px;
    padding: 5px;
    font-family: Istok -webkit-body;
}
    </style>
</head>


<body>

  <div class="container-method">
        <div class="container-row-1">
        <h3 class="text1">1</h3>
        </div>
        </div>
     <div class="arrows1" ><hr class="hr1"></div>  
      <div class="container-row-2">
        <h3 class="text2">2</h3>
        </div>
        
     <div class="arrows2" ><hr class="hr2"></div> 

    
     <div class="container-row-3">
       <h3 class="text3">3</h3>
       </div>
      
       <div class="order-successful-1">
        <img src="images\icons/confirm 1.png" alt="confirm">
        <h3 class="order-successful">Order Successful</h3><br>
        <form action="ShoppingCartServlet">
            <button class="ok" style="color: white; font-weight: bold">OK</button>
        </form>
       </div>
</body>
</html>
