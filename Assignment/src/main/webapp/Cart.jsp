<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Cart.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Cart Management</h1>
<form id="formItem" name="formItem">
 CartId:
 <input id="CartId" name="CartId" type="text"
 class="form-control form-control-sm">
 <br> productName:
 <input id="productName" name="productName" type="text"
 class="form-control form-control-sm">
 <br> price:
 <input id="price" name="price" type="text"
 class="form-control form-control-sm">
 <br> investmentDetails:
 <input id="investmentDetails" name="investmentDetails" type="text"
 class="form-control form-control-sm">
 <br>quantity:
 <input id="quantity" name="quantity" type="text"
 class="form-control form-control-sm">
  <br>totalPrice:
 <input id="totalPrice" name="totalPrice" type="text"
 class="form-control form-control-sm">
  <br>discount:
 <input id="discount" name="discount" type="text"
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidICartIDSave"
 name="hidICartIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Cart CartObj = new Cart();
 out.print(CartObj.ReadCart());
 %>
</div>
</div> </div> </div>
</body>
</html>