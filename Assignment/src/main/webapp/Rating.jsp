<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Rating"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rating</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Rating.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Rating Management</h1>
<form id="formItem" name="formItem">
 RatingId:
 <input id="RatingId" name="RatingId" type="text"
 class="form-control form-control-sm">
 <br> ratingValue:
 <input id="ratingValue" name="ratingValue" type="text"
 class="form-control form-control-sm">
 <br> review:
 <input id="review" name="review" type="text"
 class="form-control form-control-sm">
 <br> overallRating:
 <input id="overallRating" name="overallRating" type="text"
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidIRatingSave"
 name="hidIRatingSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Rating RatingObj = new Rating();
 out.print(RatingObj.ReadRating());
 %>
</div>
</div> </div> </div>
</body>
</html>