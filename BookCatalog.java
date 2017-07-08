/*Nina Nguyen
 * CSC 143 
 * March 14, 2017
 * Assignment 4
 */

package Assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * @author Nina
 * CSC 143
 * Assignment 4
 * 3/18/2017
 *
 */
public class BookCatalog {
	private LinkedList<Book> list = new LinkedList<Book>();

	//PrintStream out = new PrintStream(new File (fileName));
	
	/**
	 * @param book
	 * @throws FileNotFoundException
	 */
	public BookCatalog(File book) throws FileNotFoundException {
		Scanner in = new Scanner(book);
		while(in.hasNextLine()){
			String code = in.next();
			String last = in.next();
			String first = in.next();
		
			String title = "";
			while (!in.hasNextInt()){
				title = title + " " + in.next();//Add space to title
			}
			int year = 0;
			while(in.hasNextInt()){
				year = in.nextInt();
			}
			double price = 0.0;
			while(in.hasNextDouble()){
				price = in.nextDouble();
			}
			in.nextLine();
			Book add = new Book(code, last, first, title, year, price);
			list.addLast(add);
		}	
	}
	

	/**
	 * @param toAdd
	 *Add a new book to text file
	 */
	public void addBook(Book toAdd){//Add book to catalog
		if (toAdd.getLast().length() <= 0 || toAdd.getFirst().length() <= 0 || toAdd.getTitle().length() <=0){
			System.out.println("Name and Title can't be empty.");
			//return;
		}
		for (Book book:list){
			String tempCode = book.getCode().replaceAll("-", "");
			String tempInput = toAdd.getCode().replaceAll("-", "");
			if (tempCode.equalsIgnoreCase(tempInput)){//Check for duplicate ISBN
				System.out.println("This is a duplicate.");
				return;
			} else {
				System.out.println("This book was added to the catalog.");
				list.addLast(toAdd);
			}
		}
	}
	
	//Test to make sure name is correct so the user doesn't have to input everything again
	/**
	 * @param name
	 * @return
	 *
	 */
	public boolean isNamevalid (String name){
		if (Character.isDigit(name.charAt(0))){
			System.out.println("The first/last name is invalid.");
			System.out.println("Please try again");
			return false;
		}
		return true;
	}
	
	/**
	 * @param userInput
	 * Delete a book from catalog
	 */
	public void deleteBook(String userInput){//delete a book from the catalog
		String tempInput = userInput.replaceAll("-", "");
	
		for (Book book: list){
		String tempCode = book.getCode().replaceAll("-", "");
			if(tempCode.equals(tempInput)){
				System.out.println("This book will be removed.");
				list.remove(book);
				return;
			} 
		}
		
		System.out.println("This book is not in the catalog.");
	}
	
	/**
	 * @param userInput
	 * Find a book in the catalog
	 */
	public void findBook(String userInput){//Find a book from the catalog
		String tempUser = userInput.replaceAll("-", "");//remove "-"
		for (Book book:list){
			String tempCode = book.getCode().replaceAll("-", "");//remove "-"
			if(tempCode.equals(tempUser) ||
					book.getLast().equalsIgnoreCase(userInput) || book.getFirst().equalsIgnoreCase(userInput)){//Ignore Case
						System.out.println("The book you're looking for is:");
						System.out.println(book);
						return;
			} 
		}
		System.out.println("It doesn't look like we have this book in our catalog.");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		Iterator<Book> i = list.iterator();
		String fullList = "";
		while (i.hasNext()){
			fullList += i.next() + "\n";
		}
		return fullList;
	}
	
	
	/**
	 * @param code
	 * @return
	 * Check to see if ISBN code is valid
	 */
	public boolean isCodeValid(String code) {
		if(code.contains("--") || code.contains("---")){
			System.out.println("The ISBN has consecutive '-'");
			return false;
		}
		
		if(code.startsWith("-") || code.endsWith("-")){//Check to see if code starts or end with "-"
			System.out.println("The ISBN starts or end with '-'");
			return false;
		}
		
		int count = 0;
		for (int j = 0; j < code.length(); j++){//Count # of '-' that appears 
			if (code.charAt(j) == '-'){
				count++;	
			}
		}
		//Short-Circuit evaluation if i don't do nested "if" loops
		if ((count != 0 )){//return false if '-' does not equal to 0 or 3
			if(count != 3){
			System.out.println("The ISBN does not have 0 or 3 dashes");
			return false;
			}
		}
		
		String temp = code.replaceAll("-", "");//remove "-"
		int total = 0;
		
		if(temp.length() != 10){
			System.out.println("The ISBN does not have 10 digits");
			return false;
		}
		
		for (int i = 0; i < temp.length()-1; i++){
			int x = i + 1;//add 1 to remove 0 indexing
			String s = temp.substring(i, i+1);
			int y = Integer.parseInt(s);//Turn string at index to int
			total = total + (x*y);//sum up element*indexes
		}
		int valid = total%11;//last digit of ISBN
		
		String temp2 = temp.toUpperCase();
		temp2 = temp2.substring(temp2.length()-1);
		if (temp2.equals("X")){
			temp2 = temp2.replace("X", "10");//change to "10" if ISBN ends with an "X"
		}
		int last = Integer.parseInt(temp2);
		if (valid != last){//compare the correct checksum with the last digit
			System.out.println("The ISBN have the wrong checksum");
			return false;
		}
		return true;//pass all tests
	}
}
