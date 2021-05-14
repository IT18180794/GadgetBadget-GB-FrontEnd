package model;

import java.sql.*; 

public class Funding {

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
	
	public String SetFunding(String productId, String rate, String description, String period, String totalfunding){ // Setting the funding for products
		String output = "";
	 
		try {
			 Connection con = connect();
			 if (con == null){ //check connection before sending Funding data to the DB
				 return "Error while connecting to the database for inserting."; 
			 }
	
			 String query = " insert into Funding(`fundingId`,`productId`,`rate`,`description`,`period`,`totalfunding`)" + " values (?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setInt(2, Integer.parseInt(productId));
			 preparedStmt.setDouble(3, Double.parseDouble(rate));
			 preparedStmt.setString(4, description);
			 preparedStmt.setInt(5, Integer.parseInt(period));
			 preparedStmt.setDouble(6, Double.parseDouble(totalfunding));
			
			 preparedStmt.execute();
			 con.close();
			 output = "Funding Details Set Successfully!!";
	   }
	   catch (Exception e){
			 output = "Error while inserting the Details to the Funding table!!";
			 System.err.println(e.getMessage());
	   }
		
	   return output;
	}
	
	public String ReadFunding(){ // Reading the Funding Details from the database
		 String output = "";
		 
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before Get the Details from Funding
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 output = "<table border='1'><tr><th>Funding Id</th><th>product Id</th>" +"<th>rate</th>" +"<th>Description</th>" +"<th>Period</th>"+"<th>Total Funding</th></tr>";
			 String query = "select * from Funding";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
		
			 while (rs.next()){
				 String fundingId = Integer.toString(rs.getInt("fundingId"));
				 String productId = Integer.toString(rs.getInt("productId"));
				 String rate = Double.toString(rs.getDouble("rate"));
				 String description = rs.getString("description");
				 String period = Integer.toString(rs.getInt("period"));
				 String totalfunding = Double.toString(rs.getDouble("totalfunding"));
			
				 output += "<tr><td>" + fundingId + "</td>";
				 output += "<td>" + productId + "</td>";
				 output += "<td>" + rate + "</td>";
				 output += "<td>" + description + "</td>";
				 output += "<td>" + period + "</td>";
				 output += "<td>" + totalfunding + "</td>";
			 }
			 con.close();
			 output += "</table>";
		 }
		 catch (Exception e){
			 output = "Error while reading the Funding Details!!";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;	 
	 }
	
	public String UpdatingFunding(String fundingId, String rate, String description, String period, String totalfunding){
	 String output = "";
	 
	 try {
		 Connection con = connect();
		 if (con == null){ //check the connection before Updating the Funding Details
			 return "Error while connecting to the database for updating."; 
		 }
		
		 String query = "UPDATE Funding SET rate=?,description=?,period=?,totalfunding=? WHERE fundingId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setDouble(1, Double.parseDouble(rate));
		 preparedStmt.setString(2, description);
		 preparedStmt.setDouble(3, Integer.parseInt(period));
		 preparedStmt.setDouble(4, Double.parseDouble(totalfunding));
		 preparedStmt.setDouble(5, Integer.parseInt(fundingId));
		
		 preparedStmt.execute();
		 con.close();
		 output = "Funding Details Updated successfully";
	 }
	 catch (Exception e){
		 output = "Error while updating the Funding Details.";
		 System.err.println(e.getMessage());
	 }
	 
	 	return output;
	 }
	
	public String DeleteFunding(String fundingId){
	     String output = "";
	     
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before delete the Funding Details from DB
				 return "Error while connecting to the database for deleting."; 
			 }
			
			 String query = "delete from Funding where fundingId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, Integer.parseInt(fundingId));
			
			 preparedStmt.execute();
			 con.close();
			 output = "Funding Details Deleted successfully";
		 }
		 catch (Exception e){
			 output = "Error while deleting the Funding Details.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	}
}
