package model;

import java.sql.*; 

public class Rating {

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
	
	public String AddRating(String productId, String ratingValue, String review, String overallRating){ // Adding ratings
		String output = "";
	 
		try {
			 Connection con = connect();
			 if (con == null){ //check connection before sending rating details to the DB
				 return "Error while connecting to the database for inserting."; 
			 }
	
			 String query = " insert into Rating(`ratingId`,`productId`,`ratingValue`,`review`,`overallRating`)" + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setInt(2, Integer.parseInt(productId));
			 preparedStmt.setDouble(3, Double.parseDouble(ratingValue));
			 preparedStmt.setString(4, review);
			 preparedStmt.setDouble(5, Double.parseDouble(overallRating));
			
			 preparedStmt.execute();
			 con.close();
			 //error handling 
			 String newRating = ReadRating();
			 output = "{\"status\":\"success\", \"data\": \"" +
             newRating + "\"}"; 
	   }
	   catch (Exception e){
		    output = "{\"status\":\"error\", \"data\":\"Error while inserting the Rating.\"}";
			System.err.println(e.getMessage());
	   }
		
		
	   return output;
	}
	
	public String ReadRating(){ //Read rating from the database
		 String output = "";
		 
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before reading the rating values 
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 output = "<table border='1'><tr><th>Rating Id</th><th>Product Id</th>" +"<th>Rating Value</th>" +"<th>Review</th>"+"<th>Overrall Rating</th></tr>";
			 String query = "select * from Rating";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
		
			 while (rs.next()){
				 String ratingId = Integer.toString(rs.getInt("ratingId"));
				 String productId = Integer.toString(rs.getInt("productId"));
				 String ratingValue = Double.toString(rs.getDouble("ratingValue"));
				 String review = rs.getString("review");
				 String overallRating = Double.toString(rs.getDouble("overallRating"));
			
				 output += "<tr><td>" + ratingId + "</td>";
				 output += "<td>" + productId + "</td>";
				 output += "<td>" + ratingValue + "</td>";
				 output += "<td>" + review + "</td>";
				 output += "<td>" + overallRating + "</td>";
				 
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-itemid='" + ratingId + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-itemid='" + ratingId + "'></td></tr>";
			 }
			 con.close();
			 output += "</table>";
		 }
		 catch (Exception e){
			 output = "Error while reading the Rating values from the database!!";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;	 
	 }
	
	public String UpdateRatings(String ratingId, String ratingValue, String review, String overallRating){
	 String output = "";
	 
	 try {
		 Connection con = connect();
		 if (con == null){ //check the connection before Updating the Product
			 return "Error while connecting to the database for updating."; 
		 }
		
		 String query = "UPDATE Rating SET ratingValue=?,review=?,overallRating=?WHERE ratingId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setDouble(1, Double.parseDouble(ratingValue));
		 preparedStmt.setString(2, review);
		 preparedStmt.setDouble(3, Double.parseDouble(overallRating));
		 preparedStmt.setDouble(4, Integer.parseInt(ratingId));
		
		 preparedStmt.execute();
		 con.close();
		 //error handling 
		 String newRating = ReadRating();
		 output = "{\"status\":\"success\", \"data\": \"" +
         newRating + "\"}"; 
   }
   catch (Exception e){
	    output = "{\"status\":\"error\", \"data\":\"Error while Updating the Rating.\"}";
		System.err.println(e.getMessage());
   }
	 
	 	return output;
	 }
	
	public String DeleteRating(String ratingId){
	     String output = "";
	     
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before delete the Rating values from DB
				 return "Error while connecting to the database for deleting."; 
			 }
			
			 String query = "delete from Rating where ratingId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, Integer.parseInt(ratingId));
			
			 preparedStmt.execute();
			 con.close();
			 //error handling 
			 String newRating = ReadRating();
			 output = "{\"status\":\"success\", \"data\": \"" +
             newRating + "\"}"; 
	   }
	   catch (Exception e){
		    output = "{\"status\":\"error\", \"data\":\"Error while deleting the Rating.\"}";
			System.err.println(e.getMessage());
	   }
		 
		 return output;
	}
}
