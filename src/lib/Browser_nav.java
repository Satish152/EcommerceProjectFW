package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Browser_nav {

    
public static WebDriver driver;


      @BeforeClass
	@Parameters({"browser"})
	public void browsernav(String browser) throws Exception{
		ConfigurationFiles con=new ConfigurationFiles();
		switch(browser){
		case "chrome":
			System.setProperty("webdriver.chrome.driver",con.getChromePath());
			driver=new ChromeDriver();	
			driver.get(con.getApplicationURL());
			driver.manage().window().maximize(); 
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver",con.getFirefoxPath());
			driver=new FirefoxDriver();
			driver.get(con.getApplicationURL());
			driver.manage().window().maximize();
			break;
			
		case "ie":
			System.setProperty("webdriver.ie.driver",con.getIEPath());
			driver=new InternetExplorerDriver();
		
			driver.get(con.getApplicationURL());
			driver.manage().window().maximize();
			break;
			
			default:
				System.setProperty("webdriver.chrome.driver",con.getChromePath());
				driver=new ChromeDriver();
				driver.get(con.getApplicationURL());
				driver.manage().window().maximize();
				break;
		}
	}
      
      @AfterMethod
      public void teardown(ITestResult result) throws Exception{
    	  if(ITestResult.FAILURE==result.getStatus()){
    		  System.out.println("script failed");
    		 ScreenCapture screenprint=new ScreenCapture(driver);
    		 screenprint.ScreenPrint(result);
    	  }
      }
      
      @AfterClass
      public void close(){
    	  System.out.println("script is closed");
    	 // driver.close();
      }
}
