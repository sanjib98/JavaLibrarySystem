<%@ page import = "lib_1.*,java.util.*, java.sql.*" %>
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
libraryDB ldb = libraryDB.getInstance();
//Making connection with Database.
Connection con = ldb.makeConnection();
if (con == null)    
{
    out.println("Error connecting to Database.");
}
try{
	ldb.populateLibrary(con);
}
catch(Exception e){
	
}
%>
<jsp:forward page="helloWorld.jsp">
<jsp:param value="1" name="note"/>
</jsp:forward>
</body>
</html>