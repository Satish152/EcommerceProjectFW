package testCaseWithKeywordFramework;

import org.testng.annotations.Test;

import lib.Browser_nav;
import lib.FunctionLibrary;
import lib.LoadExcel;

public class TestCase2_Login extends Browser_nav{

	
	@Test
	public void login() {
	
		FunctionLibrary obj=new FunctionLibrary(driver);
		try {
			obj.TestCaseExecutor(this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
