package lib_1;

import java.util.*;

public class Borrower extends Person {

	private ArrayList<Loan> borrowedBooks;          //Those books which are currently borrowed by this borrower
	
	public Borrower(int dd, String name, String addr, int cont) {
		super(dd, name, addr, cont);

		borrowedBooks = new ArrayList<Loan>();
		
	}

	//To print borrowers info
	public void printInfo()
    {
        super.printInfo();
               
        printBorrowedBooks();
    }
	
	public void printBorrowedBooks()
	{
		if(!borrowedBooks.isEmpty())
		{
			System.out.println("Borrowed Books are: ");
			
			
			for (int i = 0; i < borrowedBooks.size(); i++)
            {                      
                System.out.print(i + "-" + "\t\t");
                borrowedBooks.get(i).getBook().printInfo();
                System.out.println();
            }
        }
        else
        {
            System.out.println("No borrowed books."); 
        }
	}
	
	//To add and remove borrowed books
	public void addBorrowedBook(Loan iBook)
    {
        borrowedBooks.add(iBook);
    }
    
    public void removeBorrowedBook(Loan iBook)
    {
        borrowedBooks.remove(iBook);
    } 
	
    public ArrayList<Loan> getBorrowedBooks()
    {
        return borrowedBooks;
    }
	
    
}

