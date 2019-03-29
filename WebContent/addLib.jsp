<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "lib_1.*,java.util.*,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Librarian</title>
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
  width: 160px;
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
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
</head>



<body>
<center>

<h1>Add Librarian</h1>
<%
libraryWorks lib = libraryWorks.getInstance();
String name = request.getParameter("name");
String addr = request.getParameter("addr");
int cont = Integer.valueOf(request.getParameter("cont"));
double sal = Double.valueOf(request.getParameter("salary"));

String res = lib.createLibrarian(name,addr,cont,sal);
out.println(res);
libraryDB ldb = libraryDB.getInstance();
Connection con = ldb.makeConnection();
ldb.fillBack(con);
%>
<br>
<a href="admin.jsp"><button class = "button">Exit</button><br></a>
</center>
</body>
</html>