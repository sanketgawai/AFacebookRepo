package pomclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponant.AbstractComponant;

public class InstagramSignUpPage extends AbstractComponant{

	WebDriver driver;
	
	public InstagramSignUpPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//span[text()='Mobile Number or Email']/following-sibling::input")
	private WebElement email;
	
	@FindBy(xpath="//span[text()='Password']/following-sibling::input")
	private WebElement password;
	
	@FindBy(xpath="//span[text()='Full Name']/following-sibling::input")
	private WebElement fullName;
	
	@FindBy(xpath="//span[text()='Username']/following-sibling::input")
	private WebElement userName;
	
	@FindBy(xpath="//button[text()='Sign up']")
	private WebElement signUpButton;
	
					//div[contains(text(),\"This username isn't available\")]
					//div[contains(text(),\"This username isn't available\")]
					//span[text()='Username']/parent::label/parent::div/following-sibling::div
	@FindBy(xpath="//span[text()='Username']/parent::label/parent::div/following-sibling::div")
	private WebElement signUpErrorMessage;
	
	public String signUp(String eml,String pass,String name,String userNam)
	{
		email.sendKeys(eml);
		password.sendKeys(pass);
		fullName.sendKeys(name);
		userName.sendKeys(userNam);
		signUpButton.click();
		waitForWebElementToAppearUsingWebElement(signUpErrorMessage);	
		return signUpErrorMessage.getText();
	}
}


