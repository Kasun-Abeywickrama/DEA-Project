<%@page import="CategoryManagement.MainCategoryDetails" %>
<%@page import="CategoryManagement.SubCategoryDetails" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


    <%@include file="admin_header_part_01.jsp" %>
    
        <title>Category Management</title>
        
	<link href="css/category_management_page.css" rel="stylesheet" type="text/css">
        
        <!-- Sending a GET request when the page is loading -->
        <!-- Updating the body of the document according to the response -->
        <script>
            window.onload = function(){
                
                var xhr = new XMLHttpRequest();
              
                xhr.open("GET", "MainCategoryDetailsRetrieveServlet", true);

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        document.body.innerHTML = xhr.responseText;
                    }
                };
                
                xhr.send();
            };
        </script>
        
    <%@include file="admin_header_part_02.jsp" %>
        
        <!-- Displaying the alert message -->
        <%
            if(request.getAttribute("alert_message") != null){
        
                String alert_message = (String)request.getAttribute("alert_message");
        %>        
                <script>alert("<%=alert_message %>");</script>
        <%  
            }
        %>
        
        <div class="row">   
            <div class="col-lg-6">
                
                <h5 style="text-align: center; color: #E97000">MAIN CATEGORIES</h5>
                <br>
              
                <a href="Main-Category.jsp">ADD NEW </a>
                
                <%
                    if(request.getAttribute("MainCategory_details_list") != null){

                        @SuppressWarnings("unchecked")
                        ArrayList<MainCategoryDetails> MainCategory_details_list = (ArrayList<MainCategoryDetails>)request.getAttribute("MainCategory_details_list");

                             
                %>
                            <center>
                                <table class="table table-bordered" id="tb1">
                                    <tr>
                                        <th style="text-align: center">Main Category ID</th>
                                        <th style="text-align: center">Name</th>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                
                <%
                                    for(MainCategoryDetails i: MainCategory_details_list){
                %>
                                        <tr>
                                            <td style="text-align: center">
                                                <%=i.getMainCategoryID() %>
                                            </td>
                                            <td>
                                                <%=i.getMainCategoryName() %>
                                            </td>

                                            <td style="text-align: center">
                                               <form action="Main-Category.jsp" method="post">
                                                    <input type="hidden" name="getMainCategoryID" value="<%=i.getMainCategoryID() %>">
                                                    <input type="hidden" name="name" value="<%=i.getMainCategoryName() %>">
                                                    <button type="submit" class="btn btn-primary btn-sm" name="submit" id="btn1">Edit</button>					
                                                </form>
                                            </td>
                                            
                                            <td style="text-align: center">
                                                <script>
                                                    function d1()]{
                                                        alert("cannot deleted becouse product not empty");
                                                    }
                                                </script>
                                                <form action="CategoryRemoveServlet" method="POST">
                                                    <input type="hidden" name="getMainCategoryID" value="<%=i.getMainCategoryID() %>">
                                                    <button OnClick="d1" class="btn btn-primary btn-sm" name="submit" id="btn2">Delete</button>
						</form>
                                            </td>       
                                        </tr>
                <%                    
                                    }
                %>
                                </table>
                            </center>
                <%
                        
                        } 
                        else{
                %>          
                            <br><br><br>
                            <center>
                                <h6>No Available Main Categories</h6>
                            </center>
                <%
                        }
                    
                %> 
            </div>  
            <div class="col-lg-6">
                
                <h5 style="text-align: center; color: #E97000">SUB CATEGORIES</h5>
                <br>
                
                <a href="Sub-Category.jsp">ADD NEW</a>
                
                <%
                    if(request.getAttribute("SubCategory_details_list") != null){

                        @SuppressWarnings("unchecked")
                        ArrayList<SubCategoryDetails> SubCategory_details_list = (ArrayList<SubCategoryDetails>)request.getAttribute("SubCategory_details_list");

                                
                %>
                            <center>
                                <table class="table table-bordered" id="tb2">
                                    <tr>
                                        <th style="text-align: center">Sub Category ID</th>
                                        <th style="text-align: center">Name</th>
                                        <th style="text-align: center">Main Category ID</th> 
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                
                <%
                                    for(SubCategoryDetails i: SubCategory_details_list){
                %>
                                        <tr>
                                            <td style="text-align: center">
                                                <%=i.getSubCategoryID() %>
                                            </td>
                                            
                                             <td>
                                                <%=i.getSubCategoryName() %>
                                            </td>
                                           
                                            <td style="text-align: center">
                                                <%=i.getMainCategoryID() %>
                                            </td>
                                            <td style="text-align: center">
                                                <form action="Sub-Category.jsp" method="POST">
                                                    <input type="hidden" name="getSubCategoryID" value="<%=i.getSubCategoryID() %>">
                                                    <input type="hidden" name="name" value="<%=i.getSubCategoryName() %>">
                                                    <input type="hidden" name="main_category_id" value="<%=i.getMainCategoryID() %>">
                                                    <button type="submit" class="btn btn-primary btn-sm" name="submit" id="btn3">Edit</button>
                                                </form>
                                            </td>
                                            
                                            <td style="text-align: center">
                                                <form action="CategoryRemoveServlet2" method="POST">
                                                    <input type="hidden" name="getSubCategoryID" value="<%=i.getSubCategoryID() %>">
                                                    <button type="submit" class="btn btn-primary btn-sm" name="submit" id="btn4">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                <%    
                                    }
                %>
                                </table>
                            </center>
                <%
                        }
                        else{
                %>          
                            <br><br><br>
                            <center>
                                <h6>No Available Sub Categories</h6>
                            </center>
                <%
                        }
                    
                %> 
            </div>
        </div>
             
    <%@include file="admin_footer.jsp" %>