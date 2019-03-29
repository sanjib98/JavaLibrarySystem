 <%@ page import = "lib_1.*,java.util.*" %>
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
<%
libraryWorks lib = libraryWorks.getInstance();
ArrayList<Loan> loans = lib.viewHistory();
if (!loans.isEmpty())
{ 
    out.println("Issued Books are: \n");
    out.println("Book's Title \t Borrower's Name \t Issued Date \t Returned Date \t Fine Paid \t Return Status \n");
%><br>
<%
    for (int i = 0; i < loans.size(); i++)
    {    
    	out.println(i + " - "+ loans.get(i).getBook().getTitle() + " " + loans.get(i).getBorrower().getName() + " " + loans.get(i).getIssuedDate() +  " " + loans.get(i).getReturnDate() + " " + loans.get(i).getFineStatus()
    			+ " " + loans.get(i).getReturnStatus()+"\n");
%><br>
<%
    }
}
else
    out.println("No issued books. \n");
%>
<br>
<a href="admin.jsp"><button class = "button">Exit</button><br></a>
</center>
</body>
</html>