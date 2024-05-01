package ProductManagement;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "ReadProductBySearch", urlPatterns = {"/ReadProductBySearch"})
public class ReadProductBySearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchTerm = request.getParameter("search_term");
        List<Product> products = ProductDAO.getProductBySearch(searchTerm);
        String searchProductJson = new Gson().toJson(products);
        response.setContentType("application/json");
        response.getWriter().println(searchProductJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
