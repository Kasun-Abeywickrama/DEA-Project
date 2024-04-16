package ProductManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "ReadProductServlet", urlPatterns = {"/ReadProductServlet"})
public class ReadProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("productId");

        try {
            if (productId == null) {
                List<Product> products = ProductDAO.getProducts();
                String productJson = new Gson().toJson(products);
                response.setContentType("application/json");
                response.getWriter().println(productJson);
            } else {
                int id = Integer.parseInt(productId);
                Product product = ProductDAO.getProductById(id);
                if (product != null) {
                    String productJson = new Gson().toJson(product);
                    response.setContentType("application/json");
                    response.getWriter().println(productJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid product ID format!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("Error getting product", ex); // Consider logging to a file
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
