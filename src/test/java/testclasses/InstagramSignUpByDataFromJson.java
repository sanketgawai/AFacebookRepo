package testclasses;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractComponant.AbstractComponant;
import pomclasses.HomePage;
import pomclasses.InstagramLoginPage;
import pomclasses.InstagramSignUpPage;
import rhl.data.DataReader;
import testComponant.BaseTest;

public class InstagramSignUpByDataFromJson {

	WebDriver driver; 
	
	@BeforeMethod
	public void openApplication() throws IOException
	{
		driver = BaseTest.initalizeDriver();
		BaseTest.launchApplication();
	}
	
	@Test(dataProvider="getData")
	//public void instagramSingUp(String email,String pass,String fullName,String userName)
	public void instagramSingUp(HashMap<String,String> input)
	{
		HomePage homePage = new HomePage(driver);
		InstagramLoginPage instagramLoginPage = homePage.clickOnInstagram();
		instagramLoginPage.clickOnSignUp();
		InstagramSignUpPage instagramSignUpPage = new InstagramSignUpPage(driver);
		//String errormsg=instagramSignUpPage.signUp("onepiece@gmail.com", "threesord", "roronowa zoro", "king of hell");
		String errormsg=instagramSignUpPage.signUp(input.get("email"), input.get("password"), input.get("name"), input.get("title"));
		
		//Assert.assertEquals(errormsg, "This username isn't available. Please try another.");

		Assert.assertTrue(
		errormsg.equals("This username isn't available. Please try another.")|| errormsg.equals("Usernames can only use letters, numbers, underscores and periods."),"Unexpected error message: " + errormsg);
	}
	
	@DataProvider
	public Object getData()
	{
		//return new Object[][] {{"onepiece@gmail.com", "threesord", "roronowa zoro", "king of hell"},{"naruto@gamil.com", "rasingan", "hashirama", "god of shinobi"},{"baku no hero", "one for all", "isuku", "gare shift"}};
//		HashMap<String,String> data = new HashMap<String,String>();
//		data.put("email", "onepiece@gmail.com");
//		data.put("password", "threesord");
//	    data.put("name", "roronowa zoro");
//	    data.put("title", "king of hell");
//	    
//	    HashMap<String,String> data2 = new HashMap<String,String>();
//	    data2.put("email", "naruto@gamil.com");
//	    data2.put("password", "rasingan");
//	    data2.put("name", "hashirama");
//	    data2.put("title", "god of shinobi");	    
//	    return new Object [][] {{data},{data2}};
		
		List<HashMap<String, String>> data = DataReader.getData();

	    Object[][] result = new Object[data.size()][1];

	    for (int i = 0; i < data.size(); i++) {
	        result[i][0] = data.get(i);
	    }

	    return result;
	}
	
}
