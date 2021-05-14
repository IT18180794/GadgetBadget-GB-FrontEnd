package model;

import java.sql.*; 

public class Cart {

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
	
	public String InsertCart(String productName, String price, String investmentDetails, String quantity, String totalPrice, String discount){ // inserting The product to the database
		String output = "";
	 
		try {
			 Connection con = connect();
			 if (con == null){ //check connection before sending data to the DB
				 return "Error while connecting to the database for inserting."; 
			 }
	
			 String query = "insert into Cart(`cartId`,`productName`,`price`,`investmentDetails`,`quantity`,`totalPrice`,`discount`)" + "values (?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, productName);
			 preparedStmt.setDouble(3, Double.parseDouble(price));
			 preparedStmt.setString(4, investmentDetails);
			 preparedStmt.setDouble(5, Double.parseDouble(quantity));
			 preparedStmt.setDouble(6, Double.parseDouble(totalPrice));
			 preparedStmt.setDouble(7, Double.parseDouble(discount));
			
			 preparedStmt.execute();
			 con.close();
			 output = "Cart Details Inserted successfully!!";
	   }
	   catch (Exception e){
			 output = "Error while inserting the Cart Details!!";
			 System.err.println(e.getMessage());
	   }
		
	   return output;
	}
	
	public String ReadCart(){ // Reading the Cart details from the database
		 String output = "";
		 
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before Read the Cart Details form DB
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 output = "<table border='1'><tr><th>Cart Id</th><th>Product Name</th>" +"<th>Unit Price</th>" +"<th>Investment Details</th>"+"<th>Quantity</th>"+"+<th>TotalPrice</th>"+"<th>discount</th></tr>";
			 String query = "select * from Cart";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
		
			 while (rs.next()){
				 String cartId = Integer.toString(rs.getInt("cartId"));
				 String productName = rs.getString("productName");
				 String price = Double.toString(rs.getDouble("price"));
				 String investmentDetails = rs.getString("investmentDetails");
				 String quantity = Double.toString(rs.getDouble("quantity"));
				 String totalPrice = Double.toString(rs.getDouble("totalPrice"));
				 String discount = Double.toString(rs.getDouble("discount"));
			
				 output += "<tr><td>" + cartId + "</td>";
				 output += "<td>" + productName + "</td>";
				 output += "<td>" + price + "</td>";
				 output += "<td>" + investmentDetails + "</td>";
				 output += "<td>" + quantity + "</td>";
				 output += "<td>" + totalPrice + "</td>";
				 output += "<td>" + discount + "</td>";
			 }
			 con.close();
			 output += "</table>";
		 }
		 catch (Exception e){
			 output = "Error while reading the Cart Details!!";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;	 
	 }
	
	public String UpdateCart(String cartId, String productName, String price, String investmentDetails, String quantity, String totalPrice, String discount){
	 String output = "";
	 
	 try {
		 Connection con = connect();
		 if (con == null){ //check the connection before Updating the Cart Details
			 return "Error while connecting to the database for updating."; 
		 }
		
		 String query = "UPDATE Cart SET productName=?,price=?,investmentDetails=?,quantity=?,totalPrice=?,discount=? WHERE cartId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, productName);
		 preparedStmt.setDouble(2, Double.parseDouble(price));
		 preparedStmt.setString(3, investmentDetails);
		 preparedStmt.setDouble(4, Double.parseDouble(quantity));
		 preparedStmt.setDouble(5, Double.parseDouble(totalPrice));
		 preparedStmt.setDouble(6, Double.parseDouble(discount));
		 preparedStmt.setString(7, cartId);
		
		 preparedStmt.execute();
		 con.close();
		 output = "Cart Details Updated successfully";
	 }
	 catch (Exception e){
		 output = "Error while updating the Cart Item!!";
		 System.err.println(e.getMessage());
	 }
	 
	 	return output;
	 }
	
	public String DeleteCart(String cartId){
	     String output = "";
	     
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before delete the Cart from DB
				 return "Error while connecting to the database for deleting."; 
			 }
			
			 String query = "delete from Cart where cartId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, Integer.parseInt(cartId));
			
			 preparedStmt.execute();
			 con.close();
			 output = "Cart Item Deleted successfully";
		 }
		 catch (Exception e){
			 output = "Error while deleting the Cart.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	}
}
