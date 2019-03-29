package lib_1;

public abstract class Person {

	protected int id;           // ID of every person related to library
    //protected String password;  // Password of every person related to library
    protected String name;      // Name of every person related to library
    protected String addr;   // Address of every person related to library
    protected int cont;      // PhoneNo of every person related to library
    
    static int currentIdNumber = 0;     //This will be unique for every person, since it will be incremented when everytime
                                       //when a person is created

    public Person(int dd, String name, String addr, int cont)   // para cons.
    {
        currentIdNumber++;
        
        if(dd==-1)
        {
            id = currentIdNumber;
        }
        else
            id = dd;
        
        //password = Integer.toString(id);
        this.name = name;
        this.addr = addr;
        this.cont = cont;
    }        
    
    // Printing Info of a Person
    public void printInfo()
    {
        System.out.println("The details are: ");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + addr);
        System.out.println("Phone No: " + cont + "\n");
    }
    
    /*---------Setter FUNCs.---------*/
    public void setAddress(String addr)
    {
        this.addr = addr;
    }
    
    public void setPhone(int cont)
    {
        this.cont = cont;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    /*----------------------------*/
    
    /*-------Getter FUNCs.--------*/
    public String getName()
    {
        return name;
    }
    
   /* public String getPassword()
    {
        return password;
    }*/
    
     public String getAddress()
    {
        return addr;
    }
     
     public int getPhoneNumber()
    {
        return cont;
    }
    public int getID()
    {
        return id;
    }
    /*---------------------------*/
    
     public static void setIDCount(int n)
    {
        currentIdNumber=n;
    }

}
