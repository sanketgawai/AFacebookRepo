package testclasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomclasses.HomePage;
import pomclasses.InstagramLoginPage;
import testComponant.BaseTest;

public class LoginFaceBook  {

	WebDriver driver;
	//2nd change from github
	@BeforeMethod
	public void openApplication() throws IOException
	{
		driver = BaseTest.initalizeDriver();
		BaseTest.launchApplication();
	}
	
	
	@Test
	public void instagramLandinOnPage() throws IOException
	{
		HomePage homePage = new HomePage(driver);
		homePage.clickOnInstagram();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.instagram.com/");		
	}
	
	@Test
	public void instagramSignUp() throws IOException
	{
		HomePage homePage = new HomePage(driver);
		InstagramLoginPage instagramLoginPage = homePage.clickOnInstagram();
		instagramLoginPage.clickOnSignUp();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.instagram.com/accounts/emailsignup/");
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}
	
	
}
