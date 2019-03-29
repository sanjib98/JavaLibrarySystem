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
			//Book b = new Book(1,"ff","aa0",false);
			//lib.addBook(b);
			ArrayList<Book> books = lib.viewAllBooks();
			
			if (!books.isEmpty())
		    { 
				out.println("Author Title");
%><br>
<%			
		        for (int i = 0; i < books.size(); i++)
		        {                      
		            out.println(i + "	  -	  	" + "\t\t");
		            out.println(books.get(i).getAuthor()+"		 "+books.get(i).getTitle());	    
%><br>
<%		            
		        }
		    }
		    else
		    {
		    	out.println("Library has no books");
			}
			
%>
<br>
<br>
<%
ArrayList<Person> persons = lib.findAllBorrowers();
int i=0,borcount=0;
for(i=0;i<persons.size();i++)
{
	if(persons.get(i).getClass().getSimpleName().equals("Borrower"))
	{
		borcount+=1;
		Borrower br= (Borrower)(persons.get(i));
		out.println("Borrowers ID: "+br.getID()+" Name: "+br.getName());
%><br>
<%		
	}
}
if(borcount == 0)
	out.println("No Borrowers");		
%>
<br>
<br>
<div>
<form action = "issue.jsp">
<input type="text" class = "textbox" name="book" placeholder="Book No" required>  <br>
<input type="text" class = "textbox" name="bor" placeholder="Borrower Id" required>  <br>
<input type="submit" class = "button" value="Issue Book"><br/>
</form>
</div>
<a href="librarian.jsp"><button class = "button">Exit</button><br></a>
</center>
</body>
</html>