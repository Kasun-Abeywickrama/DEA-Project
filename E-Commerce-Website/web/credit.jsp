<%-- 
    Document   : credit
    Created on : Apr 28, 2024, 5:33:50 PM
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
    background-color: #D9D9D9C7;
    border-radius: 10px;
    margin-left: 734px;
    margin-top: -27px;
}
.container-row-3 {
    background-color: #D9D9D9C7;
    width: 48px;
    height: 49px;
    border-radius: 38px;
    margin-left: 890px;
    margin-top: -61px;
}
h3.text3 {
    margin-left: 17px;
    padding-top: 9px;
    font-size: 27px;
    color: white;
}
.from-login {
    background-color: white;
    width: 513px;
    height: 420px;
    margin-top: 68px;
    margin-left: 425px;
}
h3.card-Number {
    margin-left: 30px;
    font-family: serif;
}
h4.Card.Number {
       margin-left: 30px;
    color: #c5c5c5c7;
}
h3.Name-on-card {
    float: left;
    margin-top: 13px;
    margin-left: 30px;
    width: 100%;
}
h4.Name {
    margin-left: 30px;
    margin-top: -6px;
    color: #c5c5c5c7;
}
h3.Expiration.Date {
    margin-top: 71px;
    margin-left: 30px;
    font-family: serif;
}
h4.mm {
    margin-left: 30px;
    margin-top: -2px;
    color: #c5c5c5c7;
}
h3.CVV {
    margin-left: 320px;
    margin-top: -41px;
}
h4.cvv {
    margin-left: 323px;
    margin-top: -2px;
    color: #c5c5c5c7;
}
h2.pay-now {
   margin-left: 163px;
    margin-top: 30px;
    background-color: #FC6B03;
    width: 98px;
    height: 36px;
    border-radius: 25px;
    padding-left: 27px;
    padding-top: 12px;
    color: white;
    font-family: Istok -webkit-body;
    font-size: 19px;
}

input[type="text"] {
    float: left;
    width: 30%;
    margin-left: 28px;
    border: none;
}

button.pay {
    float: left;
    margin-top: 61px;
    padding: 7px;
    padding-left: 12px;
    padding-right: 12px;
    border-radius: 6px;
    border: none;
    background-color: #FC6B03;
    margin-left: 193px;
}

input.cvc {
    float: left;
    margin-left: 134px;
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
       

       <div class="from-login">
         <div class="images-icon">
            <img src="images\icons/visa.jpg" alt="visa">
            <img src="images\icons/Rectangle 39.jpg " alt="Rectangle">
            <img src="images\icons/Rectangle 40.png" alt="American">
         </div>
           
      <h3 class="card-Number">Card Number*</h3>
      <input type="text" name="cnum" placeholder="Card Number">
        <br>
      <h3 class="Name-on-card">Name on card*</h3>
          <input type="text" name="noc" placeholder="Name on card">
         <br>
      <h3 class="Expiration Date">Expiration Date*</h3>
         <input type="text" name="edate" placeholder="Expiration Date"> 
       <h3 class="CVV">CVC*</h2>
          <input type="text" name="cvc" placeholder="CVC" class="cvc"> 
        <br>
        
        <% if(request.getParameter("fname") != null && request.getParameter("address") != null && request.getParameter("tel") != null){ %>
            
            <form action="OrderProcessingServlet" method="post">
                <input type="hidden" name="fname" value="<%=request.getParameter("fname") %>">
                <input type="hidden" name="address" value="<%=request.getParameter("address") %>">
                <input type="hidden" name="tel" value="<%=request.getParameter("tel") %>">
                <button class="pay" style="color: white; font-weight: bold">Pay Now</button>
            </form>
        
        <% } %>
        
        
           </div>  
</body>

</html>
