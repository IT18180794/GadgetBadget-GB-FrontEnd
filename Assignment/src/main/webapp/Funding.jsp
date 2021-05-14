<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Funding"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funding</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Funding.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Funding Management</h1>
<form id="formItem" name="formItem">
 FundingId:
 <input id="FundingId" name="FundingId" type="text"
 class="form-control form-control-sm">
 <br> rate:
 <input id="rate" name="rate" type="text"
 class="form-control form-control-sm">
 <br> description:
 <input id="description" name="description" type="text"
 class="form-control form-control-sm">
 <br> period:
 <input id="period" name="period" type="text"
 class="form-control form-control-sm">
 <br> totalfunding:
 <input id="totalfunding" name="totalfunding" type="text"
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidIFundingIdSave"
 name="hidIFundingIdSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Funding FundingObj = new Funding();
 out.print(FundingObj.ReadFunding());
 %>
</div>
</div> </div> </div>
</body>
</html>