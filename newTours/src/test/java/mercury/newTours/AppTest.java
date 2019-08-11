package mercury.newTours;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HomePage;
import pages.FlightFinder;
import pages.SelectFlight;
import pages.BookAFlight;
import pages.FlightConfirmation;

public class AppTest
{
	public static WebDriver driver;
	String userName = "mercury";
	String password = "mercury";
	String fromPort = "Sydney";
	String toPort = "London";
	String fname = "Hitesh";
	String lname = "Bhugra";
	String ccNum = "123456";
	String expectedHomePageTitle = "Welcome: Mercury Tours";
	String expectedFlightFinderTitle = "Find a Flight: Mercury Tours:";
	String expectedSelectFlightTitle = "Select a Flight: Mercury Tours";
	String expectedBookAFlightTitle = "Book a Flight: Mercury Tours";
	String expectedFlightConfirmationTitle = "Flight Confirmation: Mercury Tours";
	String expectedFlightInfoText = fromPort + " to " + toPort;
	String expectedFlightConfirmationText = "Flight Confirmation";
	
	String path = System.getProperty("user.dir");
	
    @BeforeClass
    public void configureBrowser(){
    	ChromeOptions options = new ChromeOptions();
    	System.setProperty("webdriver.chrome.driver", path + ".\\assets\\chromedriver.exe");
    	driver = new ChromeDriver(options);
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    	driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
    }
    
    @Test
    public void bookFlightTest () {
    	try {
    		// Open Browser
    		driver.get("http://newtours.demoaut.com/");
    		
    		// Verify Home Page and Enter Credentials
    		HomePage homePage = new HomePage(driver);
    		String actualHomePageTitle = homePage.getHomePageTitle();
    		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
    		homePage.loginToMercuryTours(userName, password);
    		
    		// Verify Flight Finder Page and Enter Flight Query
    		FlightFinder flightFinder = new FlightFinder(driver);
    		String actualFlightFinderTitle = flightFinder.getFlightFinderTitle();
    		Assert.assertEquals(actualFlightFinderTitle, expectedFlightFinderTitle);
    		flightFinder.findFlights(fromPort, toPort);
    		
    		// Verify Select Flight Page and Select Flight
    		SelectFlight selectFlight = new SelectFlight(driver);
    		String actualSelectFlightTitle = selectFlight.getSelectFlightTitle();
    		Assert.assertEquals(actualSelectFlightTitle, expectedSelectFlightTitle);
    		String actualFlighInfoText = selectFlight.getFlightInfoText();
    		Assert.assertEquals(actualFlighInfoText, expectedFlightInfoText);
    		selectFlight.selectFlight();
    		
    		// Verify Book Flight Page and Enter Payment Details
    		BookAFlight bookFlight = new BookAFlight(driver);
    		String actualBookAFlightTitle = bookFlight.getBookAFlightTitle();
    		Assert.assertEquals(actualBookAFlightTitle, expectedBookAFlightTitle);
    		bookFlight.securePurchase(fname, lname, ccNum);
    		
    		// Verify Flight Confirmation Page and Logout
    		FlightConfirmation flightConfirmation = new FlightConfirmation(driver);
    		String actualFlightConfirmationTitle = flightConfirmation.getFlightConfirmationTitle();
    		Assert.assertEquals(actualFlightConfirmationTitle, expectedFlightConfirmationTitle);
    		String actualFlightConfirmationText = flightConfirmation.getFlightConfirmationText();
    		Assert.assertTrue(actualFlightConfirmationText.contains(expectedFlightConfirmationText));
    		flightConfirmation.logout();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }
    
    @AfterClass
    public void closeBrowser() {
    	driver.close();
	}
}
