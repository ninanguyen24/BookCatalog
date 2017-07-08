package Assignment4;

import java.io.*;
import java.util.Scanner;

/**
 * @author Nina Nguyen
 * CSC 143
 * Assignemnt 4
 *3/18/2017
 */
public class BookCatalogClient {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Welcome to the book catalog!");
		System.out.print("Please enter a catalog name: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.next();
		File f = new File(fileName);//get booklist text file
	
		while (!f.exists()){//If text file does not exist, it will create a new one.
			System.out.println("File does not exist. A new one will be created.");
			f.createNewFile();
		}
		BookCatalog catalog = new BookCatalog(f);
		PrintStream out = new PrintStream(new File("test.txt"));//save output to a different file, testing purposes
		//BookCatalog catalog = new BookCatalog(f);//Might need to create a temp so when they don't want to save you can print that
		
		boolean save = false;
		while(!save){
			boolean keepProgramRunning = false;
			while (!keepProgramRunning){
			intro();
			userConsole(catalog, out);
			System.out.println("Do you want go again? yes/no");
			String keepGoing = in.next();
			if (keepGoing.equalsIgnoreCase("yes")){
				keepProgramRunning = false;
			} else {
				keepProgramRunning = true;
			}
			}
			System.out.println("Do you want to save? yes/no");
			String answer = in.next();
			if (answer.equalsIgnoreCase("yes")){
				System.out.println("The catalog was saved.");
				//catalog.toString();
				save = true;
			} else {
				System.out.println("The file was not saved.");
				save = true;
			}
		}
	}
	
	/**
	 * @param catalog
	 * @param out
	 * Interact with user to see what they want
	 */
	public static void userConsole(BookCatalog catalog, PrintStream out){//Needs work
		
		Scanner console = new Scanner(System.in);
		int userInput = console.nextInt();
		
		if(userInput == 1){
			System.out.println("To find a book, please enter the author's last name, first name, or ISBN.");
			String findInput = console.next();
			catalog.findBook(findInput);
		} else if (userInput == 2){
			System.out.println("What is the ISBN?");
			String code = console.next();
			while (!catalog.isCodeValid(code)){//run until valid ISBN is entered
				System.out.println("Please try again.");
				code = console.next();
			}
			System.out.println("What is the author's last name?");
			String last = console.next();
			while (!catalog.isNamevalid(last)){
				last = console.next();
			}
			System.out.println("What is the author's first name?");
			String first = console.next();
			while (!catalog.isNamevalid(first)){
				first = console.next();
			}
			System.out.println("What is the book's title?");
			console.nextLine();
			String title = console.nextLine();
			System.out.println("What is the published year?");
			int year = console.nextInt();
			while (year >= 2013){//Year can't be later than 2013. Didn't create method since there's no repetiveness
				System.out.println("This is an invalid year. It can't be greater than 2013.");
				System.out.println("Please try again.");
				year = console.nextInt();
			} 
			System.out.println("What is the price of the book?");
			double price = console.nextDouble();
			while (price <= 0){//price can't be 0 or negative. Didn't create method since there's no repetiveness
				System.out.println("Invalid price. Cannot be 0 or negative.");
				System.out.println("Please try again");
				price = console.nextDouble();
			} 
			Book toAdd = new Book(code, last, first, title, year, price);
			catalog.addBook(toAdd);
			out.println(toAdd.toString());
			
		} else if (userInput == 3){
			System.out.println("What is the ISBN of the book you want to delete?");
			String deleteInput = console.next();
			deleteInput = deleteInput.replaceAll("-", "");
			catalog.deleteBook(deleteInput);
			out.println(catalog.toString());
		} else {
			System.out.println("Sorry, I did not recognize the input. Please try again.");
		}
		
	}
	
	/**
	 * Intro
	 */
	public static void intro(){
		System.out.println("A few things you can do with this program is: Add a book, delete a book, or find a book.");
		System.out.println("What would you like to do?");
		System.out.println("Enter \"1\" to find a book.");
		System.out.println("Enter \"2\" to add a book.");
		System.out.println("Enter \"3\" to delete a book.");
	}

}
