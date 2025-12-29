package testclasses;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Temp {

	public static void main(String[] args) {
		//1st change from github
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,500)");
		WebElement instagream = driver.findElement(By.xpath("//a[text()='Instagram']"));
		js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",instagream);
		

		ArrayList<String> addr = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(addr.get(1));
		wait.until(ExpectedConditions.urlContains("https://www.instagram.com/"));
		WebElement email2 = driver.findElement(By.xpath("//span[text()='Phone number, username, or email']/following-sibling::input"));
		wait.until(ExpectedConditions.visibilityOf(email2));

		email2.click();
		email2.sendKeys("onepiece@gmil.com");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.instagram.com/");
		
		WebElement signUp = driver.findElement(By.xpath("//span[text()='Sign up']"));
		

		act.keyDown(Keys.CONTROL).click(signUp).keyUp(Keys.CONTROL).build().perform();
		ArrayList<String> newAddr = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(newAddr.get(2));
		
		WebElement email = driver.findElement(By.xpath("//span[text()='Mobile Number or Email']/following-sibling::input"));
		email.sendKeys("onepiece@gmail.com");
		
		WebElement pass = driver.findElement(By.xpath("//span[text()='Password']/following-sibling::input"));
		pass.sendKeys("three swards");
		
		WebElement fullName = driver.findElement(By.xpath("//span[text()='Full Name']/following-sibling::input"));
		fullName.sendKeys("roronowa zoro");
		
		WebElement userName = driver.findElement(By.xpath("//span[text()='Username']/following-sibling::input"));
		userName.sendKeys("king_of_hell");

		WebElement signUpButton = driver.findElement(By.xpath("//button[text()='Sign up']"));
		signUpButton.click();
		
		WebElement signUpErrorMessage = driver.findElement(By.xpath("//div[contains(text(),\"This username isn't available\")]"));
		System.out.println("signUpErrorMessage : "+signUpErrorMessage.getText());
		
		Assert.assertEquals(signUpErrorMessage.getText(),"This username isn't available. Please try another.");

		
		
		
	}
	
}
