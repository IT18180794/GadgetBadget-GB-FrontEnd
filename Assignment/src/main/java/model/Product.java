package model;

import java.sql.*; 

public class Product {

	private Connection connect() { //Set the CDonnection to the database
		 Connection con = null;

		 try{
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3307/systemdb", "root", "root");
			 //For testing
			 System.out.print("Database Successfully connected...");
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return con;
	}
	
	public String InsertProduct(String productName, String price, String investment, String description){ // inserting The product to the database
		String output = "";
	 
		try {
			 Connection con = connect();
			 if (con == null){ //check connection before sending data to the DB
				 return "Error while connecting to the database for inserting."; 
			 }
	
			 String query = "insert into Products(`productId`,`productName`,`price`,`investment`,`description`)" + "values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, productName);
			 preparedStmt.setDouble(3, Double.parseDouble(price));
			 preparedStmt.setDouble(4, Double.parseDouble(investment));
			 preparedStmt.setString(5, description);
			
			 preparedStmt.execute();
			 con.close();
			 
			 //error handling 
			 String newProduct = ReadProduct();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newProduct + "\"}"; 
	   }
	   catch (Exception e){
		    output = "{\"status\":\"error\", \"data\":\"Error while inserting the prodcut.\"}";
			System.err.println(e.getMessage());
	   }
		
	   return output;
	}
	
	public String ReadProduct(){ // Reading the product from the database
		 String output = "";
		 
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before Read the Product form DB
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 output = "<table border='1'><tr><th>Product Id</th>" +"<th>ProductName</th>" +"<th>ProductPrice</th>" +"<th>ProductInvestment</th>"+"<th>Productdescription</th></tr>";
			 String query = "select * from Products";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
		
			 while (rs.next()){
				 String productId = Integer.toString(rs.getInt("productId"));
				 String productName = rs.getString("productName");
				 String price = Double.toString(rs.getDouble("price"));
				 String investment = Double.toString(rs.getDouble("investment"));
				 String description = rs.getString("description");
			
				 output += "<tr><td>" + productId + "</td>";
				 output += "<td>" + productName + "</td>";
				 output += "<td>" + price + "</td>";
				 output += "<td>" + investment + "</td>";
				 output += "<td>" + description + "</td>";
				 
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-itemid='" + productId + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-itemid='" + productId + "'></td></tr>";
			 }
			 con.close();
			 output += "</table>";
		 }
		 catch (Exception e){
			 output = "Error while reading the Product!!";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;	 
	 }
	
	public String UpdateProduct(String productId, String productName, String price, String investment, String description){
	 String output = "";
	 
	 try {
		 Connection con = connect();
		 if (con == null){ //check the connection before Updating the Product
			 return "Error while connecting to the database for updating."; 
		 }
		
		 String query = "UPDATE Products SET productName=?,price=?,investment=?,description=? WHERE productId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, productName);
		 preparedStmt.setDouble(2, Double.parseDouble(price));
		 preparedStmt.setDouble(3, Double.parseDouble(investment));
		 preparedStmt.setString(4, description);
		 preparedStmt.setDouble(5, Integer.parseInt(productId));
		
		 preparedStmt.execute();
		 con.close();
		 
		 //error handling 
		 String newProduct = ReadProduct();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newProduct + "\"}"; 
   }
   catch (Exception e){
	    output = "{\"status\":\"error\", \"data\":\"Error while Updaing the product.\"}";
		System.err.println(e.getMessage());
   }
	 
	 	return output;
	 }
	
	public String DeleteProduct(String productId){
	     String output = "";
	     
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before delete the product from DB
				 return "Error while connecting to the database for deleting."; 
			 }
			
			 String query = "delete from Products where productId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, Integer.parseInt(productId));
			
			 preparedStmt.execute();
			 con.close();
			 //error handling 
			 String newProduct = ReadProduct();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newProduct + "\"}"; 
	   }
	   catch (Exception e){
		    output = "{\"status\":\"error\", \"data\":\"Error while deleting the product.\"}";
			System.err.println(e.getMessage());
	   }
		 
		 return output;
	}
}
