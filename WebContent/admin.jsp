 <%@ page import = "lib_1.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<style>
body {
  background-color: linen;
  font-family: Verdana;
}
h2 {
  text-align: center;
} 
p {
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
.textbox {
  border-radius: 8px;
  width: 170px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  padding: 14px 40px;
}
div {
  width: 320px;
  padding: 10px;
  border: 2px solid #4CAF50;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
</head>
<body>
<h1 style = "margin-top:50px;"><center>Admin Functions</center></h1>

<center>
<div>
<form action = "addLib.jsp">
<input type="text" class = "textbox" name="name" placeholder="Name" required>  <br>
<input type="text" class = "textbox" name="addr" placeholder="Address" required>  <br>
<input type="text" class = "textbox" name="cont" placeholder="Phone No" required>  <br>
<input type="text" class = "textbox" name="salary" placeholder="Salary" required>  <br>
<input type="submit" class = "button" value="Submit"><br/>
</form>
</div>

<a href="showLoans.jsp"><button class = "button">View Issued Books</button><br></a>
<a href="showBooks.jsp"><button class = "button">View All Books</button><br></a>
<a href="helloWorld.jsp"><button class = "button">Exit</button><br></a>


</center>


<script>

</script>
</body>

</html>