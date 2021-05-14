<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>User Management</h1>
<form id="formItem" name="formItem">
 UserId:
 <input id="UserId" name="UserId" type="text"
 class="form-control form-control-sm">
 <br> name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> emailAdress:
 <input id="emailAdress" name="emailAdress" type="text"
 class="form-control form-control-sm">
 <br> age:
 <input id="age" name="age" type="text"
 class="form-control form-control-sm">
 <br>isAdmin:
 <input id="isAdmin" name="isAdmin" type="text"
 class="form-control form-control-sm">
  <br>type:
 <input id="type" name="type" type="text"
 class="form-control form-control-sm">
  <br>credit:
 <input id="credit" name="credit" type="text"
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidIUserIdSave"
 name="hidIUserIdSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 User UserObj = new User();
 out.print(UserObj.ReadUser());
 %>
</div>
</div> </div> </div>
</body>
</html>