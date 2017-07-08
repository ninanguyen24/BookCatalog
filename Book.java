/*Nina Nguyen
 * CSC 143 
 * March 14, 2017
 * Assignment 4
 */

package Assignment4;

public class Book {
	private String code;
	private String last;
	private String first;
	private String title;
	private int year;
	private double price;
	
	/**
	 * 
	 */
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param code
	 * @param last
	 * @param first
	 * @param title
	 * @param year
	 * @param price
	 */
	public Book(String code, String last, String first,String title,
				int year, double price){
		this.code = code;
		this.last = last;
		this.first = first;
		this.title = title;
		this.year = year;
		this.price = price;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return code + "\t" + last + "\t" + first + "\t" + title + "\t" + year + "\t" + price; 
	}
	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return
	 */
	public double getPrice() {
		return price;
	}

}
