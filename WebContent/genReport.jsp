<%@ page import = "lib_1.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
libraryWorks lib = libraryWorks.getInstance();
ArrayList<Book> books = lib.viewAllBooks();
if(!books.isEmpty())
{
	String[] allBooks= new String[books.size()];
	int i=0;
	for(i=0;i<books.size();i++)
	{
		allBooks[i]=books.get(i).getTitle()+"     "+books.get(i).getTitle();
	}
	fileIO.printReport(allBooks);
}
%>
<jsp:forward page="librarian.jsp">
<jsp:param value="1" name="note"/>
</jsp:forward>
</body>
</html>