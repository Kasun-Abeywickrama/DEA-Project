<%-- 
    Document   : landing-page
    Created on : Apr 25, 2024, 3:26:32 PM
    Author     : dewmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header_part_01.jsp"%>
<%@ include file="header_part_02.jsp"%>

<style>
    .padding-0{
        padding: 0;
    }
    
    .text-box{
        background-color: #D9D9D9;
        width: 250px;
        margin-right: -220px;
        padding: 10px;
        z-index: 100;
        opacity: 0.95;
        color: #603400;
    }

    .col-1{
        background-color: #f6f3ec;
        
    }
    .n-top-margin{
        margin-top: -50px;
    }
    .col-md-3{
        position: relative;
    }


    .c-lable{ 
        position: absolute; 
        right: 20px;
        background-color: #D9D9D9;
        opacity: 0.9;
        padding: 2px 0px 2px 10px;
        font-size: large;
        font-weight: bolder;
        transition: 0.3s ease;
        display: flex;
        justify-content: center;
        align-items: center;
        bottom: 20px;
        text-decoration: none;
        color: black;
        cursor: pointer;
    }


    .c-lable > i{ 
        font-size: 25px;
        margin-top: 2px;
    }

    .c-lable:hover{
        right: 30px;
        transition: 0.3s ease;
    }
    
    
</style>

        <!-- Displaying the alert message -->
        <%
            if(request.getParameter("alert") != null){
        
                String alert = (String)request.getParameter("alert");
        %>        
                <script>alert("<%=alert %>");</script>
        <%  
            }
        %>

<div class="row ">

    <div class=" col-md-2 d-flex align-items-center justify-content-center col-1">
        <div class="text-box" style="height: fit-content">
            <p class="text-start fs-4 fw-bold">We Help You Make Modern Interior</p>
            <p class="text-start">We Help You Make Modern Interior We Help You Make Modern InteriorWe Help You Make Modern InteriorWe Help You Make Modern Interior</p>
        </div>
    </div>
    <div class="col-md-10 padding-0">
        <img src="images/banner_images/s1.png" class="img-fluid" alt="...">
    </div>


</div>

<div class="container mb-5">
    <h2 class="text-center mt-5 pt-5 mb-5 fw-bold">Our Products</h2>
    <div class="row mb-3 cat-row"> 
        <div class="col-md-3 px-2 mb-3">
            <img src="images/category_images/5.png" class="img-thumbnail" alt="...">
            <span class="c-lable">Living <i class='bx bxs-chevron-right'></i></span>
        </div>
    </div>
</div>



<script>
    function setMainCategoriesToCatPanel(mainCategories) {
        var categoryRow = document.querySelector(".cat-row");

        categoryRow.innerHTML = "";

        mainCategories.forEach(function (mainCategory) {
            categoryRow.innerHTML += `
            <div class="col-md-3 px-2 mb-3">
            <div class="cart-container mb-2" style="width: 100%; height: 300px; background-image: url('images/category_images/`+mainCategory.mainCategoryId+`.png'); background-size: cover; background-position: center;"></div>
            <a href="ReadProductsByCategoriesServlet?main_category_id=`+mainCategory.mainCategoryId+`&main_category_name=`+mainCategory.mainCategoryName+`" class="c-n-lable c-lable">`+mainCategory.mainCategoryName+` <i class='bx bxs-chevron-right'></i></a>
        </div>
        `;
        });
    }

    function getMainCategoriesToCatPanel() {
//                    setActiveTab('pending');
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "ReadCategoryServlet?type=allMainCategories", true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseData = JSON.parse(xhr.responseText);
                setMainCategoriesToCatPanel(responseData);
            }
        };
        xhr.send();
    }

    getMainCategoriesToCatPanel();
</script>

 <script>
        var tabs = document.querySelectorAll('.nav-link');
        tabs.forEach(function (tab) {
            tab.classList.remove('active');
        });
        document.getElementById('nav-link-home').classList.add('active');
 </script>

<%@ include file="footer.jsp"%>
