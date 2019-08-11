package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class BookAFlight {
	WebDriver driver;
	 
	public BookAFlight(WebDriver driver){
		this.driver=driver;
	}
	
	By firstNameField = By.name("passFirst0");
	By lastNameField = By.name("passLast0");
	By creditCardNumberField = By.name("creditnumber");
	By ticketlessTravelCheckBox = By.name("ticketLess");
	By securePurchaseButton = By.name("buyFlights");
	
	public String getBookAFlightTitle () {
		return driver.getTitle();
	}
	
	public void securePurchase(String firstName, String lastNname, String ccNum) {
	    try {
	        driver.findElement(firstNameField).sendKeys(firstName);
	        driver.findElement(lastNameField).sendKeys(lastNname);
	        driver.findElement(creditCardNumberField).sendKeys(ccNum);
	        driver.findElement(ticketlessTravelCheckBox).click();
	        driver.findElement(securePurchaseButton).click();
	        Thread.sleep(2000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}