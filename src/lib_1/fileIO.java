package lib_1;
import java.io.*;

public class fileIO {

	public static void printReport(String[] allBooks) throws IOException
	{
		int i = 0;
		File f = new File("Reports.txt");
		BufferedWriter fs = new BufferedWriter(new FileWriter(f,false));  //true = append, false = overwrite
		String headings="Title        Author";
		if(f.exists())
		{
			fs.write(headings);
			for(i=0;i<allBooks.length;i++)
			{
				fs.newLine();
				fs.write(allBooks[i]);
			}
			System.out.println("Reports Generated Successfully");
		}
		else
			System.out.println("File error");
		fs.close();
		
	}
}
