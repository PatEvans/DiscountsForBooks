import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {
	
	
	public static void main(String[] args) {
		//get books from command line
		ArrayList<Book> shoppingList = new ArrayList<Book>();
		for(int i = 0;i<args.length;i++) {
			String[] bookArgs = args[i].split(",");
			shoppingList.add(new Book(bookArgs[0],bookArgs[1],bookArgs[2]));
			
		}
		
		//load in discounts from XML
		ArrayList<Discount> discountsToApply = loadDiscounts();
		
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
