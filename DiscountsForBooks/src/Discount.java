
public class Discount {
	
	protected double percentDiscount;
	Discount(double percentDiscount){
		this.percentDiscount = percentDiscount;
	}
	
	public double getPercent() {
		return percentDiscount;
	}
	
	public double discountToApply(Book book){
			return 1;
	}
	public double discountToApply(double shoppingPrice){
		return 1;
	}

	
}
