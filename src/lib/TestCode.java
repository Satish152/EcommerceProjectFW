package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class TestCode {

	public static void main(String args[]){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DELL\\Downloads\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		WebElement CartItemsCount=driver.findElement(By.xpath("//div[@class='shopping_cart']/a/span[1]"));
		System.out.println(CartItemsCount.getAttribute("textContent"));
		
	}
}
