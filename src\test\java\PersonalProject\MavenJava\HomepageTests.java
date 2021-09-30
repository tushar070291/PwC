package PersonalProject.MavenJava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomepageTests extends baseClass{

	WebDriver driver;
	BookingPage bp;
	HomePage hp;
	String city, startDate, endDate, guests, index;
	
	@BeforeClass
	public void setUpMethod() {
		
		driver = setup();
		bp = new BookingPage(driver);
		hp = new HomePage(driver);
		
	}

	@Test
	public void endToEndTest() {
		
		city = prop.getProperty("city");
		startDate = prop.getProperty("startDate");
		endDate = prop.getProperty("endDate");
		guests = prop.getProperty("guests");
		index = prop.getProperty("index");
		driver.get(prop.getProperty("url"));
		hp.enterCityName(city);
		Assert.assertTrue(hp.getCitySuggestion(city).isDisplayed(), "The suggestion for the city: " + city + " did not appear");
		hp.getCitySuggestion(city).click();
		hp.selectStartAndEndDate(startDate, endDate);
		hp.selectGuests(guests);
		driver.findElement(hp.searchButton).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bp.bookNow));
		bp.bookHotel(index);
		Assert.assertTrue(driver.getWindowHandles().size() == 2, "New window did not open up after clicking on the Book Now button");

	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
