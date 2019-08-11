package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class SelectFlight {
	WebDriver driver;
	 
	public SelectFlight(WebDriver driver){
		this.driver=driver;
	}
	
	By flightInfo = By.xpath("//td[@class='title'][1]/b/font");
	By continueButton = By.name("reserveFlights");
	
	public String getSelectFlightTitle () {
		return driver.getTitle();
	}
	
	public String getFlightInfoText () {
		return driver.findElement(flightInfo).getText();
	}
	
	public void selectFlight() {

		driver.findElement(continueButton).click();
	    try {
	        Thread.sleep(2000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}