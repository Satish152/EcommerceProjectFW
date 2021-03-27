package lib;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
import org.testng.annotations.Test;

public class Browser_nav {

    
public static WebDriver driver;


  //    @BeforeClass
	//@Parameters({"browser"})
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
      
      public void printUsername(){
    	  System.out.println("username");
      }
      
    @Test
  	public void TestCaseExecutor() throws Exception{
  		 browsernav("chrome");
		 LoadExcel.loadExcelSheet();
		for(int Tests=1;Tests<LoadExcel.sheet.getLastRowNum()+1;Tests++)  //Getting TestName
		{
		    Class<?> classname=Class.forName("testCaseWithKeywordFramework."+LoadExcel.sheet.getRow(Tests).getCell(0).getStringCellValue().trim());
              String MethodName=LoadExcel.sheet.getRow(Tests).getCell(2).getStringCellValue().trim(); //Method Name from Excel
              try{	
            	  Object parameter=null;
            	   try{
            		   parameter=LoadExcel.sheet.getRow(Tests).getCell(3).getStringCellValue();
            	   }catch(Exception e){
            		   parameter=LoadExcel.sheet.getRow(Tests).getCell(3).getRawValue();
            	   }
            	   int parameterCount=0;
            	   if(parameter instanceof String)
            		   parameterCount=Integer.valueOf((String)parameter);
            	   else if(parameter instanceof Double || parameter instanceof Integer)
            		   parameterCount=(int)parameter;
					for(Method m: classname.getMethods())    //reading Method names from Functional library
					{  
						if(m.getName().equals(MethodName))  //Compapring Method Name with Excel method name
						{
							if(m.getParameterCount()==parameterCount)   //Comparing paraameter Count from both FL and Excel
							{
								Class<?> param[]={};
								if(parameterCount!=0){
								param=new Class[parameterCount]; //Creating an array with class
			
								for(int i=0;i<m.getParameterCount();i++) 
								{
									param[i]=m.getParameterTypes()[i];  //assigning values in to array with parameter type
									
								}
								}
								Method method=classname.getMethod(MethodName,param);
								Constructor<?> con=classname.getConstructor(new Class[]{Object.class});
								method.invoke(con.newInstance(new Object[]{driver}),ParameterData(parameterCount, Tests));
							}
					}else if(m.getName().equals(""))
					{
						System.out.println("empty method name");
						break;
					}
				}
		}catch(InvocationTargetException | NoSuchMethodException | IllegalAccessException | NullPointerException  e){                               
				
			e.printStackTrace();
				assertTrue(false);
			} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
	}
	
public static String[] ParameterData(int ParameterCount,int RowNum){
	String[] data={};
	if(ParameterCount!=0){
	data=new String[ParameterCount];
	new LoadExcel();
	int cell=4;
	for(int i=0;i<ParameterCount;i++){
		String parameter=LoadExcel.sheet.getRow(RowNum).getCell(cell).getStringCellValue();
		if(parameter.equals("") | parameter.equals(null)){	
					parameter=LoadExcel.sheet.getRow(RowNum).getCell(cell).getStringCellValue();
			data[i]=parameter;	
		}else
		{
	     	data[i]=parameter;
	     
		}
	cell=cell+1;
	}
	}
	return data;
}
}
