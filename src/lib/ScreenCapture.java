package lib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


public class ScreenCapture {
public WebDriver driver;
public ScreenCapture(WebDriver driver){
	this.driver=driver;
}
	public void ScreenPrint(ITestResult result) throws Exception{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(src, new File("C:\\Users\\DELL\\Desktop\\FailedScripts\\"+result.getInstanceName()+".png"));
	}
}
