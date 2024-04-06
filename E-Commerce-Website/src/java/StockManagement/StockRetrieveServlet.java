/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockManagement;

import DatabaseConnection.db_connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sithuruwan
 */
@WebServlet(name = "StockRetrieveServlet", urlPatterns = {"/StockRetrieveServlet"})
public class StockRetrieveServlet extends HttpServlet {

    //Sending the whole product stock list to the JSP page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            db_connection db_conn = new db_connection();
            
            try{
                
                //Connect to the database
                Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);
                
                //Create a statement
                Statement stmt = con.createStatement();
                
                //Execute the SQL query to retrieve the product stock data list
                ResultSet rs = stmt.executeQuery("SELECT product.product_id, product.name ,product.stock ,sub_category.name, main_category.name FROM product,sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id ORDER BY product.product_id;");
                
                //Store the results in an arraylist of the ProductStock class
                ArrayList<ProductStock> pslist = new ArrayList();

                //Add the data to the array list
                while(rs.next()){
                    ProductStock ps = new ProductStock(rs.getInt("product.product_id"), rs.getString("product.name"), rs.getString("sub_category.name"), rs.getString("main_category.name"), rs.getInt("product.stock"));
                    pslist.add(ps);
                }
                
                con.close();
                
                request.setAttribute("stock_list", pslist);
                request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                
            }
            catch(SQLException e2){
                response.getWriter().println(e2);
            }
        }
        catch (ClassNotFoundException e1) {
            response.getWriter().println(e1);
        } 
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Sending the search data related to the search bar
        if("search".equals(request.getParameter("submit"))){
            
            String searchString = request.getParameter("search_string");
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                db_connection db_conn = new db_connection();

                try{

                    //Connect to the database
                    Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);

                    //Create a statement
                    Statement stmt = con.createStatement();

                    //Execute the SQL query to retrieve the searched product stock data list
                    ResultSet rs = stmt.executeQuery("SELECT product.product_id, product.name ,product.stock ,sub_category.name, main_category.name FROM product,sub_category, main_category WHERE product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id and product.name LIKE '"+searchString+"%' ORDER BY product.product_id;");

                    //Store the results in an arraylist of the ProductStock class
                    ArrayList<ProductStock> pslist = new ArrayList();

                    //Add the data to the array list
                    while(rs.next()){
                        ProductStock ps = new ProductStock(rs.getInt("product.product_id"), rs.getString("product.name"), rs.getString("sub_category.name"), rs.getString("main_category.name"), rs.getInt("product.stock"));
                        pslist.add(ps);
                    }

                    con.close();

                    request.setAttribute("stock_list", pslist);
                    request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);

                }
                catch(SQLException e2){
                    response.getWriter().println(e2);
                }
            }
            catch (ClassNotFoundException e1) {
                response.getWriter().println(e1);
            } 
        }
        
        //Sending the stock details of a single product to the JSP page
        if("Edit Stock".equals(request.getParameter("submit"))){
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));

            try{

                Class.forName("com.mysql.cj.jdbc.Driver");

                db_connection db_conn = new db_connection();

                try{

                    Connection con = DriverManager.getConnection(db_conn.url, db_conn.uname, db_conn.password);

                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery("SELECT product.name, product.stock, sub_category.name, main_category.name FROM product, sub_category, main_category WHERE product_id = "+product_id+" and product.sub_category_id = sub_category.sub_category_id and sub_category.main_category_id = main_category.main_category_id;");

                    if(rs.next()){

                        ProductStock ps = new ProductStock(product_id, rs.getString("product.name"), rs.getString("sub_category.name"), rs.getString("main_category.name"), rs.getInt("product.stock"));

                        con.close();

                        request.setAttribute("product_details", ps);
                        request.getRequestDispatcher("/stock_edit_page.jsp").forward(request,response);
                    }
                    else{
                        con.close();

                        request.setAttribute("alert_message", "Product does not exist");
                        request.getRequestDispatcher("/stock_management_page.jsp").forward(request,response);
                    }
                }
                catch(SQLException e2){
                    response.getWriter().println(e2);
                }
            }
            catch(ClassNotFoundException e1){
                response.getWriter().println(e1);
            }  
        } 
    }
}
