package lib;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class DynamicXML {

	@Test
	public static void testRun()
	{
		LoadExcel excel=new LoadExcel();
		excel.loadExcelSheet();
		
		
		XmlSuite suite = new XmlSuite();
		suite.setName("Testing");
		suite.setVerbose(1);
		
		
		XmlTest test = new XmlTest(suite);
		test.setName("Tests");
		test.addParameter("browser", "chrome");
		test.setPreserveOrder(true);
		
	  XmlClass testclass=new XmlClass("testCaseWithKeywordFramework."+excel.book.getSheetAt(1).getRow(1).getCell(0).getStringCellValue());
      List<XmlClass> classes = new ArrayList<XmlClass>();	
	  classes.add(testclass);
	  test.setXmlClasses(classes);
				
	  TestNG testng=new TestNG();
	  List<XmlSuite> suites=new ArrayList<XmlSuite>();
	  suites.add(suite);
	  testng.setXmlSuites(suites);
      testng.run();
		
		
	}
	
}
