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
<%@ include file="header_part_02.jsp"%>

<h1>User Details</h1>
<form action="UpdateUserServlet" method="post">
    <label>Username:</label>
    <input type="text" name="username" value="<%= user.getUsername()%>"><br>
    <label>Email:</label>
    <input type="email" name="email" value="<%= user.getEmail()%>"><br>
    <label>First Name:</label>
    <input type="text" name="firstName" value="<%= user.getFirstName()%>"><br>
    <label>Last Name:</label>
    <input type="text" name="lastName" value="<%= user.getLastName()%>"><br>
    <label>Phone Number:</label>
    <input type="text" name="phoneNumber" value="<%= user.getPhoneNumber()%>"><br>
    <label>Address:</label>
    <input type="text" name="address" value="<%= user.getAddress()%>"><br>
    <input type="submit" value="Update">
</form>

<%@ include file="footer.jsp"%>
