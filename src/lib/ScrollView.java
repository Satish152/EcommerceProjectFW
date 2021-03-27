package lib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ScrollView {
public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","H:\\files\\chromedriver.exe");
		driver=new ChromeDriver();	
		driver.get("https://purpletalk.goupshot.com");
		driver.manage().window().maximize(); 
		driver.findElement(By.id("LoginForm_username")).sendKeys("sekhar.meesala@yesgnome.com");
		driver.findElement(By.id("LoginForm_password")).sendKeys("Narma1131");
		driver.findElement(By.id("login_submit")).click();
		Thread.sleep(5000);
		List<WebElement> list=driver.findElements(By.xpath("//div[@class='left_nav_block main_tab']/ul/li/a/span[2]"));
		WebElement ratings=driver.findElement(By.xpath("//span[text()='Ratings']"));
		WebElement app=driver.findElement((By.id("app")));
		Actions action=new Actions(driver);
		action.moveToElement(app).perform();
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",ratings);
	    ratings.click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@title='Duplicate this rating.']")).click();
	    List<WebElement> options=driver.findElements(By.xpath("//div[contains(@id,'mCSB')]/descendant::span[@class='app_title']"));
	    List<WebElement> checkbox=driver.findElements(By.xpath("//div[contains(@id,'mCSB')]/descendant::label"));
	    for(int i=0;i<options.size();i++){
	    	Thread.sleep(1000);
	    	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",options.get(i));	
	    	 System.out.println(options.get(i).getText());
	    	 if(options.get(i).getText().equals("iOS App")){
	    		 checkbox.get(i).click(); 
	    		 
	    		 break;
	    	 }
	    }
		
		
	}

}
