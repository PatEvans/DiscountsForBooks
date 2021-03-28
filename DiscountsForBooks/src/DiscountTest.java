

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
	
	private ArrayList<Discount> discountsToApply=new ArrayList<Discount>();
	private Book mobyDick;
	private Book maxSim;
	private Book threeMen;
	private Book year2000;
	private Book price29;
	private Book expensiveBook;
	
	@BeforeEach
	public void setUp() {
		discountsToApply.add(new YearDiscount(0.9,2000));
		discountsToApply.add(new PriceDiscount(0.95,3000));
		mobyDick = new Book("Moby Dick","1851", "£15.20");
		maxSim = new Book("The Terrible Privacy of Maxwell Sim", "2010", "£13.14");
		threeMen = new Book("Three Men in a Boat", "1889" ,"£12.87");
		year2000 = new Book("Three Men in a Boat", "2000" ,"£12.87");
		price29 = new Book("Three Men in a Boat", "2000" ,"£29.99");
		expensiveBook = new Book("Three Men in a Boat", "2000" ,"£1101.05");
	}
	@Test
	@DisplayName("Single Book with No Discount Should apply 0% discount")
	public void singleBookNoDiscountTest() {
		ArrayList<Book> singleBook = new ArrayList<Book>();
		singleBook.add(mobyDick);
		assertEquals("£15.20",Main.calculatePrice(singleBook, discountsToApply),"Single Book with No Discount Should apply 0% discount");
	}
	
	@Test
	@DisplayName("Two Books with No Discount Should apply 0% discount")
	public void twoBooksNoDiscountTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(mobyDick);
		listOfBooks.add(threeMen);
		
		assertEquals("£28.07",Main.calculatePrice(listOfBooks, discountsToApply),"Two Books with No Discount Should apply 0% discount");
	}
	
	@Test
	@DisplayName("Year Discount Should apply 10% to Book after 2000")
	public void oneBooksYearDiscountTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(maxSim);
		
		
		assertEquals("£11.82",Main.calculatePrice(listOfBooks, discountsToApply),"Year Discount Should apply 10% to Book after 2000");
	}
	
	@Test
	@DisplayName(" Price Discount applies 5% discount over 30£")
	public void threeBooksPriceDiscountTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		
		assertEquals("£43.32",Main.calculatePrice(listOfBooks, discountsToApply)," Price Discount applies 5% discount over 30£");
	}
	@Test
	@DisplayName("Year Discount Should apply 10% to Book after 2000, Price Discount applies 5% discount over 30£")
	public void threeBooksPriceAndYearDiscountTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(mobyDick);
		listOfBooks.add(maxSim);
		listOfBooks.add(mobyDick);
		
		assertEquals("£40.11",Main.calculatePrice(listOfBooks, discountsToApply),"Year Discount Should apply 10% to Book after 2000, Price Discount applies 5% discount over 30£");
	}
	
	@Test
	@DisplayName("Year Discount Should not apply 10% to Book published in 2000")
	public void year2000DiscountTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(year2000);
		
		assertEquals("£12.87",Main.calculatePrice(listOfBooks, discountsToApply),"Year Discount Should not apply 10% to Book published in 2000");
	}
	
	@Test
	@DisplayName("Price Discount Should not apply 5% to £29.99")
	public void price29DiscountTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(price29);
		
		assertEquals("£29.99",Main.calculatePrice(listOfBooks, discountsToApply),"Price Discount Should not apply 5% to £29.99");
	}
	
	@Test
	@DisplayName("No Books in shopping list should produce total of £0")
	public void noBooksTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		
		assertEquals("£0.00",Main.calculatePrice(listOfBooks, discountsToApply),"No Books in shopping list should produce total of £0");
	}
	
	@Test
	@DisplayName("Totals Over £100 Should Display Correctly")
	public void expensiveTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		listOfBooks.add(mobyDick);
		assertEquals("£101.08",Main.calculatePrice(listOfBooks, discountsToApply),"Totals Over £100 Should Display Correctly");
	}
	@Test
	@DisplayName("Totals Over £1000 Should Display Correctly")
	public void veryExpensiveTest() {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(expensiveBook);

		assertEquals("£1045.99",Main.calculatePrice(listOfBooks, discountsToApply),"Totals Over £1000 Should Display Correctly");
	}
	

}
