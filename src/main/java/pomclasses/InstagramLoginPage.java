package pomclasses;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponant.AbstractComponant;

public class InstagramLoginPage extends AbstractComponant {

	WebDriver driver;
	
	public InstagramLoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Sign up']")
	private WebElement signUp;
	
	public void clickOnSignUp()
	{
		Set<String> oldWindows = driver.getWindowHandles();  // capture BEFORE click
		clickOnAndOpenInAnotherTabUsingActionClass(signUp);
		switchToNewWindow(oldWindows);		
	}
	
}
