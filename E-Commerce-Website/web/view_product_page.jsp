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
    int subCategoryId = Integer.parseInt(request.getParameter("sub_category_id"));
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
        <form id="myForm" action="UpdateProductServlet?product_id=<%= productId%>"  method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                    <td></td>
                    <td colspan="2">
                        <%
                            String imagePath = "images/product_images/" + productId + ".png";
                            String timestamp = String.valueOf(System.currentTimeMillis());
                            String imageUrl = imagePath + "?timestamp=" + timestamp;
                        %>
                        <img alt="Loading.." src="<%= imageUrl%>" style="width: 100px"/>
                    </td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td>Buying Price:</td>
                    <td><input type="text" name="buying_price" id="buyingPrice"></td>
                </tr>
                <tr>
                    <td>Selling Price:</td>
                    <td><input type="text" name="selling_price" id="sellingPrice"></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea name="description" rows="4" cols="30" id="description"></textarea></td>
                </tr>
                <tr>
                    <td>Image:</td>
                    <td><input type="file" name="image" accept="image/png"></td>
                </tr>  
                <tr>
                    <td>Sub-category:</td>
                    <td>
                        <select name="sub_cat">

                            <%
                                DBConnectionManager dbc = new DBConnectionManager();
                                Connection connection = null;
                                PreparedStatement statement = null;

                                try {
                                    connection = dbc.getDBConnection();
                                    statement = connection.prepareStatement("select * from sub_category");
                                    ResultSet resultSet = statement.executeQuery();
                                    while (resultSet.next()) {
                                        int subCategoryIdNew = resultSet.getInt("sub_category_id");
                                        String subCategory = resultSet.getString("name");
                                        if (subCategoryId == subCategoryIdNew) {
                            %>
                            <option value="<%= subCategoryIdNew%>" selected><%= subCategory%></option>
                            <%
                                System.out.println("subCategoryId : " + subCategoryId + "    " + subCategory);
                            } else {
                            %>
                            <option value="<%= subCategoryIdNew%>"><%= subCategory%></option>
                            <%
                                        }
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
                        </select>
                    </td>
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
                        document.getElementById("sellingPrice").value = responseData.sellingPrice;
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
