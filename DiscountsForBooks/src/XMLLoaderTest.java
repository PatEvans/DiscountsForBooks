import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XMLLoaderTest {

	@Test
	@DisplayName("Discount Loaded from XML Should Give Known Percent Value")
	public void loadPercentFromXMLTest() {
		ArrayList<Discount> discountsToApply = Main.loadDiscounts("src/XMLTest.xml");
		Discount discount = discountsToApply.get(0);
		double percent=discount.getPercent();
		assertEquals(0.9,percent,"Discount Loaded from XML Should Give Known Value");
	}
	@Test
	@DisplayName("Discount Loaded from XML Should Give Known Type Value")
	public void loadTypeFromXMLTest() {
		ArrayList<Discount> discountsToApply = Main.loadDiscounts("src/XMLTest.xml");
		Discount discount = discountsToApply.get(0);
		boolean type= discount instanceof YearDiscount;
		assertEquals(true,type,"Discount Loaded from XML Should Give Known Value");
	}
	@Test
	@DisplayName("Year Discount Loaded from XML Should Give Known Year Value")
	public void loadDiscountFromXMLTest() {
		ArrayList<Discount> discountsToApply = Main.loadDiscounts("src/XMLTest.xml");
		Discount discount = discountsToApply.get(0);
		double percent=((YearDiscount) discount).getYear();
		assertEquals(2000,percent,"Discount Loaded from XML Should Give Known Value");
	}
}
