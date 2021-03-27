package testCaseWithKeywordFramework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import lib.Browser_nav;
import lib.FunctionLibrary;
import lib.LoadExcel;

public class TestCase2_Login extends Browser_nav{
WebDriver driver;

	public TestCase2_Login(Object driver) {
		this.driver=(WebDriver)driver;
	}
	
	
	public void login() {
        driver.get("https://google.com");
		
	}
}
