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
    	  
       
    
    
    // if("update_details".equals(request.getParameter("submit"))){
    CategoryManegmnetDao operation = new CategoryManegmnetDao();
    
    try{
            
        String message = operation.updateSCategory(subCategoryId,mainCategoryId, S_name );
        
        if(message != null){
            response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message="+message);
        }
        else{
            response.sendRedirect("cMainCategoryDetailsRetrieveServlet?alert_message=Category Does Not Exist");
        }
        
    }
    catch(SQLException e){
        response.sendRedirect("MainCategoryDetailsRetrieveServlet?alert_message=Error Updating Sub Category Details");
    }    
}
}
