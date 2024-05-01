package CategoryManagement;
import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CategoryUpdateServlet2", urlPatterns = {"/CategoryUpdateServlet2"})
public class CategoryUpdateServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	String subCategoryIdStr = request.getParameter("id");
    	String mainCategoryIdStr = request.getParameter("main_category_id");
    	
    	int subCategoryId = Integer.parseInt(subCategoryIdStr);
    	int mainCategoryId = Integer.parseInt(mainCategoryIdStr);

    	String S_name = request.getParameter("name");
    	  
        DBConnectionManager dbcon = new DBConnectionManager();
                
        try{
                    
            Connection connection = dbcon.getDBConnection();

            String sql = "UPDATE sub_category SET name = ?, main_category_id = ? WHERE sub_category_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
                
            statement.setString(1, S_name);
            statement.setInt(2,mainCategoryId  );
                
            statement.setInt(3,subCategoryId  );
               
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.getWriter().print("An existing Sub Category was updated successfully!");
                    
                request.setAttribute("alert_message", "Sub Category details updated successfully");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request,response);
               
                statement.close();
                connection.close();
                dbcon.closeDBConnection();
               
            }
            else{
                                
                statement.close();
                connection.close();
                dbcon.closeDBConnection();
                                
                request.setAttribute("alert_message", "Unable to update the sub category details");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request,response); 
                            	 
            }
    
        }
        catch(SQLException e2){
            response.getWriter().println(e2);
        }     
    }  
}
