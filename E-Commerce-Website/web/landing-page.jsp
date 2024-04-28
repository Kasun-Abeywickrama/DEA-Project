<%-- 
    Document   : landing-page
    Created on : Apr 25, 2024, 3:26:32 PM
    Author     : dewmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp"%>
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
        .col-md{
          position: relative;
        }

        .c-n-lable{
          bottom: 20px;
        }

        .c-s-lable{
          bottom: 70px;
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

<div class="row ">

    <div class="col-md-2 d-flex align-items-center justify-content-center col-1">
        <div class="text-box">
            <p class="text-start fs-4 fw-bold">We Help You Make Modern Interior</p>
            <p class="text-start">We Help You Make Modern Interior We Help You Make Modern InteriorWe Help You Make Modern InteriorWe Help You Make Modern Interior</p>
        </div>
    </div>
    <div class="col-md-10 padding-0">
        <img src="images/banner_images/s1.png" class="img-fluid" alt="...">
    </div>


</div>

<div class="container mb-5">
    <h2 class="text-center mt-5 pt-5 mb-4 fw-bold">Our Products</h2>
    <div class="row mb-3"> 
        <div class="col-md px-2 n-top-margin ">
            <img src="images/category_images/c5.png" class="img-fluid" alt="...">
            <span class="c-s-lable c-lable">Living <i class='bx bxs-chevron-right'></i></span>
        </div>
        <div class="col-md px-2">
            <img src="images/category_images/c6.png" class="img-fluid" alt="...">
            <span class="c-n-lable c-lable">Kitchen <i class='bx bxs-chevron-right'></i></span>
        </div>
        <div class="col-md px-2">
            <img src="images/category_images/c7.png" class="img-fluid" alt="...">
            <span class="c-n-lable c-lable">Sleeping <i class='bx bxs-chevron-right'></i></span>
        </div>
        <div class="col-md px-2 n-top-margin">
            <img src="images/category_images/c8.png" class="img-fluid" alt="...">
            <span class="c-s-lable c-lable">Dining <i class='bx bxs-chevron-right'></i></span>
        </div>
    </div>


    <div class="row"> 
        <div class="col-md px-2 n-top-margin">
            <img src="images/category_images/c1.png" class="img-fluid" alt="...">
            <span class="c-s-lable c-lable">Dressing <i class='bx bxs-chevron-right'></i></span>
        </div>
        <div class="col-md px-2">
            <img src="images/category_images/c2.png" class="img-fluid" alt="...">
            <span class="c-n-lable c-lable">Office <i class='bx bxs-chevron-right'></i></span>
        </div>
        <div class="col-md px-2">
            <img src="images/category_images/c3.png" class="img-fluid" alt="...">
            <span class="c-n-lable c-lable">Studying <i class='bx bxs-chevron-right'></i></span>
        </div>
        <div class="col-md px-2 n-top-margin">
            <img src="images/category_images/c4.png" class="img-fluid" alt="...">
            <span class="c-s-lable c-lable">Entertaining <i class='bx bxs-chevron-right'></i></span>
        </div>
    </div>

</div>




<%@ include file="footer.jsp"%>