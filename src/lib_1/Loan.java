package lib_1;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class Loan {

	private Borrower borrower;      
    private Book book;
    
    //private Staff issuer;
    private Date issuedDate;
    
    private Date dateReturned;
    private boolean rStatus;   //return status of book
    
    private boolean finePaid;
       
    public Loan(Borrower bor, Book b, Date iDate, Date rDate, boolean fPaid, boolean rS)  // Para cons.
    {
        borrower = bor;
        book = b;
        //issuer = i;
        //receiver = r;
        issuedDate = iDate;
        dateReturned = rDate;
        rStatus = rS;
        finePaid = fPaid;
    }
    
    /*----- Getter FUNCs.------------*/
    
    public Book getBook()       //Returns the book
    {
        return book;
    }
    
    
    public Date getIssuedDate()     //Returns the date on which this particular book was issued
    {
        return issuedDate;
    } 

    public Date getReturnDate()     //Returns the date on which this particular book was returned
    {
        return dateReturned;
    }
    
    public Borrower getBorrower()   //Returns the Borrower to whom the book was issued
    {
        return borrower;
    }
    
    public boolean getFineStatus()  // Returns status of fine
    {
        return finePaid;
    }
    public boolean getReturnStatus()  // Returns if book returned or not
    {
        return rStatus;
    }
   
    
    
    /*Setter FUNCs*/
    public void setReturnedDate(Date dReturned)
    {
        dateReturned = dReturned;
    }
    
    public void setFineStatus(boolean fStatus)
    {
        finePaid = fStatus;
    }   
    
    public void setReturnStatus(boolean rS)
    {
    	rStatus = rS;
    }
    
   
    



    //Computes fine for a particular loan 
    public double computeFine()
    {

               
        double totalFine = 0;
        
        if (!finePaid)
        {    
            Date iDate = issuedDate;
            Date rDate = new Date();                

            long days =  ChronoUnit.DAYS.between(rDate.toInstant(), iDate.toInstant());        
            days=0-days;

            days = days - libraryWorks.getInstance().deadline;

            if(days>0)
                totalFine = days * libraryWorks.getInstance().fine;
            else
                totalFine=0;
        }
        return totalFine;
    }
    
    
    public void payFine()
    {
        //Computing Fine
        
        double totalFine = computeFine();
                
        if (totalFine > 0)
        {
            System.out.println("Total Fine: " + totalFine);

            System.out.println("Do you want to pay? (y/n)");
            
            Scanner input = new Scanner(System.in); 
            
            String choice = input.next();
            
            if(choice.equals("y") || choice.equals("Y"))
                finePaid = true; 
            
            if(choice.equals("n") || choice.equals("N"))
                finePaid = false; 
        }
        else
        {
            System.out.println("No fine is generated.");
            finePaid = true;
        }        
    }


    // Extending issued Date 
    public void renewIssuedBook(Date iDate)
    {        
        issuedDate = iDate;
        
        System.out.println("The deadline of the book " + getBook().getTitle() + " has been extended.");
        System.out.println("Issued Book is successfully renewed!");
    }

}
