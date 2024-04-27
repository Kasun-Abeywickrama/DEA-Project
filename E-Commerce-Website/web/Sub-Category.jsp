<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    
    <%@include file="admin_header_part_01.jsp" %>
    
        <title>Sub Category Management </title>
            
    <%@include file="admin_header_part_02.jsp" %>    
            
            <!-- SUB CATEGORY -->
            
        <%
            if(request.getParameter("main_category_id")!=null && request.getParameter("name")!=null && request.getParameter("getSubCategoryID")!=null ){
            	 	
            int user =Integer.parseInt( request.getParameter("getSubCategoryID"));
            System.out.println(user);
            String Sname = request.getParameter("name");
            System.out.println(Sname);
            String main_category_id = request.getParameter("main_category_id");
            System.out.println(main_category_id);
            
            request.setAttribute("user", user);
            request.setAttribute("Sname", Sname);
            request.setAttribute("main_category_id", main_category_id);
            
        }%>
             
            <!-- SUB CATEGORY -->
            <div class="container col-md-6">
                <div class="card">
                    <div class="card-body">
                        
                        <% if(request.getParameter("getSubCategoryID") != null) { %>
                            <form action="CategoryUpdateServlet2" method="post">
                        <% } %>
                        
                        <% if(request.getParameter("getSubCategoryID") == null) { %>
                            <form action="CategoryAddServlet2" method="post">
                        <% } %>

                        
                        <h3 style="text-align: center; color: #E97000">
                            <% if(request.getParameter("getSubCategoryID") != null) { %>
                                 Edit Sub Category
                            <% } %>
                                
                            <% if(request.getParameter("getSubCategoryID") == null) { %>
                                Add New Sub Category
                            <% } %>
                        </h3>
                        <br>

                        <% if(request.getParameter("getSubCategoryID") != null) { %>
                            <input type="hidden" name="id" value="<%=request.getParameter("getSubCategoryID") %>" />
                        
                            <fieldset class="form-group">
                                <label>Main Category ID</label> 
                                <input type="text" value="<%=request.getParameter("main_category_id") %>" class="form-control" name="main_category_id" readonly>
                            </fieldset>
                            <br>
                            <fieldset class="form-group">
                                <label>Category Name</label> <input type="text" value="<%=request.getParameter("name") %>" class="form-control" name="name" required="required">
                            </fieldset>
                        <% } %>
                        
                        <% if(request.getParameter("getSubCategoryID") == null) { %>
                        
                            <fieldset class="form-group">
                                <label>Main Category ID</label> 
                                <input type="text" class="form-control" name="main_category_id" required="required">
                            </fieldset>
                            <br>
                            <fieldset class="form-group">
                                <label>Category Name</label> <input type="text" class="form-control" name="name" required="required">
                            </fieldset>
                        <% } %>
                       
                        <br>
                        <center><button type="submit" class="btn btn-primary btn-sm" style="height: 40px; font-size: 18px;">Save</button></center>
                        </form>
                    </div>
                </div>
            </div>
    
    <%@include file="admin_footer.jsp" %>