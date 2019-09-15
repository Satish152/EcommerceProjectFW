package testCaseWithKeywordFramework;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import lib.Browser_nav;
import lib.FunctionLibrary;
import lib.LoadExcel;

public class TestCase3_Register extends Browser_nav{
	
	@Test
	public void register(){
			FunctionLibrary obj=new FunctionLibrary(driver);
  		try {
			obj.TestCaseExecutor(this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}
	}