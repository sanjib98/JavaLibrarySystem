package lib_1;
import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.Date;
import java.io.*;

public class libraryDB {

	private static libraryDB ldb;
	libraryWorks lib = libraryWorks.getInstance();
	public static libraryDB getInstance() {
		
		if(ldb==null)
		{
			ldb = new libraryDB();
		}
		
		return ldb;
	}
	
	
	private libraryDB() {
		// TODO Auto-generated constructor stub
	}

	//Connecting to DB
	public Connection makeConnection()
	{
		try {
			System.setProperty("jdbc.drivers", "com.mysql.jdbc.driver");
			//System.out.println(System.getProperty("jdbc.drivers"));
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			System.out.println("Connected database successfully...");
			return con;
			
			
		}
		
		catch(SQLException e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	//Loading all info into DB
	public void populateLibrary(Connection con) throws Exception
	{
		libraryDB ldb = this;
		Statement stmt = con.createStatement( );
		
		//Populating Book
        String SQL = "SELECT * FROM BOOK";
        ResultSet rs = stmt.executeQuery( SQL );
        
        if(!rs.next())
        {
           System.out.println("\nNo Books Found in Library"); 
        }
        
        else
        {
            int maxID = 0;
            
            do
            {
                if(rs.getString("TITLE") !=null && rs.getString("AUTHOR")!=null && rs.getInt("ID")!=0)
                {
                    String title=rs.getString("TITLE");
                    String author=rs.getString("AUTHOR");
                    int id= rs.getInt("ID");
                    boolean issue=rs.getBoolean("IS_ISSUED");
                    Book b = new Book(id,title,author,issue);
                    lib.addBookinLibrary(b);
                    
                    if (maxID < id)
                        maxID = id;
                }
            }while(rs.next());
            
            // setting Book Count
            Book.setIDCount(maxID);              
        }
        System.out.println("Book Populated");
        
        //Populating Librarian
        SQL="SELECT ID,PNAME,PHONE,ADDRESS,SALARY FROM PERSON INNER JOIN LIBRARIAN ON ID=L_ID";
        rs=stmt.executeQuery(SQL);
        if(!rs.next())
        {
           System.out.println("No Librarian Found in Library"); 
        }
        else
        {
            do
            {
                int id=rs.getInt("ID");
                String lname=rs.getString("PNAME");
                String adrs=rs.getString("ADDRESS"); 
                int phn=rs.getInt("PHONE");
                double sal=rs.getDouble("SALARY");
                librarians l= new librarians(id,lname,adrs,phn,sal);

                lib.addLibrarian(l);
                
            }while(rs.next());
       
        }
        System.out.println("Librarian Populated");
        
        //Populating Borrowers
        
        SQL="SELECT ID,PNAME,PHONE,ADDRESS FROM PERSON INNER JOIN BORROWER ON ID=B_ID";
        
        rs=stmt.executeQuery(SQL);
                  
        if(!rs.next())
        {
           System.out.println("No Borrower Found in Library"); 
        }
        else
        {
            do
            {
                    int id=rs.getInt("ID");
                    String name=rs.getString("PNAME");
                    String adrs=rs.getString("ADDRESS"); 
                    int phn=rs.getInt("PHONE"); 
                    
                    Borrower b= new Borrower(id,name,adrs,phn);
                    lib.addBorrowerDB(b);
                                            
            }while(rs.next());
                            
        }
        System.out.println("Borrower Populated");
        
        
      //Populating Loan
        SQL ="SELECT * FROM LOAN";
        rs=stmt.executeQuery(SQL);
        if(!rs.next()) 
        {
        	System.out.println("No Loans ");
        }
        else
        {
        	do
        	{
        		int borid=rs.getInt("BORROWER");
                int bokid=rs.getInt("BOOK");
                Date idate=new Date (rs.getTimestamp("ISS_DATE").getTime());
                Date rdate;
                boolean rStatus = rs.getBoolean("rStatus");
                
                boolean fineStatus = rs.getBoolean("FINE_PAID");
                Borrower bb = null;
                
                if(rStatus == true)
                	rdate=new Date (rs.getTimestamp("RET_DATE").getTime()); 
                else
                	rdate = null;
                
                for(int i=0;i<lib.getPersons().size();i++)
                {
                    if(lib.getPersons().get(i).getID()==borid)
                    {
                        bb=(Borrower)(lib.getPersons().get(i));
                        break;
                    }
                }
                
                ArrayList<Book> books = lib.getBooks();
                for(int k=0;k<books.size();k++)
                {
                    if(books.get(k).getID()==bokid)
                    {   
                      //System.out.println(k+" "+rStatus);
                      Loan l = new Loan(bb,books.get(k),idate,rdate,fineStatus,rStatus);
                      lib.addLoan(l);
                      break;
                    }
                }
                
        	}while(rs.next());
        }
        System.out.println("Loan Populated");
        
        
     // Borrowed Books
        SQL="SELECT ID,BOOK FROM PERSON INNER JOIN BORROWER ON ID=B_ID INNER JOIN BORROWER_BOOK ON B_ID=BORROWER ";
        
        rs=stmt.executeQuery(SQL);
                  
        if(!rs.next())
        {
           System.out.println("No Borrower has borrowed yet from Library"); 
        }
        else
        {
        	do
        	{
        		int id=rs.getInt("ID");      // borrower
                int bid=rs.getInt("BOOK");   // book
                
                Borrower bb=null;
                
                for(int i=0;i<lib.getPersons().size();i++)
                {
                    if(lib.getPersons().get(i).getClass().getSimpleName().equals("Borrower"))
                    {
                        if(lib.getPersons().get(i).getID()==id)
                        {
                            bb=(Borrower)(lib.getPersons().get(i));
                            break;
                        }
                    }
                }
                
                ArrayList<Loan> books = lib.getLoans();
                
                for(int i=0;i<books.size();i++)
                {
                    if(books.get(i).getBook().getID()==bid )
                    {  
                      Loan bBook= new Loan(bb,books.get(i).getBook(),books.get(i).getIssuedDate(),null,books.get(i).getFineStatus(),books.get(i).getReturnStatus());
                      bb.addBorrowedBook(bBook);
                      break;
                    }
                }
        	}while(rs.next());
        }
        System.out.println("Borrowed Book Populated");
        
        
        

        ArrayList<Person> persons = lib.getPersons();
        //Setting Person ID Count 
        int max=0;
        
        for(int i=0;i<persons.size();i++)
        {
            if (max < persons.get(i).getID())
                max=persons.get(i).getID();
        }

        Person.setIDCount(max);
        
	}
	
	
	// Filling Changes back to Database
	public void fillBack(Connection con) throws Exception
	{
		//Loan Table Cleared
        String temp = "DELETE FROM LIBRARY.LOAN";
        PreparedStatement stmts = con.prepareStatement(temp);
        stmts.executeUpdate();
                    
        //Borrowed Books Table Cleared
        temp = "DELETE FROM LIBRARY.BORROWER_BOOK";
        stmts = con.prepareStatement(temp);
        stmts.executeUpdate();
        
        //Books Table Cleared
        temp = "DELETE FROM LIBRARY.BOOK";
        stmts = con.prepareStatement(temp);
        stmts.executeUpdate();
        
        //Librarian Table Cleared
        temp = "DELETE FROM LIBRARY.LIBRARIAN";
        stmts = con.prepareStatement(temp);
        stmts.executeUpdate();
        
        //Borrower Table Cleared
        temp = "DELETE FROM LIBRARY.BORROWER";
        stmts = con.prepareStatement(temp);
        stmts.executeUpdate();
        
        //Person Table Cleared
        temp = "DELETE FROM LIBRARY.PERSON";
        stmts = con.prepareStatement(temp);
        stmts.executeUpdate();
        
        
        //Filling Person Table
        for(int i=0;i<lib.getPersons().size();i++)
        {
            temp = "INSERT INTO LIBRARY.PERSON (ID,PNAME,PHONE,ADDRESS) values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(temp);
            
            stmt.setInt(1, lib.getPersons().get(i).getID());
            stmt.setString(2, lib.getPersons().get(i).getName());
            stmt.setInt(3, lib.getPersons().get(i).getPhoneNumber());
            stmt.setString(4, lib.getPersons().get(i).getAddress());
            
            
            stmt.executeUpdate();
        }
        
        
        //Filling Borrowers Table
        for(int i=0;i<lib.getPersons().size();i++)
        {
            if (lib.getPersons().get(i).getClass().getSimpleName().equals("Borrower"))
            {
                temp = "INSERT INTO LIBRARY.BORROWER(B_ID) values (?)";
                PreparedStatement stmt = con.prepareStatement(temp);

                stmt.setInt(1, lib.getPersons().get(i).getID());

                stmt.executeUpdate();    
            }
        }
        
      //Filling librarian Table
        for(int i=0;i<lib.getPersons().size();i++)
        {
            if (lib.getPersons().get(i).getClass().getSimpleName().equals("librarians"))
            {
                temp = "INSERT INTO LIBRARY.LIBRARIAN(L_ID,SALARY) values (?,?)";
                PreparedStatement stmt = con.prepareStatement(temp);

                stmt.setInt(1, lib.getPersons().get(i).getID());
                stmt.setDouble(2, ((librarians)(lib.getPersons().get(i))).getSal());
                stmt.executeUpdate();    
            }
        }
        
        ArrayList<Book> books = lib.getBooks();
        //Filling Books Table
        for(int i=0;i<books.size();i++)
        {
            temp = "INSERT INTO LIBRARY.BOOK (ID,TITLE,AUTHOR,IS_ISSUED) values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(temp);
            
            stmt.setInt(1,books.get(i).getID());
            stmt.setString(2,books.get(i).getTitle());
            stmt.setString(3, books.get(i).getAuthor());
            stmt.setBoolean(4, books.get(i).getIssuedStatus());
            stmt.executeUpdate();
            
        }
        System.out.println("Books filled");
        
        ArrayList<Loan> loans = lib.getLoans();
       
        
        
      //Filling Loan Table
        for(int i=0;i<loans.size();i++)
        {
        	
            temp = "INSERT INTO LIBRARY.LOAN(LN_ID,BORROWER,BOOK,ISS_DATE,RET_DATE,FINE_PAID,rStatus) values (?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(temp);
            stmt.setInt(1,i+1);
            stmt.setInt(2,loans.get(i).getBorrower().getID());
            stmt.setInt(3,loans.get(i).getBook().getID());
            stmt.setBoolean(6,loans.get(i).getFineStatus());
            stmt.setTimestamp(4,new java.sql.Timestamp(loans.get(i).getIssuedDate().getTime()));
            stmt.setBoolean(7, loans.get(i).getReturnStatus());
            
            if(loans.get(i).getReturnStatus() == true)
            {
                stmt.setTimestamp(5,new java.sql.Timestamp(loans.get(i).getReturnDate().getTime()));
                //System.out.println("1");
            }
            else
            {
            	stmt.setNull(5,Types.INTEGER);
            	//System.out.println("2");
            }
                
            try {
            stmt.executeUpdate();
            }
            catch(Exception ex)
            {
            	System.out.println(ex);
            }
   
        }
        System.out.println("Loan Filled");
        
      
        
        
        //Filling Borrowed books table
          for(int i=0;i<lib.getBooks().size();i++)
          {
              if(lib.getBooks().get(i).getIssuedStatus()==true)
              {
            	  
                  for(int j=0;j<loans.size();j++)
                  {
                      if(lib.getBooks().get(i).getID()==loans.get(j).getBook().getID())
                      {
                    	
                          if(loans.get(j).getReturnStatus()==false)
                          {
                        	
                            temp = "INSERT INTO LIBRARY.BORROWER_BOOK(BOOK,BORROWER) values (?,?)";
                            PreparedStatement stmt = con.prepareStatement(temp);
                            stmt.setInt(1,loans.get(j).getBook().getID());
                            stmt.setInt(2,loans.get(j).getBorrower().getID());
                           
                            try {
                            stmt.executeUpdate();
                            }
                            catch(Exception ex)
                            {
                            	System.out.println(ex);
                            }
                            break;
                          }
                      }
                      
                  }
                  
              }
          }   
          System.out.println("Borrowed books filled");
        
        
	}
	//Filling Done
	
	
}
