<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    
    <%@include file="admin_header_part_01.jsp" %>
    
        <title>Main Category Management </title>
            
    <%@include file="admin_header_part_02.jsp" %>    
            
            <!-- MAIN CATEGORY -->
        <% if(request.getParameter("getMainCategoryID") != null) { %>
            <p style="color: #E97000;"><i>Categories &nbsp;>&nbsp; Edit Main Category</i></p>
        <% } %>
                        
        <% if(request.getParameter("getMainCategoryID") == null) { %>
            <p style="color: #E97000;"><i>Categories &nbsp;>&nbsp; Add New Main Category</i></p>
        <% } %>
        
        <%
            if(request.getParameter("getMainCategoryID") != null && request.getParameter("name") != null) {
                String mainCategoryIdStr = request.getParameter("getMainCategoryID");
                int user = Integer.parseInt(mainCategoryIdStr);
                System.out.println(user);
                String Mname = request.getParameter("name");
                System.out.println(Mname);


                request.setAttribute("user", user);
                request.setAttribute("Mname", Mname);

            }
        %>
            
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        
                        <% if(request.getParameter("getMainCategoryID") != null) { %>
                            <form action="CategoryUpdateServlet" method="post">
                        <% } %>
                        
                        <% if(request.getParameter("getMainCategoryID") == null) { %>
                            <form action="CategoryAddServlet" method="post">
                        <% } %>

                        
                        <h3 style="text-align: center; color: #E97000">
                            <% if(request.getParameter("getMainCategoryID") != null) { %>
                                Edit Main Category
                            <% } %>
                                
                            <% if(request.getParameter("getMainCategoryID") == null) { %>
                                Add New Main Category
                            <% } %>
                        </h3>
                        
                        <br>

                        <% if(request.getParameter("getMainCategoryID") != null) { %>
                            <input type="hidden" name="id" value="<%=request.getParameter("getMainCategoryID") %>" />

                            <fieldset class="form-group">
                                <label>Main Category Name</label> 
                                <input type="text" value="<%=request.getParameter("name") %>" class="form-control" name="name" required="required">
                            </fieldset>
                        <% } %>
                        
                        <% if(request.getParameter("getMainCategoryID") == null) { %>

                            <fieldset class="form-group">
                                <label>Main Category Name</label> 
                                <input type="text" class="form-control" name="name" required="required">
                            </fieldset>
                        <% } %>
                        
                        <br>
                        <center><button type="submit" class="btn btn-primary btn-sm" style="height: 40px; font-size: 18px;">Save</button></center>
                        </form>
                    </div>
                </div>
            </div>
            
    <%@include  file="admin_footer.jsp" %>