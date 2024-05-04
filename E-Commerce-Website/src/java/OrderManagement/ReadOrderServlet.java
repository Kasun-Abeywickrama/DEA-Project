package OrderManagement;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ReadOrderServlet", urlPatterns = {"/ReadOrderServlet"})
public class ReadOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderId = request.getParameter("orderId");
        String orderStatus = request.getParameter("orderStatus");
        String orderProductsDetails = request.getParameter("orderProductsDetails");

        try {
            if (orderId == null && orderStatus == null && orderProductsDetails == null) {
                List<Order> orders = OrderDAO.getOrders();
                String ordersJson = new Gson().toJson(orders);
                response.setContentType("application/json");
                response.getWriter().println(ordersJson);
            } else if (orderId != null && orderStatus == null && orderProductsDetails == null) {
                int id = Integer.parseInt(orderId);
                Order order = OrderDAO.getOrderById(id);
                request.setAttribute("order", order);

                if (order != null) {
                    AdditionalFunctions adf = new AdditionalFunctions();
                    String userName = adf.getUsernameById(order.getUserId());
                    request.setAttribute("userName", userName);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

                ArrayList<OrderProductsDetails> orderProducts = OrderDAO.getOrderProductsDetails(id);
                request.setAttribute("productsList", orderProducts);
                request.getRequestDispatcher("order_page.jsp").forward(request, response);

            } else if (orderId == null && orderStatus != null && orderProductsDetails == null) {
                List<Order> orders = OrderDAO.getOrdersByStatus(orderStatus);
                String ordersJson = new Gson().toJson(orders);
                response.setContentType("application/json");
                response.getWriter().println(ordersJson);
            }else {
                response.getWriter().println("Invalid query parameter format!");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid order ID format!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("Error getting product", ex); // Consider logging to a file
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
