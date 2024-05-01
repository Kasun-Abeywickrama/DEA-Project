<%-- 
    Document   : sign_up_page
    Created on : Mar 26, 2024, 10:22:49 PM
    Author     : Sithuruwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign Up</title>
        <!-- Bootstrap -->
	<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
	<link href="css/sign_up_page.css" rel="stylesheet" type="text/css">
        
        <script src="js/javascript_validation.js"></script>
    </head>
    <body>
        
        <!-- Displaying the message received from the servlet -->
        <%
            if(request.getParameter("message") != null){
                String message = (String)request.getParameter("message");
                
                %>
                <script>alert("<%=message %>");</script>
                <%
            }
        %>
        
  	<div class="container-fluid">
            <div class="container">
		<div class="vertical-center">
                    
                    <center>
		  	<h1>Sign Up</h1>
                    </center>  
                    <br><br>
                    
                    <form action="SignUpServlet" method="POST" onsubmit="return sign_up_validate()" name="sign_up_form">
			<center>
                            <div class="form-group">
                                <input type="text" class="form-control" name="username" placeholder="Username" style="height: 45px;">
                            </div>
                            <div class="form-group">
				<input type="password" class="form-control" name="password" placeholder="Password" style="height: 45px;">
                            </div>
                            <div class="form-group">
				<input type="password" class="form-control" name="re_password" placeholder="Re-Enter Password" style="height: 45px;">
                            </div>
				<button type="submit" class="btn btn-primary">Sign Up</button>
			</center>
                    </form>
                    <br>
                    
                    <center>
		  	<p>Already have an account? <a href="sign_in_page.jsp">Sign In</a></p>
                    </center>
                    
  	  	</div>
            </div>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
	<script src="js/jquery-3.4.1.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/popper.min.js"></script> 
	<script src="js/bootstrap-4.4.1.js"></script>
    </body>
</html>