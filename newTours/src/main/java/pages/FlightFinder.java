package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class FlightFinder {
	
	WebDriver driver;
	 
	public FlightFinder(WebDriver driver){
		this.driver = driver;
	}
	
	By oneWayRadio = By.cssSelector("input[value='oneway']");
	By fromDropdown = By.name("fromPort");
	By toDropdown = By.name("toPort");
	By firstClassRadio = By.cssSelector("input[value='First']");
	By continueButton = By.name("findFlights");
	
	public String getFlightFinderTitle () {
		return driver.getTitle();
	}
	
	public void findFlights(String fromPort, String toPort) {
	    try {
	        driver.findElement(oneWayRadio).click();
	        driver.findElement(fromDropdown).sendKeys(fromPort);
	        driver.findElement(toDropdown).sendKeys(toPort);
	        driver.findElement(firstClassRadio).click();
	        Thread.sleep(2000);
	        driver.findElement(continueButton).click();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}