package lib;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

public class FunctionLibrary {
	
	  public static WebDriver driver;
	
	  static WebElement Cart;
	  static WebElement button;
	  static WebElement Summary;
	  static WebElement ForgotPwd;
	  static WebElement Signin;
	  String condition;
	  static WebElement Proceed;static WebElement deliverytab;
	  static WebElement email;static WebElement pwd;static WebElement login;
	  static LoadExcel TestData=new LoadExcel();
		
	public FunctionLibrary(WebDriver driver){
		this.driver=driver;
		
	}
	
	//Test Case executable method
	public void TestCaseExecutor(Class classname) throws ClassNotFoundException{
		
		FunctionLibrary lib=new FunctionLibrary(driver);
		 TestData.loadExcelSheet();
		for(int Tests=1;Tests<TestData.sheet.getLastRowNum()+1;Tests++)  //Getting TestName
		{
		   String ClassName=classname.getSimpleName();
		   
             String TestName=TestData.sheet.getRow(Tests).getCell(0).getStringCellValue().trim();
		     if(ClassName.equals(TestName)) //Comparing TestNames from sheet
             {
              String MethodName=TestData.sheet.getRow(Tests).getCell(2).getStringCellValue().trim(); //Method Name from Excel
              try{	
					int parameterCount=(int) TestData.sheet.getRow(Tests).getCell(3).getNumericCellValue();   //parameter count from excel
					
					for(Method m: FunctionLibrary.class.getMethods())    //reading Method names from Functional library
					{  
						if(m.getName().equals(MethodName))  //Compapring Method Name with Excel method name
						{
							if(m.getParameterCount()==parameterCount)   //Comparing paraameter Count from both FL and Excel
							{
								Class<?> param[]=new Class[parameterCount]; //Creating an array with class
			
								for(int i=0;i<m.getParameterCount();i++) 
								{
									param[i]=m.getParameterTypes()[i];  //assigning values in to array with parameter type
									
								}
								
								Method method=FunctionLibrary.class.getMethod(MethodName,param);
								method.invoke(lib,FunctionLibrary.ParameterData(parameterCount, Tests));	
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
			}
		  }
		}	
	}
	
public static String[] ParameterData(int ParameterCount,int RowNum){
	String[] data=new String[ParameterCount];
	LoadExcel TestData=new LoadExcel();
	int cell=4;
	for(int i=0;i<ParameterCount;i++){
		String parameter=TestData.sheet.getRow(RowNum).getCell(cell).getStringCellValue();
		if(parameter.equals("") | parameter.equals(null)){	
			XSSFCell Cell=TestData.sheet.getRow(RowNum).getCell(cell);
					parameter=TestData.sheet.getRow(RowNum).getCell(cell).getStringCellValue();
			data[i]=parameter;	
		}else
		{
	     	data[i]=parameter;
	     
		}
	cell=cell+1;
	}
	return data;
}
	//this function validate that webelement is displaying on the webpage or not
	public static void setselectExist(WebElement ele)
	{
		if(ele.isDisplayed()){
			assertTrue(true);
		}else
		{
			assertTrue(false);
		}
	}
	
	//this function validate that Webelement is Enabled on the Webpage or not
	public static void setselectEnabled(WebElement ele)
	{
		if(ele.isEnabled()){
			assertTrue(true);
		}else
		{
			assertTrue(false);
		}
	}
	
	public static void SearchValueExist(String Value,String Condition){
		if(Value.contains(Condition)){
			assertTrue(true);
		}else
		{
			assertTrue(false);
		}
	}
	
	public static void setclickExist(WebElement ele){
		if(ele.isDisplayed()){
			assertTrue(true);
			ele.click();
		}
	}

 //this function click on the element if the element is exist
	public static void setselectclickexist(WebElement ele,String condition)
	{
	
		if(ele.getText().equals("")){
			if(ele.isEnabled() && ele.isDisplayed()){
				assertTrue(true);
				ele.click();
			}else if(!ele.isEnabled() )
			{
				assertTrue(false);
			}
	    
		}else if(ele.getText().equals(condition))
		{
			if(ele.isEnabled() && ele.isDisplayed()){
				assertTrue(true);
				ele.click();
			}else if(!ele.isEnabled() )
			{
				assertTrue(false);
			}
		}
	
		}
	
	public static void setselectvalueexist(WebElement ele,String set1){
		ele.sendKeys(set1);
	}
	
	
	public static void login(String Validate,String Username,String Password) throws Exception{
		switch(Validate){
		
		case "Signin_link":
			Signin=driver.findElement(By.xpath("//a[@class='login']"));	
			FunctionLibrary.setclickExist(Signin);
			email=driver.findElement(By.id("email"));
			pwd=driver.findElement(By.id("passwd"));
			login=driver.findElement(By.id("SubmitLogin"));
			ForgotPwd=driver.findElement(By.xpath("//a[@title='Recover your forgotten password']"));
			break;
			
		case "Page_home":
			  WebElement Home=driver.findElement(By.xpath("//a[@title='Home']/span"));	 
			  FunctionLibrary.setclickExist(Home);
			  break;
			  	
		case "Field_Validation":
			FunctionLibrary.setselectExist(email);
			FunctionLibrary.setselectExist(pwd);
			FunctionLibrary.setselectExist(ForgotPwd);
			FunctionLibrary.setselectExist(login); 
			break;
			
		case "Login":
			FunctionLibrary.setselectExist(email);
			FunctionLibrary.setselectExist(pwd);
			FunctionLibrary.setselectExist(ForgotPwd);
			FunctionLibrary.setselectExist(login);
			FunctionLibrary.setselectvalueexist(email, Username);
			FunctionLibrary.setselectvalueexist(pwd, Password);
			FunctionLibrary.setclickExist(login);
			break;
			
		case "OnlyLoginField":
			FunctionLibrary.setselectExist(email);
			FunctionLibrary.setselectExist(pwd);
			FunctionLibrary.setselectExist(ForgotPwd);
			FunctionLibrary.setselectExist(login);
			break;
		
		}
	}
	
	
	
	public static void Cart_Manage(String Validation,String Username,String Pwd) throws Exception{
		 Thread.sleep(3000);
		 WebElement CartOption=driver.findElement(By.xpath("//div[@class='shopping_cart']"));
	     WebElement CartItemsCount=driver.findElement(By.xpath("//div[@class='shopping_cart']/a/span[1]")); 
	     WebElement Success=driver.findElement(By.xpath("//div[@id='layer_cart']/div[1]/div[1]/h2[1]"));
	     Actions action=new Actions(driver);
		switch(Validation){
		case "Validate_Cart":
	       FunctionLibrary.setselectExist(CartOption);
			break;
			
		case "ItemsInCart":
			WebElement item=driver.findElement(By.xpath("//ul[@id='homefeatured']/li[2]/div[1]"));
			action.moveToElement(item).build().perform();
			Thread.sleep(3000);
			WebElement Btn_AddtoCart=driver.findElement(By.xpath("//ul[@id='homefeatured']/li[2]/div[1]/div[2]/div[2]/a[@title='Add to cart']"));
		    FunctionLibrary.setclickExist(Btn_AddtoCart);
		    String Content=Success.getAttribute("textContent").trim();
			FunctionLibrary.SearchValueExist(Content, "successfully");
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(4000);
			CartItemsCount=driver.findElement(By.xpath("//div[@class='shopping_cart']/a/span[1]")); 
			String Itemsadded=CartItemsCount.getAttribute("textContent").trim();
			Itemsadded.valueOf(true);
			FunctionLibrary.SearchValueExist(Itemsadded, "1");
			int count=Integer.parseInt(Itemsadded);
			if(count>0)
				assertTrue(true);
				else
				assertTrue(false);
			break;
			
		case "CartBar":
			WebElement Cart=driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
			action.moveToElement(Cart).build().perform();
			break;
			
		case "cart_loginfieldval":
			WebElement button=driver.findElement(By.id("button_order_cart")); 
			//Checkout button in Cart dropdwon
			Thread.sleep(2000);
			FunctionLibrary.setclickExist(button);
			WebElement Summary=driver.findElement(By.xpath("//ul[@id='order_step']/li[1]/span"));
			String condition=Summary.getAttribute("textContent").trim();
			FunctionLibrary.SearchValueExist(condition, "Summary");
			Thread.sleep(3000);
			WebElement Proceed=driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/a[1]"));
			FunctionLibrary.setclickExist(Proceed);
			Thread.sleep(2000);
			break;
			
		case "Cart_loginval":
			deliverytab=driver.findElement(By.xpath("//ul[@id='address_delivery']/li[1]/h3"));
			String text=deliverytab.getAttribute("textContent").trim();
			System.out.println(text);
			Thread.sleep(3000);
			Assertion assertion=new Assertion();
			assertion.assertEquals(text, "Your delivery address");
			break;
			
		case "ClickOnItemInCart":
			WebElement ItemImage=driver.findElement(By.xpath("//a[@class='cart-images']/img"));
			WebElement ItemName=driver.findElement(By.xpath("//div[@class='product-name']/a[1]"));
			String IName=ItemName.getText();
			FunctionLibrary.setselectExist(ItemImage);
			FunctionLibrary.setclickExist(ItemImage);
			String SelectedOrderedItem=driver.findElement(By.xpath("//div[@class='pb-center-column col-xs-12 col-sm-4']/h1")).getText().trim();
			FunctionLibrary.SearchValueExist(IName, SelectedOrderedItem);
			break;
		}
			
	}  
	
	public static void Site_MenuValidation(String Validation){
		switch(Validation){
		case "Contactus":
			String result=driver.findElement(By.xpath("//div[@id='center_column']/h1")).getAttribute("textContent");
			FunctionLibrary.SearchValueExist(result,"Contact us");
			break;
			
		}	
	}
	
	public static void UserRegister(String Validation,String FirstName,String LastName,String Password,String Index,String Email,String Address,String Mobile) throws InterruptedException{
		switch(Validation){
		case "Field_Validation":
		    WebElement Create=driver.findElement(By.xpath("//h3[@class='page-subheading']"));
		    WebElement email_field=driver.findElement(By.id("email_create"));
		    WebElement Create_button=driver.findElement(By.id("SubmitCreate"));
		    FunctionLibrary.setselectExist(Create);
		    FunctionLibrary.setselectExist(email_field);
		    FunctionLibrary.setselectExist(Create_button);
		    email_field.sendKeys(Email);
		    FunctionLibrary.setclickExist(Create_button);
		    switch(FirstName){
		   
		    case "AlreadyRegistered":
		       String WarningMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol[1]/li[1]")).getText();
		       if(WarningMessage.contains("An account using this email address has already been registered."))
		    	   assertTrue(true);
		       else
		    	   assertTrue(false);
		       break;
		       
		    case "Invalid":
		    	String Invalid=driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol[1]/li[1]")).getText();
		    	if(Invalid.contains("Invalid email address"))
		    		assertTrue(true);
		    	else
		    		assertTrue(false);
		    	break;
		    }
		    break;
		    
		case "Personal_Information":
			Thread.sleep(3000);
			WebElement Register_Create=driver.findElement(By.xpath("//h3[@class='page-subheading']"));
			FunctionLibrary.setselectExist(Register_Create);
			for(int i=1;i<3;i++){	
				if(Integer.toString(i).equals(Index))
				driver.findElement(By.id("id_gender"+Index)).click();
			}
			driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
			driver.findElement(By.id("customer_lastname")).sendKeys(LastName);
			WebElement Email_field=driver.findElement(By.id("email"));
			if(Email_field.getAttribute("value").equals(Email))
				assertTrue(true);
			else
				assertTrue(false);
	        driver.findElement(By.id("passwd")).sendKeys(Password);
	        WebElement Days=driver.findElement(By.id("days"));
	        Select day=new Select(Days);
	        day.selectByIndex(4);
	        WebElement Months=driver.findElement(By.id("months"));
	        Select month=new Select(Months);
	        month.selectByIndex(5);
	        WebElement Years=driver.findElement(By.id("years"));
	        Select year=new Select(Years);
	        year.selectByIndex(28);
	        break;
	        
		case "Address_Information":
			driver.findElement(By.id("company")).sendKeys("Test");
			driver.findElement(By.id("address1")).sendKeys(Address);
			driver.findElement(By.id("city")).sendKeys("NewYork");
			WebElement State=driver.findElement(By.id("id_state"));
			Select state=new Select(State);
			state.selectByVisibleText("New York");
			WebElement Country=driver.findElement(By.id("id_country"));
			Select country=new Select(Country);
			country.selectByIndex(1);
			driver.findElement(By.id("postcode")).sendKeys("53454");
			driver.findElement(By.id("phone_mobile")).sendKeys(Mobile);
			driver.findElement(By.id("alias")).clear();
			driver.findElement(By.id("alias")).sendKeys(Address);
			break;
			
		case "Register":
			driver.findElement(By.id("submitAccount")).click();
			break;
			
			
	}
}
}
