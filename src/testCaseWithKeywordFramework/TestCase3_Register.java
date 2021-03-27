package testCaseWithKeywordFramework;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import lib.Browser_nav;
import lib.FunctionLibrary;
import lib.LoadExcel;


public class TestCase3_Register{
	WebDriver driver;
	public TestCase3_Register(Object driver) {
		// TODO Auto-generated constructor stub
		this.driver=(WebDriver)driver;
	}

	public void register(String name){
			FunctionLibrary obj=new FunctionLibrary(driver);
			obj.test(name);
	}
	}