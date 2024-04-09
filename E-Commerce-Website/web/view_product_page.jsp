<%-- 
    Document   : update_product_page
    Created on : Apr 5, 2024, 12:39:41 PM
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

        <h1>Edit Product</h1>  
        <form id="myForm">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="text" name="price" id="price"></td>
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
                    <td>Sub-category:</td>
                    <td><input type="text" name="sub_cat" id="sub_cat"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><button type="button" onclick="submitForm()">Save</button></td>
                </tr>
            </table>
        </form>

        <script>
            function getData(productId) {

                var xhr = new XMLHttpRequest();
//                xhr.open("GET", "Product_CRUD_Servlet", true);
                xhr.open("GET", "Product_CRUD_Servlet?productId=" + encodeURIComponent(productId), true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
//                        console.log(xhr.responseText);
                        var responseData = JSON.parse(xhr.responseText);
                        document.getElementById("name").value = responseData.name;
                        document.getElementById("price").value = responseData.price;
                        document.getElementById("description").value = responseData.description;
                        document.getElementById("image").value = responseData.image;
                        document.getElementById("sub_cat").value = responseData.sub_cat;
                    }
                };

                var formData = "productId=" + encodeURIComponent(productId);

//                xhr.send(formData);
                xhr.send();

            }

            getData("2");

        </script>

        <script>
            function submitForm() {
                var form = document.getElementById("myForm");
                var formData = new FormData(form);

                var xhr = new XMLHttpRequest();
                xhr.open("PUT", "Product_CRUD_Servlet", true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
//                        console.log(xhr.responseText);

                    }
                };
                xhr.send(formData);
            }
        </script>
    </body>
</html>
