
public class PriceDiscount extends Discount{
	private int priceToApplyAt;
	PriceDiscount(double percentDiscount,int price) {
		super(percentDiscount,"Total");
		this.priceToApplyAt=price;
		// TODO Auto-generated constructor stub
	}
	public double discountToApply(double shoppingTotalPrice){
		if(shoppingTotalPrice>priceToApplyAt) {
			return percentDiscount;
		}
		else {
			return 1;
		}
	}

}
