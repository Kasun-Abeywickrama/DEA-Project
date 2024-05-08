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


@WebServlet(name = "CategoryRemoveServlet", urlPatterns = {"/CategoryRemoveServlet"})
public class CategoryRemoveServlet extends HttpServlet { 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int mainCategoryId = Integer.parseInt(request.getParameter("getMainCategoryID"));
        CategoryManegmnetDao operation = new CategoryManegmnetDao();
            
    try{
                
        String message = operation.removeMCategory(mainCategoryId);
        
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
