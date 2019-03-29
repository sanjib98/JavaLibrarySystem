package lib_1;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class libraryWorks {

	private String name;		//name of library
	private ArrayList <Person> persons;        // all librarians and borrowers  
    private ArrayList <Book> books;            // all books 
    public ArrayList <Loan> loans;			   // all loans
    
    public int deadline;
    public double fine;
    
    private static libraryWorks lw;
    
	public static libraryWorks getInstance() {
		
		if(lw==null)
		{
			lw = new libraryWorks();
		}
		
		return lw;
	}
	
	private libraryWorks()
	{
		name = null;
		persons = new ArrayList<Person>();
		books = new ArrayList<Book>();
		loans = new ArrayList<Loan>();
	}
	
	
	public void setReturnDate(int deadline)
	{
		this.deadline = deadline;
	}
	
	public void setFine(int fine)
	{
		this.fine = fine;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public ArrayList<Person> getPersons()
    {
        return persons;
    }
	
	public ArrayList<Book> getBooks()
    {
        return books;
    }

	public void addBorrower(Borrower b)
    {
        persons.add(b);
    }
	
	public void addLibrarians(librarians l)
	{
		persons.add(l);
	}
	
	public void addLoan(Loan ln)
    {
        loans.add(ln);
    }
	public void changeIssuedStatus(int id)
	{
		int i=0;
		for(i=0;i<books.size();i++)
		{
			if(books.get(i).getID() == id)
				books.get(i).setIssuedStatus(true);
		}
	}
	public void changeLoanStatus(int bor, int bk)
	{
		int i =0;
		for(i=0;i<loans.size();i++)
		{
			if(loans.get(i).getBorrower().getID() == bor && loans.get(i).getBook().getID() == bk)
			{
				loans.get(i).setReturnStatus(true);
				loans.get(i).setReturnedDate(new Date());
			}
		}
	}
	
	
	public ArrayList<Person> findAllBorrowers()
	{
		/*int i=0,borcount=0;
		for(i=0;i<persons.size();i++)
		{
			if(persons.get(i).getClass().getSimpleName().equals("Borrower"))
			{
				borcount+=1;
				Borrower br= (Borrower)(persons.get(i));
				System.out.println("Borrowers ID: "+br.getID()+" Name: "+br.getName());
			}
		}
		if(borcount == 0)
			System.out.println("No Borrowers");*/
		return persons;
	}
	
	
	
	public Borrower findBorrower()
	{
		System.out.println("Enter borrower's ID: ");
		Scanner sc = new Scanner(System.in);
		int id = 0;
		id = sc.nextInt();
		
		for (int i = 0; i < persons.size(); i++)
        {
            if (persons.get(i).getID() == id && persons.get(i).getClass().getSimpleName().equals("Borrower"))
                return (Borrower)(persons.get(i));
        }
		
		System.out.println("This ID did not match any borrower ");
		return null;
	}
	
	public Borrower findParticularBorrower(int id)
	{
		Borrower bor = null;
		for (int i = 0; i < persons.size(); i++)
        {
            if (persons.get(i).getID() == id && persons.get(i).getClass().getSimpleName().equals("Borrower"))
                bor= (Borrower)(persons.get(i));
        }
		return bor;
	}
	
	public librarians getlibrarians()
	{
		System.out.println("Enter librarians's ID: ");
		Scanner sc = new Scanner(System.in);
		int id = 0;
		id = sc.nextInt();
		
		for (int i = 0; i < persons.size(); i++)
        {
            if (persons.get(i).getID() == id && persons.get(i).getClass().getSimpleName().equals("librarians"))
                return (librarians)(persons.get(i));
        }
		
		System.out.println("This ID did not match any librarian ");
		return null;
	}
	
	public void addBook(Book b)
	{
		books.add(b);
	}
	
	
	public void removeBook(Book b)
	{
		int i,j,flag=0; 	
		for(i=0;i<persons.size();i++)
		{
			if(persons.get(i).getClass().getSimpleName().equals("Borrower"))
			{
				ArrayList<Loan> borBooks = ((Borrower)(persons.get(i))).getBorrowedBooks();
				
				for(j=0;j<borBooks.size();j++)
				{
					if(borBooks.get(j).getBook() == b)
					{
						flag = 1;
						System.out.println("This book is currently borrowed. Cannot be deleted. ");
						break;
					}
				}
			}
			if(flag == 1)
				break;
		}
		
		if(flag == 0)
		{
			books.remove(b);
			System.out.println("Book deleted successfully ");
		}
		
	}
	
	public ArrayList<Book> searchForBooks()
	{
		int choice,i;
		String search="";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice \n 1. Search by Author  2. Search by title 3. Show All ");
		choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			System.out.println("Enter Author name: ");
			search=sc.next();
			break;
		case 2:
			System.out.println("Enter Title ");
			search=sc.next();
			break;
		case 3:
			break;
		default:
			System.out.println("Wrong Input");
			
		}
		
		ArrayList<Book> matchedBooks = new ArrayList<Book>();
		
		for(i=0;i<books.size();i++)
		{
			Book b = books.get(i);
			
			if(choice == 1)
			{
				if (b.getAuthor().equals(search))
                    matchedBooks.add(b);
			}
			else if(choice == 2)
			{
				if (b.getTitle().equals(search))
                    matchedBooks.add(b);
			}
			else if(choice == 3)
			{
				matchedBooks.add(b);
			}
			
		}
		
		if(!matchedBooks.isEmpty())
		{
			System.out.println("Books found are ");
			for (i = 0; i < matchedBooks.size(); i++)
            {                      
                System.out.print(i + "-" + "\t\t");
                matchedBooks.get(i).printInfo();
            }
			return matchedBooks;
		}
		else
		{
			System.out.println("No Books were found ");
			return null;
		}
	}
	
	
	public ArrayList<Book> viewAllBooks()
    {  
        return books;
    }
	
	public ArrayList<Book> getAllBooks()
	{
		if (!books.isEmpty())
        { 
			ArrayList<Book> matchedBooks = new ArrayList<Book>();
            for (int i = 0; i < books.size(); i++)
            {                      
                matchedBooks.add(books.get(i));
            }
            return matchedBooks;
        }
		else
			return null;
	}
	
	public double computeFine2(Borrower borrower)
    {
        
        double totalFine = 0;        
        double per_loan_fine = 0;
        
        for (int i = 0; i < loans.size(); i++)
        {
            Loan l = loans.get(i);
            
            if ((l.getBorrower() == borrower))
            {
                per_loan_fine = l.computeFine();
                System.out.print(i + " - "+ loans.get(i).getBook().getTitle() + " " + loans.get(i).getBorrower().getName() + " " + loans.get(i).getIssuedDate() +  " " + loans.get(i).getReturnDate() + " " + per_loan_fine);                
                
                totalFine += per_loan_fine;
            }            
        }
        
        return totalFine;
    }
	
	//History of books
	 public ArrayList<Loan> viewHistory()
	    {
	        return loans;
	    }
	 
	 public void createBook(String title, String author)
	 {
		 Book b = new Book(-1,title,author,false);	
		 addBook(b);
		 System.out.println("Book with title: "+b.getTitle()+" is created");
		 
	 }
	 
	 
	 public String createBorrower(String n,String addr, int cont)		//p= person is borrower/librarian
	 {
		 Scanner sc = new Scanner(System.in);
		 /*System.out.println("Enter Name: ");
		 String n=sc.nextLine();
		 
		 System.out.println("Enter Address: ");
		 String addr=sc.nextLine();
		 
		 System.out.println("Enter Phone No: ");
		 int cont=sc.nextInt();*/
		 String res="";
		 Borrower b = new Borrower(-1,n,addr,cont);
		 addBorrower(b);
			 
		 //System.out.println("Borrower "+ b.getName()+" is created");
		 res= "Borrower "+ b.getName()+" is created";
		 
		 return res;
	 }
	 
	 public String createLibrarian(String n,String addr, int cont, double salary)
	 {
		 librarians lb = new librarians(-1,n,addr,cont,salary);
         addLibrarians(lb);
         
         //System.out.println("Librarian "+ lb.getName()+" is created");
         String res= "Librarian "+ lb.getName()+" is created";
         return res;
	 }
	 
	 //For DB
	 public void addBookinLibrary(Book b)
	 {
		 books.add(b);
	 }
	 public void addLibrarian(librarians l)
	 {
		 persons.add(l);
	 }
	 public void addBorrowerDB(Borrower b)
	 {
		 persons.add(b);
	 }
	 public ArrayList<Loan> getLoans()
	 {
		 return loans;
	 }
}
