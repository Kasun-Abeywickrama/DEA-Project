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
    if(request.getParameter("error_state") != null){
        errState = "alert('"+request.getParameter("error_state")+"');";
        userName = request.getParameter("user_name");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SignInServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Email : </td>
                        <td><input type="text" name="email" value="<%=userName%>"/></td>
                    </tr>
                    <tr>
                        <td>Password : </td>
                        <td><input type="password" name="pwd" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="reset" value="Cancel" />  <input type="submit" value="Login" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        
        
        <script>
            <%=errState%>
        </script>
    </body>
</html>
