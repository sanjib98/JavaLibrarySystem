package lib_1;
import java.util.*;
import java.sql.*;

public class MAIN {

	public MAIN() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String anyKey;
		//For all functions
		libraryWorks lib = libraryWorks.getInstance();
		lib.setFine(10);
		lib.setName("My library");
		lib.setReturnDate(5);
		
		//For database
		libraryDB ldb = libraryDB.getInstance();
		
		
		// Making connection with Database.
        Connection con = ldb.makeConnection();
		
		if (con == null)    
        {
            System.out.println("Error connecting to Database.");
            return;
        }
		
		try
		{
			ldb.populateLibrary(con);
			boolean stop = false;
	        while(!stop)
	        {
	        	System.out.println("Welcome to Library Management System");
	        	System.out.println("Following Functionalities are available: ");
	        	System.out.println("1- Librarian");
	            System.out.println("2- Borrower");
	            System.out.println("3- Admininstrative Functions");
	            System.out.println("4- Exit");
	            
	            int choice = 0;
	            choice = sc.nextInt();
	            
	            if(choice == 1)
	            {
	            	System.out.println("\n\n Welcome to Librarian's Portal");
	            	while(true)
	            	{
	            		
		            	System.out.println("1- Search a Book");
	                    System.out.println("2- Check Personal Info of Borrower");
	                    System.out.println("3- Check Total Fine of Borrower");   
	                    System.out.println("4- Issue a Book");
	                    System.out.println("5- Return a Book");                        
	                    System.out.println("6- Renew a Book");
	                    System.out.println("7- Add a new Borrower");
	                    System.out.println("8- Add Book");
	                    System.out.println("9- All Borrowers");
	                    System.out.println("11- Generate Report");
	                    System.out.println("10- Exit");
	                    
	                    choice = sc.nextInt();
	                    if(choice == 10)
	                    	break;
	                    //Search a Book
	                    if (choice == 1)
	                    {
	                        lib.searchForBooks();
	                    }
	                    
	                    //Check Personal Info of Borrower
	                    else if(choice == 2)
	                    {
	                    	Borrower bor = lib.findBorrower();
	                    	
	                    	if(bor!=null)
	                            bor.printInfo();
	                    }
	                    //Check Total Fine of Borrower
	                    else if(choice == 3)
	                    {
	                    	Borrower bor = lib.findBorrower();
	                        
	                        if(bor!=null)
	                        {
	                            double totalFine = lib.computeFine2(bor);
	                            System.out.println("Your Total Fine is : Rs " + totalFine );                     
	                        }
	                    }
	                    
	                    //Issue Book
	                    else if(choice == 4)
	                    {
	                    	ArrayList<Book> books = lib.searchForBooks();
	                    	
	                    	if(books!=null)
	                    	{
	                    		System.out.println("Enter book no: ");
	                    		int ip = sc.nextInt();
	                    		Book b = books.get(ip);
	                    		System.out.println("Book being borrowed "+b.getAuthor()+" "+b.getTitle());
	                    		Borrower bor = lib.findBorrower();
	                    		if(bor!=null)
	                            {
	                    			if(b.getIssuedStatus() == false)
	                    			{
	                    				b.issueBook(bor); 
		                                lib.changeIssuedStatus(b.getID());
		                                System.out.println("Book Issued");
	                    			}
	                    			else
	                    				System.out.println("Book already borrowed ");
	                            }
	                    	}
	                    }
	                    
	                    //Return Book
	                    else if(choice == 5)
	                    {
	                    	Borrower bor = lib.findBorrower();
	                    	int i=0;
	                    	if(bor!=null)
	                    	{
	                    		bor.printBorrowedBooks();
	                            ArrayList<Loan> loans = bor.getBorrowedBooks();
	                            
	                            if (!loans.isEmpty())
	                            {
	                            	System.out.println("All loans of this borrower: ");
	                            	for(i=0;i<loans.size();i++)
	                            	{
	                            		System.out.println(i+" -   "+loans.get(i).getBorrower().getName()+" "+loans.get(i).getBook().getTitle());
	                            	}
	                            	System.out.println("Enter Loan number ");
	                                int ip = sc.nextInt();
	                                Loan l = loans.get(ip);
	                                //System.out.println("return1  "+l.getReturnStatus());
	                                l.getBook().returnBook(bor, l);
	                                //lib.loans.get(ip).setReturnStatus(true);
	                                //lib.loans.get(ip).setReturnedDate(new Date());
	                                lib.changeLoanStatus(l.getBorrower().getID(),l.getBook().getID());
	                                System.out.println(l.getBorrower().getID()+" "+l.getBook().getID());
	                                //System.out.println("Book Returned");
	                                System.out.println("return2  "+l.getReturnStatus());
	                            }
	                            else
	                                System.out.println("This borrower " + bor.getName() + " has no book to return.");
	                    	}
	                    }
	                    
	                    //Renew Book
	                    else if(choice == 6)
	                    {
	                    	int i=0;
	                    	Borrower bor = lib.findBorrower();
	                    	if(bor!=null)
	                        {
	                            bor.printBorrowedBooks();
	                            ArrayList<Loan> loans = bor.getBorrowedBooks();
	                            
	                            if (!loans.isEmpty())
	                            {
	                            	for(i=0;i<loans.size();i++)
	                            	{
	                            		System.out.println("All loans of this borrower: ");
	                            		System.out.println(i+" -   "+loans.get(i).getBorrower().getName()+" "+loans.get(i).getBook().getTitle());
	                            	}
	                            	System.out.println("Enter Loan number ");
	                            	int ip = sc.nextInt();
	             
	                                loans.get(ip).renewIssuedBook(new java.util.Date()); 
	                                System.out.println("Book Renewed");
	                            }
	                            else
	                                System.out.println("\nThis borrower " + bor.getName() + " has no issued book which can be renewed."); 
	                        }
	                    }
	                     
	                    //Add Borrower
	                    else if(choice == 7)
	                   	{
	                    	System.out.println("Enter Name: ");
                   		 	String name=sc.next();	
                   		 	//name = "sss";
                   		 	System.out.println("Enter Address: ");
                   		 	String addr=sc.next();
                   		 
                   		 	System.out.println("Enter Phone No: ");
                   		 	int cont=sc.nextInt();
	                   		String res = lib.createBorrower(name,addr,cont);
	                   		System.out.println(res);
	                   	}
	                    	
	                    //Add Book
	                    else if(choice == 8)
	                    {
	                    	System.out.println("Enter Title");
	                    	String t = sc.next();
	                    	System.out.println("Enter Author");
	                    	String a = sc.next();
	                    	lib.createBook(t, a);
	                    	
	                    }
	                    
	                    //All Borrowers
	                    else if(choice == 9) 
	                    {
	                    	ArrayList<Person> persons = lib.findAllBorrowers();
	                    	int i=0,borcount=0;
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
	                			System.out.println("No Borrowers");
	                    }
	                    
	                    //Report generation
	                    else if(choice == 11)
	                    {
	                    	
	                    	ArrayList<Book> books = lib.getAllBooks();
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
	                    	
	                    }
	                    System.out.println("Press any key to continue ");
	                    anyKey=sc.next();
	            	}
	            }	
	            
	            else if(choice == 2)
	            {
	            	System.out.println("\n\n Welcome to Borrower Portal");
	            	while(true)
	            	{
	            		System.out.println("1- Search a Book");
	                    System.out.println("2- Check Personal Info of Borrower");
	                    System.out.println("3- Check Total Fine of Borrower");   
	                    System.out.println("4- Exit");
	                    
	                    choice = sc.nextInt();
	                    
	                    //Search a Book
	                    if (choice == 1)
	                    {
	                        lib.searchForBooks();
	                    }
	                    
	                    //Check Personal Info of Borrower
	                    else if(choice == 2)
	                    {
	                    	Borrower bor = lib.findBorrower();
	                    	
	                    	if(bor!=null)
	                            bor.printInfo();
	                    }
	                    //Check Total Fine of Borrower
	                    else if(choice == 3)
	                    {
	                    	Borrower bor = lib.findBorrower();
	                        
	                        if(bor!=null)
	                        {
	                            double totalFine = lib.computeFine2(bor);
	                            System.out.println("Your Total Fine is : Rs " + totalFine );                     
	                        }
	                    }
	                    //Exit
	                    else if(choice == 4)
	                    	break;
	            	}
	            	
	            }
	            
	            
	            
	            
	            else if(choice == 3)
	            {
	            	
	            		while(true)
	            		{
	            			System.out.println("Welcome to Admin's Portal");
	            			
	                        System.out.println("1- Add Librarian"); 
	                        System.out.println("2- View Issued Books History");  
	                        System.out.println("3- View All Books in Library"); 
	                        System.out.println("4- Logout"); 
	                        
	                        choice = sc.nextInt();
	                        
	                        if(choice == 4)
	                        	break;
	                        else if (choice == 1)
	                        {
	                        	System.out.println("Enter Name: ");
	                   		 	String nm=sc.next();
	                   		 
	                   		 	System.out.println("Enter Address: ");
	                   		 	String addr=sc.next();
	                   		 
	                   		 	System.out.println("Enter Phone No: ");
	                   		 	int cont=sc.nextInt();
	                   		 	
	                   		 	System.out.println("Enter Salary: ");
	                   		 	double sal = sc.nextDouble();
	                        	String res = lib.createLibrarian(nm,addr,cont,sal);
	                        	System.out.println(res);
	                        }
	                        else if(choice == 2)
	                        {
	                        	ArrayList<Loan> loans = lib.viewHistory();
	                        	if (!loans.isEmpty())
	                	        { 
	                	            System.out.println("Issued Books are: ");
	                	            System.out.println("Book's Title \t Borrower's Name \t Issued Date \t Returned Date \t Fine Paid \t Return Status");
	                	            for (int i = 0; i < loans.size(); i++)
	                	            {    
	                	            	System.out.println(i + " - "+ loans.get(i).getBook().getTitle() + " " + loans.get(i).getBorrower().getName() + " " + loans.get(i).getIssuedDate() +  " " + loans.get(i).getReturnDate() + " " + loans.get(i).getFineStatus()
	                	            			+ " " + loans.get(i).getReturnStatus());
	                	            }
	                	        }
	                	        else
	                	            System.out.println("No issued books."); 
	                        }
	                        else if(choice == 3)
	                        {
	                        	ArrayList<Book> books = lib.viewAllBooks();
	                        	if (!books.isEmpty())
	                            { 
	                                System.out.println("Books are: ");
	                                
	                                for (int i = 0; i < books.size(); i++)
	                                {                      
	                                    System.out.print(i + "-" + "\t\t");
	                                    books.get(i).printInfo();
	                                }
	                            }
	                            else
	                                System.out.println("Currently Library has no books."); 
	                        }
	                        
	                        System.out.println("\n Press any key to continue ");
	                        anyKey=sc.next();
	            		}
	            	
	            }
	            
	            else
	            	stop = true;
	            
	        }
	        
	      //Loading back all the records in database
	      //ldb.fillBack(con);
		}
		catch(Exception e) {
			
		}
	}

}
