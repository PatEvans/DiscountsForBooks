import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {
	private static Book mobyDick=new Book("Moby Dick","1851", "£15.20");
	private static Book maxSim=new Book("The Terrible Privacy of Maxwell Sim", "2010", "£13.14");
	private static Book stilLife=new Book("Still Life With Woodpecker", "1980", "£11.05");
	private static Book threeMen=new Book("Three Men in a Boat", "1889" ,"£12.87");
	private static Book timeMachine=new Book("The Time Machine" ,"1895", "£10.43");
	private static Book theCaves=new Book("The Caves of Steel", "1954", "£8.12");
	private static Book theThoughts=new Book("Idle Thoughts of an Idle Fellow", "1886", "£7.32");
	private static Book christmasCarol=new Book("A Christmas Carol", "1843", "£4.23");
	private static Book twoCities=new Book("A Tale of Two Cities", "1859", "£6.32");
	private static Book greatExpectations=new Book("Great Expectations", "1861", "£13.21");
	
	public static void main(String[] args) {
		
		//load in discounts from XML
		ArrayList<Discount> discountsToApply = loadDiscounts();
		ArrayList<Book> shoppingList = new ArrayList<Book>();
		shoppingList.add(mobyDick);
		shoppingList.add(threeMen);
		String totalPrice=calculatePrice(shoppingList,discountsToApply);
		System.out.println(totalPrice);
	}
	
	public static ArrayList<Discount> loadDiscounts(){
		ArrayList<Discount> discountsToApply = new ArrayList<Discount>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File("src/Discounts.xml"));
		} catch (ParserConfigurationException |SAXException | IOException e) {

			e.printStackTrace();
		}
		
		NodeList discountList = document.getElementsByTagName("Discount");
		for (int i = 0; i < discountList.getLength(); i++) {
			Element discountElem = (Element) discountList.item(i);
			String type =  discountElem.getAttribute("type");
			double percent = Double.parseDouble(discountElem.getAttribute("percent"));
			int attribute =  Integer.parseInt(discountElem.getAttribute("attribute"));
			Discount discount=null;
			if(type.equals("Year")) {
				discount=new YearDiscount(percent,attribute);
			}else if(type.equals("Price")) {
				discount=new PriceDiscount(percent,attribute);
			}
			discountsToApply.add(discount);
		}
		
		
		return discountsToApply;
	}
	
	static String calculatePrice(ArrayList<Book> shoppingList,ArrayList<Discount> discountsToApply) {
		
		//apply individual book discounts
		double totalPrice = 0;
		for(int i = 0; i < shoppingList.size(); i++) {
			Book book=shoppingList.get(i);
			double priceOfBook = book.getPrice();
			
			//apply all discounts that are applied to individual books here
			double individualDiscount=1;
			for(Discount discount: discountsToApply) {
				
					individualDiscount*=discount.discountToApply(book);
				
			}
			
			priceOfBook = priceOfBook*individualDiscount;
			totalPrice+=priceOfBook;
		}
		
		//apply all total price discounts here
		double totalDiscount=1;
		for(Discount discount: discountsToApply) {
			
				totalDiscount*=discount.discountToApply(totalPrice);
			
		}
		
		totalPrice = totalPrice*totalDiscount;
		
		//ensure no floating point rounding error
		//always round final down to the penny
		int integerPrice = (int) totalPrice;
		
		Float formattedPrice=(float)integerPrice/100;
		String finalPrice = String.format("£%.2f" ,formattedPrice);
		return finalPrice;
	}
}
