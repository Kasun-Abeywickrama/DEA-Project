<%-- 
    Document   : footer
    Created on : Apr 29, 2024, 12:03:27 AM
    Author     : dewmi
--%>


</div>

<footer>

    <div class="container-fluid footer-bar">

        <div class="row ">
            <div class="col-md logo1 d-flex flex-column align-items-center justify-content-center">
                <div class="div1">EliteElegance</div>
                <div class="div2">F U R N I T U R E</div>
            </div>
            <div class="col-md contact-info1">
                <div class="contact1">0112345678</div>
                <div class="contact1">20, Galle Road ,Colombo.</div>
                <div class="contact1">furnitures.com</div>
                <div class="smedia">
                    <span class="m1"><a href="https://mail.google.com"><img  src="images/footer_images/mail.jpg" alt="" width=17px height=15px></a></span>
                    <span class="m2"><a href="https://mail.facebook.com"><img src="images/footer_images/fb2.png" alt="" width=17px height=17px></a></span>
                    <span class="m3"><a href="https://mail.instagram.com"><img src="images/footer_images/insta2.png" alt="" width=17px height=17px></a></span>
                </div>
            </div>
            <div class="col-md li-info1">
                <div class="lin1"><a href="landing-page.jsp" style="text-decoration: none; color: black;">Categories</a></div>
                <div class="lin1"><a href="contact_us.jsp" style="text-decoration: none; color: black;">Contact us</a></div>
                <div class="lin1"><a href="about_us.jsp" style="text-decoration: none; color: black;">About us</a></div>
                <div class="lin1"><a href="store_location.jsp" style="text-decoration: none; color: black;">Store location</a></div>

            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById('search-input').addEventListener('input', function () {
        if (this.value !== "") {
            var searchResultWrapper = document.getElementById("search-result-wrapper");
            searchResultWrapper.style.display = "block";
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "ReadProductBySearch?search_term=" + this.value, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var responseData = JSON.parse(xhr.responseText);
                    var tableBody = document.getElementById("search-result");
                    tableBody.innerHTML = "";

                    responseData.forEach(function (product) {
                        var productId = product.productId;
                        var productName = product.name;

                        var newRow = document.createElement('tr');

                        var productNameCell = document.createElement('td');

                        var submitForm = document.createElement('form');
                        submitForm.action = "ProductViewServlet?product_id=" + productId;
                        submitForm.method = "POST";

                        submitForm.innerHTML = ' <button type="submit" name="submit" value="get_details" class="serch-resulr-btn">' + productName + '</button>';
                        productNameCell.style.padding = "1px 0";
                        productNameCell.appendChild(submitForm);

                        newRow.appendChild(productNameCell);

                        tableBody.appendChild(newRow);
                    });
                }
            };
            xhr.send();
        } else {
            var searchResultWrapper = document.getElementById("search-result-wrapper");
            searchResultWrapper.style.display = "none";
        }
    });
</script>

</body>
</html>



