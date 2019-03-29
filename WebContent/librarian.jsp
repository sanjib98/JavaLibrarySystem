<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<center>
<h1 style = "margin-top:30px;"><center>Librarian Functions</center></h1>

<a href="issueBook.jsp"><button class = "button">Issue Book</button><br></a>

<div>
<form action = "renewBook.jsp">
<input type="text" class = "textbox" name="brID" placeholder="Borrower Id" required>  <br>
<input type="submit" class = "button" value="Renew Book"><br/>
</form>
</div>

<div>
<form action = "returnBook.jsp">
<input type="text" class = "textbox" name="borID" placeholder="Borrower Id" required>  <br>
<input type="submit" class = "button" value="Return Book"><br/>
</form>
</div>

<div>
<form action = "addBook.jsp">
<input type="text" class = "textbox" name="title" placeholder="Title" required>  <br>
<input type="text" class = "textbox" name="author" placeholder="Author" required>  <br>
<input type="submit" class = "button" value="Create Book"><br/>
</form>
</div>

<div>
<form action = "addBorrower.jsp">
<input type="text" class = "textbox" name="borName" placeholder="Name" required>  <br>
<input type="text" class = "textbox" name="borAddr" placeholder="Address" required>  <br>
<input type="text" class = "textbox" name="borPh" placeholder="Phone" required>  <br>
<input type="submit" class = "button" value="Create Borrower"><br/>
</form>
</div>



<a href="showBorrower.jsp"><button class = "button">All Borrowers</button><br></a>
<a href="genReport.jsp"><button class = "button">Generate Report</button><br></a>
<a href="helloWorld.jsp"><button class = "button">Exit</button><br></a>
</center>
</body>
</html>