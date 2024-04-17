package ProductManagement;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            String name = request.getParameter("name");
            double buyingPrice = Double.parseDouble(request.getParameter("buying_price"));
            double sellingPrice = Double.parseDouble(request.getParameter("selling_price"));
            String description = request.getParameter("description");
//            String image = request.getParameter("image");
            int subCategoryId = Integer.parseInt(request.getParameter("sub_cat"));

            // Get the image file from the request
            Part imagePart = request.getPart("image");
            String imageName = imagePart.getSubmittedFileName();

            int pId = Integer.parseInt(request.getParameter("product_id"));
            boolean imageStatus = false;

            String imageVal = request.getParameter("image");
            if (imageVal == null) {
                ImageManager imo = new ImageManager();
                imageStatus = imo.updateImage(pId, imagePart);
            }
            //Update image details to the Database
            Product product = new Product(productId, name, buyingPrice, sellingPrice, description, imageName, subCategoryId);
            boolean status = ProductDAO.updateProduct(product);
            if (status) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect("view_all_products_page.jsp");

            } else {
                response.getWriter().println("Product deletion unsuccessfull");
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
