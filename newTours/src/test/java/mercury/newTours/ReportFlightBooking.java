package mercury.newTours;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import pages.BookAFlight;
import pages.FlightConfirmation;
import pages.FlightFinder;
import pages.HomePage;
import pages.SelectFlight;

public class ReportFlightBooking {

	String path = System.getProperty("user.dir");
	private static WebDriver driver;
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	private static ExtentTest bookFlightTest;
	private static String userName = "mercury";
	private static String password = "mercury";
	private static String fromPort = "Sydney";
	private static String toPort = "London";
	private static String fname = "Hitesh";
	private static String lname = "Bhugra";
	private static String ccNum = "123456";
	private static String expectedHomePageTitle = "Welcome: Mercury Tours";
	private static String expectedFlightFinderTitle = "Find a Flight: Mercury Tours:";
	private static String expectedSelectFlightTitle = "Select a Flight: Mercury Tours";
	private static String expectedBookAFlightTitle = "Book a Flight: Mercury Tours";
	private static String expectedFlightConfirmationTitle = "Flight Confirmation: Mercury Tours";
	private static String expectedFlightInfoText = fromPort + " to " + toPort;
	private static String expectedFlightConfirmationText = "Flight Confirmation";
	
	@Parameters("browserName")
	@BeforeClass
	public void configureBrowser (String browserName) {
		htmlReporter = new ExtentHtmlReporter("bookFlightReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + ".\\assets\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else {
			System.setProperty("webdriver.gecko.driver", path + ".\\assets\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}
	
	@Test
	public void bookFlightTest () {
		bookFlightTest = extent.createTest("Book Flight Test", "Sample description");
		
		bookFlightTest.log(Status.INFO, "Starting Test Case");
    	// Open Browser
		driver.get("http://newtours.demoaut.com/");
		
		// Verify Home Page and Enter Credentials
		HomePage homePage = new HomePage(driver);
		String actualHomePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
		bookFlightTest.pass("Home Page opened");
		homePage.loginToMercuryTours(userName, password);
		bookFlightTest.pass("Login done");
		
		// Verify Flight Finder Page and Enter Flight Query
		FlightFinder flightFinder = new FlightFinder(driver);
		String actualFlightFinderTitle = flightFinder.getFlightFinderTitle();
		Assert.assertEquals(actualFlightFinderTitle, expectedFlightFinderTitle);
		bookFlightTest.pass("Flight Finder Page opened");
		flightFinder.findFlights(fromPort, toPort);
		bookFlightTest.pass("Flight Search done");
		
		// Verify Select Flight Page and Select Flight
		SelectFlight selectFlight = new SelectFlight(driver);
		String actualSelectFlightTitle = selectFlight.getSelectFlightTitle();
		Assert.assertEquals(actualSelectFlightTitle, expectedSelectFlightTitle);
		bookFlightTest.pass("Select Flight Page opened");
		String actualFlighInfoText = selectFlight.getFlightInfoText();
		Assert.assertEquals(actualFlighInfoText, expectedFlightInfoText);
		bookFlightTest.pass("Page contains correct flight info");
		selectFlight.selectFlight();
		bookFlightTest.pass("Flight select opened");
		
		// Verify Book Flight Page and Enter Payment Details
		BookAFlight bookFlight = new BookAFlight(driver);
		String actualBookAFlightTitle = bookFlight.getBookAFlightTitle();
		Assert.assertEquals(actualBookAFlightTitle, expectedBookAFlightTitle);
		bookFlightTest.pass("Book Flight page opened");
		bookFlight.securePurchase(fname, lname, ccNum);
		bookFlightTest.pass("Secure Purchase done");
		
		// Verify Flight Confirmation Page and Logout
		FlightConfirmation flightConfirmation = new FlightConfirmation(driver);
		String actualFlightConfirmationTitle = flightConfirmation.getFlightConfirmationTitle();
		Assert.assertEquals(actualFlightConfirmationTitle, expectedFlightConfirmationTitle);
		bookFlightTest.pass("Flight Confirmation Page opened");
		String actualFlightConfirmationText = flightConfirmation.getFlightConfirmationText();
		Assert.assertTrue(actualFlightConfirmationText.contains(expectedFlightConfirmationText));
		bookFlightTest.pass("Flight booking");
		flightConfirmation.logout();
		bookFlightTest.pass("Logout done");
	}
	
	@AfterClass
	public void closeBrowser () {
		driver.close();
    	driver.quit();
    	bookFlightTest.info("Test completed");
		extent.flush();
	}
}
