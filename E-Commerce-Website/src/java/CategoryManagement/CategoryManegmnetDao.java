package CategoryManagement;


import DatabaseConnection.DBConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class CategoryManegmnetDao {

	 

	 // Add data to main category
	 
	public String addMCategory(String name) throws SQLException   {
	    String message = null;
	    DBConnectionManager dbc = new DBConnectionManager();
	    String ADD_MAIN="INSERT INTO main_category (name) VALUES (?)";

	    // Main Category Add
	    try (Connection connection = dbc.getDBConnection()) {
	        PreparedStatement catstmt = connection.prepareStatement(ADD_MAIN);
	        catstmt.setString(1, name);
	        int rowsAffected = catstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            message =  "Main Category added successfully";
	        } else {
	            message= "Unable to add main category";
	        }
	    } catch (SQLException e) {
	        System.out.println(e);
	        throw e;
	    } finally {
	        dbc.closeDBConnection(); 
	    } 
	    return message;     
	}



	 // Add data to sub category
	 
	public String addSCategory(String name, int main_category_id) throws SQLException  {
	    String message = null;
	    DBConnectionManager dbc = new DBConnectionManager();
	    String ADD_MAIN="INSERT INTO sub_category (name, main_category_id) VALUES (?,?)";

	    // Sub Category Add
	    try (Connection connection = dbc.getDBConnection()) {
	        PreparedStatement catstmt = connection.prepareStatement(ADD_MAIN);
	        catstmt.setString(1, name);
	        catstmt.setInt(2, main_category_id);
	        int rowsAffected = catstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            message =  "Sub Category added successfully";
	        } else {
	            message= "Unable to add sub category";
	        }
	    } catch (SQLException e) {
	        System.out.println(e);
	        throw e;
	    } finally {
	        dbc.closeDBConnection(); 
	    } 
	    return message;     
	}

	
	
	//RETRIVE MAIN CATEGORY DETAIL 
	

	  
	  public ArrayList<MainCategoryDetails> GetAllDataM() throws SQLException {
		    ArrayList<MainCategoryDetails> mainCategoryDetailsList = new ArrayList<>();
		    DBConnectionManager dbcon = new DBConnectionManager(); 

		    try {
		        Connection connection = dbcon.getDBConnection();
		        Statement stmt1 = connection.createStatement();
		        ResultSet rs1 = stmt1.executeQuery("SELECT * FROM main_category;");

		        while (rs1.next()) {
		            MainCategoryDetails mdl = new MainCategoryDetails(
		                rs1.getInt("main_category_id"), 
		                rs1.getString("name"));
		            mainCategoryDetailsList.add(mdl);
		        }

		        rs1.close();
		        stmt1.close();
		        dbcon.closeDBConnection();
		    } catch(SQLException e){
		        System.out.println(e);
		        throw e;
		    }

		    return mainCategoryDetailsList;
		}

          
         
          
	  
	  
	  
	  // Retrieve Subcategory Details
	  public ArrayList<SubCategoryDetails> GetAllDataS() throws SQLException {
		    ArrayList<SubCategoryDetails> SubCategoryDetailsList = new ArrayList<>();
		    DBConnectionManager dbcon = new DBConnectionManager(); 

		    try {
		        Connection connection = dbcon.getDBConnection();
		        Statement stmt2 = connection.createStatement();
		        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM sub_category;");

		        while (rs2.next()) {
		            SubCategoryDetails su = new SubCategoryDetails(
		                rs2.getInt("sub_category_id"),
		                rs2.getInt("main_category_id"),
		                rs2.getString("name"));
		            SubCategoryDetailsList.add(su);
		        }

		        rs2.close();
		        stmt2.close();
		        dbcon.closeDBConnection();
		    } catch(SQLException e){
		        System.out.println(e);
		        throw e;
		    }

		    return SubCategoryDetailsList;
		}

	  
	 // UDATE MAIN CATAGORY 
	  
	  public String updateMCategory(int id, String name) throws SQLException {
		    String message = null;
		    DBConnectionManager dbcon = new DBConnectionManager(); 

		    try {
		        Connection connection = dbcon.getDBConnection();
		        String sql = "UPDATE main_category SET name = ? WHERE main_category_id = ?";
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, name);
		        statement.setInt(2,id );
		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            message = "An existing Main Category was updated successfully!";
		            statement.close();
		            connection.close();
		            dbcon.closeDBConnection();
		        } else {
		            statement.close();
		            connection.close();
		            dbcon.closeDBConnection();
		            message= "Unable to update the main category details";
		        }
		    } catch(SQLException e){
		        System.out.println(e);
		        throw e;
		    }

		    return message;     
		}
 
	  
	  
	//UPDATE SUBCATEGORY
	  
	  
	  public String updateSCategory(int id, int main_category_id,String name) throws SQLException {
		    String message = null;
		    DBConnectionManager dbcon = new DBConnectionManager(); 
	  
  	  
      
              
      try{
                  
          Connection connection = dbcon.getDBConnection();

          String sql = "UPDATE sub_category SET name = ?, main_category_id = ? WHERE sub_category_id = ?";

          PreparedStatement statement = connection.prepareStatement(sql);
              
          statement.setString(1, name);
          statement.setInt(2,main_category_id );
              
          statement.setInt(3,id );
             
          int rowsUpdated = statement.executeUpdate();
          if (rowsUpdated > 0) {
              message="An existing Sub Category was updated successfully!";
                  
              
              statement.close();
              connection.close();
              dbcon.closeDBConnection();
             
          }
          else{
                              
              statement.close();
              connection.close();
              dbcon.closeDBConnection();
                              
              message= "Unable to update the sub category details";
               
                          	 
          }
  
      
	  } catch(SQLException e){
	        System.out.println(e);
	        throw e;
	    }

	    return message;     
  }  
	  
	  
	//DELETE MAIN CATOGORY   
	  
	  public String removeMCategory(int main_category_id) throws SQLException {
		    String message = null;
		    DBConnectionManager dbcon = new DBConnectionManager(); 

		    try {
		        Connection connection = dbcon.getDBConnection();
		        Statement stmt = connection.createStatement();
		        int rowsAffected = stmt.executeUpdate("DELETE FROM main_category WHERE main_category_id=" + main_category_id);

		        if (rowsAffected > 0) {
		            message = "Main Category removed successfully";
		        } else {
		            message = "Unable to remove the main category, Already used in several sub-categories";
		        }

		        stmt.close();
		        dbcon.closeDBConnection();
		    } catch(SQLException e){
		        System.out.println(e);
		        throw e;
		    }

		    return message;
		}
	  
	  
	  
	  
	  
	  // SUBCATEGORY DELETION
	  
	  public String removeSCategory(int sub_category_id) throws SQLException {
		    String message = null;
		    DBConnectionManager dbcon = new DBConnectionManager();
	  
	  

      try {
          Connection connection = dbcon.getDBConnection();
          Statement stmt = connection.createStatement();

          int rowsAffected = stmt.executeUpdate("DELETE FROM sub_category WHERE sub_category_id=" + sub_category_id);

          if (rowsAffected > 0) {
              message = "Sub Category removed successfully";
          } else {
              message = "Unable to remove the sub category, Already associated with several products";
          }

          stmt.close();
          dbcon.closeDBConnection();

          

      } catch(SQLException e){
	        System.out.println(e);
	        throw e;
	    }

	    return message;
  }


	
}

