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
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pId = Integer.parseInt(request.getParameter("product_id"));

        try {
            ImageManager imo = new ImageManager();
            boolean imageDeleteStatus = imo.deleteImage(pId);

            if (imageDeleteStatus) {
                boolean status = ProductDAO.deleteProduct(pId);
                if (status) {
                    response.sendRedirect("view_all_products_page.jsp");
                } else {
                    response.getWriter().println("Product deleting error. please try again");
                }
            } else {
                response.getWriter().println("Product deleting error. please try again");
            }

        } catch (SQLException ex) {
            response.getWriter().println("Product Delete Unsuccessfull!");
        }
    }

}
