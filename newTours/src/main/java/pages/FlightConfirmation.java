package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class FlightConfirmation {
	WebDriver driver;
	 
	public FlightConfirmation(WebDriver driver){
		this.driver=driver;
	}
	
	By flightConfirmation = By.xpath("//td[@class='frame_header_info']/table/tbody/tr/td[1]/b/font/font/b/font[1]");
	By logoutButton = By.linkText("SIGN-OFF");
	
	public String getFlightConfirmationTitle () {
		return driver.getTitle();
	}
	
	public String getFlightConfirmationText () {
		return driver.findElement(flightConfirmation).getText();
	}
	
	public void logout () {
		try {
			driver.findElement(logoutButton).click();
	        Thread.sleep(2000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}