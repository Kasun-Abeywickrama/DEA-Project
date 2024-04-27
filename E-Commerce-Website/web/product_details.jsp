<%-- 
    Document   : product_details
    Created on : Apr 26, 2024, 3:35:34 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Untitled Document</title>
<link href="css/bootstrap-4.4.1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/product_details.css">

</head>

<body>
<div class="row">
    <div class="col-lg-6"><img src="images/product_images/Rectangle 20.png" width="550" height="400" alt=""/>
	</div>
  
  <div class="col-lg-6">
	<div class="box01">
		<h3>William Standard Dinning Table with Chairs</h3>
		<p>Teak : 36’W     72’L</p><br>
        <p><strong>Rs. 100,000.00</strong></p><br>
         	<div class="input-group">
				Quantity: &nbsp;
				<button id="decrement">-</button>
				<input type="number" id="input" value="0" readonly>
				<button id="increment">+</button>
			</div>
			
  <div class="btn">
	<button class="btn1">Buy Now</button>
	<button class="btn2">Add to Cart</button>
  </div>
	</div>
  </div>
</div>
    <script src="js/product_details.js"></script>
</body>
</html>