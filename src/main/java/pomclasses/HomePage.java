package pomclasses;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponant.AbstractComponant;

public class HomePage extends AbstractComponant {

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	}
	
	@FindBy(xpath="//a[text()='Instagram']")
	private WebElement instagram;
	
	
	public InstagramLoginPage clickOnInstagram()
	{
		Set<String> oldWindows = driver.getWindowHandles();  // capture BEFORE click
		//clickOnAndOpenInAnotherTabUsingActionClass(instagram);
		clickOnAndOpenInAnotherTabUsingKey(instagram);
		switchToNewWindow(oldWindows);
		return new InstagramLoginPage(driver);
	}

	
}
