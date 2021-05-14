package model;

import java.sql.*; 

public class User {

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
	
	public String AddUser(String name, String emailAdress, String age, String isAdmin, String type, String credit){ // inserting The users to the database
		String output = "";
	 
		try {
			 Connection con = connect();
			 if (con == null){ //check connection before sending user data to the DB
				 return "Error while connecting to the database for inserting."; 
			 }
	
			 String query = "insert into User(`userId`,`name`,`emailAdress`,`age`,`isAdmin`,`type`,`credit`)" + "values (?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, emailAdress);
			 preparedStmt.setInt(4, Integer.parseInt(age));
			 preparedStmt.setString(5, isAdmin);
			 preparedStmt.setString(6, type);
			 preparedStmt.setDouble(7, Double.parseDouble(credit));
			
			 preparedStmt.execute();
			 con.close();
			 //error handling 
			 String newUser = ReadUser();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newUser + "\"}"; 
	   }
	   catch (Exception e){
		    output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}";
			System.err.println(e.getMessage());
	   }
		
		
	   return output;
	}
	
	public String ReadUser(){ // Reading user details
		 String output = "";
		 
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before Read the User form DB
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 output = "<table border='1'><tr><th>User Id</th><th>Name</th>" +"<th>EmailAdress</th>" +"<th>Age</th>"+"<th>isAdmin</th>"+"<th>Type</th>" +"<th>Credit</th></tr>";
			 String query = "select * from User";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
		
			 while (rs.next()){
				 String userId = Integer.toString(rs.getInt("userId"));
				 String name = rs.getString("name");
				 String emailAdress = rs.getString("emailAdress");
				 String age = Integer.toString(rs.getInt("age"));
				 String isAdmin = rs.getString("isAdmin");
				 String type = rs.getString("type");
				 String credit = Double.toString(rs.getDouble("credit"));
			
				 output += "<tr><td>" + userId + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + emailAdress + "</td>";
				 output += "<td>" + age + "</td>";
				 output += "<td>" + isAdmin + "</td>";
				 output += "<td>" + type + "</td>";
				 output += "<td>" + credit + "</td>";
				 
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-itemid='" + userId + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-itemid='" + userId + "'></td></tr>";
			 }
			 con.close();
			 output += "</table>";
		 }
		 catch (Exception e){
			 output = "Error while reading the User!!";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;	 
	 }
	
	public String UpdateUser(String userId, String name, String emailAdress, String age, String isAdmin, String type, String credit){
	 String output = "";
	 
	 try {
		 Connection con = connect();
		 if (con == null){ //check the connection before Updating the User
			 return "Error while connecting to the database for updating."; 
		 }
		
		 String query = "UPDATE User SET name=?,emailAdress=?,age=?,isAdmin=?,type=?,credit=? WHERE userId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, emailAdress);
		 preparedStmt.setInt(3, Integer.parseInt(age));
		 preparedStmt.setString(4, isAdmin);
		 preparedStmt.setString(5, type);
		 preparedStmt.setDouble(6, Double.parseDouble(credit));
		 preparedStmt.setInt(7, Integer.parseInt(userId));
		
		 preparedStmt.execute();
		 con.close();
		 //error handling 
		 String newUser = ReadUser();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newUser + "\"}"; 
   }
   catch (Exception e){
	    output = "{\"status\":\"error\", \"data\":\"Error while Updating the User.\"}";
		System.err.println(e.getMessage());
   }
	
	 
	 	return output;
	 }
	
	public String DeleteUser(String userId){
	     String output = "";
	     
		 try {
			 Connection con = connect();
			 if (con == null){ //check the connection before delete the User from DB
				 return "Error while connecting to the database for deleting."; 
			 }
			
			 String query = "delete from User where userId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 preparedStmt.setInt(1, Integer.parseInt(userId));
			
			 preparedStmt.execute();
			 con.close();
			 //error handling 
			 String newUser = ReadUser();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newUser + "\"}"; 
	   }
	   catch (Exception e){
		    output = "{\"status\":\"error\", \"data\":\"Error while deleting the User.\"}";
			System.err.println(e.getMessage());
	   }
		
		 
		 return output;
	}
}
