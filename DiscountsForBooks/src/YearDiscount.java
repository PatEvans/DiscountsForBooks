
public class YearDiscount extends Discount{
	private int year;
	YearDiscount(double percentDiscount,int year) {
		super(percentDiscount,"Individual");
		this.year=year;
		// TODO Auto-generated constructor stub
	}
	public double discountToApply(Book book){
		if(book.getYear()>year) {
			return percentDiscount;
		}
		else {
			return 1;
		}
	}

}
