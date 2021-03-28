
public class Book {
	private String title;
	private int price;
	private int year;
	Book(String title, String year,String price){
		this.title=title;
		price=price.substring(1);
		//price in pennies
		this.price = (int)(Double.parseDouble(price)*100);	
		this.year = Integer.parseInt(year);
	}
	
	//gettter methods
	public String getTitle() {
		return title;
	}
	public int getPrice() {
		return price;
	}
	public int getYear() {
		return year;
	}
	
	
}
