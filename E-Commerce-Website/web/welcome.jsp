<%-- 
    Document   : welcome
    Created on : Mar 27, 2024, 8:44:37 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome !</h1>

        <table border="0">
            <tr>
                <td>
                    <form action="Home">
                        <input type="submit" value="Check" />
                    </form>
                </td>
                <td>
                    <form action="LogOut" method="POST">
                        <input type="submit" value="Logout" />
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
