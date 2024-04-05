/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "Product_CRUD_Servlet", urlPatterns = {"/Product_CRUD_Servlet"})
public class Product_CRUD_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String subCat = request.getParameter("sub_cat");

        Product_CRUD_DB_Class ob = new Product_CRUD_DB_Class();
        boolean state = ob.addProduct(name, price, description, image, subCat);
        if (state) {
            response.getWriter().println("Insertion successful");
        } else {
            response.getWriter().println("Insertion failed");
        }

    }

}
