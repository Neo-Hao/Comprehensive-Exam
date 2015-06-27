import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/*
 * RandomWord.java
 * CSCI 1301
 * 
 * The purpose of this class is to provide
 * a method to return a pseudorandomly generated 
 * word from a website, and it is intended for use
 * in a programming project.  This class requires an 
 * internet connection.  Do not modify any source code 
 * in this file.  
 */
public class RandomWord {
	
	//The number of times the method newWord() has been called
	private static int numberOfMethodCalls = 0;

	//The maximum number of times the method newWord() can be called
	private static final int MAX_CALLS = 25;

	//The delay (in milliseconds) to retrieve a new word from a website 
	private static final int DELAY = 25;

	/*
	 * Return a pseudorandomly generated word from a website.
	 * 
	 * This method requires an internet connection, and it can
	 * be called at most MAX_CALLS times.  If it is called more
	 * than MAX_CALLS, then it will display an error message 
	 * and terminate. 
	 */
	public static String newWord()
	{
		String myWord = "";
		URLConnection connection;
		
		//Check that the number of method calls has not exceeded MAX_CALLS
		numberOfMethodCalls++;
		if(numberOfMethodCalls > MAX_CALLS){
			System.out.println("Error:  Your program cannot call RandomWord.newWord() more than " + MAX_CALLS + " times.");
			System.out.println("Goodbye!");
			System.exit(0);
		}
		
		//Retrieve a pseudorandomly generated word from a website
		try {
			while(myWord.length() < 4 || myWord.length() > 9)
			{
				Thread.sleep(DELAY);
				connection = new URL("http://randomword.setgetgo.com/get.php").openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				myWord = scanner.nextLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		return myWord.toLowerCase().trim();
	}
}
