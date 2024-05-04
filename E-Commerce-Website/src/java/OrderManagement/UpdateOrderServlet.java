package OrderManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/UpdateOrderServlet"})
public class UpdateOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderStatus = request.getParameter("order_status");
        int orderId = Integer.parseInt(request.getParameter("order_id"));

        try {
           boolean status = OrderDAO.updateOrderStatus(orderStatus, orderId);
           if(status){
               response.sendRedirect("ReadOrderServlet?orderId="+orderId);
           }
        } catch (SQLException ex) {
            Logger.getLogger(ReadOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
