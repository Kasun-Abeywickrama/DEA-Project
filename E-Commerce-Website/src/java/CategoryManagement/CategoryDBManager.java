/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CategoryManagement;

import DatabaseConnection.DBConnectionManager;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dewmi
 */
public class CategoryDBManager {

    private static final String GET_ALL_MAIN_CATEGORIES_SQL = "SELECT * FROM main_category";
//    private static final String GET_ALL_SUB_CATEGORIES_SQL = "SELECT * FROM sub_category";
    private static final String GET_ALL_SUB_CATEGORIES_SQL = "SELECT sub_category.sub_category_id, main_category.main_category_id, sub_category.name AS sub_category_name, main_category.name AS main_category_name FROM sub_category INNER JOIN main_category ON sub_category.main_category_id = main_category.main_category_id";
    private static final String ADD_CATEGORY_SQL = "INSERT INTO main_category (name) VALUES (?)";

    public boolean addCategory(String name) {

        DBConnectionManager dbc = new DBConnectionManager();
        Connection connection = dbc.getDBConnection();
        PreparedStatement statement = null;
        boolean state = false;

        try {
            statement = connection.prepareStatement(ADD_CATEGORY_SQL);
            statement.setString(1, name);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                state = true;
                System.out.println("Category insertion successful");
            } else {
                System.out.println("Category insertion failed");
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbc.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error : Connection closing error");
            }
        }
        return state;
    }

    public String getAllMainCategories() {

        DBConnectionManager dbc = new DBConnectionManager();
        Connection connection = dbc.getDBConnection();
        PreparedStatement statement = null;
        List<MainCategory> categories = new ArrayList<>();

        try {
            statement = connection.prepareStatement(GET_ALL_MAIN_CATEGORIES_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int mainCategoryId = resultSet.getInt("main_category_id");
                String mainCategoryName = resultSet.getString("name");
                categories.add(new MainCategory(mainCategoryId, mainCategoryName));
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    dbc.closeDBConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error : Connection closing error");
            }
        }

        String categoriesJson = new Gson().toJson(categories);
        return categoriesJson;
    }

//    public String getAllSubCategories() {
//        DBConnectionManager dbc = new DBConnectionManager();
//        Connection connection = dbc.getDBConnection();
//        PreparedStatement statement = null;
//        List<SubCategory> subCategories = new ArrayList<>();
//
//        try {
//            statement = connection.prepareStatement(GET_ALL_SUB_CATEGORIES_SQL);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int subCategoryId = resultSet.getInt("sub_category_id");
//                int mainCategoryId = resultSet.getInt("main_category_id");
//                String subCategoryName = resultSet.getString("sub_category_name");
//                String mainCategoryName = resultSet.getString("main_category_name");
//                subCategories.add(new SubCategory(subCategoryId, mainCategoryId, subCategoryName, mainCategoryName));
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error : " + e);
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    dbc.closeDBConnection();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error : Connection closing error");
//            }
//        }
//
//        String categoriesJson = new Gson().toJson(subCategories);
//        return categoriesJson;
//    }

}
