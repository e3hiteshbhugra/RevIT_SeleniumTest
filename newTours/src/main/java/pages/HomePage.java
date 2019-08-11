package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
 
public class HomePage {
 
	WebDriver driver;
	 
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	By username = By.name("userName");
	By password = By.name("password");
	By loginButton = By.name("login");
	 
	public String getHomePageTitle () {
		return driver.getTitle();
	}
	
	public void loginToMercuryTours(String user, String pass) {
		try {
			driver.findElement(username).sendKeys(user);
			driver.findElement(password).sendKeys(pass);
			Thread.sleep(3000);
			driver.findElement(loginButton).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}