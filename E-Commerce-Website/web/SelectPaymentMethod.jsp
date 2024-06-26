<%-- 
    Document   : SelectPaymentMethod
    Created on : Apr 28, 2024, 5:02:29 PM
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
    background-color: #D9D9D9C7;
    border-radius: 10px;
    margin-left: 507px;
    margin-top: -26px;
}
.container-row-2 {
    background-color: #D9D9D9C7;
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
.container-fluid.page-wrapper {
    background-color: white;
    width: 490px;
    height: 159px;
    margin-left: 450px;
    margin-top: 104px;
}
.payment-method {
    background-color: #FC6B03;
    height: 90px;
    width: 154px;
    border-radius: 13px;
    margin-left: 69px;
   margin-top: 38px;
    float: left;
}
.card{
    padding: 10px;

}
.container.cash-on-dilivery {
    background-color: white;
    border-style: ridge;
    width: 140px;
    height: 85px;
    border-radius: 9px;
    margin-left: 276px;
    margin-top: -92px;
    float: left;
}
.container.cash.on.dilivery-image {
    margin-left: 30px;
}
.container.card-image {
    margin-left: 48px;
}
.container.card-name {
       margin-left: 9px;
    margin-top: 8px;
}
.container.cash.on.dilivery-name {
    margin-left: 17px;
    margin-top: -8px;
}
.container.cash {

    padding-top: 8px;
    padding-bottom: 1px;
}

button.credit {
    float: left;
    margin-top: 55px;
    margin-left: 56px;
    padding: 17px;
    border-radius: 12px;
    background-color: #FC6B03;
    border: none;
    cursor: pointer;
}

button.cash {
    float: left;
    margin-top: 47px;
    margin-left: 87px;
    padding: 17px;
    border-radius: 12px;
    background-color: #e1e1e18a;
    border: none;
    cursor: pointer;
}
    </style>
   
</head>


<body>
    
    
     <div class="main " >
    <div class="">
        <div class="container-row-1 ">
        <h3 class="text1">1</h3>
        </div>
        </div>
     <div class="arrows1" ><hr class="hr1"></div>  
      <div class="container-row-2 ">
        <h3 class="text2 ">2</h3>
        </div>
       
     <div class="arrows2 " ><hr class="hr2"></div> 

    
     <div class="container-row-3 ">
       <h3 class="text3 ">3</h3>
       </div>
       

<div class="container-fluid page-wrapper ">
       
        <% if(request.getParameter("fname") != null && request.getParameter("address") != null && request.getParameter("tel") != null){ %>
                
            <form action="credit.jsp" method="post">
                <input type="hidden" name="fname" value="<%=request.getParameter("fname") %>">
                <input type="hidden" name="address" value="<%=request.getParameter("address") %>">
                <input type="hidden" name="tel" value="<%=request.getParameter("tel") %>">
                <div class="container card-name"><button class="credit" style="color:white; font-weight: bold">Credit/Debit Card</button></div>
            </form> 
      
            
            
            <form action="cashondilivery.jsp" method="post">
                <input type="hidden" name="fname" value="<%=request.getParameter("fname") %>">
                <input type="hidden" name="address" value="<%=request.getParameter("address") %>">
                <input type="hidden" name="tel" value="<%=request.getParameter("tel") %>">  
                <div class="container cash on dilivery-name"><button class="cash" style="color:black; font-weight: bold">Cash on delivery</button></div>
            </form>
                
        <% } %>
               
</div>
        
        

     </div>
    
</body>
</html>

