<%-- 
    Document   : create_product_page
    Created on : Mar 28, 2024, 12:53:45 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <h1>Add Product</h1>  
        <form action="CreateProductServlet" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>Buying Price:</td>
                    <td><input type="text" name="buying_price"></td>
                </tr>
                <tr>
                    <td>Selling Price:</td>
                    <td><input type="text" name="selling_price"></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea name="description" rows="4" cols="30"></textarea></td>
                </tr>
                <tr>
                    <td>Image:</td>
                    <td><input type="text" name="image"></td>
                </tr>
                <tr>
                    <td>Sub-category:</td>
                    <td><input type="text" name="sub_cat"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="submit" value="Add Product"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
