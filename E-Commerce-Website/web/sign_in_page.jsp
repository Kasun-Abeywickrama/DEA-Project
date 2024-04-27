<%-- 
    Document   : sign_in_page
    Created on : Mar 27, 2024, 6:45:51 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String errState = "";
    String userName = "";
    if (request.getParameter("error_state") != null) {
        errState = "alert('" + request.getParameter("error_state") + "');";
        userName = request.getParameter("user_name");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign in</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Istok+Web' rel='stylesheet'>

    </head>
    <body>
        <div class="container-fluid" style="background-color: #F6F3EC;">
            <div class="row d-flex align-items-center justify-content-center" style="height: 100vh">
                <form action="SignInServlet" method="POST" class="d-flex flex-column col-lg-4 col-md-8 col-sm-4" style="font-family: 'Istok Web', sans-serif;">
                    <h1 class="text-center mb-5" style="font-weight: 600;">Sign In</h1>
                    <input type="text" name="email" value="<%=userName%>" placeholder="Email" class="form-control mb-4" style="height: 45px;"/>
                    <input type="password" name="pwd" value="" placeholder="Password" class="form-control" style="height: 45px;"/>
                    <span class="mb-4 mt-2" style=""><a href="#" style="text-decoration: none; color: black; float: right;">Forgot Password ?</a></span>
                    <div class="d-flex justify-content-center mb-5">
                        <input type="submit" value="Sign In" class="" style="border: none; width: 130px; height: 40px; background-color: #FC6B03; color: white; margin: 0; padding: 0; font-size: 17px; line-height: 40px;"/>
                    </div>

                    <button class="mb-4 d-flex justify-content-center" style="border: 1px solid rgb(180, 178, 178); background-color: white; height: 45px; border-radius: 5px; width: 100%;"> 
                        <div class="d-flex align-items-center justify-content-center mx-3" style="width: 45px; height: 45px;">
                            <img src="images/icons/google.png" alt="image" style="width: 30px; height: 30px;"> 
                        </div>
                        <div class="text-start" style="line-height: 45px; width: 200px; height: 45px; overflow: hidden">
                            Sign in with Google
                        </div>
                    </button>
                    <button class="mb-4 d-flex justify-content-center" style="border: 1px solid rgb(180, 178, 178); background-color: white; height: 45px; border-radius: 5px; width: 100%;"> 
                        <div class="d-flex align-items-center justify-content-center mx-3" style="width: 45px; height: 45px;">
                            <img src="images/icons/facebook.png" alt="image" style="width: 30px; height: 30px;"> 
                        </div>
                        <div class="text-start" style="line-height: 45px; width: 200px; height: 45px; overflow: hidden">
                            Sign in with Facebook
                        </div>
                    </button>
                    <span class="text-center">Don't have an account? <a href="sign_up_page.jsp">Sign up</a></span>
                </form>
            </div>
        </div>
        <script>
            <%=errState%>
        </script>
    </body>
</html>


