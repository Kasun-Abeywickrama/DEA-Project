<%-- 
    Document   : header
    Created on : Apr 25, 2024, 3:30:59 PM
    Author     : dewmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Josefin+Sans&display=swap"
    />

    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>


    <style>
      .logo {
        height: 80px;
      }
      .logo > .sp1 {
        font-family: "Josefin Sans", sans-serif;
        font-size: 28px;
        margin-bottom: -8px;
        color: #603400;
      }
      .logo > .sp2 {
        font-size: 18px;
        color: #603400;
      }

      .top-bar {
        background-color: #f6f3ec;
      }

      .form-control {
        border-color: #967e61;
      }

      .form-control:focus {
        border-color: #967e61;
        box-shadow: 0 0 0 0.2rem rgba(233, 112, 0, 0.25);
      }

      .navbar-collapse{
        margin-bottom: -40px;;
      }

      .nav-link{
        padding: 6px 30px !important;
      }

      .active{
        background-color: black;
        color: white !important;
        border-radius: 5px;
      }



    </style>
  </head>
  <body>
    <header>
      <div class="container top-bar">
        <div class="row py-2">
          <div
            class="col-md-3 logo d-flex flex-column align-items-center justify-content-center"
          >
            <span class="sp1">EliteElegance</span>
            <span class="sp2">F U R N I T U R E</span>
          </div>
          <div class="col-md-9">
            <div class="row">
              <div class="col-md-8">
                <input
                  type="text "
                  class="form-control search-input"
                  placeholder="Search"
                />
              </div>
              <div
                class="col-md-4 d-flex align-items-center justify-content-end"
              >
                <div class="me-4">Cart</div>
                <div class="">Hi, Dewmini</div>
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
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                          </li>
                          <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Categories
                            </a>
                            <ul class="dropdown-menu">
                              <li><a class="dropdown-item" href="#">Action</a></li>
                              <li><a class="dropdown-item" href="#">Another action</a></li>
                              <li><hr class="dropdown-divider"></li>
                              <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>   
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="#">Contact us</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="#">About us</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="#">Store location</a>
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

    <div class="container content">


  