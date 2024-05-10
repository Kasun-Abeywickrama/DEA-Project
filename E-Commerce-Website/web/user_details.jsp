<%-- 
    Document   : user_details
    Created on : Apr 29, 2024, 10:35:28 AM
    Author     : dewmi
--%>

<%@page import="UserManagement.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% User user = (User) request.getAttribute("user");%>
<%@ include file="header_part_01.jsp"%>
<title>My Account</title>
<%@ include file="header_part_02.jsp"%>

<style>
    .uform {
        border-color: #D2D2D2;
    }

    .uform:focus {
        border-color: #967e61;
        box-shadow: 0 0 0 0.2rem rgba(233, 112, 0, 0.25);
    }
    
    .form-group{
        margin-bottom: 40px;
        padding: 0 30px 0 30px;
    }
    .user-details-wrapper{
        margin:  20px;
        width: 100%;
        height: fit-content;
    }

    h3{
        padding: 30px;
    }
    
    .btn-up {
        margin: 0 30px 30px 0;
        height: 30px;
        width: 90px;
        font-size: 15px;
        background-color:  #E97000;
        border: 0;
    }
    
    .form-name {
        font-size: 17px;
    }



</style>


<center><div class="container user-details-wrapper">
                    

    <h3>User Details</h3>
                
    </div></center>

            <form action="UpdateUserServlet" method="post">


    <div class="row">


            <div class="col-md">
                <div class="form-group">
                    <label for="exampleFormControlInput1" class="form-name mb-2">Username</label>
                    <input type="text" name="username" value="<%= user.getUsername()%>" class="form-control uform" id="exampleFormControlTextarea1">    
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput2" class="form-name mb-2">Email</label>
                    <input type="text" name="username" value="<%= user.getEmail()%>" class="form-control uform" id="exampleFormControlTextarea2">    
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput3" class="form-name mb-2">First Name</label>
                    <input type="text" name="firstName" value="<%= user.getFirstName()%>" class="form-control uform" id="exampleFormControlTextarea3">    
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput4" class="form-name mb-2">Last Name</label>
                    <input type="text" name="lastName" value="<%= user.getLastName()%>" class="form-control uform" id="exampleFormControlTextarea4">    
                </div>
            </div>

            <div class="col-md">


                <div class="form-group">
                    <label for="exampleFormControlInput5" class="form-name mb-2">Phone Number</label>
                    <input type="text" name="phoneNumber" value="<%= user.getPhoneNumber()%>" class="form-control uform" id="exampleFormControlTextarea5">    
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput6" class="form-name mb-2">Address</label>
                    <input type="text" name="address" value="<%= user.getAddress()%>" class="form-control uform" id="exampleFormControlTextarea6">    
                </div>
                <button type="submit" class="btn btn-primary btn-sm float-end btn-up">Update</button>

            </div>



    </div>
        </form>







<%@ include file="footer.jsp"%>
