package OrderManagement;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
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
                if (order != null) {
                    String OrderJson = new Gson().toJson(order);

                    // Add new customer name to JsonObject
                    AdditionalFunctions adf = new AdditionalFunctions();
                    String updatedOrderJson = adf.addCustomerNameToJsonObject(OrderJson);

                    response.setContentType("application/json");
                    response.getWriter().println(updatedOrderJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else if (orderId == null && orderStatus != null && orderProductsDetails == null) {
                List<Order> orders = OrderDAO.getOrdersByStatus(orderStatus);
                String ordersJson = new Gson().toJson(orders);
                response.setContentType("application/json");
                response.getWriter().println(ordersJson);
            } else if (orderId != null && orderStatus == null && orderProductsDetails != null) {

                AdditionalFunctions adf = new AdditionalFunctions();
                String orderProductDetailsJSONString = adf.getOrderProductsDetails(orderId);
                
                response.setContentType("application/json");
                response.getWriter().println(orderProductDetailsJSONString);

            } else {
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
