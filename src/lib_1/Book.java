package lib_1;

import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Book {

	
	private int bookID;           // ID given by a library to a book to make it distinguishable from other books
	private String title;         // Title of a book 
	//private String subject;       // Subject to which a book is related!
	private String author;        // Author of book!
    private boolean isIssued;        // this will be true if the book is currently issued to some borrower.
    static int currentIdNumber = 0; 
    
    public Book(int id,String t, String a, boolean issued) {
		 
    	currentIdNumber++;
        if(id==-1)
        {
            bookID = currentIdNumber;
        }
        else
            bookID=id;
        
        title = t;
        //subject = s;
        author = a;
        isIssued = issued;
    	
	}
    
    public void printInfo()
    {
        System.out.println(title + " " + author);
    }

    

    public String getTitle()
    {
        return title;
    }


    public String getAuthor()
    {
        return author;
    }
    
    public boolean getIssuedStatus()
    {
        return isIssued;
    }
    
    public void setIssuedStatus(boolean s)
    {
        isIssued = s;
    }
    
     public int getID()
    {
        return bookID;
    }
    
    public static void setIDCount(int n)
    {
        currentIdNumber = n;
    }

    public void changeBookInfo()
    {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Update Author (y/n)");
    	String ip=sc.next();
    	if(ip.equals("y"))
    	{
    		System.out.println("Enter Author's name: ");
    		author=sc.next();
    	}
    	
    	
    	System.out.println("Update Title (y/n)");
    	ip=sc.next();
    	if(ip.equals("y"))
    	{
    		System.out.println("Enter new Title: ");
    		title=sc.next();
    	}
    	
    	System.out.println("Book Updated");
    }
    
    //Issuing a book
    public void issueBook(Borrower bor)
    {
    	Date dt = new Date();
    	
    	if(isIssued)
    	{
    		System.out.println("Book "+title+" is already issued");
    	}
    	else
    	{
    		setIssuedStatus(true);
    		
    		Loan newIssue = new Loan(bor, this, dt, null, false, false);
    		
    		libraryWorks.getInstance().addLoan(newIssue);
    		bor.addBorrowedBook(newIssue);
    		//System.out.println("k");
    	}
    }

    //To return a book
    public void returnBook(Borrower bor, Loan ln)
    {
    	ln.getBook().setIssuedStatus(false);
    	ln.setReturnedDate(new Date());
    	ln.setReturnStatus(true);
    	//System.out.println("return3  "+ln.getReturnStatus());
    	//System.out.println("return3  "+ln.getReturnDate());
    	ln.payFine();
    	
    	bor.removeBorrowedBook(ln);
    	
    	System.out.println("Book "+ln.getBook().getTitle()+ " is returned successfully");
    	
    	
    }
    

}
