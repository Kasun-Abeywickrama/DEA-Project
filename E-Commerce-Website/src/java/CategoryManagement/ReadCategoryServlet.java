package CategoryManagement;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dewmi
 */
@WebServlet(name = "ReadCategoryServlet", urlPatterns = {"/ReadCategoryServlet"})
public class ReadCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        String requestType = request.getParameter("type");
        String mainCategoryId = request.getParameter("mainCategoryId");
        String subCategoryId = request.getParameter("subCategoryId");

        if (!requestType.equals(null)) {
            if (requestType.equals("allMainCategories")) {
                CategoryDBManager cdbo = new CategoryDBManager();
                String categoriesJSON = cdbo.getAllMainCategories();
                response.setContentType("application/json");
                response.getWriter().println(categoriesJSON);

            } 
//            else if (requestType.equals("allSubCategories")) {
//                
//                CategoryDBManager cdbo = new CategoryDBManager();
//                String categoriesJSON = cdbo.getAllSubCategories();
//                response.setContentType("application/json");
//                response.getWriter().println(categoriesJSON);
//
//            } 
            else if (requestType.equals("mainCategory") && !mainCategoryId.equals(null)) {

            } else if (requestType.equals("subCategory") && !subCategoryId.equals(null)) {

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
