import java.util.ArrayList;


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
		ArrayList<Discount> discountsToApply = importDiscounts();
		
	}
	
	public static ArrayList<Discount> importDiscounts(){
		ArrayList<Discount> discountsToApply = new ArrayList<Discount>();
		boolean type=false;
		Discount currentDiscount = null;
		if(type==false) {
			currentDiscount=new NewDiscount(0.9,2000);
		}
		discountsToApply.add(currentDiscount);
		type=true;
		if(type==true) {
			currentDiscount=new PriceDiscount(0.95,3000);
		}
		discountsToApply.add(currentDiscount);
		return discountsToApply;
	}
}
