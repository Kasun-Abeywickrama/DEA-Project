<%-- 
    Document   : sign_up_page
    Created on : Mar 26, 2024, 10:22:49 PM
    Author     : Sithuruwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if(request.getAttribute("message") != null){
                String message = (String)request.getAttribute("message");
                
                %>
                <script>alert("<%=message %>");</script>
                <%
            }
        %>
        <form action="SignUpServlet" method="POST">
            <input type="text" name="username" value="" />
            <input type="password" name="password" value="" />
            <input type="password" name="re_password" value="" />
            <input type="submit" value="submit" />
        </form>
    </body>
</html>
