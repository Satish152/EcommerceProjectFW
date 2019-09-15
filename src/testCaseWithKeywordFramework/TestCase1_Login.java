package testCaseWithKeywordFramework;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import lib.Browser_nav;
import lib.FunctionLibrary;
import lib.LoadExcel;

public class TestCase1_Login extends Browser_nav{
	

	@Test
	public void Login() throws  SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException{
		
		LoadExcel TestData=new LoadExcel();
		TestData.loadExcelSheet();
		
		FunctionLibrary lib=new FunctionLibrary(driver);
	   
		for(int Tests=1;Tests<TestData.sheet.getLastRowNum()+1;Tests++)  //Getting TestName
		{
             String ClassName=this.getClass().getSimpleName();
             String TestName=TestData.sheet.getRow(Tests).getCell(0).getStringCellValue().trim();
		if(ClassName.equals(TestName)) //Comparing TestNames from sheet
          {
              String MethodName=TestData.sheet.getRow(Tests).getCell(2).getStringCellValue().trim();  //Method Name from Excel
			      
			try{	
					int parameterCount=(int) TestData.sheet.getRow(Tests).getCell(3).getNumericCellValue();   //parameter count from excel
					
					for(Method m: FunctionLibrary.class.getMethods())    //reading Method names from Functional library
					{  
						if(m.getName().equals(MethodName))  //Compapring Method Name with Excel method name
						
							if(m.getParameterCount()==parameterCount)   //Comparing paraameter Count from both FL and Excel
							{
								Class<?> param[]=new Class[parameterCount]; //Creating an array with class
								
								for(int i=0;i<m.getParameterCount();i++) 
								{
									param[i]=m.getParameterTypes()[i];   //assigning values in to array with parameter type
								}
								Method method=FunctionLibrary.class.getMethod(MethodName,param); 
								method.invoke(lib,TestData.sheet.getRow(Tests).getCell(5).getStringCellValue(),TestData.sheet.getRow(Tests).getCell(6).getStringCellValue(),TestData.sheet.getRow(Tests).getCell(4).getStringCellValue());	
							}
					}
		}catch(InvocationTargetException | NoSuchMethodException e){                               
				e.printStackTrace();
				assertTrue(false);
			}
		  }
		}
	  }	
    
  }

