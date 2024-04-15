package ProductManagement;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(request.getParameter("sub_cat"));
        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            String name = request.getParameter("name");
            double buyingPrice = Double.parseDouble(request.getParameter("buying_price"));
            double sellingPrice = Double.parseDouble(request.getParameter("selling_price"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            int subCategoryId = Integer.parseInt(request.getParameter("sub_cat"));

            Product product = new Product(productId, name, buyingPrice, sellingPrice, description, image, subCategoryId);
            boolean status = ProductDAO.updateProduct(product);

            if (status) {
                response.setStatus(HttpServletResponse.SC_CREATED); // Created successfully
                response.sendRedirect("view_all_products_page.jsp");
            }

        } catch (NumberFormatException e) {
            // Handle parsing error (invalid price format)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid format!");

        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
