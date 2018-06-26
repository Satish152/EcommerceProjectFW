package lib;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighLightElement {
	
	public void highlight(WebDriver driver,WebElement ele){
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:solid 2px red; background:yellow;')",ele);
	}
}
