<%@ page import = "lib_1.*,java.util.*,java.sql.*" %>
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
int br = Integer.valueOf(request.getParameter("bID"));
int ip = Integer.valueOf(request.getParameter("loan"));
Borrower bor = lib.findParticularBorrower(br);
ArrayList<Loan> loans = bor.getBorrowedBooks();
Loan l = loans.get(ip);
l.getBook().returnBook(bor, l);
lib.changeLoanStatus(l.getBorrower().getID(),l.getBook().getID());

libraryDB ldb = libraryDB.getInstance();
Connection con = ldb.makeConnection();
ldb.fillBack(con);
%>
<jsp:forward page="librarian.jsp">
<jsp:param value="1" name="note"/>
</jsp:forward>
</body>
</html>