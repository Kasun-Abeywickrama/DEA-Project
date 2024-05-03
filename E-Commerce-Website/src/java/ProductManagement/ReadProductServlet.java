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

        String productId = request.getParameter("product_id");

        try {
            if (productId == null) {
                List<Product> products = ProductDAO.getProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("view_all_products_page.jsp").forward(request, response);
            } else {
                int id = Integer.parseInt(productId);
                Product product = ProductDAO.getProductById(id);
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("view_product_page.jsp").forward(request, response);
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
