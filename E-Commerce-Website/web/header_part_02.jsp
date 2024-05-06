<%-- 
    Document   : header_part_02
    Created on : Apr 28, 2024, 5:05:02 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
</head>
<body>
    <header>
        <div class="container-fluid top-bar">
            <div class="row py-2">
                    <div class="col-md-3 logo d-flex flex-column align-items-center justify-content-center">
                        <a class="logo-active" href="landing-page.jsp" style="font-family: Josefin Sans, sans-serif; font-size: 28px "><span class="sp1">EliteElegance</span></a>
                        <a class="logo-active" href="landing-page.jsp" style="font-size: 18px"><span class="sp2">F U R N I T U R E</span></a>

                        
                    </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-7 position-relative">
                            <input type="text" class="form-control search-input" placeholder="Search" id="search-input"/>
                            <div id="search-result-wrapper" class="position-absolute w-100" style="z-index: 200; display: none; background-color: #f6f3ec;">
                                <table border="0" class="w-100">
                                    <tbody id="search-result">
                                    
                                    </tbody>
                                </table>

                            </div>
                        </div>
                        <div
                            class="col-md-5 d-flex align-items-center justify-content-end pe-5"
                            >
                            <div class="me-4"><a href="ShoppingCartServlet" style="color:black">Shopping Cart</a></div>
                            <%
                                HttpSession ses1 = request.getSession(false);
                
                                if(ses1 != null){
                                    if(ses1.getAttribute("user_name") != null){
                            %>
                                        <div class="me-3">Hi, <%=ses1.getAttribute("user_name") %></div>
                            <%
                                    }
                                    else{
                            %>
                                        <div class="me-3">Hi, User</div>
                            <%
                                    }
                                }
                                else{
                            %>
                                    <div class="me-3">Hi, User</div>
                            <%
                                }
                            %>
                                            
                            <div class="">
                                <button class="btn btn-sm d-flex align-items-center justify-content-center" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling"><i class='bx bx-menu fs-4'></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="row">

                        <nav class="navbar navbar-expand-lg">
                            <div class="container-fluid">
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                        <li class="nav-item">

                                            <a id="nav-link-home" class="nav-link" aria-current="page" href="landing-page.jsp">Home</a>

                                        </li>
                                        <li class="nav-item dropdown">
                                            <a id="nav-link-categories" class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                Categories
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" href="#">Action</a></li>
                                            </ul>   
                                        </li>
                                        <li class="nav-item">
                                            <a id="nav-link-contact-us" class= "nav-link" aria-current="page" href="contact_us.jsp">Contact us</a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="nav-link-about-us" class="nav-link" aria-current="page" href="about_us.jsp">About us</a>
                                        </li>
                                        <li class="nav-item">
                                            <a id="nav-link-store-location" class="nav-link" aria-current="page" href="store_location.jsp">Store location</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </nav>

                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasScrollingLabel">EliteElegance</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <% 
                HttpSession ses2 = request.getSession(false);
                
                if(ses2 != null){
                    if(ses2.getAttribute("role") != null){
                        if(Integer.parseInt((String)ses2.getAttribute("role")) == 1){
            %> 
                            <div class="mb-4">
                                <form action="AdminDashboardDataRetrieveServlet" style="height: 50px">
                                    <input type="submit" value="Admin Dashboard" class="w-100 h-100 offcanvas-buttons" />
                                </form>
                            </div>
            <%                    
                        }
                    }
                }
            %>
            <div class="mb-4">
                <form action="ReadUserServlet" style="height: 50px">
                    <input type="submit" value="My Account" class="w-100 h-100 offcanvas-buttons" />
                </form>
            </div>
            <div class="mb-4">
                <form action="LogoutServlet" method="POST" style="height: 50px">
                    <input type="submit" value="Log out" class="w-100 h-100 offcanvas-buttons" />
                </form>
            </div>
        </div>
    </div>


  <div class="container-fluid content">

  <script src="js/header.js"></script>

