<%-- 
    Document   : view_product_page
    Created on : Apr 10, 2024, 8:25:22 PM
    Author     : HP
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="DatabaseConnection.DBConnectionManager"%>

<%
    String productId = request.getParameter("product_id");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View product</title>
    </head>
    <body>

        <h1>Edit Product</h1> 
        <form action="DeleteProductServlet?product_id=<%= productId%>" method="POST">          
            <input type="submit" value="Delete" />
        </form>
        <form id="myForm" action="UpdateProductServlet?product_id=<%= productId%>"  method="POST" >
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td>Buying Price:</td>
                    <td><input type="text" name="buying_price" id="buyingPrice"></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea name="description" rows="4" cols="30" id="description"></textarea></td>
                </tr>
                <tr>
                    <td>Image:</td>
                    <td><input type="text" name="image" id="image"></td>
                </tr>
                <tr>
                    <%
                        DBConnectionManager dbc = new DBConnectionManager();
                        Connection connection = null;
                        PreparedStatement statement = null;
                        String subCategory = null;

                        try {
                            connection = dbc.getDBConnection();
                            statement = connection.prepareStatement("select * from sub_category where sub_category_id = ?");
                            statement.setInt(1, Integer.parseInt(productId));
                            ResultSet resultSet = statement.executeQuery();
                            if (resultSet.next()) {
                                subCategory = resultSet.getString("name");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error : " + e);
                        } finally {
                            try {
                                if (statement != null) {
                                    statement.close();
                                }
                                if (connection != null) {
                                    dbc.closeDBConnection();
                                }
                            } catch (SQLException e) {
                                System.out.println("Error : Connection closing error");
                            }
                        }
                    %>
                    <td>Sub-category:</td>
                    <td><input type="text" name="sub_cat" id="sub_cat" value="<%= subCategory%>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="submit" value="Save" /></td>
                </tr>
            </table>
        </form>

        <script>
            function getData(productId) {

                var xhr = new XMLHttpRequest();
                xhr.open("GET", "ReadProductServlet?productId=" + encodeURIComponent(productId), true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var responseData = JSON.parse(xhr.responseText);
                        document.getElementById("name").value = responseData.name;
                        document.getElementById("buyingPrice").value = responseData.buyingPrice;
                        document.getElementById("description").value = responseData.description;
                        document.getElementById("image").value = responseData.image;
                    }
                };
                xhr.send();

            }

            getData(<%= productId%>);

        </script>
    </body>
</html>
