<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Product.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Product Management</h1>
<form id="formItem" name="formItem">
 productId:
 <input id="productId" name="productId" type="text"
 class="form-control form-control-sm">
 <br> productName:
 <input id="productName" name="productName" type="text"
 class="form-control form-control-sm">
 <br> price:
 <input id="price" name="price" type="text"
 class="form-control form-control-sm">
 <br> investment:
 <input id="investment" name="investment" type="text"
 class="form-control form-control-sm">
 <br>description:
 <input id="description" name="description" type="text"
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidCartIDSave"
 name="hidCartIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Product ProductObj = new Product();
 out.print(ProductObj.ReadProduct());
 %>
</div>
</div> </div> </div>
</body>
</html>