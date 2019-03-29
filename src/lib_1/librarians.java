package lib_1;

public class librarians extends Person {

	protected double sal;
	
	public librarians(int dd, String name, String addr, int cont, double sal) {
		super(dd, name, addr, cont);

		this.sal=sal;
		
	}

	public void printInfo() 
	{
		super.printInfo();
		System.out.println("Salary: "+sal);
	}
	
	public double getSal()
	{
		return sal;
	}
}
