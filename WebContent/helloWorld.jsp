<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Test</title>
<style>
body {
  background-color: linen;
  font-family: Verdana;
}
h2 {
  text-align: center;
} 
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  border-radius: 8px;
  width: 250px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  padding: 14px 40px;
}
</style>
</head>
<body>



<h1 style = "margin-top:50px;"><center>Welcome To Library Management System</center></h1>
<h2>Functions</h2>
<center>
<a href = "librarian.jsp"><button class = "button">Librarian</button></a><br>
<a href = "borrower.jsp"> <button class = "button">Borrower</button></a><br>
<a href = "admin.jsp"> <button class = "button">Admin</button></a><br>
</center>
</body>
</html>