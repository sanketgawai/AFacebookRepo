package testComponant;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseTest {

	//static WebDriver driver;
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Getter for ThreadLocal driver
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static Properties getPropertiFileData() throws IOException {
        Properties prop = new Properties();
        File file = new File(System.getProperty("user.dir")
                + "\\src\\main\\java\\resources\\GlobalData.properties");
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);
        return prop;
    }

    public static WebDriver initalizeDriver() throws IOException {

        Properties prop = getPropertiFileData();
        String browserName = prop.getProperty("browserName");

        WebDriver localDriver;

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            localDriver = new ChromeDriver();
        } 
        else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            localDriver = new EdgeDriver();
        } 
        else {
            WebDriverManager.firefoxdriver().setup();
            localDriver = new FirefoxDriver();
        }

        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        localDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        localDriver.manage().window().maximize();

        // ✅ Store driver in ThreadLocal
        driver.set(localDriver);

        return localDriver;
    }

    public static void launchApplication() throws IOException {
        Properties prop = getPropertiFileData();
        String url = prop.getProperty("baseUrl");

        // ✅ Correct ThreadLocal usage
        getDriver().get(url);
    }

    // Optional cleanup
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
	
}
