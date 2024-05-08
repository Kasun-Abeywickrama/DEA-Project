package CategoryManagement;
import DatabaseConnection.DBConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryRemoveServlet2", urlPatterns = {"/CategoryRemoveServlet2"})
public class CategoryRemoveServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int subCategoryId = Integer.parseInt(request.getParameter("getSubCategoryID"));

        CategoryManegmnetDao operation = new CategoryManegmnetDao();
        
        try{
                    
            String message = operation.removeSCategory(subCategoryId);
            
            if(message != null){
                response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message="+message);
            }
            else{
                response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message=Category Does Not Exist");
            }
        }
        catch(SQLException e){
            response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message=Error Deleting Category");
        }
    }
    }
    
    
   
        
       
      
