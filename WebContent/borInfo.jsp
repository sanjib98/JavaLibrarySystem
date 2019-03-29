<%@ page import = "lib_1.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<%
libraryWorks lib = libraryWorks.getInstance();
int br = Integer.valueOf(request.getParameter("borID"));
Borrower bor = lib.findParticularBorrower(br);
double totalFine = lib.computeFine2(bor);
out.println("Borrowers ID: "+bor.getID()+" Name: "+bor.getName()+" Total Fine: "+totalFine);
%>
<br>
<a href="borrower.jsp"><button class = "button">Exit</button><br></a>
</center>
</body>
</html>