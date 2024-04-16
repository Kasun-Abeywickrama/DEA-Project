package ProductManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author HP
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
@MultipartConfig
public class CreateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            double buyingPrice = Double.parseDouble(request.getParameter("buying_price"));
            double sellingPrice = Double.parseDouble(request.getParameter("selling_price"));
            String description = request.getParameter("description");
            int subCategoryId = Integer.parseInt(request.getParameter("sub_cat"));

            // Get the image file from the request
            Part imagePart = request.getPart("image");
            String imageName = imagePart.getSubmittedFileName();

            Product product = new Product(name, buyingPrice, sellingPrice, description, imageName, subCategoryId);
            int newId = ProductDAO.addProduct(product);

            if (newId != 0) {
                ImageManager imo = new ImageManager();
                boolean status = imo.storeImage(newId, imagePart);
                if (status) {
                    response.setStatus(HttpServletResponse.SC_CREATED); // Created successfully
                    response.sendRedirect("view_all_products_page.jsp");
                }
            }

        } catch (NumberFormatException e) {
            // Handle parsing error (invalid price format)
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid price format!");
        } catch (SQLException ex) {
            Logger.getLogger(CreateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
