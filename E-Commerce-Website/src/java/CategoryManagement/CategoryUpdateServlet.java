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


@WebServlet(name = "CategoryUpdateServlet", urlPatterns = {"/CategoryUpdateServlet"})
public class CategoryUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	String mainCategoryIdStr = request.getParameter("id");
    	
    	int mainCategoryId = Integer.parseInt(mainCategoryIdStr);

    	String M_name = request.getParameter("name");
        
        DBConnectionManager dbcon = new DBConnectionManager();
                
        try{
                    
            Connection connection = dbcon.getDBConnection();
                    
            String sql = "UPDATE main_category SET name = ? WHERE main_category_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
                
            statement.setString(1, M_name);
            statement.setInt(2,mainCategoryId  );
               
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.getWriter().print("An existing Main Category was updated successfully!");
                    
                request.setAttribute("alert_message", "Main Category details updated successfully");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request,response);
               
                statement.close();
                connection.close();
                dbcon.closeDBConnection();
               
            }
            else{
                                
                statement.close();
                connection.close();
                dbcon.closeDBConnection();
                                
                request.setAttribute("alert_message", "Unable to update the main category details");
                request.getRequestDispatcher("/category_management_page.jsp").forward(request,response); 
                         	 
            }
                
                
        }
        catch(SQLException e2){
            response.getWriter().println(e2);
        }      
    }  
}
